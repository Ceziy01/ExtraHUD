package me.ceziy.extrahud.extrahud.key;

import me.ceziy.extrahud.extrahud.config.ModConfigs;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.fabricmc.fabric.api.client.keybinding.v1.KeyBindingHelper;
import net.minecraft.client.option.KeyBinding;
import net.minecraft.client.util.InputUtil;
import org.lwjgl.glfw.GLFW;

public class ModKeys {
    private static KeyBinding keyBinding;
    public static void registerKeys() {

        keyBinding = KeyBindingHelper.registerKeyBinding(new KeyBinding(
                "key.examplemod.spook",
                InputUtil.Type.KEYSYM,
                GLFW.GLFW_KEY_H,
                "category.extrahud"
        ));

        ClientTickEvents.END_CLIENT_TICK.register(client -> {
            while (keyBinding.wasPressed()) {
                if (ModConfigs.SHOW_HUD) {
                    ModConfigs.SHOW_HUD = false;
                } else {
                    ModConfigs.SHOW_HUD = true;
                }
            }
        });
    }
}
