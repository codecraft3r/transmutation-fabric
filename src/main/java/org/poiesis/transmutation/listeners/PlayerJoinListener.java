package org.poiesis.transmutation.listeners;

import com.google.gson.Gson;
import net.fabricmc.fabric.api.networking.v1.PacketSender;
import net.fabricmc.fabric.api.networking.v1.ServerPlayConnectionEvents;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.network.ServerPlayNetworkHandler;
import org.poiesis.transmutation.Main;
import org.poiesis.transmutation.emc.ItemEntry;

import java.util.ArrayList;

public class PlayerJoinListener implements ServerPlayConnectionEvents.Join {
    @Override
    public void onPlayReady(ServerPlayNetworkHandler handler, PacketSender sender, MinecraftServer server) {
        var player = handler.getPlayer();
        ArrayList<ItemEntry> emcMap = Main.emcValueStore.getEmcMap();
        Gson gson = new Gson();
        for (ItemEntry entry : emcMap) {
            if (entry.emcValue <= 0) {
                continue;
            }
            String json = gson.toJson(entry);
            Main.LOGGER.info("Sending EMC entry to player " + player.getName().getString() + ": " + json);
            Main.EMCMAPCHANNEL.serverHandle(player).send(new Main.EmcMapPacket(json));
        }
    }
}
