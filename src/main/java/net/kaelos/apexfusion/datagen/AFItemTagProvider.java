package net.kaelos.apexfusion.datagen;

import java.util.concurrent.CompletableFuture;

import net.kaelos.apexfusion.AF;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.HolderLookup.Provider;
import net.minecraft.data.PackOutput;
import net.minecraft.data.tags.ItemTagsProvider;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.common.data.ExistingFileHelper;

public class AFItemTagProvider extends ItemTagsProvider {
    public AFItemTagProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> lookupProvider, CompletableFuture<TagLookup<Block>> tagLookupProvider, ExistingFileHelper exFileHelper) {
        super(output, lookupProvider, tagLookupProvider, AF.MOD_ID, exFileHelper);
    }

    @Override
    protected void addTags(Provider provider) {}
}
