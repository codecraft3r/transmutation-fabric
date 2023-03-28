package org.poiesis.transmutation.emc;


import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.recipe.Ingredient;
import net.minecraft.recipe.Recipe;
import net.minecraft.recipe.RecipeType;
import net.minecraft.registry.DynamicRegistryManager;
import net.minecraft.registry.Registries;
import net.minecraft.server.MinecraftServer;
import org.poiesis.transmutation.Main;

import java.util.ArrayList;
import java.util.Collections;


public class EmcValueMapper {
    public void mapRecipes(MinecraftServer server) {
        for (Recipe<?> recipe : server.getRecipeManager().values()) {
            // iterate over all recipes
            if(Main.emcValueStore.getEmcValue(Registries.ITEM.getId(recipe.getOutput(DynamicRegistryManager.EMPTY).getItem()).toString()) != 0) {
                continue;
            }
            ArrayList<Integer> lowestIngredientValues = new ArrayList<>();
            for (Ingredient ingredient : recipe.getIngredients()) {
                // iterate over all ingredients in recipe
                ArrayList<Integer> allPossibleIngredientValues = new ArrayList<>();
                if (ingredient.getMatchingStacks().length == 0) {
                    continue;
                }
                for (ItemStack itemStack : ingredient.getMatchingStacks()) {
                    // iterate over all items in ingredient
                    Item item = itemStack.getItem();
                    int emcValue = Main.emcValueStore.getEmcValue(Registries.ITEM.getId(item).toString());
                    allPossibleIngredientValues.add(emcValue);

                }
                lowestIngredientValues.add(Collections.min(allPossibleIngredientValues));
            }
            // set recipe emc value to sum of lowest value ingredients, if any ingredient has no emc value, skip recipe
            if (checkForZero(lowestIngredientValues)) {
                continue;
            }
            Main.emcValueStore.addEmcValue(Registries.ITEM.getId(recipe.getOutput(DynamicRegistryManager.EMPTY).getItem()).toString(), sumArrayListValues(lowestIngredientValues));
        }
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
