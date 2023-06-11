package org.poiesis.transmutation.registration;

import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.minecraft.block.Block;
import net.minecraft.block.Material;
import net.minecraft.item.BlockItem;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;
import org.poiesis.transmutation.block.TransmutationTableBlock;

public class RegisterBlocks {
    public static final Block TRANSMUTATION_TABLE = new TransmutationTableBlock(FabricBlockSettings.of(Material.STONE).hardness(3.5f));

    public static void registerAll() {
        Registry.register(Registries.BLOCK, new Identifier("transmutation", "transmutation_table"), TRANSMUTATION_TABLE);
        Registry.register(Registries.ITEM, new Identifier("transmutation", "transmutation_table"), new BlockItem(TRANSMUTATION_TABLE, new FabricItemSettings()));
    }


}
