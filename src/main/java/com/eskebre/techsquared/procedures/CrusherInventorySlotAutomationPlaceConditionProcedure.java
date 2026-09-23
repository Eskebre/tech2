package com.eskebre.techsquared.procedures;

import net.minecraft.world.item.ItemStack;
import net.minecraft.tags.ItemTags;
import net.minecraft.resources.Identifier;
import net.minecraft.core.Direction;

public class CrusherInventorySlotAutomationPlaceConditionProcedure {
	public static boolean execute(Direction direction, ItemStack itemstack, double index) {
		if (direction == null)
			return false;
		if (direction == Direction.UP) {
			if (0 == index) {
				return true;
			}
		} else if (2 == index && itemstack.is(ItemTags.create(Identifier.parse("tech2:fuel")))) {
			return true;
		}
		return false;
	}
}