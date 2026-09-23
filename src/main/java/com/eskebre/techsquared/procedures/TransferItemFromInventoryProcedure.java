package com.eskebre.techsquared.procedures;

import net.neoforged.neoforge.transfer.transaction.Transaction;
import net.neoforged.neoforge.transfer.item.ItemUtil;
import net.neoforged.neoforge.transfer.item.ItemResource;
import net.neoforged.neoforge.transfer.ResourceHandler;
import net.neoforged.neoforge.common.extensions.ILevelExtension;
import net.neoforged.neoforge.capabilities.Capabilities;

import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.Container;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.core.BlockPos;

public class TransferItemFromInventoryProcedure {
	public static boolean execute(LevelAccessor world, double x, double y, double z, double x_to, double y_to, double z_to) {
		double i = 0;
		i = 0;
		for (int _i1 = 0; _i1 < getBlockInventorySlotCount(world, BlockPos.containing(x, y, z)); _i1++) {
			if (!(Blocks.AIR.asItem() == (extractFromBlockInventory(world, BlockPos.containing(x, y, z), (int) i, 1, true)).getItem())) {
				if (PlaceItemInInventoryProcedure.execute(world, x_to, y_to, z_to, itemFromBlockInventory(world, BlockPos.containing(x, y, z), (int) i).copy())) {
					if (world instanceof ServerLevel _serverLevel) {
						BlockEntity _be = _serverLevel.getBlockEntity(BlockPos.containing(x, y, z));
						if (_be instanceof Container _container) {
							_container.getItem((int) i).shrink(1);
						}
					}
					return true;
				}
				i = i + 1;
			}
		}
		return false;
	}

	private static int getBlockInventorySlotCount(LevelAccessor world, BlockPos pos) {
		if (world instanceof ILevelExtension ext) {
			ResourceHandler<ItemResource> itemHandler = ext.getCapability(Capabilities.Item.BLOCK, pos, null);
			if (itemHandler != null)
				return itemHandler.size();
		}
		return 0;
	}

	private static ItemStack extractFromBlockInventory(LevelAccessor world, BlockPos pos, int slotId, int amount, boolean simulate) {
		if (world instanceof ILevelExtension ext) {
			ResourceHandler<ItemResource> itemHandler = ext.getCapability(Capabilities.Item.BLOCK, pos, null);
			if (itemHandler != null && slotId >= 0 && slotId < itemHandler.size()) {
				ItemResource extractedResource = itemHandler.getResource(slotId);
				ItemStack extractedStack = ItemStack.EMPTY;
				if (extractedResource.isEmpty() || amount < 0)
					return extractedStack;
				try (var tx = Transaction.openRoot()) {
					int extracted = itemHandler.extract(slotId, extractedResource, amount, tx);
					extractedStack = extractedResource.toStack(extracted);
					if (!simulate)
						tx.commit();
				}
				return extractedStack;
			}
		}
		return ItemStack.EMPTY;
	}

	private static ItemStack itemFromBlockInventory(LevelAccessor world, BlockPos pos, int slot) {
		if (world instanceof ILevelExtension ext) {
			ResourceHandler<ItemResource> itemHandler = ext.getCapability(Capabilities.Item.BLOCK, pos, null);
			if (itemHandler != null)
				return ItemUtil.getStack(itemHandler, slot);
		}
		return ItemStack.EMPTY;
	}
}