package com.fablic.createeconomy.economy;

import com.fablic.createeconomy.MoneyMod;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import net.minecraft.datafixer.DataFixTypes;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.nbt.NbtElement;
import net.minecraft.nbt.NbtList;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.world.PersistentState;

public class PlayerBalanceState extends PersistentState {
    private static final String LIST_KEY = "Balances";
    private static final String UUID_KEY = "Uuid";
    private static final String BALANCE_KEY = "Balance";
    private static final String DATA_ID = MoneyMod.MOD_ID + "_balances";

    public static final PersistentState.Type<PlayerBalanceState> TYPE = new PersistentState.Type<>(
        PlayerBalanceState::new,
        PlayerBalanceState::fromNbt,
        DataFixTypes.LEVEL
    );

    private final Map<UUID, Long> balances = new HashMap<>();

    public long getBalance(UUID uuid) {
        return balances.getOrDefault(uuid, 0L);
    }

    public void setBalance(UUID uuid, long amount) {
        balances.put(uuid, amount);
        markDirty();
    }

    public void addBalance(UUID uuid, long delta) {
        if (delta == 0) {
            return;
        }
        long newAmount = getBalance(uuid) + delta;
        balances.put(uuid, newAmount);
        markDirty();
    }

    @Override
    public NbtCompound writeNbt(NbtCompound nbt, RegistryWrapper.WrapperLookup registryLookup) {
        NbtList list = new NbtList();
        for (Map.Entry<UUID, Long> entry : balances.entrySet()) {
            NbtCompound entryNbt = new NbtCompound();
            entryNbt.putUuid(UUID_KEY, entry.getKey());
            entryNbt.putLong(BALANCE_KEY, entry.getValue());
            list.add(entryNbt);
        }
        nbt.put(LIST_KEY, list);
        return nbt;
    }

    public static PlayerBalanceState fromNbt(NbtCompound nbt, RegistryWrapper.WrapperLookup registryLookup) {
        PlayerBalanceState state = new PlayerBalanceState();
        NbtList list = nbt.getList(LIST_KEY, NbtElement.COMPOUND_TYPE);
        for (int i = 0; i < list.size(); i++) {
            NbtCompound entryNbt = list.getCompound(i);
            UUID uuid = entryNbt.getUuid(UUID_KEY);
            long balance = entryNbt.getLong(BALANCE_KEY);
            state.balances.put(uuid, balance);
        }
        return state;
    }

    public static PlayerBalanceState get(ServerWorld world) {
        return world.getPersistentStateManager().getOrCreate(TYPE, DATA_ID);
    }
}
