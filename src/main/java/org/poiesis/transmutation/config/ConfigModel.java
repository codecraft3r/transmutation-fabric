package org.poiesis.transmutation.config;

import io.wispforest.owo.config.annotation.Config;
import io.wispforest.owo.config.annotation.Modmenu;
import net.minecraft.item.Items;
import java.util.HashMap;
@Modmenu(modId = "transmutation")
@Config(name = "transmutation", wrapperName = "TransmutationConfig")
public class ConfigModel {

    public HashMap<String, Integer> emcMap = new HashMap<>() {{
        put(String.valueOf(Items.DIRT), 1);
        put(String.valueOf(Items.COBBLESTONE), 1);
        put(String.valueOf(Items.STONE), 1);
        put(String.valueOf(Items.GRASS_BLOCK), 1);
        put(String.valueOf(Items.SAND), 1);
        put(String.valueOf(Items.GRAVEL), 1);
        put(String.valueOf(Items.OAK_LOG), 1);
    }};
}
