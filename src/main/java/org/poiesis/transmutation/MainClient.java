package org.poiesis.transmutation;

import net.fabricmc.api.ClientModInitializer;
import org.poiesis.transmutation.registration.RegisterScreens;


public class MainClient implements ClientModInitializer {
    @Override public void onInitializeClient() {
        // Register the screen
        RegisterScreens.registerClient();
    }
}
