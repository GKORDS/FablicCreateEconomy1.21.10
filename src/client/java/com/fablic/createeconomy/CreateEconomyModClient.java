package com.fablic.createeconomy;

import com.fablic.createeconomy.client.gui.TradingScreen;
import com.fablic.createeconomy.network.ModNetworkClient;
import com.fablic.createeconomy.registry.ModScreenHandlers;
import net.fabricmc.api.ClientModInitializer;
import net.minecraft.client.gui.screen.ingame.HandledScreens;

public class CreateEconomyModClient implements ClientModInitializer {
    private static long cachedBalance;

    public static long getCachedBalance() {
        return cachedBalance;
    }

    public static void setCachedBalance(long balance) {
        cachedBalance = balance;
    }

    @Override
    public void onInitializeClient() {
        HandledScreens.register(ModScreenHandlers.TRADING_SCREEN_HANDLER, TradingScreen::new);
        ModNetworkClient.register();
    }
}
