package auxy.paintedplanks

import auxy.paintedplanks.block.ModBlocks
import net.minecraft.core.registries.Registries
import net.minecraft.network.chat.Component
import net.minecraft.world.item.CreativeModeTab
import net.minecraft.world.item.DyeColor
import net.neoforged.neoforge.registries.DeferredRegister
import java.util.function.Supplier


object CreativeModeTab {
    val CREATIVE_TABS: DeferredRegister<CreativeModeTab> =
        DeferredRegister.create(Registries.CREATIVE_MODE_TAB, PaintedPlanks.ID)
    val PAINTEDPLANKS_TAB: Supplier<CreativeModeTab> = CREATIVE_TABS.register("paintedplanks") { ->
        CreativeModeTab.builder()
            .title(Component.translatable("itemGroup.paintedplanks"))
            .icon { ModBlocks.COLORED_WOOD_FAMILIES[DyeColor.RED]!!.planks.toStack() }
            .displayItems { _, output ->
                for (family in ModBlocks.COLORED_WOOD_FAMILIES.values) {
                    for (blockHolder in family) {
                        output.accept(blockHolder.get())
                    }
                }
            }
            .build()

    }
}
