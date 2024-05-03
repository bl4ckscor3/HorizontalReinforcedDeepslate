package bl4ckscor3.mod.horizontalreinforceddeepslate.datagen;

import java.util.List;
import java.util.concurrent.CompletableFuture;

import bl4ckscor3.mod.horizontalreinforceddeepslate.HorizontalReinforcedDeepslate;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.level.block.Block;
import net.neoforged.neoforge.common.data.BlockTagsProvider;
import net.neoforged.neoforge.common.data.ExistingFileHelper;

public class BlockTagGenerator extends BlockTagsProvider {
	public BlockTagGenerator(PackOutput output, CompletableFuture<HolderLookup.Provider> lookupProvider, ExistingFileHelper existingFileHelper) {
		super(output, lookupProvider, HorizontalReinforcedDeepslate.MODID, existingFileHelper);
	}

	@Override
	protected void addTags(HolderLookup.Provider provider) {
		//@formatter:off
		List<TagKey<Block>> blockTags = List.of(
				BlockTags.DRAGON_IMMUNE,
				BlockTags.FEATURES_CANNOT_REPLACE,
				BlockTags.WITHER_IMMUNE);
		//@formatter:on

		blockTags.forEach(tagKey -> tag(tagKey).add(HorizontalReinforcedDeepslate.HORIZONTAL_REINFORCED_DEEPSLATE.get()));
	}
}
