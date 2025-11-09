package com.fablic.createeconomy.client.gui;

import com.fablic.createeconomy.MoneyMod;
import com.fablic.createeconomy.MoneyModClient;
import com.fablic.createeconomy.network.payload.SellAllInventoryPayload;
import com.fablic.createeconomy.screen.TradingScreenHandler;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.screen.ingame.HandledScreen;
import net.minecraft.client.gui.widget.ButtonWidget;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.text.Text;

public class TradingScreen extends HandledScreen<TradingScreenHandler> {
    public TradingScreen(TradingScreenHandler handler, PlayerInventory inventory, Text title) {
        super(handler, inventory, title);
        this.backgroundWidth = 176;
        this.backgroundHeight = 166;
    }

    @Override
    protected void init() {
        super.init();
        this.titleX = (this.backgroundWidth - this.textRenderer.getWidth(this.title)) / 2;
        int buttonWidth = 120;
        int buttonHeight = 20;
        int x = (this.width - buttonWidth) / 2;
        int y = this.height / 2 + 10;
        this.addDrawableChild(ButtonWidget.builder(Text.translatable("gui." + MoneyMod.MOD_ID + ".sell_all"), button ->
            ClientPlayNetworking.send(new SellAllInventoryPayload())
        ).dimensions(x, y, buttonWidth, buttonHeight).build());
    }

    @Override
    public void render(DrawContext context, int mouseX, int mouseY, float delta) {
        this.renderBackground(context);
        super.render(context, mouseX, mouseY, delta);
        Text balanceText = Text.translatable("gui." + MoneyMod.MOD_ID + ".balance", MoneyModClient.getClientBalance());
        int balanceX = (this.width - this.textRenderer.getWidth(balanceText)) / 2;
        int balanceY = this.height / 2 - 40;
        context.drawText(this.textRenderer, balanceText, balanceX, balanceY, 0xFFFFFF, false);
        drawMouseoverTooltip(context, mouseX, mouseY);
    }

    @Override
    protected void drawBackground(DrawContext context, float delta, int mouseX, int mouseY) {
        int left = (this.width - this.backgroundWidth) / 2;
        int top = (this.height - this.backgroundHeight) / 2;
        context.fill(left, top, left + this.backgroundWidth, top + this.backgroundHeight, 0xFF202020);
        context.fill(left + 4, top + 4, left + this.backgroundWidth - 4, top + 24, 0xFF303030);
        context.fill(left + 4, top + 28, left + this.backgroundWidth - 4, top + this.backgroundHeight - 4, 0xFF1A1A1A);
    }
}
