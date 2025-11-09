package com.fablic.createeconomy.screen;

import com.fablic.createeconomy.registry.ModScreenHandlers;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.screen.ScreenHandler;
import net.minecraft.screen.slot.Slot;

public class TradingScreenHandler extends ScreenHandler {
    public TradingScreenHandler(int syncId, PlayerInventory playerInventory) {
        super(ModScreenHandlers.TRADING_SCREEN_HANDLER, syncId);
        addPlayerInventory(playerInventory, 8, 84);
        addPlayerHotbar(playerInventory, 8, 142);
    }

    private void addPlayerInventory(PlayerInventory inventory, int left, int top) {
        for (int row = 0; row < 3; ++row) {
            for (int column = 0; column < 9; ++column) {
                int index = column + row * 9 + 9;
                this.addSlot(new Slot(inventory, index, left + column * 18, top + row * 18));
            }
        }
    }

    private void addPlayerHotbar(PlayerInventory inventory, int left, int top) {
        for (int slot = 0; slot < 9; ++slot) {
            this.addSlot(new Slot(inventory, slot, left + slot * 18, top));
        }
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
