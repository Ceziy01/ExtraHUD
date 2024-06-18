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
                .setParentScreen(parent)
                .setTitle(Text.translatable("modmenu.configscreen.title"));

        ConfigEntryBuilder entryBuilder = builder.entryBuilder();

        //Main Category
        ConfigCategory general = builder.getOrCreateCategory(Text.translatable("modmenu.configscreen.maincategory"));

        general.addEntry(entryBuilder.startBooleanToggle(Text.translatable("modmenu.configscreen.showhud"), ModConfig.INSTANCE.showhud)
                .setDefaultValue(true)
                .setSaveConsumer(newValue -> ModConfig.INSTANCE.showhud = newValue)
                .build());

        //Display Category
        ConfigCategory display = builder.getOrCreateCategory(Text.translatable("modmenu.configscreen.displaycategory"));

        display.addEntry(entryBuilder.startBooleanToggle(Text.translatable("modmenu.configscreen.showdimentionwithcoords"), ModConfig.INSTANCE.showdimentionwithcoords)
                .setDefaultValue(true)
                .setSaveConsumer(newValue -> ModConfig.INSTANCE.showdimentionwithcoords= newValue)
                .build());

        display.addEntry(entryBuilder.startBooleanToggle(Text.translatable("modmenu.configscreen.showoverworldcoords"), ModConfig.INSTANCE.showoverworldcoords)
                .setDefaultValue(false)
                .setSaveConsumer(newValue -> ModConfig.INSTANCE.showoverworldcoords= newValue)
                .build());

        display.addEntry(entryBuilder.startBooleanToggle(Text.translatable("modmenu.configscreen.shownetherwithcoords"), ModConfig.INSTANCE.shownethercoords)
                .setDefaultValue(false)
                .setSaveConsumer(newValue -> ModConfig.INSTANCE.shownethercoords= newValue)
                .build());

        display.addEntry(entryBuilder.startBooleanToggle(Text.translatable("modmenu.configscreen.showblocklightlevel"), ModConfig.INSTANCE.showblocklightlevel)
                .setDefaultValue(true)
                .setSaveConsumer(newValue -> ModConfig.INSTANCE.showblocklightlevel = newValue)
                .build());

        builder.setSavingRunnable(ModConfig::saveConfig);

        return builder.build();
    }
}
