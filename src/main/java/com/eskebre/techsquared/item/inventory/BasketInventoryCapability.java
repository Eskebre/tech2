package com.eskebre.techsquared.item.inventory;

import net.neoforged.neoforge.transfer.item.ItemResource;
import net.neoforged.neoforge.transfer.item.ItemAccessItemHandler;
import net.neoforged.neoforge.transfer.access.ItemAccess;
import net.neoforged.neoforge.event.entity.item.ItemTossEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.bus.api.SubscribeEvent;

import net.minecraft.world.entity.player.Player;
import net.minecraft.core.component.DataComponents;

import com.eskebre.techsquared.world.inventory.BasketGUIMenu;
import com.eskebre.techsquared.init.Tech2ModItems;

@EventBusSubscriber
public class BasketInventoryCapability extends ItemAccessItemHandler {
	@SubscribeEvent
	public static void onItemDropped(ItemTossEvent event) {
		if (event.getEntity().getItem().getItem() == Tech2ModItems.BASKET.get()) {
			Player player = event.getPlayer();
			if (player.containerMenu instanceof BasketGUIMenu)
				player.closeContainer();
		}
	}

	public BasketInventoryCapability(ItemAccess access) {
		super(access, DataComponents.CONTAINER, 9);
	}

	@Override
	public boolean isValid(int index, ItemResource resource) {
		return super.isValid(index, resource) && resource.getItem() != Tech2ModItems.BASKET.get();
	}
}