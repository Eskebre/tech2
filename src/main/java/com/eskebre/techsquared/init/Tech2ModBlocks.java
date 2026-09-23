/*
 *    MCreator note: This file will be REGENERATED on each build.
 */
package com.eskebre.techsquared.init;

import net.neoforged.neoforge.registries.DeferredRegister;
import net.neoforged.neoforge.registries.DeferredBlock;

import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.Block;

import java.util.function.Function;

import com.eskebre.techsquared.block.*;
import com.eskebre.techsquared.Tech2Mod;

public class Tech2ModBlocks {
	public static final DeferredRegister.Blocks REGISTRY = DeferredRegister.createBlocks(Tech2Mod.MODID);
	public static final DeferredBlock<Block> UPPER;
	public static final DeferredBlock<Block> COBBLESTONE_GENERATOR;
	public static final DeferredBlock<Block> CRUSHER;
	public static final DeferredBlock<Block> BLOCK_PLACER;
	public static final DeferredBlock<Block> BLOCK_BREAKER;
	public static final DeferredBlock<Block> FREEZER;
	public static final DeferredBlock<Block> MACHINE_BLOCK;
	public static final DeferredBlock<Block> DYER;
	static {
		UPPER = register("upper", UpperBlock::new);
		COBBLESTONE_GENERATOR = register("cobblestone_generator", CobblestoneGeneratorBlock::new);
		CRUSHER = register("crusher", CrusherBlock::new);
		BLOCK_PLACER = register("block_placer", BlockPlacerBlock::new);
		BLOCK_BREAKER = register("block_breaker", BlockBreakerBlock::new);
		FREEZER = register("freezer", FreezerBlock::new);
		MACHINE_BLOCK = register("machine_block", MachineBlockBlock::new);
		DYER = register("dyer", DyerBlock::new);
	}

	// Start of user code block custom blocks
	// End of user code block custom blocks
	private static <B extends Block> DeferredBlock<B> register(String name, Function<BlockBehaviour.Properties, ? extends B> supplier) {
		return REGISTRY.registerBlock(name, supplier);
	}
}