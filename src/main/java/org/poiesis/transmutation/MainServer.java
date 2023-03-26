package org.poiesis.transmutation;

import com.google.gson.Gson;
import net.fabricmc.api.DedicatedServerModInitializer;
import net.fabricmc.fabric.api.event.lifecycle.v1.ServerLifecycleEvents;
import net.fabricmc.fabric.api.networking.v1.ServerPlayConnectionEvents;
import org.poiesis.transmutation.listeners.PlayerJoinListener;
import org.poiesis.transmutation.listeners.ServerStartListener;


import java.util.HashMap;

import static org.poiesis.transmutation.Main.*;

public class MainServer implements DedicatedServerModInitializer {
    @Override
    public void onInitializeServer() {;
        // Register the server tick listener
        ServerLifecycleEvents.SERVER_STARTING.register(new ServerStartListener());
        ServerPlayConnectionEvents.JOIN.register(new PlayerJoinListener());
        LOGGER.info("Loading EMC values from config...");
        emcValueStore.loadDefaultValues(config.emcMap());
        LOGGER.info("Done. Transmutation mod initialized.");
    }
}
