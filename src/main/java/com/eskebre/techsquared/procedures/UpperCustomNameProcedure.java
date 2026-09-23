package com.eskebre.techsquared.procedures;

import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.core.BlockPos;

public class UpperCustomNameProcedure {
	public static String execute(LevelAccessor world, double x, double y, double z) {
		String name = "";
		if (world.getBlockEntity(new BlockPos((int) x, (int) y, (int) z)) instanceof net.minecraft.world.Nameable nameable && nameable.hasCustomName()) {
			name = nameable.getCustomName().getString();
		}
		if (("").equals(name)) {
			name = "Item Hopper";
		}
		return name;
	}
}