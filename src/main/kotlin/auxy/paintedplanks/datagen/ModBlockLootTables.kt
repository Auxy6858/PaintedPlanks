package auxy.paintedplanks.datagen

import auxy.paintedplanks.block.ModBlocks
import net.minecraft.core.HolderLookup
import net.minecraft.data.loot.BlockLootSubProvider
import net.minecraft.world.flag.FeatureFlags
import net.minecraft.world.level.block.Block

class ModBlockLootTables(registries: HolderLookup.Provider) :
    BlockLootSubProvider(setOf(), FeatureFlags.REGISTRY.allFlags(), registries) {

    override fun generate() {
        for (family in ModBlocks.COLORED_WOOD_FAMILIES) {
            val wood = family.value
            this.dropSelf(wood.planks.get())
            this.dropSelf(wood.stairs.get())
            this.dropSelf(wood.trapdoor.get())
            this.dropSelf(wood.fence.get())
            this.dropSelf(wood.fenceGate.get())
            this.dropSelf(wood.pressurePlate.get())
            this.dropSelf(wood.button.get())
            this.dropSelf(wood.log.get())
            this.dropSelf(wood.sign.get())
            this.dropSelf(wood.wallSign.get())
            this.add(wood.slab.get()) { block -> createSlabItemTable(block) }
            this.add(wood.door.get()) { block -> createDoorTable(block) }
        }
    }

    override fun getKnownBlocks(): Iterable<Block> {
        val blocksList = mutableListOf<Block>()

        for (family in ModBlocks.COLORED_WOOD_FAMILIES) {
            for (blockHolder in family.value) {
                blocksList.add(blockHolder.get())
            }
        }

        return blocksList
    }
}
