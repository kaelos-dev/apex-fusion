package net.kaelos.apexfusion.block.render;

import com.mojang.blaze3d.vertex.PoseStack;

import net.kaelos.apexfusion.block.entity.base.MultiblockBlockEntity;
import net.kaelos.apexfusion.block.multiblocks.CoreBlock;
import net.minecraft.client.renderer.LevelRenderer;
import net.minecraft.client.renderer.MultiBufferSource;
import software.bernie.geckolib.model.GeoModel;
import software.bernie.geckolib.renderer.GeoBlockRenderer;

public class MultiblockRenderer<T extends MultiblockBlockEntity> extends GeoBlockRenderer<T> {
    private final double blockDisplacement;
    
    public MultiblockRenderer(GeoModel<T> model, double blockDisplacement) {
        super(model);
        this.blockDisplacement = blockDisplacement;
    }

    @Override
    public void render(T animatable, float partialTick, PoseStack poseStack, MultiBufferSource bufferSource, int packedLight, int packedOverlay) {
        if (animatable.getBlockState().getValue(CoreBlock.IS_ASSEMBLED)) {
            poseStack.pushPose();
            poseStack.translate(0, -this.blockDisplacement, 0);

            int lightAtTop = LevelRenderer.getLightColor(animatable.getLevel(), animatable.getBlockPos().above((int) this.blockDisplacement));
            super.render(animatable, partialTick, poseStack, bufferSource, lightAtTop, packedOverlay);

            poseStack.popPose();
        }
    }
}
