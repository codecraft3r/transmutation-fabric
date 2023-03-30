package org.poiesis.transmutation.ui;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.Inventory;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.screen.slot.Slot;

public class InfiniteItemSlot extends Slot {
    private final Item item;

    public InfiniteItemSlot(Inventory inventory, int index, int x, int y, Item item) {
        super(inventory, index, x, y);
        this.item = item;
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
        return new ItemStack(this.item, 1);
    }
    @Override
    public ItemStack takeStack(int amount) {
        return new ItemStack(this.item, amount);
    }
}
