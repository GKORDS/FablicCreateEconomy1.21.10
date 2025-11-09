package com.fablic.createeconomy.economy;

import com.fablic.createeconomy.registry.ModItems;
import net.minecraft.item.ItemStack;
import net.minecraft.server.network.ServerPlayerEntity;

public final class TradingLogic {
    private TradingLogic() {
    }

    public static long sellInventory(ServerPlayerEntity player) {
        long total = 0;
        var inventory = player.getInventory();

        for (int i = 0; i < inventory.main.size(); i++) {
            ItemStack stack = inventory.main.get(i);
            if (stack.isEmpty() || stack.isOf(ModItems.TRADING_STICK)) {
                continue;
            }

            long price = PriceTable.getPrice(stack);
            if (price > 0) {
                total += price;
                inventory.main.set(i, ItemStack.EMPTY);
            }
        }

        if (total > 0) {
            inventory.markDirty();
            player.currentScreenHandler.sendContentUpdates();
        }

        return total;
    }
}
