package com.eskebre.techsquared.procedures;

import net.neoforged.neoforge.transfer.item.ItemUtil;
import net.neoforged.neoforge.transfer.item.ItemResource;
import net.neoforged.neoforge.transfer.ResourceHandler;
import net.neoforged.neoforge.common.extensions.ILevelExtension;
import net.neoforged.neoforge.capabilities.Capabilities;

import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.item.component.CustomData;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.Container;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.core.component.DataComponents;
import net.minecraft.core.BlockPos;

import com.eskebre.techsquared.init.Tech2ModItems;

public class DyerOnTickUpdateProcedure {
	public static boolean execute(LevelAccessor world, double x, double y, double z) {
		double color = 0;
		ItemStack color_item = ItemStack.EMPTY;
		ItemStack convert_item = ItemStack.EMPTY;
		if ((itemFromBlockInventory(world, BlockPos.containing(x, y, z), 0).copy()).getItem() == Tech2ModItems.BASKET.get()) {
			convert_item = (itemFromBlockInventory(world, BlockPos.containing(x, y, z), 0).copy()).copy();
			if (!((itemFromBlockInventory(world, BlockPos.containing(x, y, z), 2).copy()).getItem() == Blocks.AIR.asItem())) {
				return false;
			}
		} else {
			return false;
		}
		color = GetColorIDFromItemProcedure.execute(itemFromBlockInventory(world, BlockPos.containing(x, y, z), 1).copy());
		{
			final String _tagName = "color";
			final double _tagValue = color;
			CustomData.update(DataComponents.CUSTOM_DATA, convert_item, tag -> tag.putDouble(_tagName, _tagValue));
		}
		if (world instanceof ServerLevel _serverLevel) {
			BlockEntity _be = _serverLevel.getBlockEntity(BlockPos.containing(x, y, z));
			if (_be instanceof Container _container) {
				_container.getItem(0).shrink(1);
			}
		}
		if (world instanceof ServerLevel _serverLevel) {
			BlockEntity _be = _serverLevel.getBlockEntity(BlockPos.containing(x, y, z));
			if (_be instanceof Container _container) {
				_container.getItem(1).shrink(1);
			}
		}
		insertInBlockInventory(world, BlockPos.containing(x, y, z), 2, 1, convert_item, false);
		return true;
	}

	private static ItemStack itemFromBlockInventory(LevelAccessor world, BlockPos pos, int slot) {
		if (world instanceof ILevelExtension ext) {
			ResourceHandler<ItemResource> itemHandler = ext.getCapability(Capabilities.Item.BLOCK, pos, null);
			if (itemHandler != null)
				return ItemUtil.getStack(itemHandler, slot);
		}
		return ItemStack.EMPTY;
	}

	private static int insertInBlockInventory(LevelAccessor world, BlockPos pos, int slotId, int amount, ItemStack itemstack, boolean simulate) {
		if (world instanceof ILevelExtension ext) {
			ResourceHandler<ItemResource> itemHandler = ext.getCapability(Capabilities.Item.BLOCK, pos, null);
			if (itemHandler != null && slotId >= 0 && slotId < itemHandler.size()) {
				ItemStack inserted = itemstack.copy();
				inserted.setCount(amount);
				return ItemUtil.insertItemReturnRemaining(itemHandler, slotId, inserted, simulate, null).getCount();
			}
		}
		return itemstack.getCount();
	}
}