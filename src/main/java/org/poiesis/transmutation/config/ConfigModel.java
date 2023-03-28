package org.poiesis.transmutation.config;

import io.wispforest.owo.config.annotation.Config;
import io.wispforest.owo.config.annotation.Modmenu;
import net.minecraft.item.Items;
import java.util.HashMap;
@Modmenu(modId = "transmutation")
@Config(name = "transmutation", wrapperName = "TransmutationConfig")
public class ConfigModel {

    public HashMap<String, Integer> emcMap = new HashMap<>() {{
        put("minecraft:dirt", 1);
        put("minecraft:stone", 1);
        put("minecraft:gravel", 16);
        put("minecraft:flint", 16);
        put("minecraft:coal", 64);
        put("minecraft:iron_ingot", 128);
        put("minecraft:gold_ingot", 256);

    }};
}
