package auxy.paintedplanks

import auxy.paintedplanks.block.ModBlocks
import auxy.paintedplanks.block.ModBlocks.BLOCK_REGISTRY
import auxy.paintedplanks.item.ModItems.ITEM_REGISTRY
import auxy.paintedplanks.CreativeModeTab.CREATIVE_TABS
import auxy.paintedplanks.block.ModBlockEntities
import auxy.paintedplanks.block.entity.ModSignBlockEntity
import net.minecraft.client.model.geom.ModelLayerLocation
import net.minecraft.client.model.geom.ModelLayers
import net.minecraft.client.renderer.ItemBlockRenderTypes
import net.minecraft.client.renderer.RenderType
import net.minecraft.client.renderer.Sheets
import net.minecraft.world.level.block.Blocks
import net.minecraft.world.level.block.FireBlock
import net.neoforged.bus.api.SubscribeEvent
import net.neoforged.fml.common.EventBusSubscriber
import net.neoforged.fml.common.Mod
import net.neoforged.fml.event.lifecycle.FMLClientSetupEvent
import net.neoforged.fml.event.lifecycle.FMLCommonSetupEvent
import net.neoforged.fml.event.lifecycle.FMLDedicatedServerSetupEvent
import net.neoforged.api.distmarker.Dist
import net.neoforged.neoforge.client.event.EntityRenderersEvent
import net.minecraft.client.renderer.blockentity.SignRenderer
import net.minecraft.client.renderer.blockentity.HangingSignRenderer
import org.apache.logging.log4j.Level
import org.apache.logging.log4j.LogManager
import org.apache.logging.log4j.Logger
import thedarkcolour.kotlinforforge.neoforge.forge.MOD_BUS
import thedarkcolour.kotlinforforge.neoforge.forge.runForDist

/**
 * Main mod class. Should be an `object` declaration annotated with `@Mod`.
 * The modid should be declared in this object and should match the modId entry
 * in neoforge.mods.toml.
 *
 * An example for blocks is in the `blocks` package of this mod.
 */
@Mod(PaintedPlanks.ID)
@EventBusSubscriber
object PaintedPlanks {
    const val ID = "paintedplanks"
    val LOGGER: Logger = LogManager.getLogger(ID)

    init {
        // Register the KDeferredRegister to the mod-specific event bus
        BLOCK_REGISTRY.register(MOD_BUS)
        ITEM_REGISTRY.register(MOD_BUS)
        CREATIVE_TABS.register(MOD_BUS)
        ModBlockEntities.BLOCK_ENTITY_TYPES.register(MOD_BUS)

        runForDist(
            clientTarget = { MOD_BUS.addListener(::onClientSetup) },
            serverTarget = { MOD_BUS.addListener(::onServerSetup) }
        )
    }

    private fun onClientSetup(event: FMLClientSetupEvent) {
        LOGGER.log(Level.INFO, "Initializing client...")
        event.enqueueWork {
            ModBlocks.COLORED_WOOD_FAMILIES.values.forEach { family ->
                Sheets.addWoodType(family.woodType)
            }
        }
    }

    private fun onServerSetup(event: FMLDedicatedServerSetupEvent) {
        LOGGER.log(Level.INFO, "Server starting...")
    }

    @SubscribeEvent
    fun onCommonSetup(event: FMLCommonSetupEvent) {
        LOGGER.log(Level.INFO, "paintedplanks loaded")
        event.enqueueWork {
            val fireBlock = Blocks.FIRE as FireBlock
            for (family in ModBlocks.COLORED_WOOD_FAMILIES.values) {
                for (block in family) {
                    fireBlock.setFlammable(block.get(), 5, 20)
                }
            }
        }
    }

    @SubscribeEvent
    fun registerRenderers(event: EntityRenderersEvent.RegisterRenderers) {
        event.registerBlockEntityRenderer(ModBlockEntities.SIGN.get()) { context ->
            SignRenderer(context)
        }
    }
}
