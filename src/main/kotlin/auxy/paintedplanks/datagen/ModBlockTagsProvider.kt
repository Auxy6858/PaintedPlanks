package auxy.paintedplanks.datagen

import auxy.paintedplanks.PaintedPlanks
import auxy.paintedplanks.block.ModBlocks
import net.minecraft.core.HolderLookup
import net.minecraft.data.PackOutput
import net.minecraft.tags.BlockTags
import net.neoforged.neoforge.common.data.BlockTagsProvider
import net.neoforged.neoforge.common.data.ExistingFileHelper
import java.util.concurrent.CompletableFuture

/**
 * Generates block tag JSON files (e.g. `data/minecraft/tags/block/planks.json`) for all
 * colored wood blocks. Run via the `data` run configuration / `./gradlew runData` -
 * output lands in `src/generated/resources`.
 */
class ModBlockTagsProvider(
    output: PackOutput,
    lookupProvider: CompletableFuture<HolderLookup.Provider>,
    existingFileHelper: ExistingFileHelper?
) : BlockTagsProvider(output, lookupProvider, PaintedPlanks.ID, existingFileHelper) {

    override fun addTags(provider: HolderLookup.Provider) {
        for (family in ModBlocks.COLORED_WOOD_FAMILIES.values) {
            tag(BlockTags.PLANKS).add(family.planks.get())
            tag(BlockTags.WOODEN_STAIRS).add(family.stairs.get())
            tag(BlockTags.WOODEN_SLABS).add(family.slab.get())
            tag(BlockTags.WOODEN_DOORS).add(family.door.get())
            tag(BlockTags.WOODEN_TRAPDOORS).add(family.trapdoor.get())

            tag(BlockTags.MINEABLE_WITH_AXE)
                .add(
                    family.planks.get(),
                    family.stairs.get(),
                    family.slab.get(),
                    family.door.get(),
                    family.trapdoor.get()
                )
        }
    }
}
