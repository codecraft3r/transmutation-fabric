package org.poiesis.transmutation.block;

import io.wispforest.owo.registration.reflect.BlockRegistryContainer;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;

public class RegisterBlocks implements BlockRegistryContainer {
    public static final Block TRANSMUTATION_TABLE = new TransmutationTableBlock(FabricBlockSettings.copy(Blocks.CRAFTING_TABLE).nonOpaque());

}
