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
        for (family in ModBlocks.COLORED_WOOD_FAMILIES) {
            tag(BlockTags.PLANKS).add(family.value.planks.get())
            tag(BlockTags.WOODEN_STAIRS).add(family.value.stairs.get())
            tag(BlockTags.WOODEN_SLABS).add(family.value.slab.get())
            tag(BlockTags.WOODEN_DOORS).add(family.value.door.get())
            tag(BlockTags.WOODEN_TRAPDOORS).add(family.value.trapdoor.get())


            for (blockHolder in family.value) {
                tag(BlockTags.MINEABLE_WITH_AXE)
                    .add(
                        blockHolder.get()
                    )
            }
        }
    }
}
