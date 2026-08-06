package auxy.paintedplanks

import auxy.paintedplanks.block.ModBlocks
import auxy.paintedplanks.block.ModBlocks.BLOCK_REGISTRY
import auxy.paintedplanks.item.ModItems.ITEM_REGISTRY
import auxy.paintedplanks.CreativeModeTab.CREATIVE_TABS
import net.minecraft.world.level.block.Blocks
import net.minecraft.world.level.block.FireBlock
import net.neoforged.bus.api.SubscribeEvent
import net.neoforged.fml.common.EventBusSubscriber
import net.neoforged.fml.common.Mod
import net.neoforged.fml.event.lifecycle.FMLClientSetupEvent
import net.neoforged.fml.event.lifecycle.FMLCommonSetupEvent
import net.neoforged.fml.event.lifecycle.FMLDedicatedServerSetupEvent
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

        runForDist(
            clientTarget = { MOD_BUS.addListener(::onClientSetup) },
            serverTarget = { MOD_BUS.addListener(::onServerSetup) }
        )
    }

    private fun onClientSetup(event: FMLClientSetupEvent) {
        LOGGER.log(Level.INFO, "Initializing client...")
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
}
