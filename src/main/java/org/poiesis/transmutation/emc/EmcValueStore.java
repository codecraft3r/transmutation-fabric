package org.poiesis.transmutation.emc;

import net.minecraft.item.Item;
import org.poiesis.transmutation.Main;

import java.util.ArrayList;
import java.util.HashMap;

public class EmcValueStore {
    private ArrayList<ItemEntry> emcValues = new ArrayList<>();

    public void loadDefaultValues(HashMap<String, Integer> defaultValues) {
        for (String key : defaultValues.keySet()) {
            ItemEntry itemEntry = new ItemEntry();
            itemEntry.itemName = key;
            itemEntry.emcValue = defaultValues.get(key);
            Main.LOGGER.info("Adding default emc value for " + key + ": " + defaultValues.get(key));
            emcValues.add(itemEntry);
        }
    }

    public void addEmcValue(String itemName, int emcValue) {
        // check if item already has emc value
        for (ItemEntry itemEntry : emcValues) {
            if (itemEntry.itemName.equals(itemName)) {
                itemEntry.emcValue = emcValue;
                return;
            }
        }
        // if item doesn't have emc value, add it
        ItemEntry itemEntry = new ItemEntry();
        itemEntry.itemName = itemName;
        itemEntry.emcValue = emcValue;
        emcValues.add(itemEntry);
    }
    public int getEmcValue(String itemName) {
        for (ItemEntry itemEntry : emcValues) {
            if (itemEntry.itemName.equals(itemName)) {
                return itemEntry.emcValue;
            }
        }
        return 0;
    }
    public ArrayList<ItemEntry> getEmcMap() {
        return emcValues;
    }

}
