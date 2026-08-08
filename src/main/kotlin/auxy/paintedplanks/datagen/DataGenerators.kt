package auxy.paintedplanks.datagen

import net.minecraft.world.item.DyeColor
import net.neoforged.bus.api.SubscribeEvent
import net.neoforged.fml.common.EventBusSubscriber
import net.neoforged.neoforge.data.event.GatherDataEvent
import java.io.File
import java.nio.file.Path

/**
 * Registers all data providers (tags, recipes, loot tables, etc.) that get run
 * by the `data` run configuration / `./gradlew runData`.
 */
@EventBusSubscriber
object DataGenerators {
    @SubscribeEvent
    fun gatherData(event: GatherDataEvent) {
        val generator = event.generator
        val output = generator.packOutput
        val lookupProvider = event.lookupProvider
        val existingFileHelper = event.existingFileHelper

        println("Outputting files to ${output.outputFolder}")

        generator.addProvider(
            event.includeServer(),
            ModBlockTagsProvider(output, lookupProvider, existingFileHelper)
        )

        val mainResourcesRoot = output.outputFolder.parent.parent.resolve("main/resources")
        val generatedResourcesRoot = output.outputFolder
        val jobs = mutableListOf<DittoJsonJob>()

        for (templateFile in redWoodTemplateFiles(mainResourcesRoot.toFile())) {
            val relativePath = mainResourcesRoot.relativize(templateFile.toPath())
            val relativePathString = relativePath.toString().replace(File.separatorChar, '/')

            for (dyeColor in DyeColor.entries) {
                if (dyeColor == DyeColor.RED) continue

                val replacement = "${dyeColor.getName()}_"
                val destinationRelativePath = relativePathString.replace("red_", replacement)
                val handwrittenFile = mainResourcesRoot.resolve(destinationRelativePath)

                if (handwrittenFile.toFile().exists()) {
                    continue
                }

                jobs += DittoJsonJob(
                    sourceFile = templateFile,
                    destinationPath = generatedResourcesRoot.resolve(destinationRelativePath),
                    target = "red_",
                    replacement = replacement
                )
            }
        }

        println("Prepared ${jobs.size} colored wood JSON generation jobs")

        if (jobs.isNotEmpty()) {
            generator.addProvider(
                true,
                DittoJsonBatch("Colored wood JSON templates", jobs)
            )
        }
    }

    private fun redWoodTemplateFiles(mainResourcesRoot: File): List<File> {
        return mainResourcesRoot
            .walkTopDown()
            .filter { file ->
                file.isFile &&
                        file.extension == "json" &&
                        "red_" in file.name &&
                        ("/assets/paintedplanks/" in file.invariantSeparatorsPath ||
                                "/data/paintedplanks/" in file.invariantSeparatorsPath)
            }
            .toList()
    }
}
