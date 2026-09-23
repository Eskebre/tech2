/*
 *	MCreator note: This file will be REGENERATED on each build.
 */
package com.eskebre.techsquared.init;

import net.neoforged.neoforge.client.event.RegisterMenuScreensEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.api.distmarker.Dist;

import com.eskebre.techsquared.client.gui.*;

@EventBusSubscriber(Dist.CLIENT)
public class Tech2ModScreens {
	@SubscribeEvent
	public static void clientLoad(RegisterMenuScreensEvent event) {
		event.register(Tech2ModMenus.UPPER_GUI.get(), UpperGuiScreen::new);
		event.register(Tech2ModMenus.COBBLESTONE_GENERATOR_GUI.get(), CobblestoneGeneratorGUIScreen::new);
		event.register(Tech2ModMenus.CRUSHER_GUI.get(), CrusherGUIScreen::new);
		event.register(Tech2ModMenus.BASKET_GUI.get(), BasketGUIScreen::new);
		event.register(Tech2ModMenus.BLOCK_PLACER_GUI.get(), BlockPlacerGuiScreen::new);
		event.register(Tech2ModMenus.FREEZER_GUI.get(), FreezerGUIScreen::new);
		event.register(Tech2ModMenus.DYER_GUI.get(), DyerGUIScreen::new);
	}

	public interface ScreenAccessor {
		void updateMenuState(int elementType, String name, Object elementState);
	}
}