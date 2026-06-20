package bl4ckscor3.mod.horizontalreinforceddeepslate.lib;

import java.util.function.Supplier;

import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceKey;

public interface Platform {
	<R, T extends R> void register(ResourceKey<? extends Registry<R>> registry, Supplier<T> entry, String path);

	default <R, T extends R> void register(ResourceKey<? extends Registry<R>> registry, RegistryObject<R, T> registryObject) {
		register(registry, registryObject.object(), registryObject.key().identifier().getPath());
	}
}
