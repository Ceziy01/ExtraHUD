package me.ceziy.extrahud.extrahud.config;

import me.shedaniel.clothconfig2.api.ConfigBuilder;
import me.shedaniel.clothconfig2.api.ConfigCategory;
import me.shedaniel.clothconfig2.api.ConfigEntryBuilder;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.text.Text;

import java.util.Arrays;

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

        display.addEntry(entryBuilder.startStringDropdownMenu(Text.translatable("modmenu.configscreen.playercoords"), ModConfig.INSTANCE.coordinatestype)
                .setDefaultValue("Dimentional")
                .setSelections(Arrays.asList("Dimentional", "Overworld", "Off"))
                .setSaveConsumer(newValue -> ModConfig.INSTANCE.coordinatestype = newValue)
                .setSuggestionMode(false)
                .build()
        );

        display.addEntry(entryBuilder.startBooleanToggle(Text.translatable("modmenu.configscreen.shownetherwithcoords"), ModConfig.INSTANCE.shownethercoords)
                .setDefaultValue(false)
                .setSaveConsumer(newValue -> ModConfig.INSTANCE.shownethercoords = newValue)
                .build());

        display.addEntry(entryBuilder.startStringDropdownMenu(Text.translatable("modmenu.configscreen.lightlevel"), ModConfig.INSTANCE.lighttype)
                .setDefaultValue("Mixed")
                .setSelections(Arrays.asList("Mixed", "Block", "Sky", "Off"))
                .setSaveConsumer(newValue -> ModConfig.INSTANCE.lighttype = newValue)
                .setSuggestionMode(false)
                .build()
        );

        display.addEntry(entryBuilder.startStringDropdownMenu(Text.translatable("modmenu.configscreen.gametime"), ModConfig.INSTANCE.timetype)
                .setDefaultValue("24h")
                .setSelections(Arrays.asList("24h", "12h", "Off"))
                .setSaveConsumer(newValue -> ModConfig.INSTANCE.timetype = newValue)
                .setSuggestionMode(false)
                .build()
        );

        builder.setSavingRunnable(ModConfig::saveConfig);

        return builder.build();
    }
}
