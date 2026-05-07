package net.kaelos.apexfusion.datagen;

import net.kaelos.apexfusion.AF;
import net.minecraft.data.PackOutput;
import net.minecraftforge.client.model.generators.ItemModelProvider;
import net.minecraftforge.common.data.ExistingFileHelper;

public class AFItemModelProvider extends ItemModelProvider {
    public AFItemModelProvider(PackOutput output, ExistingFileHelper exFileHelper) {
        super(output, AF.MOD_ID, exFileHelper);
    }

    @Override
    protected void registerModels() {}
}
