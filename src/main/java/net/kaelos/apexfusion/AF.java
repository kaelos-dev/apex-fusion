package net.kaelos.apexfusion;

import org.slf4j.Logger;

import com.mojang.logging.LogUtils;

import net.kaelos.apexfusion.block.AFBlocks;
import net.kaelos.apexfusion.block.entity.AFBlockEntities;
import net.kaelos.apexfusion.block.entity.multiblocks.CokeOvenBlockEntity;
import net.kaelos.apexfusion.block.model.CokeOvenBlockModel;
import net.kaelos.apexfusion.block.render.MultiblockRenderer;
import net.kaelos.apexfusion.item.AFCreativeModeTabs;
import net.kaelos.apexfusion.item.AFItems;
import net.kaelos.apexfusion.multiblock.MultiblockManager;
import net.kaelos.apexfusion.util.lists.MultiblocksList;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.EntityRenderersEvent;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.AddReloadListenerEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

@Mod(AF.MOD_ID)
public class AF {
    public static final String MOD_ID = "apexfusion";

    public static final Logger LOGGER = LogUtils.getLogger();

    public AF(final FMLJavaModLoadingContext context) {
        IEventBus eventBus = context.getModEventBus();
        MinecraftForge.EVENT_BUS.register(this);

        AFBlocks.register(eventBus);
        AFItems.register(eventBus);
        AFCreativeModeTabs.register(eventBus);
        AFBlockEntities.register(eventBus);

        MinecraftForge.EVENT_BUS.addListener(this::onAddReloadListeners);
    }

    private void onAddReloadListeners(AddReloadListenerEvent event) {
        event.addListener(new MultiblockManager());
    }

    @Mod.EventBusSubscriber(modid = MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
    public static class ClientModEvents {
        @SuppressWarnings("unchecked")
        @SubscribeEvent
        public static void registerRenderers(EntityRenderersEvent.RegisterRenderers event) {
            event.registerBlockEntityRenderer((BlockEntityType<CokeOvenBlockEntity>) AFBlockEntities.MULTIBLOCKS_ENTITIES.get(MultiblocksList.COKE_OVEN).get(), context -> new MultiblockRenderer<>(new CokeOvenBlockModel(), 1D));
        }
    }
}
