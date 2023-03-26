package org.poiesis.transmutation;

import net.fabricmc.api.ModInitializer;
import org.poiesis.transmutation.config.TransmutationConfig;
import org.poiesis.transmutation.emc.EmcValueMapper;
import org.poiesis.transmutation.emc.EmcValueStore;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Main implements ModInitializer {
	// This logger is used to write text to the console and the log file.
	// It is considered best practice to use your mod id as the logger's name.
	// That way, it's clear which mod wrote info, warnings, and errors.
	public static final Logger LOGGER = LoggerFactory.getLogger("transmutation");
	public static final TransmutationConfig config = TransmutationConfig.createAndLoad();
	public static EmcValueStore emcValueStore = new EmcValueStore();
	public static EmcValueMapper emcValueMapper = new EmcValueMapper();

	@Override
	public void onInitialize() {
		// This code runs as soon as Minecraft is in a mod-load-ready state.
		// However, some things (like resources) may still be uninitialized.
		// Proceed with mild caution.
		LOGGER.info("Loading EMC values from config...");
		emcValueStore.loadDefaultValues(config.emcMap());
		LOGGER.info("Done. Mapping recipes...");
		emcValueMapper.mapRecipes();
		LOGGER.info("Done. Transmutation mod initialized.");

	}

}
