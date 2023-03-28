package org.poiesis.transmutation.block;

import io.wispforest.owo.registration.reflect.AutoRegistryContainer;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;

public class RegisterBlockEntities implements AutoRegistryContainer<BlockEntityType<?>> {
    public static final BlockEntityType<TransmutationBlockEntity> TRANSMUTATION_TABLE_BLOCK_ENTITY = BlockEntityType.Builder.create(TransmutationBlockEntity::new, RegisterBlocks.TRANSMUTATION_TABLE).build(null);

    @Override
    public Registry<BlockEntityType<?>> getRegistry() {
        return Registries.BLOCK_ENTITY_TYPE;
    }

    @Override
    @SuppressWarnings("unchecked")
    public Class<BlockEntityType<?>> getTargetFieldType() {
        return (Class<BlockEntityType<?>>) (Object) BlockEntityType.class;
    }
}
