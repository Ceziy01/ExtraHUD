package me.ceziy.extrahud.extrahud.key;

import me.ceziy.extrahud.extrahud.config.ModConfig;
import me.ceziy.extrahud.extrahud.config.ModConfigScreen;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.fabricmc.fabric.api.client.keybinding.v1.KeyBindingHelper;
import net.minecraft.client.option.KeyBinding;
import net.minecraft.client.util.InputUtil;
import org.lwjgl.glfw.GLFW;

public class ModKeys {
    private static KeyBinding keyBindingHideHUD;
    private static KeyBinding keyBindingSettings;
    public static void registerKeys() {

        keyBindingHideHUD = KeyBindingHelper.registerKeyBinding(new KeyBinding(
                "extrahud.key.hidehud",
                InputUtil.Type.KEYSYM,
                GLFW.GLFW_KEY_H,
                "category.extrahud"
        ));

        keyBindingSettings = KeyBindingHelper.registerKeyBinding(new KeyBinding(
                "extrahud.key.opensettings",
                InputUtil.Type.KEYSYM,
                GLFW.GLFW_KEY_APOSTROPHE,
                "category.extrahud"
        ));

        ClientTickEvents.END_CLIENT_TICK.register(client -> {
            while (keyBindingHideHUD.wasPressed()) {
                if (ModConfig.INSTANCE.showhud) {
                    ModConfig.INSTANCE.showhud = false;
                } else {
                    ModConfig.INSTANCE.showhud = true;
                }
                ModConfig.saveConfig();
            }
        });

        ClientTickEvents.END_CLIENT_TICK.register(client -> {
            while (keyBindingSettings.wasPressed()) {
                client.execute(() -> {
                    client.setScreen(ModConfigScreen.getConfigScreen(client.currentScreen));
                });
            }
        });
    }
}
