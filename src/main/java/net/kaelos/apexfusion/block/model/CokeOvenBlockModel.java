package net.kaelos.apexfusion.block.model;

import net.kaelos.apexfusion.AF;
import net.kaelos.apexfusion.block.entity.multiblocks.CokeOvenBlockEntity;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.model.GeoModel;

public class CokeOvenBlockModel extends GeoModel<CokeOvenBlockEntity> {
    @Override
    public ResourceLocation getModelResource(CokeOvenBlockEntity animatable) {
        return ResourceLocation.fromNamespaceAndPath(AF.MOD_ID, "geo/coke_oven.geo.json");
    }

    @Override
    public ResourceLocation getTextureResource(CokeOvenBlockEntity animatable) {
        return ResourceLocation.fromNamespaceAndPath(AF.MOD_ID, "textures/multiblocks/coke_oven.png");
    }

    @Override
    public ResourceLocation getAnimationResource(CokeOvenBlockEntity animatable) {
        return null;
    }
}
