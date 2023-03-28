package org.poiesis.transmutation.components;

import net.minecraft.nbt.NbtCompound;
import net.minecraft.nbt.NbtElement;
import net.minecraft.nbt.NbtList;
import net.minecraft.nbt.NbtString;
import net.minecraft.server.network.ServerPlayerEntity;

import java.util.ArrayList;

public class KnowledgeStoreComponent implements SyncedStringArrayListComponent {
    private ArrayList<String> list = new ArrayList<>();
    private final Object provider;
    KnowledgeStoreComponent(Object provider) {
        this.provider = provider;
    }

    @Override
    public ArrayList<String> getList() {
        return list;
    }
    @Override
    public void addToList(String value) {
        this.list.add(value);
    }
    @Override
    public void removeFromList(String value) {
        this.list.remove(value);
    }
    @Override public boolean shouldSyncWith(ServerPlayerEntity player) {
        return player == this.provider;
    }
    @Override public void writeToNbt(NbtCompound tag) {
        NbtList nbtList = new NbtList();
        for (String s : this.list) {
            nbtList.add(NbtString.of(s));
        }
        tag.put("knowledge", nbtList);
    }
    @Override public void readFromNbt(NbtCompound tag) {
        NbtList nbtList = tag.getList("knowledge", NbtElement.STRING_TYPE);
        for (NbtElement nbtElement : nbtList) {
            this.list.add(nbtElement.asString());
        }
    }

}
