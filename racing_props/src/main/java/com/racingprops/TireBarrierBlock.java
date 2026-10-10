package com.racingprops;

import com.mojang.serialization.MapCodec;
import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;

/** Barrière de pneus : amortit les chutes et les chocs (dégâts de chute réduits de 80 %). */
public class TireBarrierBlock extends Block {
    public static final MapCodec<TireBarrierBlock> CODEC = simpleCodec(TireBarrierBlock::new);

    public TireBarrierBlock(Properties props) { super(props); }

    @Override
    protected MapCodec<? extends Block> codec() { return CODEC; }

    @Override
    public void fallOn(Level level, BlockState state, BlockPos pos, Entity entity, float fallDistance) {
        entity.causeFallDamage(fallDistance, 0.2F, level.damageSources().fall());
    }
}
