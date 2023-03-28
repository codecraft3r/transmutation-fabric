package org.poiesis.transmutation.components;

import dev.onyxstudios.cca.api.v3.component.ComponentKey;
import dev.onyxstudios.cca.api.v3.component.ComponentRegistry;
import dev.onyxstudios.cca.api.v3.entity.EntityComponentFactoryRegistry;
import dev.onyxstudios.cca.api.v3.entity.EntityComponentInitializer;
import dev.onyxstudios.cca.api.v3.entity.RespawnCopyStrategy;
import net.minecraft.util.Identifier;

public final class RegisterComponents implements EntityComponentInitializer {
    public static final ComponentKey<SyncedIntComponent> SYNCED_INT_COMPONENT = ComponentRegistry.getOrCreate(new Identifier("transmutation", "synced_int"), SyncedIntComponent.class);
    public static final ComponentKey<SyncedStringArrayListComponent> SYNCED_STRING_ARRAY_LIST_COMPONENT = ComponentRegistry.getOrCreate(new Identifier("transmutation", "synced_string_array_list"), SyncedStringArrayListComponent.class);
    @Override
    public void registerEntityComponentFactories(EntityComponentFactoryRegistry registry) {
        registry.registerForPlayers(SYNCED_INT_COMPONENT, player -> new EmcStoreComponent(player), RespawnCopyStrategy.ALWAYS_COPY);
        registry.registerForPlayers(SYNCED_STRING_ARRAY_LIST_COMPONENT, player -> new KnowledgeStoreComponent(player), RespawnCopyStrategy.ALWAYS_COPY);
    }

}
