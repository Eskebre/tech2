package com.eskebre.techsquared.procedures;

import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.ItemStack;

public class FuelFromItemProcedure {
	public static double execute(ItemStack item) {
		if (Items.COAL == item.getItem()) {
			return 800;
		} else if (Blocks.COAL_BLOCK.asItem() == item.getItem()) {
			return 8000;
		} else if (Items.CHARCOAL == item.getItem()) {
			return 400;
		} else if (Blocks.DRIED_KELP_BLOCK.asItem() == item.getItem()) {
			return 100;
		} else if (Items.COAL == item.getItem()) {
			return 800;
		}
		return 0;
	}
}