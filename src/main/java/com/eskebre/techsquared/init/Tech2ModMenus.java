/*
 *	MCreator note: This file will be REGENERATED on each build.
 */
package com.eskebre.techsquared.init;

import net.neoforged.neoforge.registries.DeferredRegister;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.network.PacketDistributor;
import net.neoforged.neoforge.common.extensions.IMenuTypeExtension;
import net.neoforged.neoforge.client.network.ClientPacketDistributor;

import net.minecraft.world.inventory.Slot;
import net.minecraft.world.inventory.MenuType;
import net.minecraft.world.entity.player.Player;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.core.registries.Registries;
import net.minecraft.client.Minecraft;

import java.util.Map;

import com.eskebre.techsquared.world.inventory.*;
import com.eskebre.techsquared.network.MenuStateUpdateMessage;
import com.eskebre.techsquared.Tech2Mod;

public class Tech2ModMenus {
	public static final DeferredRegister<MenuType<?>> REGISTRY = DeferredRegister.create(Registries.MENU, Tech2Mod.MODID);
	public static final DeferredHolder<MenuType<?>, MenuType<UpperGuiMenu>> UPPER_GUI = REGISTRY.register("upper_gui", () -> IMenuTypeExtension.create(UpperGuiMenu::new));
	public static final DeferredHolder<MenuType<?>, MenuType<CobblestoneGeneratorGUIMenu>> COBBLESTONE_GENERATOR_GUI = REGISTRY.register("cobblestone_generator_gui", () -> IMenuTypeExtension.create(CobblestoneGeneratorGUIMenu::new));
	public static final DeferredHolder<MenuType<?>, MenuType<CrusherGUIMenu>> CRUSHER_GUI = REGISTRY.register("crusher_gui", () -> IMenuTypeExtension.create(CrusherGUIMenu::new));
	public static final DeferredHolder<MenuType<?>, MenuType<BasketGUIMenu>> BASKET_GUI = REGISTRY.register("basket_gui", () -> IMenuTypeExtension.create(BasketGUIMenu::new));
	public static final DeferredHolder<MenuType<?>, MenuType<BlockPlacerGuiMenu>> BLOCK_PLACER_GUI = REGISTRY.register("block_placer_gui", () -> IMenuTypeExtension.create(BlockPlacerGuiMenu::new));
	public static final DeferredHolder<MenuType<?>, MenuType<FreezerGUIMenu>> FREEZER_GUI = REGISTRY.register("freezer_gui", () -> IMenuTypeExtension.create(FreezerGUIMenu::new));
	public static final DeferredHolder<MenuType<?>, MenuType<DyerGUIMenu>> DYER_GUI = REGISTRY.register("dyer_gui", () -> IMenuTypeExtension.create(DyerGUIMenu::new));

	public interface MenuAccessor {
		Map<String, Object> getMenuState();

		Map<Integer, Slot> getSlots();

		default void sendMenuStateUpdate(Player player, int elementType, String name, Object elementState, boolean needClientUpdate) {
			getMenuState().put(elementType + ":" + name, elementState);
			if (player instanceof ServerPlayer serverPlayer) {
				PacketDistributor.sendToPlayer(serverPlayer, new MenuStateUpdateMessage(elementType, name, elementState));
			} else if (player.level().isClientSide()) {
				if (Minecraft.getInstance().screen instanceof Tech2ModScreens.ScreenAccessor accessor && needClientUpdate)
					accessor.updateMenuState(elementType, name, elementState);
				ClientPacketDistributor.sendToServer(new MenuStateUpdateMessage(elementType, name, elementState));
			}
		}

		default <T> T getMenuState(int elementType, String name, T defaultValue) {
			try {
				return (T) getMenuState().getOrDefault(elementType + ":" + name, defaultValue);
			} catch (ClassCastException e) {
				return defaultValue;
			}
		}
	}
}