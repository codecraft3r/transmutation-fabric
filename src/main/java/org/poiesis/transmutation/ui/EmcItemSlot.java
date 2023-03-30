package org.poiesis.transmutation.ui;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.Inventory;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.registry.Registries;
import net.minecraft.screen.slot.Slot;
import org.poiesis.transmutation.Main;
import org.poiesis.transmutation.components.RegisterComponents;
import org.poiesis.transmutation.components.SyncedIntComponent;

public class EmcItemSlot extends Slot {
    private final Item item;
    private final PlayerEntity player;

    public EmcItemSlot(Inventory inventory, int index, int x, int y, Item item, PlayerEntity player) {
        super(inventory, index, x, y);
        this.item = item;
        this.player = player;
    }

    @Override
    public boolean canInsert(ItemStack stack) {
        return false;
    }

    @Override
    public boolean canTakeItems(PlayerEntity playerEntity) {
        return true;
    }
    @Override
    public ItemStack getStack() {
        if (RegisterComponents.SYNCED_INT_COMPONENT.get(this.player).getValue() >= Main.emcValueStore.getEmcValue(Registries.ITEM.getId(this.item).toString())) {
            return new ItemStack(this.item, 1);
        }
        return ItemStack.EMPTY;
    }
    @Override
    public ItemStack takeStack(int amount) {
        SyncedIntComponent playerEmc = RegisterComponents.SYNCED_INT_COMPONENT.get(this.player);
        int itemEmc = Main.emcValueStore.getEmcValue(Registries.ITEM.getId(this.item).toString());
        if (playerEmc.getValue() >= itemEmc) {
            playerEmc.setValue(playerEmc.getValue() - itemEmc);
            return new ItemStack(this.item, 1);
        }
        return ItemStack.EMPTY;
    }
}
