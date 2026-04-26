package bl4ckscor3.mod.horizontalreinforceddeepslate;

import java.util.function.Supplier;

import com.google.common.base.Suppliers;

import net.minecraft.core.registries.Registries;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockBehaviour;

public record RegistryObject<T>(Identifier id, Supplier<T> object) {
	public static <B extends Block> RegistryObject<B> block(String id, BlockConstructor<B> blockConstructor, Supplier<BlockBehaviour.Properties> properties) {
		Identifier key = HorizontalReinforcedDeepslate.id(id);
		return new RegistryObject<>(
			key,
			Suppliers.memoize(() -> blockConstructor.construct(properties.get().setId(ResourceKey.create(Registries.BLOCK, key))))
		);
	}

	public T get() {
		return object.get();
	}

	@FunctionalInterface
	public interface BlockConstructor<B extends Block> {
		B construct(BlockBehaviour.Properties properties);
	}
}
