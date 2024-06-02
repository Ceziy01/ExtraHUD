package me.ceziy.extrahud.extrahud;

import net.fabricmc.api.ModInitializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ExtraHUD implements ModInitializer {
    public static final String MOD_ID = "extrahud";
    public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

    @Override
    public void onInitialize() {
        LOGGER.info("Initializing mod " + MOD_ID);
    }
}