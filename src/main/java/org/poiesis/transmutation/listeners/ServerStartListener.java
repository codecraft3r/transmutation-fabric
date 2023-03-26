package org.poiesis.transmutation.listeners;

import net.fabricmc.fabric.api.event.lifecycle.v1.ServerLifecycleEvents;
import org.poiesis.transmutation.Main;

public class ServerStartListener implements ServerLifecycleEvents.ServerStarting {
    @Override
    public void onServerStarting(net.minecraft.server.MinecraftServer server) {
        // This code runs when the server starts.
        // It is safe to add commands and change data packs here.
        // This is the earliest point at which you can safely interact with the server.
        // However, the server is not yet ready to accept commands or players.
        // Proceed with caution.
        Main.LOGGER.info("Mapping EMC values from recipes...");
        Main.emcValueMapper.mapRecipes(server);
        Main.LOGGER.info("Done. EMC values mapped.");
    }

}
