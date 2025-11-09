package com.fablic.createeconomy.registry;

import com.fablic.createeconomy.CreateEconomyMod;
import com.fablic.createeconomy.item.TradingStickItem;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroups;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public final class ModItems {
    public static final Item TRADING_STICK = register("trading_stick", new TradingStickItem(new Item.Settings().maxCount(1)));
    public static final Item COIN = register("coin", new Item(new Item.Settings()));

    private ModItems() {}

    public static void register() {
        ItemGroupEvents.modifyEntriesEvent(ItemGroups.TOOLS).register(entries -> entries.add(TRADING_STICK));
        ItemGroupEvents.modifyEntriesEvent(ItemGroups.INGREDIENTS).register(entries -> entries.add(COIN));
    }

    private static Item register(String name, Item item) {
        return Registry.register(Registries.ITEM, Identifier.of(CreateEconomyMod.MOD_ID, name), item);
    }
}
