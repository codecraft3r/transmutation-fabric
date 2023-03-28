package org.poiesis.transmutation.ui;

import io.wispforest.owo.client.screens.ScreenUtils;
import io.wispforest.owo.client.screens.SlotGenerator;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.SimpleInventory;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.registry.Registries;
import net.minecraft.screen.ScreenHandler;
import net.minecraft.screen.slot.Slot;
import net.minecraft.util.Identifier;
import org.poiesis.transmutation.Main;
import org.poiesis.transmutation.components.RegisterComponents;
import org.poiesis.transmutation.components.SyncedIntComponent;
import org.poiesis.transmutation.components.SyncedStringArrayListComponent;

import java.util.ArrayList;

import static org.poiesis.transmutation.components.RegisterComponents.*;

public class TransmutationScreenHandler extends ScreenHandler {

    private final SimpleInventory transmutationInventory;
    private final SimpleInventory sacrificialInventory;
    public TransmutationScreenHandler(int syncId, PlayerInventory playerInventory) {
        this(syncId, playerInventory, new SimpleInventory(SYNCED_STRING_ARRAY_LIST_COMPONENT.get(playerInventory.player).getList().size() != 0 ? SYNCED_STRING_ARRAY_LIST_COMPONENT.get(playerInventory.player).getList().size() : 1), new SimpleInventory(1));
    }

    public TransmutationScreenHandler(int syncId, PlayerInventory playerInventory, SimpleInventory transmutationInventory, SimpleInventory sacrificialInventory) {
        super(Main.TRANSMUTATION_SCREEN_HANDLER_TYPE, syncId);
        this.transmutationInventory = transmutationInventory;
        SimpleInventory previousTransmutationInventory = new SimpleInventory(SYNCED_STRING_ARRAY_LIST_COMPONENT.get(playerInventory.player).getList().size() != 0 ? SYNCED_STRING_ARRAY_LIST_COMPONENT.get(playerInventory.player).getList().size() : 1);
        this.sacrificialInventory = sacrificialInventory;
        SyncedStringArrayListComponent knowledge = SYNCED_STRING_ARRAY_LIST_COMPONENT.get(playerInventory.player);
        int size = SYNCED_STRING_ARRAY_LIST_COMPONENT.get(playerInventory.player).getList().size();
        SlotGenerator.begin(this::addSlot, 8, 17)
                .slotFactory((inv, index, x, y) -> new Slot(inv, index, x, y))
                .grid(this.transmutationInventory, 0, size, size > 0 ? 1 : 0)
                .moveTo(8, 84)
                .slotFactory((inv, index, x, y) -> new Slot(inv, index, x, y))
                .grid(this.sacrificialInventory, 0, 1, 1)
                .moveTo(8, 142)
                .slotFactory(Slot::new)
                .playerInventory(playerInventory);

        for (String itemId : knowledge.getList()) {
            Item item = Registries.ITEM.get(new Identifier(itemId));
            SyncedIntComponent emcStore = SYNCED_INT_COMPONENT.get(playerInventory.player);
            int currentEmc = emcStore.getValue();
            if(currentEmc >= Main.emcValueStore.getEmcValue(itemId)) {
                this.transmutationInventory.addStack(new ItemStack(item));
                previousTransmutationInventory.addStack(new ItemStack(item));
            }

        }
        this.sacrificialInventory.addListener((inv) -> {
            if (!this.sacrificialInventory.getStack(0).isEmpty()) {
                ItemStack stack = this.sacrificialInventory.getStack(0);
                Item item = this.sacrificialInventory.getStack(0).getItem();
                int emcValue = Main.emcValueStore.getEmcValue(Registries.ITEM.getId(item).toString());
                if (emcValue != 0) {
                    SyncedIntComponent emcStore = SYNCED_INT_COMPONENT.get(playerInventory.player);
                    int currentEmc = emcStore.getValue();
                    if (!knowledge.getList().contains(Registries.ITEM.getId(item).toString())) {
                        knowledge.addToList(Registries.ITEM.getId(item).toString());
                    }
                    emcStore.setValue(currentEmc + (emcValue * stack.getCount()));
                    this.sacrificialInventory.setStack(0, ItemStack.EMPTY);
                }
            }
        });

        this.transmutationInventory.addListener((inv) -> {
            for (int i = 0; i < transmutationInventory.size(); i++) {
                ItemStack prevStack = previousTransmutationInventory.getStack(i);
                ItemStack newStack = transmutationInventory.getStack(i);
                if (!ItemStack.areEqual(prevStack, newStack)) {
                    int current_emc = SYNCED_INT_COMPONENT.get(playerInventory.player).getValue();
                    SYNCED_INT_COMPONENT.get(playerInventory.player).setValue(current_emc - Main.emcValueStore.getEmcValue(Registries.ITEM.getId(prevStack.getItem()).toString()));
                    if(SYNCED_INT_COMPONENT.get(playerInventory.player).getValue() >= Main.emcValueStore.getEmcValue(Registries.ITEM.getId(prevStack.getItem()).toString())) {
                        inv.setStack(i, prevStack);
                        previousTransmutationInventory.setStack(i, prevStack);
                    } else {
                        previousTransmutationInventory.setStack(i, ItemStack.EMPTY);
                    }
                }
            }
        });


    }

    public boolean canUse(PlayerEntity player) {
        return true;
    }
    public ItemStack quickMove(PlayerEntity player, int invSlot) {
        return ScreenUtils.handleSlotTransfer(this, invSlot, this.sacrificialInventory.size());
    }



}
