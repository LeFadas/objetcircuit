package com.racingprops;

import com.mojang.serialization.MapCodec;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.HorizontalDirectionalBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;

/** Glissière béton orientable (se place dans le sens où le joueur regarde). */
public class ConcreteBarrierBlock extends HorizontalDirectionalBlock {
    public static final MapCodec<ConcreteBarrierBlock> CODEC = simpleCodec(ConcreteBarrierBlock::new);
    private static final VoxelShape SHAPE_X = Block.box(0, 0, 4, 16, 16, 12);
    private static final VoxelShape SHAPE_Z = Block.box(4, 0, 0, 12, 16, 16);

    public ConcreteBarrierBlock(Properties props) {
        super(props);
        registerDefaultState(stateDefinition.any().setValue(FACING, Direction.NORTH));
    }

    @Override
    protected MapCodec<? extends HorizontalDirectionalBlock> codec() { return CODEC; }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> b) { b.add(FACING); }

    @Override
    public BlockState getStateForPlacement(BlockPlaceContext ctx) {
        return defaultBlockState().setValue(FACING, ctx.getHorizontalDirection());
    }

    @Override
    protected VoxelShape getShape(BlockState s, BlockGetter l, BlockPos p, CollisionContext c) {
        return s.getValue(FACING).getAxis() == Direction.Axis.X ? SHAPE_Z : SHAPE_X;
    }
}
