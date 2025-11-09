package com.fablic.createeconomy;

import com.fablic.createeconomy.client.gui.TradingScreen;
import com.fablic.createeconomy.network.ModNetworkClient;
import com.fablic.createeconomy.registry.ModScreenHandlers;
import net.fabricmc.api.ClientModInitializer;
import net.minecraft.client.gui.screen.ingame.HandledScreens;

public class MoneyModClient implements ClientModInitializer {
    private static long clientBalance;

    @Override
    public void onInitializeClient() {
        ModNetworkClient.register();
        HandledScreens.register(ModScreenHandlers.TRADING_SCREEN_HANDLER, TradingScreen::new);
    }

    public static void setClientBalance(long balance) {
        clientBalance = balance;
    }

    public static long getClientBalance() {
        return clientBalance;
    }
}
