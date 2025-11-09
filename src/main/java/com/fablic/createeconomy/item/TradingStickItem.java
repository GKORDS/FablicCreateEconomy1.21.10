package com.fablic.createeconomy.item;

import com.fablic.createeconomy.MoneyMod;
import com.fablic.createeconomy.economy.PlayerBalanceManager;
import com.fablic.createeconomy.screen.TradingScreenHandler;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.screen.NamedScreenHandlerFactory;
import net.minecraft.screen.SimpleNamedScreenHandlerFactory;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.text.Text;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.world.World;

public class TradingStickItem extends Item {
    private static final Text TITLE = Text.translatable("item." + MoneyMod.MOD_ID + ".trading_stick");

    public TradingStickItem(Settings settings) {
        super(settings);
    }

    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand) {
        if (!world.isClient && user instanceof ServerPlayerEntity serverPlayer) {
            NamedScreenHandlerFactory factory = new SimpleNamedScreenHandlerFactory(
                (syncId, playerInventory, player) -> new TradingScreenHandler(syncId, playerInventory),
                TITLE
            );
            serverPlayer.openHandledScreen(factory);
            PlayerBalanceManager.syncBalance(serverPlayer);
        }
        return TypedActionResult.success(user.getStackInHand(hand), world.isClient);
    }
}
