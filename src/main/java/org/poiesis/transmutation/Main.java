package org.poiesis.transmutation;

import com.google.gson.Gson;
import io.wispforest.owo.network.OwoNetChannel;
import io.wispforest.owo.registration.reflect.FieldRegistrationHandler;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.event.lifecycle.v1.ServerLifecycleEvents;
import net.fabricmc.fabric.api.networking.v1.ServerPlayConnectionEvents;
import net.fabricmc.fabric.api.screenhandler.v1.ExtendedScreenHandlerType;
import net.fabricmc.fabric.api.screenhandler.v1.ScreenHandlerRegistry;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.resource.featuretoggle.FeatureFlags;
import net.minecraft.util.Identifier;
import org.poiesis.transmutation.block.RegisterBlockEntities;
import org.poiesis.transmutation.block.TransmutationBlockEntity;
import org.poiesis.transmutation.config.TransmutationConfig;
import org.poiesis.transmutation.emc.EmcValueMapper;
import org.poiesis.transmutation.emc.EmcValueStore;
import org.poiesis.transmutation.emc.ItemEntry;
import org.poiesis.transmutation.block.RegisterBlocks;
import org.poiesis.transmutation.listeners.PlayerJoinListener;
import org.poiesis.transmutation.listeners.ServerStartListener;
import org.poiesis.transmutation.ui.TransmutationScreenHandler;
import net.minecraft.screen.ScreenHandlerType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Main implements ModInitializer {
	// This logger is used to write text to the console and the log file.
	// It is considered best practice to use your mod id as the logger's name.
	// That way, it's clear which mod wrote info, warnings, and errors.
	public static final Logger LOGGER = LoggerFactory.getLogger("transmutation");
	public static final TransmutationConfig config = TransmutationConfig.createAndLoad();

	// emc
	public static EmcValueStore emcValueStore = new EmcValueStore();
	public static EmcValueMapper emcValueMapper = new EmcValueMapper();
	public static final ScreenHandlerType<TransmutationScreenHandler> TRANSMUTATION_SCREEN_HANDLER_TYPE = new ScreenHandlerType<>(TransmutationScreenHandler::new, FeatureFlags.DEFAULT_ENABLED_FEATURES);
	// networking
	public record EmcMapPacket(String emcMap) {}
	public static final OwoNetChannel EMCMAPCHANNEL = OwoNetChannel.create(new Identifier("transmutation", "main"));

	//screens
	//public static final ScreenHandlerType<TransmutationScreenHandler> TRANSMUTATION_SCREEN_HANDLER_TYPE = new ScreenHandlerType<>(TransmutationScreenHandler::new, FeatureFlags.DEFAULT_ENABLED_FEATURES);


	@Override
	public void onInitialize() {
		// Register the networking channel
		EMCMAPCHANNEL.registerClientbound(EmcMapPacket.class, (message, access) -> {
			LOGGER.info("Received EMC map from server");
			// convert the array to a ArrayList<ItemEntry>
			Gson gson = new Gson();
			ItemEntry itemEntry = gson.fromJson(message.emcMap, ItemEntry.class);
			LOGGER.info("Received EMC entry: " + itemEntry.toString());
			emcValueStore.addEmcValue(itemEntry.itemName, itemEntry.emcValue);

		});

		// Register listeners
		ServerLifecycleEvents.SERVER_STARTING.register(new ServerStartListener());
		ServerPlayConnectionEvents.JOIN.register(new PlayerJoinListener());

		// Load EMC values from config
		LOGGER.info("Loading EMC values from config...");
		emcValueStore.loadDefaultValues(config.emcMap());
		LOGGER.info("Done. Transmutation mod initialized.");

		// Register items
		FieldRegistrationHandler.register(RegisterBlocks.class, "transmutation", false);
		//Register block entities
		FieldRegistrationHandler.register(RegisterBlockEntities.class, "transmutation", false);


		// register screen handler
		Registry.register(Registries.SCREEN_HANDLER, new Identifier("transmutation", "transmutation_screen_handler"), TRANSMUTATION_SCREEN_HANDLER_TYPE);
		// This code runs as soon as Minecraft is in a mod-load-ready state.
		// However, some things (like resources) may still be uninitialized.
		// Proceed with mild caution.

	}

}
