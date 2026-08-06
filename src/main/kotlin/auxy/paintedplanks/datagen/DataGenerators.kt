package auxy.paintedplanks.datagen

import net.neoforged.bus.api.SubscribeEvent
import net.neoforged.fml.common.EventBusSubscriber
import net.neoforged.neoforge.data.event.GatherDataEvent

/**
 * Registers all data providers (tags, recipes, loot tables, etc.) that get run
 * by the `data` run configuration / `./gradlew runData`.
 */
@EventBusSubscriber(bus = EventBusSubscriber.Bus.MOD)
object DataGenerators {
    @SubscribeEvent
    fun gatherData(event: GatherDataEvent) {
        val generator = event.generator
        val output = generator.packOutput
        val lookupProvider = event.lookupProvider
        val existingFileHelper = event.existingFileHelper

        generator.addProvider(
            event.includeServer(),
            ModBlockTagsProvider(output, lookupProvider, existingFileHelper)
        )
    }
}
