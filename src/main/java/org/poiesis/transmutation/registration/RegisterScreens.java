package org.poiesis.transmutation.registration;

import net.minecraft.client.gui.screen.ingame.HandledScreens;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.resource.featuretoggle.FeatureFlags;
import net.minecraft.screen.ScreenHandlerType;
import net.minecraft.util.Identifier;
import org.poiesis.transmutation.ui.TransmutationScreen;
import org.poiesis.transmutation.ui.TransmutationScreenHandler;

public class RegisterScreens {
    public static final ScreenHandlerType<TransmutationScreenHandler> TRANSMUTATION_SCREEN_HANDLER = new ScreenHandlerType<>(TransmutationScreenHandler::new, FeatureFlags.DEFAULT_ENABLED_FEATURES);
    public static void registerShared() {
        Registry.register(Registries.SCREEN_HANDLER, new Identifier("transmutation", "transmutation_screen_handler"), TRANSMUTATION_SCREEN_HANDLER);
    }
    public static void registerClient() {
        HandledScreens.register(TRANSMUTATION_SCREEN_HANDLER, TransmutationScreen::new);
    }
}
