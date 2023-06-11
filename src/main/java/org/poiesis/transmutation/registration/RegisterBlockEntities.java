package org.poiesis.transmutation.registration;

import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;
import org.poiesis.transmutation.block.TransmutationBlockEntity;

public class RegisterBlockEntities {
    public static final BlockEntityType<TransmutationBlockEntity> TRANSMUTATION_TABLE_BLOCK_ENTITY = BlockEntityType.Builder.create(TransmutationBlockEntity::new, RegisterBlocks.TRANSMUTATION_TABLE).build(null);

    public static void registerAll() {
        Registry.register(Registries.BLOCK_ENTITY_TYPE, new Identifier("transmutation", "transmutation_table_block_entity"), TRANSMUTATION_TABLE_BLOCK_ENTITY);
    }
}
