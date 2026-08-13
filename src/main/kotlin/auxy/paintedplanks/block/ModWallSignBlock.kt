package auxy.paintedplanks.block

import net.minecraft.core.BlockPos
import net.minecraft.world.level.block.WallSignBlock
import net.minecraft.world.level.block.entity.BlockEntity
import net.minecraft.world.level.block.state.BlockBehaviour
import net.minecraft.world.level.block.state.BlockState
import net.minecraft.world.level.block.state.properties.WoodType
import auxy.paintedplanks.block.entity.ModSignBlockEntity


class ModWallSignBlock(
    properties: Properties,
    type: WoodType
) : WallSignBlock(type, properties) {

    override fun newBlockEntity(pos: BlockPos, state: BlockState): BlockEntity =
        ModSignBlockEntity(pos, state)
}
