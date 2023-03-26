package org.poiesis.transmutation.emc;

import java.util.HashMap;

public class EmcValueStore {
    private HashMap<String, Integer> emcValues = new HashMap<>();

    public void loadDefaultValues(HashMap<String, Integer> defaultValues) {
        emcValues = defaultValues;
    }
    public void addEmcValue(String itemName, int emcValue) {
        emcValues.put(itemName, emcValue);
    }
    public int getEmcValue(String itemName) {
        if (!emcValues.containsKey(itemName)) {
            return 0;
        }
        return emcValues.get(itemName);
    }

}
