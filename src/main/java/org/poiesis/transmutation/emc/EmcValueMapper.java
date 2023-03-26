package org.poiesis.transmutation.emc;


import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.recipe.Ingredient;
import net.minecraft.recipe.Recipe;
import net.minecraft.recipe.RecipeType;
import net.minecraft.registry.DynamicRegistryManager;
import net.minecraft.server.MinecraftServer;
import org.poiesis.transmutation.Main;

import java.util.ArrayList;


public class EmcValueMapper {
    public void mapRecipes(MinecraftServer server) {
        for (Recipe<?> recipe : server.getRecipeManager().values()) {// iterate over all recipes
            if (recipe.getType() != RecipeType.CRAFTING) {
                continue;
            }
            if(Main.emcValueStore.getEmcValue(String.valueOf(recipe.getOutput(DynamicRegistryManager.EMPTY).getItem())) == 0) {
                continue;
            }
            ArrayList<Integer> lowestIngredientValues = new ArrayList<>();
            for (Ingredient ingredient : recipe.getIngredients()) {
                // iterate over all ingredients in recipe
                ArrayList<Integer> allPossibleIngredientValues = new ArrayList<>();
                for (ItemStack itemStack : ingredient.getMatchingStacks()) {
                    // iterate over all items in ingredient
                    Item item = itemStack.getItem();
                    allPossibleIngredientValues.add(Main.emcValueStore.getEmcValue(String.valueOf(item)));
                }
                // get lowest value item from ingredient
                lowestIngredientValues.add(getLowestValue(allPossibleIngredientValues));
            }
            // set recipe emc value to sum of lowest value ingredients, if any ingredient has no emc value, skip recipe
            if (checkForZero(lowestIngredientValues)) {
                continue;
            }
            Main.emcValueStore.addEmcValue(String.valueOf(recipe.getOutput(DynamicRegistryManager.EMPTY).getItem()), sumArrayListValues(lowestIngredientValues));
        }
    }
    private int getLowestValue(ArrayList<Integer> values) {
        int lowestValue = Integer.MAX_VALUE;
        for (int value : values) {
            if (value < lowestValue) {
                lowestValue = value;
            }
        }
        if (lowestValue > Integer.MAX_VALUE - 1) {
            return 0;
        }
        return lowestValue;

    }
    private boolean checkForZero(ArrayList<Integer> values) {
        for (int value : values) {
            if (value == 0) {
                return true;
            }
        }
        return false;
    }
    private int sumArrayListValues(ArrayList<Integer> values) {
        return values.stream().mapToInt(Integer::intValue).sum();

    }
}
