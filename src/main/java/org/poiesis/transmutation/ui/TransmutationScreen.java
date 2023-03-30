package org.poiesis.transmutation.ui;

import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.screen.ingame.HandledScreen;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;

public class TransmutationScreen extends HandledScreen<TransmutationScreenHandler> {
    private static final Identifier TEXTURE = new Identifier("textures/gui/container/generic_54.png");

    public TransmutationScreen(TransmutationScreenHandler screenHandler, PlayerInventory playerInventory, Text title) {
        super(screenHandler, playerInventory, title);
        this.passEvents = false;
        this.backgroundWidth = 176;
        this.backgroundHeight = 222;
    }

    @Override
    protected void drawBackground(MatrixStack matrices, float delta, int mouseX, int mouseY) {
        this.client.getTextureManager().bindTexture(TEXTURE);
        int x = (this.width - this.backgroundWidth) / 2;
        int y = (this.height - this.backgroundHeight) / 2;
        this.drawTexture(matrices, x, y, 0, 0, this.backgroundWidth, this.backgroundHeight);
    }

}

