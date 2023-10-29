package com.whirlpool.paintedplanks.datagen;

import com.whirlpool.paintedplanks.PaintedPlanks;
import com.whirlpool.paintedplanks.block.ModBlocks;
import net.minecraft.data.PackOutput;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.client.model.generators.BlockStateProvider;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.registries.RegistryObject;

public class ModBlockStateProvider extends BlockStateProvider {
    public ModBlockStateProvider(PackOutput output, ExistingFileHelper exFileHelper) {
        super(output, PaintedPlanks.MODID, exFileHelper);
    }

    @Override
    protected void registerStatesAndModels(){
        blockWithItem(ModBlocks.RED_WOOD);

    }

    private void blockWithItem(RegistryObject<Block> BlockRegistryObject) {
        simpleBlockItem(BlockRegistryObject.get(), cubeAll(BlockRegistryObject.get()));
    }
}
