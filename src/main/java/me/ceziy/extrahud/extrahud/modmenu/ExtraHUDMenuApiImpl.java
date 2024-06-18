package me.ceziy.extrahud.extrahud.modmenu;

import com.terraformersmc.modmenu.api.ConfigScreenFactory;
import com.terraformersmc.modmenu.api.ModMenuApi;
import me.ceziy.extrahud.extrahud.config.ModConfigScreen;

public class ExtraHUDMenuApiImpl implements ModMenuApi {
    @Override
    public ConfigScreenFactory<?> getModConfigScreenFactory() {
        return ModConfigScreen::getConfigScreen;
    }

}
