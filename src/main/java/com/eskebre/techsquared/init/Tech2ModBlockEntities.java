/*
 *    MCreator note: This file will be REGENERATED on each build.
 */
package com.eskebre.techsquared.init;

import net.neoforged.neoforge.transfer.item.WorldlyContainerWrapper;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.capabilities.RegisterCapabilitiesEvent;
import net.neoforged.neoforge.capabilities.Capabilities;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.bus.api.SubscribeEvent;

import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.Block;
import net.minecraft.core.registries.BuiltInRegistries;

import com.eskebre.techsquared.block.entity.*;
import com.eskebre.techsquared.Tech2Mod;

@EventBusSubscriber
public class Tech2ModBlockEntities {
	public static final DeferredRegister<BlockEntityType<?>> REGISTRY = DeferredRegister.create(BuiltInRegistries.BLOCK_ENTITY_TYPE, Tech2Mod.MODID);
	public static final DeferredHolder<BlockEntityType<?>, BlockEntityType<UpperBlockEntity>> UPPER = register("upper", Tech2ModBlocks.UPPER, UpperBlockEntity::new);
	public static final DeferredHolder<BlockEntityType<?>, BlockEntityType<CobblestoneGeneratorBlockEntity>> COBBLESTONE_GENERATOR = register("cobblestone_generator", Tech2ModBlocks.COBBLESTONE_GENERATOR, CobblestoneGeneratorBlockEntity::new);
	public static final DeferredHolder<BlockEntityType<?>, BlockEntityType<CrusherBlockEntity>> CRUSHER = register("crusher", Tech2ModBlocks.CRUSHER, CrusherBlockEntity::new);
	public static final DeferredHolder<BlockEntityType<?>, BlockEntityType<BlockPlacerBlockEntity>> BLOCK_PLACER = register("block_placer", Tech2ModBlocks.BLOCK_PLACER, BlockPlacerBlockEntity::new);
	public static final DeferredHolder<BlockEntityType<?>, BlockEntityType<FreezerBlockEntity>> FREEZER = register("freezer", Tech2ModBlocks.FREEZER, FreezerBlockEntity::new);
	public static final DeferredHolder<BlockEntityType<?>, BlockEntityType<DyerBlockEntity>> DYER = register("dyer", Tech2ModBlocks.DYER, DyerBlockEntity::new);

	// Start of user code block custom block entities
	// End of user code block custom block entities
	private static <T extends BlockEntity> DeferredHolder<BlockEntityType<?>, BlockEntityType<T>> register(String registryname, DeferredHolder<Block, Block> block, BlockEntityType.BlockEntitySupplier<T> supplier) {
		return REGISTRY.register(registryname, () -> new BlockEntityType(supplier, block.get()));
	}

	@SubscribeEvent
	public static void registerCapabilities(RegisterCapabilitiesEvent event) {
		event.registerBlockEntity(Capabilities.Item.BLOCK, UPPER.get(), WorldlyContainerWrapper::new);
		event.registerBlockEntity(Capabilities.Item.BLOCK, COBBLESTONE_GENERATOR.get(), WorldlyContainerWrapper::new);
		event.registerBlockEntity(Capabilities.Item.BLOCK, CRUSHER.get(), WorldlyContainerWrapper::new);
		event.registerBlockEntity(Capabilities.Item.BLOCK, BLOCK_PLACER.get(), WorldlyContainerWrapper::new);
		event.registerBlockEntity(Capabilities.Item.BLOCK, FREEZER.get(), WorldlyContainerWrapper::new);
		event.registerBlockEntity(Capabilities.Item.BLOCK, DYER.get(), WorldlyContainerWrapper::new);
	}
}