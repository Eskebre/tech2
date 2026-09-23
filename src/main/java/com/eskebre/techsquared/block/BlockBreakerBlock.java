package com.eskebre.techsquared.block;

import net.minecraft.world.level.redstone.Orientation;
import net.minecraft.world.level.material.MapColor;
import net.minecraft.world.level.block.state.properties.EnumProperty;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.Rotation;
import net.minecraft.world.level.block.Mirror;
import net.minecraft.world.level.block.DirectionalBlock;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.Level;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.core.Direction;
import net.minecraft.core.BlockPos;

import javax.annotation.Nullable;

import com.eskebre.techsquared.procedures.BlockPlacerRedstoneOffProcedure;
import com.eskebre.techsquared.procedures.BlockBreakerOnTickUpdateProcedure;

public class BlockBreakerBlock extends Block {
	public static final EnumProperty<Direction> FACING = DirectionalBlock.FACING;
	public static final BooleanProperty BLOCK_DROPS = BooleanProperty.create("block_drops");
	public static final BooleanProperty TRIGGERED = BooleanProperty.create("triggered");

	public BlockBreakerBlock(BlockBehaviour.Properties properties) {
		super(properties.mapColor(MapColor.STONE).strength(3.5f, 5f).requiresCorrectToolForDrops());
		this.registerDefaultState(this.stateDefinition.any().setValue(FACING, Direction.NORTH).setValue(BLOCK_DROPS, true).setValue(TRIGGERED, false));
	}

	@Override
	protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
		super.createBlockStateDefinition(builder);
		builder.add(FACING, BLOCK_DROPS, TRIGGERED);
	}

	@Override
	public BlockState getStateForPlacement(BlockPlaceContext context) {
		BlockState state = super.getStateForPlacement(context);
		if (state == null)
			return null;
		return state.setValue(FACING, context.getNearestLookingDirection().getOpposite()).setValue(BLOCK_DROPS, true).setValue(TRIGGERED, false);
	}

	public BlockState rotate(BlockState state, Rotation rot) {
		return state.setValue(FACING, rot.rotate(state.getValue(FACING)));
	}

	public BlockState mirror(BlockState state, Mirror mirrorIn) {
		return state.rotate(mirrorIn.getRotation(state.getValue(FACING)));
	}

	@Override
	public void neighborChanged(BlockState blockstate, Level world, BlockPos pos, Block neighborBlock, @Nullable Orientation orientation, boolean moving) {
		super.neighborChanged(blockstate, world, pos, neighborBlock, orientation, moving);
		if (world.getBestNeighborSignal(pos) > 0) {
			BlockBreakerOnTickUpdateProcedure.execute(world, pos.getX(), pos.getY(), pos.getZ(), blockstate);
		} else {
			BlockPlacerRedstoneOffProcedure.execute(world, pos.getX(), pos.getY(), pos.getZ());
		}
	}
}