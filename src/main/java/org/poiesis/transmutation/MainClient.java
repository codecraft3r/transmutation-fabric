package org.poiesis.transmutation;

import net.fabricmc.api.ClientModInitializer;
import net.minecraft.client.gui.screen.ingame.HandledScreens;
import org.poiesis.transmutation.ui.TransmutationScreen;
import org.poiesis.transmutation.Main;

public class MainClient implements ClientModInitializer {
    @Override public void onInitializeClient() {
        HandledScreens.register(Main.TRANSMUTATION_SCREEN_HANDLER, TransmutationScreen::new);
    }
}
