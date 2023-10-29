package com.whirlpool.paintedplanks.item;

import com.whirlpool.paintedplanks.PaintedPlanks;
import com.whirlpool.paintedplanks.block.ModBlocks;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;

public class ModCreativeModeTabs {
    public static final DeferredRegister<CreativeModeTab> CREATIVE_MODE_TABS =
            DeferredRegister.create(Registries.CREATIVE_MODE_TAB, PaintedPlanks.MODID);


    public static final RegistryObject<CreativeModeTab> PAINTEDPLANKS_TAB = CREATIVE_MODE_TABS.register("paintedplanks_tab",
            () -> CreativeModeTab.builder().icon(() -> new ItemStack(ModBlocks.RED_WOOD.get()))
                    .title(Component.translatable("creativetab.paintedplanks_tab"))
                    .displayItems((pParameters, pOutput) -> {

//                    pOutput.accept(ModItems.TEST_ITEM.get());
                    pOutput.accept(ModBlocks.RED_WOOD.get());
                    pOutput.accept(ModBlocks.RED_WOOD_BUTTON.get());
                    pOutput.accept(ModBlocks.RED_WOOD_DOOR.get());
                    pOutput.accept(ModBlocks.RED_WOOD_FENCE.get());
                    pOutput.accept(ModBlocks.RED_WOOD_FENCE_GATE.get());
                    pOutput.accept(ModBlocks.RED_WOOD_PRESSURE_PLATE.get());
                    pOutput.accept(ModBlocks.RED_WOOD_SLAB.get());
                    pOutput.accept(ModBlocks.RED_WOOD_STAIRS.get());
                    pOutput.accept(ModBlocks.RED_WOOD_TRAPDOOR.get());





                    })
                    .build());
    public static void register(IEventBus eventBus){
        CREATIVE_MODE_TABS.register(eventBus);
    }
}

