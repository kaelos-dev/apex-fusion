package net.kaelos.apexfusion;

import org.slf4j.Logger;

import com.mojang.logging.LogUtils;

import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

@Mod(AF.MOD_ID)
public class AF {
    public static final String MOD_ID = "apexfusion";

    public static final Logger LOGGER = LogUtils.getLogger();

    public AF(final FMLJavaModLoadingContext context) {
        IEventBus eventBus = context.getModEventBus();
        MinecraftForge.EVENT_BUS.register(this);
    }
}
