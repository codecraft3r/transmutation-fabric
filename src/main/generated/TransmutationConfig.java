package org.poiesis.transmutation.config;

import blue.endless.jankson.Jankson;
import io.wispforest.owo.config.ConfigWrapper;
import io.wispforest.owo.config.Option;
import io.wispforest.owo.util.Observable;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Consumer;

public class TransmutationConfig extends ConfigWrapper<org.poiesis.transmutation.config.ConfigModel> {

    private final Option<java.util.HashMap<java.lang.String,java.lang.Integer>> emcMap = this.optionForKey(new Option.Key("emcMap"));

    private TransmutationConfig() {
        super(org.poiesis.transmutation.config.ConfigModel.class);
    }

    private TransmutationConfig(Consumer<Jankson.Builder> janksonBuilder) {
        super(org.poiesis.transmutation.config.ConfigModel.class, janksonBuilder);
    }

    public static TransmutationConfig createAndLoad() {
        var wrapper = new TransmutationConfig();
        wrapper.load();
        return wrapper;
    }

    public static TransmutationConfig createAndLoad(Consumer<Jankson.Builder> janksonBuilder) {
        var wrapper = new TransmutationConfig(janksonBuilder);
        wrapper.load();
        return wrapper;
    }

    public java.util.HashMap<java.lang.String,java.lang.Integer> emcMap() {
        return emcMap.value();
    }

    public void emcMap(java.util.HashMap<java.lang.String,java.lang.Integer> value) {
        emcMap.set(value);
    }




}

