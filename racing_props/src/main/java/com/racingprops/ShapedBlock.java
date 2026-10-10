package com.racingprops;

import com.mojang.serialization.MapCodec;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;

/** Bloc simple avec une forme personnalisée (cône, vibreur, ligne d'arrivée). */
public class ShapedBlock extends Block {
    public static final MapCodec<ShapedBlock> CODEC = simpleCodec(p -> new ShapedBlock(p, Shapes.block()));
    private final VoxelShape shape;

    public ShapedBlock(Properties props, VoxelShape shape) {
        super(props);
        this.shape = shape;
    }

    @Override
    protected MapCodec<? extends Block> codec() { return CODEC; }

    @Override
    protected VoxelShape getShape(BlockState s, BlockGetter l, BlockPos p, CollisionContext c) { return shape; }
}
