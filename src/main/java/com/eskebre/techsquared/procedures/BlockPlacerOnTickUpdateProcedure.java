package com.eskebre.techsquared.procedures;

import net.neoforged.neoforge.transfer.item.ItemUtil;
import net.neoforged.neoforge.transfer.item.ItemResource;
import net.neoforged.neoforge.transfer.ResourceHandler;
import net.neoforged.neoforge.common.extensions.ILevelExtension;
import net.neoforged.neoforge.capabilities.Capabilities;

import net.minecraft.world.level.block.state.properties.Property;
import net.minecraft.world.level.block.state.properties.EnumProperty;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.Container;
import net.minecraft.tags.BlockTags;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.resources.Identifier;
import net.minecraft.core.Direction;
import net.minecraft.core.BlockPos;

public class BlockPlacerOnTickUpdateProcedure {
	public static boolean execute(LevelAccessor world, double x, double y, double z, BlockState blockstate) {
		BlockState block = Blocks.AIR.defaultBlockState();
		if (getPropertyByName(blockstate, "triggered") instanceof BooleanProperty _getbp1 && blockstate.getValue(_getbp1)) {
			return false;
		}
		{
			BlockPos _pos = BlockPos.containing(x, y, z);
			BlockState _bs = world.getBlockState(_pos);
			if (_bs.getBlock().getStateDefinition().getProperty("triggered") instanceof BooleanProperty _booleanProp)
				world.setBlock(_pos, _bs.setValue(_booleanProp, true), 3);
		}
		block = ((itemFromBlockInventory(world, BlockPos.containing(x, y, z), 0).copy()).getItem() instanceof BlockItem _bi ? _bi.getBlock().defaultBlockState() : Blocks.AIR.defaultBlockState());
		if (block.getBlock() == Blocks.AIR) {
			return false;
		}
		if (!(world.getBlockState(BlockPos.containing(x + ((getDirectionFromBlockState(blockstate)).getOpposite()).getStepX(), y + ((getDirectionFromBlockState(blockstate)).getOpposite()).getStepY(),
				z + ((getDirectionFromBlockState(blockstate)).getOpposite()).getStepZ()))).is(BlockTags.create(Identifier.parse("minecraft:replaceable")))) {
			return false;
		}
		world.setBlock(BlockPos.containing(x + ((getDirectionFromBlockState(blockstate)).getOpposite()).getStepX(), y + ((getDirectionFromBlockState(blockstate)).getOpposite()).getStepY(),
				z + ((getDirectionFromBlockState(blockstate)).getOpposite()).getStepZ()), block, 3);
		if (world instanceof ServerLevel _serverLevel) {
			BlockEntity _be = _serverLevel.getBlockEntity(BlockPos.containing(x, y, z));
			if (_be instanceof Container _container) {
				_container.getItem(0).shrink(1);
			}
		}
		return true;
	}

	private static Property<?> getPropertyByName(BlockState state, String name) {
		for (Property<?> property : state.getProperties()) {
			if (property.getName().equals(name)) {
				return property;
			}
		}
		return null;
	}

	private static ItemStack itemFromBlockInventory(LevelAccessor world, BlockPos pos, int slot) {
		if (world instanceof ILevelExtension ext) {
			ResourceHandler<ItemResource> itemHandler = ext.getCapability(Capabilities.Item.BLOCK, pos, null);
			if (itemHandler != null)
				return ItemUtil.getStack(itemHandler, slot);
		}
		return ItemStack.EMPTY;
	}

	private static Direction getDirectionFromBlockState(BlockState blockState) {
		if (getPropertyByName(blockState, "facing") instanceof EnumProperty ep && ep.getValueClass() == Direction.class)
			return (Direction) blockState.getValue(ep);
		if (getPropertyByName(blockState, "axis") instanceof EnumProperty ep && ep.getValueClass() == Direction.Axis.class)
			return Direction.fromAxisAndDirection((Direction.Axis) blockState.getValue(ep), Direction.AxisDirection.POSITIVE);
		return Direction.NORTH;
	}
}