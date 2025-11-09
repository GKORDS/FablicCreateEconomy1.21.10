package com.fablic.createeconomy.economy;

import com.fablic.createeconomy.registry.ModItems;
import it.unimi.dsi.fastutil.objects.Object2IntMap;
import it.unimi.dsi.fastutil.objects.Object2IntOpenHashMap;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;

public final class PriceTable {
    private static final Object2IntMap<Item> PRICES = new Object2IntOpenHashMap<>();

    static {
        PRICES.put(Items.DIAMOND, 250);
        PRICES.put(Items.GOLD_INGOT, 100);
        PRICES.put(Items.IRON_INGOT, 50);
        PRICES.put(Items.EMERALD, 200);
        PRICES.put(Items.NETHERITE_SCRAP, 500);
        PRICES.put(ModItems.COIN, 1);
    }

    private PriceTable() {
    }

    public static long getPrice(ItemStack stack) {
        if (stack.isEmpty()) {
            return 0;
        }
        int pricePerItem = PRICES.getOrDefault(stack.getItem(), 0);
        if (pricePerItem <= 0) {
            return 0;
        }
        return (long) pricePerItem * stack.getCount();
    }
}
