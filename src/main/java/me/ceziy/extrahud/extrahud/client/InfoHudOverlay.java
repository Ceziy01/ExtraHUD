package me.ceziy.extrahud.extrahud.client;

import me.ceziy.extrahud.extrahud.ExtraHUD;
import me.ceziy.extrahud.extrahud.config.ModConfigs;
import net.fabricmc.fabric.api.client.rendering.v1.HudRenderCallback;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Position;
import net.minecraft.world.LightType;
import net.minecraft.world.World;

public class InfoHudOverlay implements HudRenderCallback {
    private static final Identifier BACK = new Identifier(ExtraHUD.MOD_ID,
            "textures/custom_hud/back.png");

    @Override
    public void onHudRender(DrawContext context, float tickDelta) {
        int x = 0;
        int y = 0;
        MinecraftClient client = MinecraftClient.getInstance();
        if (!ModConfigs.SHOW_HUD) {
            PlayerEntity player = client.player;
            Position pos = player.getPos();
            int block_light_lev = client.world.getLightLevel(LightType.BLOCK, BlockPos.ofFloored(pos));
            int light_level = block_light_lev;

            //RenderSystem.setShaderColor(0f, 0f, 0f, 0.5f);
            //context.drawTexture(BACK, x, y, 0, 0, 90, 40);
            //RenderSystem.setShaderColor(1f, 1f, 1f, 1f);

            //Player Position and World
            Item pos_item;

            if (player.getWorld().getRegistryKey() == World.NETHER) {
                pos_item = Items.NETHERRACK;
            } else if (player.getWorld().getRegistryKey() == World.END) {
                pos_item = Items.END_STONE;
            } else {
                pos_item = Items.GRASS_BLOCK;
            }
            context.drawItem(pos_item.getDefaultStack(), x +1, y +1);
            Text coords = Text.literal(String.format(" %.0f %.0f %.0f", pos.getX(), pos.getY(), pos.getZ()));
            context.drawText(client.textRenderer, coords, x +18, y +5,  0xffffff, false);

            //Light Level
            ItemStack light_item = Items.LIGHT.asItem().getDefaultStack();
            context.drawItem(light_item, x +1, y +19);
            context.drawText(client.textRenderer, Text.literal(" " + String.valueOf(light_level)), x +18, y +23,  0xffffff, false);
        }

    }


}
