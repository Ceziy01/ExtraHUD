package me.ceziy.extrahud.extrahud.config;

import me.ceziy.extrahud.extrahud.ExtraHUD;
import com.mojang.datafixers.util.Pair;

public class ModConfigs {

    public static SimpleConfig CONFIG;
    private static ModConfigProvider configs;
    public static boolean SHOW_HUD = true;
    public static void registerConfigs() {
        configs = new ModConfigProvider();
        createConfigs();
        CONFIG = SimpleConfig.of(ExtraHUD.MOD_ID + "config").provider(configs).request();
        assignConfigs();
    }
    private static void createConfigs() {
        configs.addKeyValuePair(new Pair<>("show-hud", "true"), "boolean");
    }

    private static void assignConfigs() {
        SHOW_HUD = CONFIG.getOrDefault("show-hud", true);
    }
}
