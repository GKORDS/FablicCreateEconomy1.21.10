package com.fablic.createeconomy.item;

import com.fablic.createeconomy.economy.PlayerBalanceManager;
import com.fablic.createeconomy.screen.TradingScreenHandler;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.screen.SimpleNamedScreenHandlerFactory;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.text.Text;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.world.World;

public class TradingStickItem extends Item {
    public TradingStickItem(Settings settings) {
        super(settings);
    }

    @Override
    public ActionResult use(World world, PlayerEntity user, Hand hand) {
        if (world.isClient()) {
            return ActionResult.SUCCESS;
        }

        if (user instanceof ServerPlayerEntity serverPlayer) {
            serverPlayer.openHandledScreen(new SimpleNamedScreenHandlerFactory(
                (syncId, inventory, player) -> new TradingScreenHandler(syncId, inventory),
                Text.translatable("gui.createeconomy.trading")
            ));
            PlayerBalanceManager.syncBalance(serverPlayer);
        }

        return ActionResult.CONSUME;
    }
}
