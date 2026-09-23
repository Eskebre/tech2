/*
 *    MCreator note: This file will be REGENERATED on each build.
 */
package com.eskebre.techsquared.init;

import net.neoforged.neoforge.registries.DeferredRegister;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.event.BuildCreativeModeTabContentsEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.bus.api.SubscribeEvent;

import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.CreativeModeTabs;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.network.chat.Component;
import net.minecraft.core.registries.Registries;

import com.eskebre.techsquared.Tech2Mod;

@EventBusSubscriber
public class Tech2ModTabs {
	public static final DeferredRegister<CreativeModeTab> REGISTRY = DeferredRegister.create(Registries.CREATIVE_MODE_TAB, Tech2Mod.MODID);
	public static final DeferredHolder<CreativeModeTab, CreativeModeTab> TECH_2_TAB = REGISTRY.register("tech_2_tab",
			() -> CreativeModeTab.builder().title(Component.translatable("item_group.tech2.tech_2_tab")).icon(() -> new ItemStack(Tech2ModBlocks.MACHINE_BLOCK.get())).displayItems((parameters, tabData) -> {
				tabData.accept(Tech2ModBlocks.UPPER.get().asItem());
				tabData.accept(Tech2ModBlocks.COBBLESTONE_GENERATOR.get().asItem());
				tabData.accept(Tech2ModBlocks.CRUSHER.get().asItem());
				tabData.accept(Tech2ModItems.BASKET.get());
				tabData.accept(Tech2ModBlocks.BLOCK_PLACER.get().asItem());
				tabData.accept(Tech2ModBlocks.BLOCK_BREAKER.get().asItem());
				tabData.accept(Tech2ModBlocks.FREEZER.get().asItem());
				tabData.accept(Tech2ModBlocks.MACHINE_BLOCK.get().asItem());
				tabData.accept(Tech2ModBlocks.DYER.get().asItem());
			}).build());

	@SubscribeEvent
	public static void buildTabContentsVanilla(BuildCreativeModeTabContentsEvent tabData) {
		if (tabData.getTabKey() == CreativeModeTabs.REDSTONE_BLOCKS) {
			tabData.accept(Tech2ModBlocks.UPPER.get().asItem());
		}
	}
}