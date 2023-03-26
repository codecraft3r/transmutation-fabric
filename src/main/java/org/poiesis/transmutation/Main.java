package org.poiesis.transmutation;

import com.google.gson.Gson;
import io.wispforest.owo.network.OwoNetChannel;
import net.fabricmc.api.ModInitializer;
import net.minecraft.util.Identifier;
import org.poiesis.transmutation.config.TransmutationConfig;
import org.poiesis.transmutation.emc.EmcValueMapper;
import org.poiesis.transmutation.emc.EmcValueStore;
import org.poiesis.transmutation.emc.ItemEntry;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;

public class Main implements ModInitializer {
	// This logger is used to write text to the console and the log file.
	// It is considered best practice to use your mod id as the logger's name.
	// That way, it's clear which mod wrote info, warnings, and errors.
	public static final Logger LOGGER = LoggerFactory.getLogger("transmutation");
	public static final TransmutationConfig config = TransmutationConfig.createAndLoad();
	public static EmcValueStore emcValueStore = new EmcValueStore();
	public static EmcValueMapper emcValueMapper = new EmcValueMapper();
	public static final OwoNetChannel EMCMAPCHANNEL = OwoNetChannel.create(new Identifier("transmutation", "main"));
	public record EmcMapPacket(String emcMap) {}
	@Override
	public void onInitialize() {
		// Register the channel
		EMCMAPCHANNEL.registerClientbound(EmcMapPacket.class, (message, access) -> {
			LOGGER.info("Received EMC map from server");
			// convert the array to a ArrayList<ItemEntry>
			Gson gson = new Gson();
			ItemEntry itemEntry = gson.fromJson(message.emcMap, ItemEntry.class);
			LOGGER.info("Received EMC entry: " + itemEntry.toString());
			emcValueStore.addEmcValue(itemEntry.itemName, itemEntry.emcValue);

		});
		// This code runs as soon as Minecraft is in a mod-load-ready state.
		// However, some things (like resources) may still be uninitialized.
		// Proceed with mild caution.

	}

}
