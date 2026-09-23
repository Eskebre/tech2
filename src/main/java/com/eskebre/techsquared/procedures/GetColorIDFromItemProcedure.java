package com.eskebre.techsquared.procedures;

import net.minecraft.world.item.Items;
import net.minecraft.world.item.ItemStack;

public class GetColorIDFromItemProcedure {
	public static double execute(ItemStack itemstack) {
		if (Items.WHITE_DYE == itemstack.getItem()) {
			return 1;
		} else if (Items.LIGHT_GRAY_DYE == itemstack.getItem()) {
			return 2;
		} else if (Items.GRAY_DYE == itemstack.getItem()) {
			return 3;
		} else if (Items.BLACK_DYE == itemstack.getItem()) {
			return 4;
		} else if (Items.BROWN_DYE == itemstack.getItem()) {
			return 5;
		} else if (Items.RED_DYE == itemstack.getItem()) {
			return 6;
		} else if (Items.ORANGE_DYE == itemstack.getItem()) {
			return 7;
		} else if (Items.YELLOW_DYE == itemstack.getItem()) {
			return 8;
		} else if (Items.LIME_DYE == itemstack.getItem()) {
			return 9;
		} else if (Items.GREEN_DYE == itemstack.getItem()) {
			return 10;
		} else if (Items.CYAN_DYE == itemstack.getItem()) {
			return 11;
		} else if (Items.LIGHT_BLUE_DYE == itemstack.getItem()) {
			return 12;
		} else if (Items.BLUE_DYE == itemstack.getItem()) {
			return 13;
		} else if (Items.PURPLE_DYE == itemstack.getItem()) {
			return 14;
		} else if (Items.MAGENTA_DYE == itemstack.getItem()) {
			return 15;
		} else if (Items.PINK_DYE == itemstack.getItem()) {
			return 16;
		}
		return 0;
	}
}