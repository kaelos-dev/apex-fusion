package net.kaelos.apexfusion.block.multiblocks;

import net.kaelos.apexfusion.util.IMultiblocksTileEntity;
import net.kaelos.apexfusion.util.lists.MultiblocksList;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.EntityBlock;
import net.minecraft.world.level.block.RenderShape;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition.Builder;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.block.state.properties.DirectionProperty;

public class CoreBlock extends Block implements EntityBlock {
    private final MultiblocksList data;

    public static final BooleanProperty IS_ASSEMBLED = BooleanProperty.create("is_assembled");
    public static final DirectionProperty FACING = BlockStateProperties.HORIZONTAL_FACING;
    
    public CoreBlock(MultiblocksList data) {
        super(Properties.of());
        this.data = data;

        this.registerDefaultState(this.defaultBlockState()
                .setValue(IS_ASSEMBLED, false)
                .setValue(FACING, Direction.NORTH));
    }

    @Override
    protected void createBlockStateDefinition(Builder<Block, BlockState> builder) {
        super.createBlockStateDefinition(builder);
        builder.add(IS_ASSEMBLED).add(FACING);
    }

    @Override
    public BlockEntity newBlockEntity(BlockPos pos, BlockState state) {
        return data.getEntityFactory().create(pos, state);
    }

    @Override
    protected void onRemove(BlockState state, Level level, BlockPos pos, BlockState newState, boolean isMoving) {
        if (!state.is(newState.getBlock())) {
            if (level.getBlockEntity(pos) instanceof IMultiblocksTileEntity coreEntity) {
                if (state.getValue(IS_ASSEMBLED)) {
                    coreEntity.structuralDropper(pos, level);
                }
            }
        }

        super.onRemove(state, level, pos, newState, isMoving);
    }

    @Override
    protected RenderShape getRenderShape(BlockState state) {
        return state.getValue(IS_ASSEMBLED) ? RenderShape.ENTITYBLOCK_ANIMATED : RenderShape.MODEL;
    }
}
