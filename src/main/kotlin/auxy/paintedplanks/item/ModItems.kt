package auxy.paintedplanks.item

import net.neoforged.neoforge.client.model.generators.ItemModelBuilder
import net.neoforged.neoforge.registries.DeferredRegister
import auxy.paintedplanks.PaintedPlanks

object ModItems {
    val ITEM_REGISTRY: DeferredRegister.Items = DeferredRegister.createItems(PaintedPlanks.ID)
}
