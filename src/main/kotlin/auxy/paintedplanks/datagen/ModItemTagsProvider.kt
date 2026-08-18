package auxy.paintedplanks.datagen

import auxy.paintedplanks.PaintedPlanks
import auxy.paintedplanks.block.ModBlocks
import net.minecraft.core.HolderLookup
import net.minecraft.data.PackOutput
import net.minecraft.data.tags.ItemTagsProvider
import net.minecraft.tags.BlockTags
import net.minecraft.tags.ItemTags
import net.minecraft.world.level.block.Block
import net.neoforged.neoforge.common.data.ExistingFileHelper
import java.util.concurrent.CompletableFuture

class ModItemTagsProvider(
    output: PackOutput,
    lookupProvider: CompletableFuture<HolderLookup.Provider>,
    blockTagsProvider: CompletableFuture<TagLookup<Block>>,
    existingFileHelper: ExistingFileHelper
) : ItemTagsProvider(output, lookupProvider, blockTagsProvider, PaintedPlanks.ID, existingFileHelper) {

    override fun addTags(provider: HolderLookup.Provider) {
        for (family in ModBlocks.COLORED_WOOD_FAMILIES) {
            this.tag(ItemTags.PLANKS).add(family.value.planks.asItem())
        }

    }
}
