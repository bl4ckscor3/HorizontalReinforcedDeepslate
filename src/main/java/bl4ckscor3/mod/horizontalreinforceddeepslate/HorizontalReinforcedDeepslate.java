package bl4ckscor3.mod.horizontalreinforceddeepslate;

import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockBehaviour.Properties;
import net.minecraft.world.level.material.PushReaction;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.javafmlmod.FMLJavaModLoadingContext;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.neoforged.neoforge.registries.ForgeRegistries;
import net.neoforged.neoforge.registries.RegistryObject;

@Mod(HorizontalReinforcedDeepslate.MODID)
public class HorizontalReinforcedDeepslate {
	public static final String MODID = "horizontalreinforceddeepslate";
	private static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, MODID);
	public static final RegistryObject<HorizontalReinforcedDeepslateBlock> HORIZONTAL_REINFORCED_DEEPSLATE = BLOCKS.register("horizontal_reinforced_deepslate", () -> new HorizontalReinforcedDeepslateBlock(Properties.copy(Blocks.REINFORCED_DEEPSLATE).pushReaction(PushReaction.BLOCK)));

	public HorizontalReinforcedDeepslate() {
		BLOCKS.register(FMLJavaModLoadingContext.get().getModEventBus());
	}
}
