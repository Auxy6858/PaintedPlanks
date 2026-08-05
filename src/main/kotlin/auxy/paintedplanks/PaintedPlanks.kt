package auxy.paintedplanks

import auxy.paintedplanks.block.ModBlocks
import auxy.paintedplanks.block.ModBlocks.BLOCK_REGISTRY
import auxy.paintedplanks.item.ModItems
import auxy.paintedplanks.item.ModItems.ITEM_REGISTRY
import auxy.paintedplanks.CreativeModeTab.CREATIVE_TABS
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.serialization.Serializable
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import net.minecraft.client.Minecraft
import net.minecraft.world.level.block.Blocks
import net.minecraft.world.level.block.FireBlock
import net.neoforged.bus.api.SubscribeEvent
import net.neoforged.fml.common.EventBusSubscriber
import net.neoforged.fml.common.Mod
import net.neoforged.fml.event.lifecycle.FMLClientSetupEvent
import net.neoforged.fml.event.lifecycle.FMLCommonSetupEvent
import net.neoforged.fml.event.lifecycle.FMLDedicatedServerSetupEvent
import net.neoforged.neoforge.event.BuildCreativeModeTabContentsEvent
import org.apache.logging.log4j.Level
import org.apache.logging.log4j.LogManager
import org.apache.logging.log4j.Logger
import thedarkcolour.kotlinforforge.neoforge.forge.MOD_BUS
import thedarkcolour.kotlinforforge.neoforge.forge.runForDist
import kotlin.time.Duration.Companion.seconds

/**
 * Main mod class. Should be an `object` declaration annotated with `@Mod`.
 * The modid should be declared in this object and should match the modId entry
 * in neoforge.mods.toml.
 *
 * An example for blocks is in the `blocks` package of this mod.
 */
@Mod(PaintedPlanks.ID)
@EventBusSubscriber(bus = EventBusSubscriber.Bus.MOD)
object PaintedPlanks {
    const val ID = "paintedplanks"
    val LOGGER: Logger = LogManager.getLogger(ID)

    init {
        LOGGER.log(Level.INFO, "Hello world!")

        // Register the KDeferredRegister to the mod-specific event bus
        BLOCK_REGISTRY.register(MOD_BUS)
        ITEM_REGISTRY.register(MOD_BUS)
        CREATIVE_TABS.register(MOD_BUS)

        val obj = runForDist(
            clientTarget = {
                MOD_BUS.addListener(::onClientSetup)
                Minecraft.getInstance()
            },
            serverTarget = {
                MOD_BUS.addListener(::onServerSetup)
                "test"
            })

        println(obj)

        @Serializable
        data class MySerializedThing(
            val name: String,
            val number: Int
        )

        val testObject = MySerializedThing("KotlinForForge", 712)
        val json = Json.encodeToString(testObject)
        LOGGER.log(Level.INFO, "--- JSON: $json")

        CoroutineScope(Dispatchers.Default).launch {
            LOGGER.log(Level.INFO, "Before delay")
            delay(5.seconds)
            LOGGER.log(Level.INFO, "After 5 seconds")
        }
    }

    /**
     * This is used for initializing client specific
     * things such as renderers and keymaps
     * Fired on the mod specific event bus.
     */
    private fun onClientSetup(event: FMLClientSetupEvent) {
        LOGGER.log(Level.INFO, "Initializing client...")
    }

    /**
     * Fired on the global Forge bus.
     */
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

    private fun addCreative(event: BuildCreativeModeTabContentsEvent) {

    }
}
