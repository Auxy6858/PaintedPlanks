package com.whirlpool.paintedplanks.block;

import com.whirlpool.paintedplanks.PaintedPlanks;
import com.whirlpool.paintedplanks.item.ModItems;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.properties.BlockSetType;
import net.minecraft.world.level.block.state.properties.NoteBlockInstrument;
import net.minecraft.world.level.block.state.properties.WoodType;
import net.minecraft.world.level.material.MapColor;
import net.minecraft.world.level.material.PushReaction;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import java.util.function.Supplier;

public class ModBlocks {
    public static final DeferredRegister<Block> BLOCKS =
            DeferredRegister.create(ForgeRegistries.BLOCKS, PaintedPlanks.MODID);


    // REGISTER THE PLANKS
    public static final RegistryObject<Block> RED_WOOD = registerBlock("red_planks",
            () -> new FlammableModBlock(BlockBehaviour.Properties.of().mapColor(MapColor.COLOR_RED)
                    .instrument(NoteBlockInstrument.BASS).strength(2.0F,3.0F)
                    .sound(SoundType.CHERRY_WOOD).ignitedByLava()));
    public static final RegistryObject<Block> WHITE_WOOD = registerBlock("white_planks",
            () -> new FlammableModBlock(BlockBehaviour.Properties.of().mapColor(MapColor.TERRACOTTA_WHITE)
                    .instrument(NoteBlockInstrument.BASS).strength(2.0F,3.0F)
                    .sound(SoundType.CHERRY_WOOD).ignitedByLava()));

    // REGISTER THE SLABS
    public static final RegistryObject<Block> RED_WOOD_SLAB = registerBlock("red_wood_slab",
            () -> new FlammableModSlabBlock(BlockBehaviour.Properties.of().mapColor(MapColor.COLOR_RED)
                    .instrument(NoteBlockInstrument.BASS).strength(2.0F,3.0F)
                    .sound(SoundType.CHERRY_WOOD).ignitedByLava()));

    public static final RegistryObject<Block> WHITE_WOOD_SLAB = registerBlock("white_wood_slab",
            () -> new FlammableModSlabBlock(BlockBehaviour.Properties.of().mapColor(MapColor.TERRACOTTA_WHITE)
                    .instrument(NoteBlockInstrument.BASS).strength(2.0F,3.0F)
                    .sound(SoundType.CHERRY_WOOD).ignitedByLava()));

    // REGISTER THE STAIRS

    public static final RegistryObject<Block> RED_WOOD_STAIRS = registerBlock("red_wood_stairs",
            () -> new FlammableModStairsBlock(() -> ModBlocks.RED_WOOD.get().defaultBlockState(),
                    BlockBehaviour.Properties.copy(Blocks.OAK_PLANKS).sound(SoundType.CHERRY_WOOD).ignitedByLava()));

    // REGISTER THE DOORS

    public static final RegistryObject<Block> RED_WOOD_DOOR = registerBlock("red_wood_door",
            () -> new FlammableModDoorBlock(BlockBehaviour.Properties.of().mapColor(MapColor.COLOR_RED)
                    .instrument(NoteBlockInstrument.BASS).strength(3.0F).sound(SoundType.CHERRY_WOOD)
                    .noOcclusion().ignitedByLava().pushReaction(PushReaction.DESTROY), BlockSetType.CHERRY));


    // REGISTER THE FENCES

    public static final RegistryObject<Block> RED_WOOD_FENCE = registerBlock("red_wood_fence",
            () -> new FlammableModFenceBlock(BlockBehaviour.Properties.of().mapColor(MapColor.COLOR_RED)
                    .instrument(NoteBlockInstrument.BASS).strength(3.0F).sound(SoundType.CHERRY_WOOD)
                    .noOcclusion().ignitedByLava().pushReaction(PushReaction.DESTROY)));



    // REGISTER THE FENCE GATES

    public static final RegistryObject<Block> RED_WOOD_FENCE_GATE = registerBlock("red_wood_fence_gate",
            () -> new FlammableModFenceGateBlock(BlockBehaviour.Properties.of().mapColor(MapColor.COLOR_RED)
                    .instrument(NoteBlockInstrument.BASS).strength(3.0F).sound(SoundType.CHERRY_WOOD)
                    .noOcclusion().ignitedByLava().pushReaction(PushReaction.DESTROY), WoodType.CHERRY));

    // REGISTER THE TRAPDOORS

    public static final RegistryObject<Block> RED_WOOD_TRAPDOOR = registerBlock("red_wood_trapdoor",
            () -> new FlammableModTrapdoorBlock(BlockBehaviour.Properties.of().mapColor(MapColor.COLOR_RED)
                    .instrument(NoteBlockInstrument.BASS).strength(3.0F).sound(SoundType.CHERRY_WOOD)
                    .noOcclusion().ignitedByLava().pushReaction(PushReaction.DESTROY),BlockSetType.CHERRY));

    // REGISTER THE BUTTONS

    public static final RegistryObject<Block> RED_WOOD_BUTTON = registerBlock("red_wood_button",
            () -> new FlammableModButtonBlock(BlockBehaviour.Properties.copy(Blocks.CHERRY_BUTTON).sound(SoundType.CHERRY_WOOD),
                    BlockSetType.CHERRY, 50, true));

    // REGISTER THE PRESSURE PLATES

    public static final RegistryObject<Block> RED_WOOD_PRESSURE_PLATE = registerBlock("red_wood_pressure_plate",
            () -> new FlammableModPressurePlateBlock(PressurePlateBlock.Sensitivity.EVERYTHING,
                    BlockBehaviour.Properties.copy(Blocks.CHERRY_PLANKS).sound(SoundType.CHERRY_WOOD).ignitedByLava(),
                    BlockSetType.CHERRY));


    // REGISTER THE SIGNS

    /*
    public static final RegistryObject<Block> RED_WOOD_SIGN = registerBlock("red_wood_sign",
            () -> new FlammableModSignBlock(BlockBehaviour.Properties.of().mapColor(MapColor.COLOR_RED)
                    .instrument(NoteBlockInstrument.BASS).strength(3.0F).sound(SoundType.CHERRY_WOOD)
                    .noOcclusion().ignitedByLava(),WoodType.CHERRY));

     */




    // REGISTER THE SIGNS WHEN THEY'RE ON THE WALL FOR SOME REASON

    /*public static final RegistryObject<Block> RED_WOOD_WALL_SIGN = registerBlock("red_wood_wall_sign",
            () -> new FlammableModWallSignBlock(BlockBehaviour.Properties.of().mapColor(MapColor.COLOR_RED)
                    .instrument(NoteBlockInstrument.BASS).strength(3.0F).sound(SoundType.CHERRY_WOOD)
                    .noOcclusion().ignitedByLava(),WoodType.CHERRY));

     */

    // COMMENTED OUT BECAUSE I CAN'T GET IT TO WORK


    private static <T extends Block> RegistryObject<T> registerBlock(String name, Supplier<T> block) {
        RegistryObject<T> toReturn = BLOCKS.register(name, block);
        registerBlockItem(name, toReturn);
        return toReturn;
    }


    private static <T extends Block> RegistryObject<Item> registerBlockItem(String name, RegistryObject<T> block) {
        return ModItems.ITEMS.register(name, () -> new BlockItem(block.get(), new Item.Properties()));
    }


    public static void register(IEventBus eventBus) {
        BLOCKS.register(eventBus);
    }

}
