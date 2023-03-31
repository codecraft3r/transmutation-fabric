package org.poiesis.transmutation.ui;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.Inventory;
import net.minecraft.inventory.SimpleInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.registry.Registries;
import net.minecraft.screen.ScreenHandler;
import net.minecraft.screen.slot.Slot;
import net.minecraft.util.Identifier;
import org.poiesis.transmutation.Main;
import org.poiesis.transmutation.components.Components;
import org.poiesis.transmutation.components.SyncedIntComponent;
import org.poiesis.transmutation.components.SyncedStringArrayListComponent;

public class TransmutationScreenHandler extends ScreenHandler {
    private final Inventory inventory;
    private final SimpleInventory sacrificialInventory;

    public TransmutationScreenHandler(int syncId, PlayerInventory playerInventory) {
        super(Main.TRANSMUTATION_SCREEN_HANDLER, syncId);
        SyncedStringArrayListComponent knowledge = Components.SYNCED_STRING_ARRAY_LIST_COMPONENT.get(playerInventory.player);
        int howBigIsYourBrain = knowledge.getList().size();
        this.inventory = new SimpleInventory(howBigIsYourBrain);
        this.sacrificialInventory = new SimpleInventory(1);

        // Create the InfiniteItemSlots
        for (int i = 0; i < howBigIsYourBrain; i++) {
                addSlot(new EmcItemSlot(inventory, i, 8 + (i % 9) * 18, 18 + (i / 9) * 18, Registries.ITEM.get(new Identifier(knowledge.getList().get(i))), playerInventory.player));
        }
        // Create sacrificial slots
        addSlot(new Slot(sacrificialInventory, 0, 140, 72));

        sacrificialInventory.addListener((listener) -> {
            ItemStack stack = sacrificialInventory.getStack(0);
            if (stack.isEmpty()) {
                return;
            }
            Identifier id = Registries.ITEM.getId(stack.getItem());
            int emcValue = Main.emcValueStore.getEmcValue(id.toString());
            if (emcValue != 0) {
                SyncedIntComponent playerEmc = Components.SYNCED_INT_COMPONENT.get(playerInventory.player);
                playerEmc.setValue(playerEmc.getValue() + (emcValue * stack.getCount()));
            } else {
                // Drop the item on the ground
                PlayerEntity player = playerInventory.player;
                player.dropItem(stack, false);
                return;
            }

            SyncedStringArrayListComponent playerKnowledge = Components.SYNCED_STRING_ARRAY_LIST_COMPONENT.get(playerInventory.player);
            if (!playerKnowledge.getList().contains(id.toString())) {
                playerKnowledge.getList().add(id.toString());
            }
            sacrificialInventory.setStack(0, ItemStack.EMPTY);
        });


        // Add the player's inventory slots
        int playerInvY = 94;
        for (int row = 0; row < 3; row++) {
            for (int col = 0; col < 9; col++) {
                int x = 8 + col * 18;
                int y = playerInvY + row * 18;
                addSlot(new Slot(playerInventory, col + row * 9 + 9, x, y));
            }
        }



        // Add the player's hotbar slots
        int hotbarY = 148;
        for (int i = 0; i < 9; i++) {
            int x = 8 + i * 18;
            addSlot(new Slot(playerInventory, i, x, hotbarY));
        }


    }

    @Override
    public ItemStack quickMove(PlayerEntity player, int slot) {
        return ItemStack.EMPTY;
    }

    @Override
    public boolean canUse(PlayerEntity player) {
        return this.inventory.canPlayerUse(player);
    }

}
