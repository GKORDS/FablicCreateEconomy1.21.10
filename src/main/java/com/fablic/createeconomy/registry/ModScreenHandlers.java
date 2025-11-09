package com.fablic.createeconomy.registry;

import com.fablic.createeconomy.MoneyMod;
import com.fablic.createeconomy.screen.TradingScreenHandler;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.resource.featuretoggle.FeatureFlags;
import net.minecraft.screen.ScreenHandlerType;

public final class ModScreenHandlers {
    public static final ScreenHandlerType<TradingScreenHandler> TRADING_SCREEN_HANDLER = Registry.register(
        Registries.SCREEN_HANDLER,
        MoneyMod.id("trading"),
        new ScreenHandlerType<>(TradingScreenHandler::new, FeatureFlags.VANILLA_FEATURES)
    );

    private ModScreenHandlers() {
    }

    public static void register() {
        // static initialiser performs registration
    }
}
