package org.poiesis.transmutation.components;

import net.minecraft.nbt.NbtCompound;
import net.minecraft.server.network.ServerPlayerEntity;

public class EmcStoreComponent implements SyncedIntComponent {
    private int value = 0;
    private final Object provider;
    public EmcStoreComponent(Object provider) {
        this.provider = provider;
    }
    @Override
    public int getValue() {
        return value;
    }
    @Override
    public void setValue(int value) {
        this.value = value;
        Components.SYNCED_INT_COMPONENT.sync(this.provider);
    }
    @Override public boolean shouldSyncWith(ServerPlayerEntity player) {
        return player == this.provider;
    }
    @Override public void readFromNbt(NbtCompound tag) { this.value = tag.getInt("value"); }
    @Override public void writeToNbt(NbtCompound tag) { tag.putInt("value", this.value); }
}
