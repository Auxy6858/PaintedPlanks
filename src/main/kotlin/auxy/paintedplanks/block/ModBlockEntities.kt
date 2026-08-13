package auxy.paintedplanks.block

import auxy.paintedplanks.PaintedPlanks.ID
import auxy.paintedplanks.block.entity.ModSignBlockEntity
import net.minecraft.core.registries.BuiltInRegistries
import net.minecraft.world.level.block.entity.BlockEntityType
import net.neoforged.neoforge.registries.DeferredHolder
import net.neoforged.neoforge.registries.DeferredRegister

import java.util.function.Supplier

object ModBlockEntities {
    val BLOCK_ENTITY_TYPES: DeferredRegister<BlockEntityType<*>> =
        DeferredRegister.create(BuiltInRegistries.BLOCK_ENTITY_TYPE, ID)

    val SIGN: DeferredHolder<BlockEntityType<*>, BlockEntityType<ModSignBlockEntity>> =
        BLOCK_ENTITY_TYPES.register("sign", Supplier {
            val blocks = ModBlocks.COLORED_WOOD_FAMILIES.values.flatMap {
                listOf(it.sign.get(), it.wallSign.get())
            }.toTypedArray()

            BlockEntityType.Builder.of(::ModSignBlockEntity, *blocks).build(null)
        })
}
