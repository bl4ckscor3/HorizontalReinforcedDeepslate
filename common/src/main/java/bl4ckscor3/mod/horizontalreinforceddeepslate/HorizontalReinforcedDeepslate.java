package bl4ckscor3.mod.horizontalreinforceddeepslate;

import bl4ckscor3.mod.horizontalreinforceddeepslate.lib.Platform;
import bl4ckscor3.mod.horizontalreinforceddeepslate.lib.RegisteredBlock;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.Identifier;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.PushReaction;

public class HorizontalReinforcedDeepslate {
	public static final String MODID = "horizontalreinforceddeepslate";
	public static final RegisteredBlock<HorizontalReinforcedDeepslateBlock> HORIZONTAL_REINFORCED_DEEPSLATE = RegisteredBlock.create("horizontal_reinforced_deepslate", HorizontalReinforcedDeepslateBlock::new, () -> BlockBehaviour.Properties.ofFullCopy(Blocks.REINFORCED_DEEPSLATE).pushReaction(PushReaction.BLOCK));
	private static Platform platform;

	public synchronized static void initialize(Platform platform) {
		if (HorizontalReinforcedDeepslate.platform != null) {
			throw new IllegalArgumentException(MODID + " platform has already been initialized");
		}

		HorizontalReinforcedDeepslate.platform = platform;
		platform.register(Registries.BLOCK, HORIZONTAL_REINFORCED_DEEPSLATE);
	}

	public static Identifier id(String path) {
		return Identifier.fromNamespaceAndPath(MODID, path);
	}

	public static Platform platform() {
		return platform;
	}
}
