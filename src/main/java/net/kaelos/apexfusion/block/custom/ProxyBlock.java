package net.kaelos.apexfusion.block.custom;

import net.kaelos.apexfusion.block.entity.custom.ProxyBlockEntity;
import net.kaelos.apexfusion.util.IMultiblocksTileEntity;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.EntityBlock;
import net.minecraft.world.level.block.RenderShape;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.PushReaction;

public class ProxyBlock extends Block implements EntityBlock {
    public ProxyBlock() {
        super(Properties.of()
            .noLootTable()
            .noOcclusion()
            .strength(2.0F)
            .pushReaction(PushReaction.IGNORE)
            .sound(SoundType.STONE)
        );
    }

    @Override
    public BlockEntity newBlockEntity(BlockPos pos, BlockState state) {
        return new ProxyBlockEntity(pos, state);
    }

    @Override
    protected RenderShape getRenderShape(BlockState state) {
        return RenderShape.INVISIBLE;
    }

    @Override
    protected void onRemove(BlockState state, Level level, BlockPos pos, BlockState newState, boolean isMoving) {
        if (!state.is(newState.getBlock())) {
            if (level.getBlockEntity(pos) instanceof ProxyBlockEntity proxy) {
                BlockPos corePos = proxy.getCorePos();

                if (level.getBlockEntity(corePos) instanceof IMultiblocksTileEntity core) {
                    core.structuralDestruction();
                }
            }
        }

        super.onRemove(state, level, pos, newState, isMoving);
    }
}
