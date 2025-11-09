package com.fablic.createeconomy.client.gui;

import com.fablic.createeconomy.CreateEconomyModClient;
import com.fablic.createeconomy.network.ModNetworkClient;
import com.fablic.createeconomy.screen.TradingScreenHandler;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.screen.ingame.HandledScreen;
import net.minecraft.client.gui.widget.ButtonWidget;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.text.Text;

public class TradingScreen extends HandledScreen<TradingScreenHandler> {
    private ButtonWidget sellButton;

    public TradingScreen(TradingScreenHandler handler, PlayerInventory inventory, Text title) {
        super(handler, inventory, title);
        this.backgroundWidth = 176;
        this.backgroundHeight = 166;
    }

    @Override
    protected void init() {
        super.init();
        int buttonWidth = 150;
        int buttonHeight = 20;
        int buttonX = this.x + (this.backgroundWidth - buttonWidth) / 2;
        int buttonY = this.y + 20;

        this.sellButton = ButtonWidget.builder(Text.translatable("gui.createeconomy.sell_all"), button ->
            ModNetworkClient.sendSellAllRequest()
        ).dimensions(buttonX, buttonY, buttonWidth, buttonHeight).build();

        addDrawableChild(this.sellButton);
    }

    @Override
    protected void drawBackground(DrawContext context, float delta, int mouseX, int mouseY) {
        context.fill(this.x, this.y, this.x + this.backgroundWidth, this.y + this.backgroundHeight, 0xFF101010);
    }

    @Override
    public void render(DrawContext context, int mouseX, int mouseY, float delta) {
        renderBackground(context, mouseX, mouseY, delta);
        super.render(context, mouseX, mouseY, delta);
        drawBalance(context);
        drawMouseoverTooltip(context, mouseX, mouseY);
    }

    private void drawBalance(DrawContext context) {
        long balance = CreateEconomyModClient.getCachedBalance();
        Text text = Text.translatable("gui.createeconomy.balance", balance);
        context.drawText(this.textRenderer, text, this.x + 12, this.y + 50, 0xFFFFFF, false);
    }
}
