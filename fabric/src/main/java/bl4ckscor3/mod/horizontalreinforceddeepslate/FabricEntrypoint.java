package bl4ckscor3.mod.horizontalreinforceddeepslate;

import java.util.Optional;
import java.util.function.Supplier;

import bl4ckscor3.mod.horizontalreinforceddeepslate.lib.Platform;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.event.player.BlockEvents;
import net.minecraft.core.Holder;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceKey;

public class FabricEntrypoint implements ModInitializer, Platform {
	@Override
	public void onInitialize() {
		HorizontalReinforcedDeepslate.initialize(this);
		BlockEvents.USE_ITEM_ON.register((itemStack, _, level, _, player, _, blockHitResult) ->
			HorizontalReinforcedDeepslateBlock.onRightClickBlock(itemStack, blockHitResult.getDirection(), level, blockHitResult.getBlockPos(), player)
		);
	}

	@Override
	@SuppressWarnings({"rawtypes", "unchecked"})
	public <R, T extends R> void register(ResourceKey<? extends Registry<R>> registryKey, Supplier<T> entry, String path) {
		Optional<Holder.Reference<R>> registry = BuiltInRegistries.REGISTRY.get((ResourceKey) registryKey);

		if (registry.isEmpty()) {
			throw new IllegalArgumentException("Couldn't find registry " + registryKey);
		}

		Registry.register((Registry<R>) registry.get().value(), HorizontalReinforcedDeepslate.id(path), entry.get());
	}
}
