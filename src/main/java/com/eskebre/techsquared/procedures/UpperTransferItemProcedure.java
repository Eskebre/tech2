package com.eskebre.techsquared.procedures;

import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.Level;
import net.minecraft.tags.BlockTags;
import net.minecraft.resources.Identifier;
import net.minecraft.core.BlockPos;

public class UpperTransferItemProcedure {
	public static void execute(LevelAccessor world, double x, double y, double z) {
		boolean update = false;
		update = false;
		if (!(world instanceof Level _level0 && _level0.hasNeighborSignal(BlockPos.containing(x, y, z)))) {
			TransferItemFromInventoryProcedure.execute(world, x, y, z, x, y + 1, z);
			if ((world.getBlockState(BlockPos.containing(x, y - 1, z))).is(BlockTags.create(Identifier.parse("tech2:upper_extractable")))) {
				TransferItemFromInventoryProcedure.execute(world, x, y - 1, z, x, y, z);
			}
		}
	}
}