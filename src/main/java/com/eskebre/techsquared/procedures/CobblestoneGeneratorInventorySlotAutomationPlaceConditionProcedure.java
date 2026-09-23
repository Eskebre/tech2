package com.eskebre.techsquared.procedures;

import net.minecraft.world.item.ItemStack;
import net.minecraft.tags.ItemTags;
import net.minecraft.resources.Identifier;

public class CobblestoneGeneratorInventorySlotAutomationPlaceConditionProcedure {
	public static boolean execute(ItemStack itemstack) {
		if (!itemstack.is(ItemTags.create(Identifier.parse("tech2:fuel")))) {
			return false;
		}
		return true;
	}
}