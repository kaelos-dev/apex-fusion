package net.kaelos.apexfusion.util;

import net.kaelos.apexfusion.block.AFBlocks;
import net.kaelos.apexfusion.block.entity.custom.ProxyBlockEntity;
import net.kaelos.apexfusion.block.multiblocks.CoreBlock;
import net.kaelos.apexfusion.multiblock.MultiblockTemplate;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;

public interface IMultiblocksTileEntity {
    MultiblockTemplate getTemplate();
    BlockPos getCorePos();
    Level getLevel();

    default void assemble(Player player) {
        MultiblockTemplate template = getTemplate();
        BlockPos corePos = getCorePos();
        int[] offset = template.getCoreOffset();
        Level level = getLevel();

        if (isStructureComplete(template, corePos, offset, level)) {
            structuralReplacement(template, corePos, offset, level, player);
        }
    }

    default boolean isStructureComplete(MultiblockTemplate template, BlockPos corePos, int[] coreOffset, Level level) {
        for (int y = 0; y < template.getPattern().length; y++) {
            String[] layer = template.getPattern()[y];
            for (int z = 0; z < layer.length; z++) {
                String row = layer[z];
                for (int x = 0; x < row.length(); x++) {
                    char symbol = row.charAt(x);
                    if (symbol == ' ') continue;

                    BlockPos targetPos = corePos.offset(x - coreOffset[0], y - coreOffset[1], z - coreOffset[2]);

                    String blockId = template.getDictionary().get(symbol);
                    Block block = BuiltInRegistries.BLOCK.get(ResourceLocation.parse(blockId));

                    Block actualBlock = level.getBlockState(targetPos).getBlock();

                    if (actualBlock != block) 
                        return false;
                }
            }
        }

        return true;
    }
    
    default void structuralReplacement(MultiblockTemplate template, BlockPos corePos, int[] coreOffset, Level level, Player player) {
        for (int y = 0; y < template.getPattern().length; y++) {
            String[] layer = template.getPattern()[y];
            for (int z = 0; z < layer.length; z++) {
                String row = layer[z];
                for (int x = 0; x < row.length(); x++) {
                    char symbol = row.charAt(x);
                    if (symbol == ' ') continue;

                    BlockPos targetPos = corePos.offset(x - coreOffset[0], y - coreOffset[1], z - coreOffset[2]);

                    if (!targetPos.equals(corePos)) {
                        level.setBlock(targetPos, AFBlocks.PROXY.get().defaultBlockState(), 3);
                        if (level.getBlockEntity(targetPos) instanceof ProxyBlockEntity proxy) proxy.setCorePos(corePos);
                    }

                    BlockState coreState = level.getBlockState(corePos);
                    Direction facing = player.getDirection().getOpposite();
                    if (coreState.hasProperty(CoreBlock.IS_ASSEMBLED)) {
                        level.setBlock(corePos, coreState.setValue(CoreBlock.IS_ASSEMBLED, true).setValue(CoreBlock.FACING, facing), 3);
                    }
                }
            }
        }
    }

    default void structuralDestruction() {
        MultiblockTemplate template = getTemplate();
        BlockPos corePos = getCorePos();
        int[] coreOffset = template.getCoreOffset();
        Level level = getLevel();

        for (int y = 0; y < template.getPattern().length; y++) {
            String[] layer = template.getPattern()[y];
            for (int z = 0; z < layer.length; z++) {
                String row = layer[z];
                for (int x = 0; x < row.length(); x++) {
                    char symbol = row.charAt(x);
                    if (symbol == ' ') continue;

                    BlockPos targetPos = corePos.offset(x - coreOffset[0], y - coreOffset[1], z - coreOffset[2]);

                    level.destroyBlock(targetPos, false);
                }
            }
        }
    }

    default void structuralDropper(BlockPos pos, Level level) {
        MultiblockTemplate template = getTemplate();

        for (int y = 0; y < template.getPattern().length; y++) {
            String[] layer = template.getPattern()[y];
            for (int z = 0; z < layer.length; z++) {
                String row = layer[z];
                for (int x = 0; x < row.length(); x++) {
                    char symbol = row.charAt(x);
                    if (symbol == ' ' || symbol == 'C') continue;

                    String blockId = template.getDictionary().get(symbol);
                    Block block = BuiltInRegistries.BLOCK.get(ResourceLocation.parse(blockId));

                    ItemStack stack = new ItemStack(block);
                    ItemEntity itemEntity = new ItemEntity(
                        level,
                        pos.getX() + 0.5,
                        pos.getY() + 0.5,
                        pos.getZ() + 0.5,
                        stack
                    );

                    level.addFreshEntity(itemEntity);
                }
            }
        }
    }
}
