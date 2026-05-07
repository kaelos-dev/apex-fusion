package net.kaelos.apexfusion.datagen;

import java.util.concurrent.CompletableFuture;

import net.kaelos.apexfusion.AF;
import net.kaelos.apexfusion.block.AFBlocks;
import net.kaelos.apexfusion.util.lists.MultiblocksList;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.HolderLookup.Provider;
import net.minecraft.data.PackOutput;
import net.minecraft.tags.BlockTags;
import net.minecraftforge.common.data.BlockTagsProvider;
import net.minecraftforge.common.data.ExistingFileHelper;

public class AFBlockTagProvider extends BlockTagsProvider {
    public AFBlockTagProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> lookupProvider, ExistingFileHelper exFileHelper) {
        super(output, lookupProvider, AF.MOD_ID, exFileHelper);
    }

    @Override
    protected void addTags(Provider provider) {
        for (MultiblocksList lists : MultiblocksList.values()) {
            tag(BlockTags.MINEABLE_WITH_PICKAXE)
                    .add(AFBlocks.MULTI_CONSTRUCTIONS_BLOCKS.get(lists).get())
                    .add(AFBlocks.MULTI_CORE_BLOCKS.get(lists).get());

            tag(BlockTags.NEEDS_STONE_TOOL)
                    .add(AFBlocks.MULTI_CONSTRUCTIONS_BLOCKS.get(lists).get())
                    .add(AFBlocks.MULTI_CORE_BLOCKS.get(lists).get());
        }
    }
}
