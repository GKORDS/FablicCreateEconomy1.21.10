package com.fablic.createeconomy.screen;

import com.fablic.createeconomy.registry.ModScreenHandlers;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.screen.ScreenHandler;

public class TradingScreenHandler extends ScreenHandler {
    private final PlayerInventory playerInventory;

    public TradingScreenHandler(int syncId, PlayerInventory playerInventory) {
        super(ModScreenHandlers.TRADING_SCREEN_HANDLER, syncId);
        this.playerInventory = playerInventory;
    }

    public PlayerInventory getPlayerInventory() {
        return playerInventory;
    }

    @Override
    public boolean canUse(PlayerEntity player) {
        return true;
    }

    @Override
    public ItemStack quickMove(PlayerEntity player, int index) {
        return ItemStack.EMPTY;
    }
}
