package auxy.paintedplanks.block

import auxy.paintedplanks.PaintedPlanks
import auxy.paintedplanks.PaintedPlanks.ID
import auxy.paintedplanks.item.ModItems
import net.minecraft.client.renderer.Sheets

import net.minecraft.world.level.block.Block
import net.minecraft.world.level.block.state.BlockBehaviour
import net.minecraft.world.level.block.state.properties.NoteBlockInstrument
import net.minecraft.world.item.BlockItem
import net.minecraft.world.item.DyeColor
import net.minecraft.world.item.Item
import net.minecraft.world.level.block.ButtonBlock
import net.minecraft.world.level.block.SoundType
import net.minecraft.world.level.block.StairBlock
import net.minecraft.world.level.block.SlabBlock
import net.minecraft.world.level.block.DoorBlock
import net.minecraft.world.level.block.FenceBlock
import net.minecraft.world.level.block.FenceGateBlock
import net.minecraft.world.level.block.PressurePlateBlock
import net.minecraft.world.level.block.TrapDoorBlock
import net.minecraft.world.level.block.RotatedPillarBlock
import net.minecraft.world.level.block.SignBlock
import net.minecraft.world.level.block.StandingSignBlock
import net.minecraft.world.level.block.WallSignBlock
import net.minecraft.world.item.*


import net.minecraft.world.level.block.state.properties.BlockSetType
import net.minecraft.world.level.block.state.properties.WoodType
import net.minecraft.world.level.material.MapColor
import net.minecraft.world.level.material.PushReaction
import net.neoforged.neoforge.registries.DeferredBlock
import net.neoforged.neoforge.registries.DeferredRegister
import org.apache.logging.log4j.Level


// THIS LINE IS REQUIRED FOR USING PROPERTY DELEGATES
import thedarkcolour.kotlinforforge.neoforge.forge.getValue

object ModBlocks {
    val BLOCK_REGISTRY: DeferredRegister.Blocks = DeferredRegister.createBlocks(ID)

    // If you get an "overload resolution ambiguity" error, include the arrow at the start of the closure.
    private fun woodProps(dyeColor: DyeColor): BlockBehaviour.Properties {
        return BlockBehaviour.Properties.of()
            .mapColor(dyeColor.mapColor)
            .instrument(NoteBlockInstrument.BASS)
            .strength(2.0F, 3.0F)
            .sound(SoundType.CHERRY_WOOD)
            .ignitedByLava()
    }

    data class ColoredWoodFamily(
        val woodType: WoodType,
        val planks: DeferredBlock<Block>,
        val stairs: DeferredBlock<StairBlock>,
        val slab: DeferredBlock<SlabBlock>,
        val door: DeferredBlock<DoorBlock>,
        val trapdoor: DeferredBlock<TrapDoorBlock>,
        val pressurePlate: DeferredBlock<PressurePlateBlock>,
        val fence: DeferredBlock<FenceBlock>,
        val fenceGate: DeferredBlock<FenceGateBlock>,
        val button: DeferredBlock<ButtonBlock>,
        val log: DeferredBlock<RotatedPillarBlock>,
        val sign: DeferredBlock<ModStandingSignBlock>,
        val wallSign: DeferredBlock<ModWallSignBlock>,
    ) : Iterable<DeferredBlock<out Block>> {
        override fun iterator(): Iterator<DeferredBlock<out Block>> =
            listOf(
                planks,
                stairs,
                slab,
                door,
                trapdoor,
                pressurePlate,
                fence,
                fenceGate,
                button,
                log,
                sign,
                wallSign,
            ).iterator()
    }

    val COLORED_WOOD_FAMILIES: Map<DyeColor, ColoredWoodFamily> = DyeColor.entries.associateWith { dyeColor ->
        val colorName = "${dyeColor.getName()}_wood"
        val properties = woodProps(dyeColor)

        PaintedPlanks.LOGGER.log(Level.DEBUG, "Registering $colorName wood")

        val woodType = WoodType.register(WoodType(ID + ":" + colorName, BlockSetType.CHERRY))

        val planksSupplier = BLOCK_REGISTRY.register("${colorName}_planks") { -> Block(properties) }

        val stairsSupplier = BLOCK_REGISTRY.register("${colorName}_stairs") { ->
            StairBlock(planksSupplier.get().defaultBlockState(), properties)
        }

        val slabSupplier = BLOCK_REGISTRY.register("${colorName}_slab") { -> SlabBlock(properties) }

        val doorSupplier = BLOCK_REGISTRY.register("${colorName}_door") { ->
            DoorBlock(BlockSetType.CHERRY, properties.noOcclusion())
        }

        val trapdoorSupplier = BLOCK_REGISTRY.register("${colorName}_trapdoor") { ->
            TrapDoorBlock(BlockSetType.CHERRY, properties.noOcclusion())
        }

        val pressurePlateSupplier = BLOCK_REGISTRY.register("${colorName}_pressure_plate") { ->
            PressurePlateBlock(BlockSetType.CHERRY, properties)
        }

        val fenceSupplier = BLOCK_REGISTRY.register("${colorName}_fence") { ->
            FenceBlock(properties)
        }

        val fenceGateSupplier = BLOCK_REGISTRY.register("${colorName}_fence_gate") { ->
            FenceGateBlock(woodType, properties)
        }

        val buttonSupplier = BLOCK_REGISTRY.register("${colorName}_button") { ->
            ButtonBlock(BlockSetType.CHERRY, 20, properties)
        }

        val logSuppplier = BLOCK_REGISTRY.register("${colorName}_log") { ->
            RotatedPillarBlock(properties)
        }

        val signSupplier = BLOCK_REGISTRY.register("${colorName}_sign") { ->
            ModStandingSignBlock(properties.noCollission(), woodType)
        }

        val wallSignSupplier = BLOCK_REGISTRY.register("${colorName}_wall_sign") { ->
            ModWallSignBlock(properties.noCollission(), woodType)
        }

        ModItems.ITEM_REGISTRY.registerSimpleBlockItem(planksSupplier)
        ModItems.ITEM_REGISTRY.registerSimpleBlockItem(stairsSupplier)
        ModItems.ITEM_REGISTRY.registerSimpleBlockItem(slabSupplier)
        ModItems.ITEM_REGISTRY.registerSimpleBlockItem(doorSupplier)
        ModItems.ITEM_REGISTRY.registerSimpleBlockItem(trapdoorSupplier)
        ModItems.ITEM_REGISTRY.registerSimpleBlockItem(pressurePlateSupplier)
        ModItems.ITEM_REGISTRY.registerSimpleBlockItem(fenceSupplier)
        ModItems.ITEM_REGISTRY.registerSimpleBlockItem(fenceGateSupplier)
        ModItems.ITEM_REGISTRY.registerSimpleBlockItem(buttonSupplier)
        ModItems.ITEM_REGISTRY.registerSimpleBlockItem(logSuppplier)
        ModItems.ITEM_REGISTRY.register("${colorName}_sign") { ->
            SignItem(
                Item.Properties().stacksTo(16),
                signSupplier.get(),
                wallSignSupplier.get()
            )
        }

        ColoredWoodFamily(
            woodType = woodType,
            planks = planksSupplier,
            stairs = stairsSupplier,
            slab = slabSupplier,
            door = doorSupplier,
            trapdoor = trapdoorSupplier,
            pressurePlate = pressurePlateSupplier,
            fence = fenceSupplier,
            fenceGate = fenceGateSupplier,
            button = buttonSupplier,
            log = logSuppplier,
            sign = signSupplier,
            wallSign = wallSignSupplier,
        )
    }

}
