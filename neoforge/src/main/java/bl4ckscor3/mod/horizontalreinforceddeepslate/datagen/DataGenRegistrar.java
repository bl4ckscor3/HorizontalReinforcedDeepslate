package bl4ckscor3.mod.horizontalreinforceddeepslate.datagen;

import bl4ckscor3.mod.horizontalreinforceddeepslate.HorizontalReinforcedDeepslate;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.data.event.GatherDataEvent;

@EventBusSubscriber(modid = HorizontalReinforcedDeepslate.MODID)
public class DataGenRegistrar {
	private DataGenRegistrar() {}

	@SubscribeEvent
	public static void onGatherData(GatherDataEvent.Client event) {
		event.createProvider(BlockTagGenerator::new);
	}
}
