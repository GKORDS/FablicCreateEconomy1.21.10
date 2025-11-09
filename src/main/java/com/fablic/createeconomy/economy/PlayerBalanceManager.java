package com.fablic.createeconomy.economy;

import com.fablic.createeconomy.network.ModNetwork;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.server.world.ServerWorld;

public final class PlayerBalanceManager {
    private PlayerBalanceManager() {
    }

    public static void onServerStarted(MinecraftServer server) {
        // Ensure the persistent state is created early so the first join is quick.
        PlayerBalanceState.get(server.getOverworld());
    }

    public static long getBalance(ServerPlayerEntity player) {
        PlayerBalanceState state = getState(player);
        return state.getBalance(player.getUuid());
    }

    public static void setBalance(ServerPlayerEntity player, long amount) {
        PlayerBalanceState state = getState(player);
        state.setBalance(player.getUuid(), Math.max(0, amount));
        syncBalance(player);
    }

    public static void addBalance(ServerPlayerEntity player, long delta) {
        PlayerBalanceState state = getState(player);
        long newAmount = Math.max(0, state.getBalance(player.getUuid()) + delta);
        state.setBalance(player.getUuid(), newAmount);
        syncBalance(player);
    }

    public static void syncBalance(ServerPlayerEntity player) {
        ModNetwork.sendBalance(player, getBalance(player));
    }

    private static PlayerBalanceState getState(ServerPlayerEntity player) {
        ServerWorld world = player.getServerWorld();
        return PlayerBalanceState.get(world.getServer().getOverworld());
    }
}
