package me.ceziy.extrahud.extrahud;

import me.ceziy.extrahud.extrahud.client.InfoHudOverlay;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.rendering.v1.HudRenderCallback;

public class ExtraHUDClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        HudRenderCallback.EVENT.register(new InfoHudOverlay());
    }
}
