package bl4ckscor3.mod.horizontalreinforceddeepslate;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.Direction.Axis;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition.Builder;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.EnumProperty;
import net.minecraft.world.level.gameevent.GameEvent;
import net.minecraft.world.phys.HitResult;
import net.minecraftforge.event.entity.player.PlayerInteractEvent.RightClickBlock;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;

@EventBusSubscriber(modid = HorizontalReinforcedDeepslate.MODID)
public class HorizontalReinforcedDeepslateBlock extends Block {
	public static final EnumProperty<Direction> HORIZONTAL_FACING = BlockStateProperties.HORIZONTAL_FACING;

	public HorizontalReinforcedDeepslateBlock(Properties properties) {
		super(properties);

		registerDefaultState(stateDefinition.any().setValue(HORIZONTAL_FACING, Direction.NORTH));
	}

	@SubscribeEvent
	public static void onRightClickBlock(RightClickBlock event) {
		ItemStack held = event.getItemStack();

		if (held.getItem() instanceof BlockItem blockItem && blockItem.getBlock() == Blocks.REINFORCED_DEEPSLATE) {
			Direction face = event.getFace();

			if (face.getAxis() != Axis.Y) {
				Level level = event.getWorld();
				BlockPos placeAt = event.getPos().relative(face);

				if (level.isEmptyBlock(placeAt)) {
					Player player = event.getPlayer();
					SoundType sound = SoundType.DEEPSLATE;
					BlockState stateToPlace = HorizontalReinforcedDeepslate.HORIZONTAL_REINFORCED_DEEPSLATE.get().defaultBlockState().setValue(HORIZONTAL_FACING, face);

					level.setBlockAndUpdate(placeAt, stateToPlace);
					level.gameEvent(GameEvent.BLOCK_PLACE, placeAt, GameEvent.Context.of(player, stateToPlace));
					level.playSound(player, placeAt.getX(), placeAt.getY(), placeAt.getZ(), sound.getPlaceSound(), SoundSource.BLOCKS, (sound.getVolume() + 1.0F) / 2.0F, sound.getPitch() * 0.8F);
					event.setCanceled(true);
					event.setCancellationResult(InteractionResult.sidedSuccess(level.isClientSide));

					if (!player.getAbilities().instabuild)
						held.shrink(1);
				}
			}
		}
	}

	@Override
	public ItemStack getCloneItemStack(BlockState state, HitResult target, BlockGetter level, BlockPos pos, Player player) {
		return Blocks.REINFORCED_DEEPSLATE.getCloneItemStack(state, target, level, pos, player);
	}

	@Override
	public String getDescriptionId() {
		return Blocks.REINFORCED_DEEPSLATE.getDescriptionId();
	}

	@Override
	protected void createBlockStateDefinition(Builder<Block, BlockState> builder) {
		builder.add(HORIZONTAL_FACING);
	}
}
