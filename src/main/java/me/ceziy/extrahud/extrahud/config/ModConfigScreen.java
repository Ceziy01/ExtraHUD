package me.ceziy.extrahud.extrahud.config;

import me.shedaniel.clothconfig2.api.ConfigBuilder;
import me.shedaniel.clothconfig2.api.ConfigCategory;
import me.shedaniel.clothconfig2.api.ConfigEntryBuilder;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.text.Text;

import me.ceziy.extrahud.extrahud.config.ModConfig;

public class ModConfigScreen {
    public static Screen getConfigScreen(Screen parent) {
        ConfigBuilder builder = ConfigBuilder.create()
                .setParentScreen(parent);

        ConfigCategory general = builder.getOrCreateCategory(Text.translatable("category.modconfig.general"));

        ConfigEntryBuilder entryBuilder = builder.entryBuilder();

        general.addEntry(entryBuilder.startBooleanToggle(Text.translatable("modmenu.configscreen.showhud"), ModConfig.INSTANCE.SHOW_HUD)
                .setDefaultValue(true)
                .setSaveConsumer(newValue -> ModConfig.INSTANCE.SHOW_HUD = newValue)
                .build());

        builder.setSavingRunnable(ModConfig::saveConfig);

        return builder.build();
    }
}
