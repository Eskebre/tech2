package com.eskebre.techsquared.procedures;

import net.neoforged.neoforge.transfer.item.ItemUtil;
import net.neoforged.neoforge.transfer.item.ItemResource;
import net.neoforged.neoforge.transfer.ResourceHandler;
import net.neoforged.neoforge.common.extensions.ILevelExtension;
import net.neoforged.neoforge.capabilities.Capabilities;

import net.minecraft.world.level.block.state.properties.Property;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.Level;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.Container;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.core.BlockPos;

public class FreezerOnTickUpdateProcedure {
	public static boolean execute(LevelAccessor world, double x, double y, double z, BlockState blockstate) {
		double fuel_usage = 0;
		fuel_usage = 20;
		if (getBlockNBTNumber(world, BlockPos.containing(x, y, z), "fuel") <= fuel_usage) {
			if (!world.isClientSide()) {
				BlockPos _bp = BlockPos.containing(x, y, z);
				BlockEntity _blockEntity = world.getBlockEntity(_bp);
				BlockState _bs = world.getBlockState(_bp);
				if (_blockEntity != null) {
					_blockEntity.getPersistentData().putDouble("fuel", FuelFromItemProcedure.execute(itemFromBlockInventory(world, BlockPos.containing(x, y, z), 1).copy()));
				}
				if (world instanceof Level _level)
					_level.sendBlockUpdated(_bp, _bs, _bs, 3);
			}
			if (world instanceof ServerLevel _serverLevel) {
				BlockEntity _be = _serverLevel.getBlockEntity(BlockPos.containing(x, y, z));
				if (_be instanceof Container _container) {
					_container.getItem(1).shrink(1);
				}
			}
		}
		if (world instanceof Level _level4 && _level4.hasNeighborSignal(BlockPos.containing(x, y, z))) {
			return false;
		}
		if ((itemFromBlockInventory(world, BlockPos.containing(x, y, z), 0).copy()).getMaxStackSize() == itemFromBlockInventory(world, BlockPos.containing(x, y, z), 0).getCount()) {
			return false;
		}
		if (getPropertyByName(blockstate, "active") instanceof BooleanProperty _getbp9 && blockstate.getValue(_getbp9)) {
			if (getBlockNBTNumber(world, BlockPos.containing(x, y, z), "fuel") <= fuel_usage) {
				return false;
			}
			if (world instanceof ServerLevel _serverLevel) {
				BlockEntity _be = _serverLevel.getBlockEntity(BlockPos.containing(x, y, z));
				if (_be instanceof Container _container) {
					ItemStack _setstack = new ItemStack(Blocks.ICE).copy();
					_setstack.setCount(itemFromBlockInventory(world, BlockPos.containing(x, y, z), 0).getCount() + 1);
					_container.setItem(0, _setstack);
				}
			}
			if (!world.isClientSide()) {
				BlockPos _bp = BlockPos.containing(x, y, z);
				BlockEntity _blockEntity = world.getBlockEntity(_bp);
				BlockState _bs = world.getBlockState(_bp);
				if (_blockEntity != null) {
					_blockEntity.getPersistentData().putDouble("fuel", (getBlockNBTNumber(world, BlockPos.containing(x, y, z), "fuel") - fuel_usage));
				}
				if (world instanceof Level _level)
					_level.sendBlockUpdated(_bp, _bs, _bs, 3);
			}
		}
		return true;
	}

	private static double getBlockNBTNumber(LevelAccessor world, BlockPos pos, String tag) {
		BlockEntity blockEntity = world.getBlockEntity(pos);
		if (blockEntity != null)
			return blockEntity.getPersistentData().getDoubleOr(tag, 0);
		return -1;
	}

	private static ItemStack itemFromBlockInventory(LevelAccessor world, BlockPos pos, int slot) {
		if (world instanceof ILevelExtension ext) {
			ResourceHandler<ItemResource> itemHandler = ext.getCapability(Capabilities.Item.BLOCK, pos, null);
			if (itemHandler != null)
				return ItemUtil.getStack(itemHandler, slot);
		}
		return ItemStack.EMPTY;
	}

	private static Property<?> getPropertyByName(BlockState state, String name) {
		for (Property<?> property : state.getProperties()) {
			if (property.getName().equals(name)) {
				return property;
			}
		}
		return null;
	}
}