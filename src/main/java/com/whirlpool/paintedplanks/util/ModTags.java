package com.whirlpool.paintedplanks.util;

import com.whirlpool.paintedplanks.PaintedPlanks;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.level.block.Block;

public class ModTags {
    public static class Blocks{
        private static TagKey<Block> tag(String name) {
            return BlockTags.create(new ResourceLocation(PaintedPlanks.MODID, name));
        }
    }
    public static class Items {

    }
}
