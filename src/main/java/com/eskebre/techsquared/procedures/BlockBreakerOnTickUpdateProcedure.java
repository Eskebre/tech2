package com.eskebre.techsquared.procedures;

import net.minecraft.world.level.block.state.properties.Property;
import net.minecraft.world.level.block.state.properties.EnumProperty;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.tags.BlockTags;
import net.minecraft.resources.Identifier;
import net.minecraft.core.Direction;
import net.minecraft.core.BlockPos;

public class BlockBreakerOnTickUpdateProcedure {
	public static boolean execute(LevelAccessor world, double x, double y, double z, BlockState blockstate) {
		if (getPropertyByName(blockstate, "triggered") instanceof BooleanProperty _getbp1 && blockstate.getValue(_getbp1)) {
			return false;
		}
		{
			BlockPos _pos = BlockPos.containing(x, y, z);
			BlockState _bs = world.getBlockState(_pos);
			if (_bs.getBlock().getStateDefinition().getProperty("triggered") instanceof BooleanProperty _booleanProp)
				world.setBlock(_pos, _bs.setValue(_booleanProp, true), 3);
		}
		if ((world.getBlockState(BlockPos.containing(x + ((getDirectionFromBlockState(blockstate)).getOpposite()).getStepX(), y + ((getDirectionFromBlockState(blockstate)).getOpposite()).getStepY(),
				z + ((getDirectionFromBlockState(blockstate)).getOpposite()).getStepZ()))).is(BlockTags.create(Identifier.parse("tech2:unbreakable")))) {
			return false;
		}
		if (getPropertyByName(blockstate, "block_drops") instanceof BooleanProperty _getbp18 && blockstate.getValue(_getbp18)) {
			{
				BlockPos _pos = BlockPos.containing(x + ((getDirectionFromBlockState(blockstate)).getOpposite()).getStepX(), y + ((getDirectionFromBlockState(blockstate)).getOpposite()).getStepY(),
						z + ((getDirectionFromBlockState(blockstate)).getOpposite()).getStepZ());
				Block.dropResources(world.getBlockState(_pos), world, BlockPos.containing(x + ((getDirectionFromBlockState(blockstate)).getOpposite()).getStepX(), y + ((getDirectionFromBlockState(blockstate)).getOpposite()).getStepY(),
						z + ((getDirectionFromBlockState(blockstate)).getOpposite()).getStepZ()), null);
				world.destroyBlock(_pos, false);
			}
		}
		return true;
	}

	private static Property<?> getPropertyByName(BlockState state, String name) {
		for (Property<?> property : state.getProperties()) {
			if (property.getName().equals(name)) {
				return property;
			}
		}
		return null;
	}

	private static Direction getDirectionFromBlockState(BlockState blockState) {
		if (getPropertyByName(blockState, "facing") instanceof EnumProperty ep && ep.getValueClass() == Direction.class)
			return (Direction) blockState.getValue(ep);
		if (getPropertyByName(blockState, "axis") instanceof EnumProperty ep && ep.getValueClass() == Direction.Axis.class)
			return Direction.fromAxisAndDirection((Direction.Axis) blockState.getValue(ep), Direction.AxisDirection.POSITIVE);
		return Direction.NORTH;
	}
}