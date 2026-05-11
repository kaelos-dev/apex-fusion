package net.kaelos.apexfusion.block.multiblocks;

import net.kaelos.apexfusion.item.AFItems;
import net.kaelos.apexfusion.util.IMultiblocksTileEntity;
import net.minecraft.core.BlockPos;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.ItemInteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;

public class ConstructionBlock extends Block {
    private int searchRadius;
    
    public ConstructionBlock(int searchRadius) {
        super(Properties.of());
        this.searchRadius = searchRadius;
    }

    @Override
    protected ItemInteractionResult useItemOn(ItemStack stack, BlockState state, Level level, BlockPos pos, Player player, InteractionHand hand, BlockHitResult hitResult) {
        if (!level.isClientSide) {
            if (player != null) {
                ItemStack itemInHand = player.getItemInHand(InteractionHand.MAIN_HAND);
                if (itemInHand.is(AFItems.INSTALLATION_WRENCH.get()) && hasFindCore(pos, level, player)) return ItemInteractionResult.SUCCESS;
            }
        }

        return ItemInteractionResult.PASS_TO_DEFAULT_BLOCK_INTERACTION;
    }

    private boolean hasFindCore(BlockPos pos, Level level, Player player) {
        int search = this.searchRadius;
        for (BlockPos checkPos : BlockPos.betweenClosed(pos.offset(-search, -search, -search), pos.offset(search, search, search))) {
            if (level.getBlockEntity(checkPos) instanceof IMultiblocksTileEntity coreEntity) {
                coreEntity.assemble(player);
                return true;
            }
        }

        return false;
    }
}
