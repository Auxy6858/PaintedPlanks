// Thank you to https://github.com/Adubbz
// the lead dev of biomes o plenty for letting me use their code for reference
package auxy.paintedplanks.block.entity

import auxy.paintedplanks.block.ModBlockEntities
import net.minecraft.core.BlockPos
import net.minecraft.world.level.block.entity.BlockEntityType
import net.minecraft.world.level.block.entity.SignBlockEntity
import net.minecraft.world.level.block.state.BlockState

class ModSignBlockEntity(
    type: BlockEntityType<out ModSignBlockEntity>,
    pos: BlockPos,
    state: BlockState
) : SignBlockEntity(type, pos, state) {

    constructor(pos: BlockPos, state: BlockState) : this(
        ModBlockEntities.SIGN.get(),
        pos,
        state
    )
}
