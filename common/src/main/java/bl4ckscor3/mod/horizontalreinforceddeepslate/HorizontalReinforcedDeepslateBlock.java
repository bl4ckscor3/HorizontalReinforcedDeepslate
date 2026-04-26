package bl4ckscor3.mod.horizontalreinforceddeepslate;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.Direction.Axis;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition.Builder;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.EnumProperty;
import net.minecraft.world.level.gameevent.GameEvent;

public class HorizontalReinforcedDeepslateBlock extends Block {
	public static final EnumProperty<Direction> HORIZONTAL_FACING = BlockStateProperties.HORIZONTAL_FACING;

	public HorizontalReinforcedDeepslateBlock(Properties properties) {
		super(properties);

		registerDefaultState(stateDefinition.any().setValue(HORIZONTAL_FACING, Direction.NORTH));
	}

	public static InteractionResult onRightClickBlock(ItemStack held, Direction face, Level level, BlockPos placeAt, Player player) {
		if (held.getItem() instanceof BlockItem blockItem && blockItem.getBlock() == Blocks.REINFORCED_DEEPSLATE) {
			if (face.getAxis() != Axis.Y) {
				BlockState stateAtPos = level.getBlockState(placeAt);
				boolean replaceBlock = false;

				//check if the clicked block is replaceable, and if it is, allow placement at that position
				if (!stateAtPos.canBeReplaced()) {
					placeAt = placeAt.relative(face);

					//if not, check if the block space next to the clicked block is replaceable, and if it is, allow placement there
					if (level.getBlockState(placeAt).canBeReplaced())
						replaceBlock = true;
				}
				else
					replaceBlock = true;

				if (replaceBlock || level.isEmptyBlock(placeAt)) {
					SoundType sound = SoundType.DEEPSLATE;
					BlockState stateToPlace = HorizontalReinforcedDeepslate.HORIZONTAL_REINFORCED_DEEPSLATE.get().defaultBlockState().setValue(HORIZONTAL_FACING, face);

					level.setBlockAndUpdate(placeAt, stateToPlace);
					level.gameEvent(GameEvent.BLOCK_PLACE, placeAt, GameEvent.Context.of(player, stateToPlace));
					level.playSound(player, placeAt.getX(), placeAt.getY(), placeAt.getZ(), sound.getPlaceSound(), SoundSource.BLOCKS, (sound.getVolume() + 1.0F) / 2.0F, sound.getPitch() * 0.8F);

					if (!player.getAbilities().instabuild)
						held.shrink(1);

					return InteractionResult.SUCCESS;
				}
			}
		}

		return InteractionResult.PASS;
	}

	@Override
	protected ItemStack getCloneItemStack(LevelReader level, BlockPos pos, BlockState state, boolean includeData) {
		return new ItemStack(Blocks.REINFORCED_DEEPSLATE);
	}

	@Override
	protected void createBlockStateDefinition(Builder<Block, BlockState> builder) {
		builder.add(HORIZONTAL_FACING);
	}
}
