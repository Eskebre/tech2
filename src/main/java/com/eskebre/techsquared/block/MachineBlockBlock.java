package com.eskebre.techsquared.block;

import net.minecraft.world.level.material.MapColor;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.Block;

public class MachineBlockBlock extends Block {
	public MachineBlockBlock(BlockBehaviour.Properties properties) {
		super(properties.mapColor(MapColor.STONE).strength(3.5f, 5f).requiresCorrectToolForDrops());
	}
}