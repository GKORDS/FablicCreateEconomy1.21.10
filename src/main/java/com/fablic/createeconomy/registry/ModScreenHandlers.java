package com.fablic.createeconomy.registry;

import com.fablic.createeconomy.CreateEconomyMod;
import com.fablic.createeconomy.screen.TradingScreenHandler;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.resource.featuretoggle.FeatureFlags;
import net.minecraft.screen.ScreenHandlerType;
import net.minecraft.util.Identifier;

public final class ModScreenHandlers {
    public static final ScreenHandlerType<TradingScreenHandler> TRADING_SCREEN_HANDLER =
        Registry.register(Registries.SCREEN_HANDLER, Identifier.of(CreateEconomyMod.MOD_ID, "trading"),
            new ScreenHandlerType<>(TradingScreenHandler::new, FeatureFlags.DEFAULT_ENABLED_FEATURES));

    private ModScreenHandlers() {}

    public static void register() {
        // Trigger class loading for static fields
    }
}
