// Thank you to https://github.com/Adubbz
// the lead dev of biomes o plenty for letting me use their code for reference

package auxy.paintedplanks.block

import auxy.paintedplanks.PaintedPlanks.ID
import auxy.paintedplanks.block.entity.ModSignBlockEntity
import net.minecraft.core.registries.BuiltInRegistries
import net.minecraft.world.level.block.entity.BlockEntityType
import net.neoforged.neoforge.registries.DeferredHolder
import net.neoforged.neoforge.registries.DeferredRegister

import com.mojang.datafixers.types.Type

import java.util.function.Supplier

// Supress the warning, the official neoforge docs use null in their examples so I trust them
// https://docs.neoforged.net/docs/1.21.1/blockentities/
// Idk what's actually going on with it
// and to be perfectly honest I don't care as long as it works and the compiler shuts up

@Suppress("TYPE_MISMATCH_BASED_ON_JAVA_ANNOTATIONS")
object ModBlockEntities {
    val BLOCK_ENTITY_TYPES: DeferredRegister<BlockEntityType<*>> =
        DeferredRegister.create(BuiltInRegistries.BLOCK_ENTITY_TYPE, ID)

    val SIGN: DeferredHolder<BlockEntityType<*>, BlockEntityType<ModSignBlockEntity>> =
        BLOCK_ENTITY_TYPES.register("sign", Supplier {
            val signBlocks = ModBlocks.COLORED_WOOD_FAMILIES.values.flatMap {
                listOf(it.sign.get(), it.wallSign.get())
            }.toTypedArray()
            BlockEntityType.Builder.of(::ModSignBlockEntity, *signBlocks).build(null)
        })
}
