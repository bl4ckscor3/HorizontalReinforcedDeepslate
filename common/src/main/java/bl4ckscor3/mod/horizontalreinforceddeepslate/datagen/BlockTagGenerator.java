package bl4ckscor3.mod.horizontalreinforceddeepslate.datagen;

import java.util.List;
import java.util.concurrent.CompletableFuture;

import bl4ckscor3.mod.horizontalreinforceddeepslate.HorizontalReinforcedDeepslate;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.tags.VanillaBlockTagsProvider;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.level.block.Block;

public class BlockTagGenerator extends VanillaBlockTagsProvider {
	public BlockTagGenerator(PackOutput output, CompletableFuture<HolderLookup.Provider> lookupProvider) {
		super(output, lookupProvider);
	}

	@Override
	protected void addTags(HolderLookup.Provider provider) {
		List<TagKey<Block>> blockTags = List.of(
			BlockTags.DRAGON_IMMUNE,
			BlockTags.FEATURES_CANNOT_REPLACE,
			BlockTags.WITHER_IMMUNE
		);

		blockTags.forEach(tagKey -> tag(tagKey).add(HorizontalReinforcedDeepslate.HORIZONTAL_REINFORCED_DEEPSLATE.get()));
	}
}
