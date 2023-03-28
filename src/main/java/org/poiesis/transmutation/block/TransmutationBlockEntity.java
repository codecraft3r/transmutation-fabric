package org.poiesis.transmutation.block;

import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.screen.NamedScreenHandlerFactory;
import net.minecraft.screen.ScreenHandler;
import net.minecraft.text.Text;
import net.minecraft.util.math.BlockPos;
import org.poiesis.transmutation.ui.TransmutationScreenHandler;

public class TransmutationBlockEntity extends BlockEntity implements NamedScreenHandlerFactory {
    public TransmutationBlockEntity(BlockPos pos, BlockState state) {
        super(RegisterBlockEntities.TRANSMUTATION_TABLE_BLOCK_ENTITY, pos, state);
    }
    @Override
    public ScreenHandler createMenu(int syncId, PlayerInventory playerInventory, PlayerEntity player) {
        return new TransmutationScreenHandler(syncId, playerInventory);
    }

    @Override
    public Text getDisplayName() {
        return Text.translatable(getCachedState().getBlock().getTranslationKey());
    }
}
