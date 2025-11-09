package com.fablic.createeconomy.economy;

import com.fablic.createeconomy.CreateEconomyMod;
import com.fablic.createeconomy.network.ModNetwork;
import com.fablic.createeconomy.registry.ModItems;
import net.fabricmc.fabric.api.networking.v1.ServerPlayConnectionEvents;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.scoreboard.Scoreboard;
import net.minecraft.scoreboard.ScoreboardCriterion;
import net.minecraft.scoreboard.ScoreboardObjective;
import net.minecraft.scoreboard.number.BlankNumberFormat;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.text.Text;
import net.minecraft.util.math.MathHelper;

public final class PlayerBalanceManager {
    private static final String OBJECTIVE_NAME = CreateEconomyMod.MOD_ID + "_balance";
    private static final Text OBJECTIVE_DISPLAY_NAME = Text.translatable("scoreboard." + CreateEconomyMod.MOD_ID + ".balance");

    private PlayerBalanceManager() {}

    public static void register() {
        ServerPlayConnectionEvents.JOIN.register((handler, sender, server) -> {
            ensureObjective(server.getScoreboard());
            syncBalance(handler.player);
        });
    }

    public static long getBalance(ServerPlayerEntity player) {
        Scoreboard scoreboard = player.getEntityWorld().getScoreboard();
        ScoreboardObjective objective = ensureObjective(scoreboard);
        return scoreboard.getOrCreateScore(player, objective).getScore();
    }

    public static void setBalance(ServerPlayerEntity player, long amount) {
        Scoreboard scoreboard = player.getEntityWorld().getScoreboard();
        ScoreboardObjective objective = ensureObjective(scoreboard);
        int clamped = MathHelper.clamp((int) amount, 0, Integer.MAX_VALUE);
        scoreboard.getOrCreateScore(player, objective).setScore(clamped);
        syncBalance(player);
    }

    public static void addBalance(ServerPlayerEntity player, long delta) {
        long current = getBalance(player);
        setBalance(player, current + delta);
    }

    public static void syncBalance(ServerPlayerEntity player) {
        ModNetwork.sendBalance(player, getBalance(player));
    }

    public static long sellInventory(ServerPlayerEntity player) {
        PlayerInventory inventory = player.getInventory();
        long total = 0;
        for (int slot = 0; slot < PlayerInventory.MAIN_SIZE; slot++) {
            ItemStack stack = inventory.getStack(slot);
            if (stack.isEmpty() || stack.isOf(ModItems.TRADING_STICK)) {
                continue;
            }
            long price = PriceTable.price(stack);
            if (price > 0) {
                total += price;
                inventory.setStack(slot, ItemStack.EMPTY);
            }
        }

        if (total > 0) {
            addBalance(player, total);
            inventory.markDirty();
            player.currentScreenHandler.sendContentUpdates();
            player.sendMessage(Text.translatable("message.createeconomy.sold", total), true);
        }

        return total;
    }

    private static ScoreboardObjective ensureObjective(Scoreboard scoreboard) {
        ScoreboardObjective objective = scoreboard.getNullableObjective(OBJECTIVE_NAME);
        if (objective == null) {
            objective = scoreboard.addObjective(
                OBJECTIVE_NAME,
                ScoreboardCriterion.DUMMY,
                OBJECTIVE_DISPLAY_NAME,
                ScoreboardCriterion.RenderType.INTEGER,
                false,
                BlankNumberFormat.INSTANCE
            );
        }
        return objective;
    }
}
