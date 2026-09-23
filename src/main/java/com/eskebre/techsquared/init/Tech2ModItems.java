/*
 *    MCreator note: This file will be REGENERATED on each build.
 */
package com.eskebre.techsquared.init;

import net.neoforged.neoforge.registries.DeferredRegister;
import net.neoforged.neoforge.registries.DeferredItem;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.client.event.RegisterRangeSelectItemModelPropertyEvent;
import net.neoforged.neoforge.capabilities.RegisterCapabilitiesEvent;
import net.neoforged.neoforge.capabilities.Capabilities;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.api.distmarker.Dist;

import net.minecraft.world.level.block.Block;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.BlockItem;
import net.minecraft.resources.Identifier;

import java.util.function.Function;

import com.eskebre.techsquared.item.inventory.BasketInventoryCapability;
import com.eskebre.techsquared.item.BasketItem;
import com.eskebre.techsquared.block.DyerBlock;
import com.eskebre.techsquared.Tech2Mod;

@EventBusSubscriber
public class Tech2ModItems {
	public static final DeferredRegister.Items REGISTRY = DeferredRegister.createItems(Tech2Mod.MODID);
	public static final DeferredItem<Item> UPPER;
	public static final DeferredItem<Item> COBBLESTONE_GENERATOR;
	public static final DeferredItem<Item> CRUSHER;
	public static final DeferredItem<Item> BASKET;
	public static final DeferredItem<Item> BLOCK_PLACER;
	public static final DeferredItem<Item> BLOCK_BREAKER;
	public static final DeferredItem<Item> FREEZER;
	public static final DeferredItem<Item> MACHINE_BLOCK;
	public static final DeferredItem<Item> DYER;
	static {
		UPPER = block(Tech2ModBlocks.UPPER);
		COBBLESTONE_GENERATOR = block(Tech2ModBlocks.COBBLESTONE_GENERATOR);
		CRUSHER = block(Tech2ModBlocks.CRUSHER);
		BASKET = register("basket", BasketItem::new);
		BLOCK_PLACER = block(Tech2ModBlocks.BLOCK_PLACER);
		BLOCK_BREAKER = block(Tech2ModBlocks.BLOCK_BREAKER);
		FREEZER = block(Tech2ModBlocks.FREEZER);
		MACHINE_BLOCK = block(Tech2ModBlocks.MACHINE_BLOCK);
		DYER = register("dyer", DyerBlock.Item::new);
	}

	// Start of user code block custom items
	// End of user code block custom items
	private static <I extends Item> DeferredItem<I> register(String name, Function<Item.Properties, ? extends I> supplier) {
		return REGISTRY.registerItem(name, supplier, Item.Properties::new);
	}

	private static DeferredItem<Item> block(DeferredHolder<Block, Block> block) {
		return block(block, new Item.Properties());
	}

	private static DeferredItem<Item> block(DeferredHolder<Block, Block> block, Item.Properties properties) {
		return REGISTRY.registerItem(block.getId().getPath(), prop -> new BlockItem(block.get(), prop), () -> properties);
	}

	@SubscribeEvent
	public static void registerCapabilities(RegisterCapabilitiesEvent event) {
		event.registerItem(Capabilities.Item.ITEM, (stack, access) -> new BasketInventoryCapability(access), BASKET.get());
	}

	@EventBusSubscriber(Dist.CLIENT)
	public static class ItemsClientSideHandler {
		@SubscribeEvent
		public static void registerItemModelProperties(RegisterRangeSelectItemModelPropertyEvent event) {
			event.register(Identifier.parse("tech2:basket/color"), BasketItem.ColorProperty.MAP_CODEC);
		}
	}
}