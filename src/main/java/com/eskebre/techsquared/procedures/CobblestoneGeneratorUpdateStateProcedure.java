package com.eskebre.techsquared.procedures;

import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.core.Direction;
import net.minecraft.core.BlockPos;

public class CobblestoneGeneratorUpdateStateProcedure {
	public static void execute(LevelAccessor world, double x, double y, double z) {
		boolean lava = false;
		boolean water = false;
		lava = false;
		water = false;
		for (Direction directioniterator : Direction.values()) {
			if (Blocks.WATER == (world.getFluidState(BlockPos.containing(x + directioniterator.getStepX(), y + directioniterator.getStepY(), z + directioniterator.getStepZ())).createLegacyBlock()).getBlock()) {
				water = true;
			} else if (Blocks.LAVA == (world.getFluidState(BlockPos.containing(x + directioniterator.getStepX(), y + directioniterator.getStepY(), z + directioniterator.getStepZ())).createLegacyBlock()).getBlock()) {
				lava = true;
			}
		}
		{
			BlockPos _pos = BlockPos.containing(x, y, z);
			BlockState _bs = world.getBlockState(_pos);
			if (_bs.getBlock().getStateDefinition().getProperty("active") instanceof BooleanProperty _booleanProp)
				world.setBlock(_pos, _bs.setValue(_booleanProp, (lava && water)), 3);
		}
	}
}