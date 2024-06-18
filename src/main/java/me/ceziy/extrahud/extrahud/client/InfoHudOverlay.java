package me.ceziy.extrahud.extrahud.client;

import me.ceziy.extrahud.extrahud.ExtraHUD;
import me.ceziy.extrahud.extrahud.config.ModConfig;

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

    private int iconY;
    private int textY;

    @Override
    public void onHudRender(DrawContext context, float tickDelta) {
        int x = 0;
        int y = 0;
        iconY = 1;
        textY = 5;
        MinecraftClient client = MinecraftClient.getInstance();
        if (ModConfig.INSTANCE.showhud) {
            PlayerEntity player = client.player;
            Position pos = player.getPos();
            int block_light_lev = client.world.getLightLevel(LightType.BLOCK, BlockPos.ofFloored(pos));
            int light_level = block_light_lev;

            //RenderSystem.setShaderColor(0f, 0f, 0f, 0.5f);
            //context.drawTexture(BACK, x, y, 0, 0, 90, 40);
            //RenderSystem.setShaderColor(1f, 1f, 1f, 1f);

            //Player Position and Dimention
            if (ModConfig.INSTANCE.showdimentionwithcoords) {
                Item pos_item;

                if (player.getWorld().getRegistryKey() == World.NETHER) {
                    pos_item = Items.NETHERRACK;
                } else if (player.getWorld().getRegistryKey() == World.END) {
                    pos_item = Items.END_STONE;
                } else {
                    pos_item = Items.GRASS_BLOCK;
                }
                context.drawItem(pos_item.getDefaultStack(), x +1, iconY);
                Text coords = Text.literal(String.format(" %.0f %.0f %.0f", Math.ceil(pos.getX()) - 1, Math.ceil(pos.getY()), Math.floor(pos.getZ())));
                context.drawText(client.textRenderer, coords, x +18, textY,  0xffffff, false);
                textY += 18;
                iconY += 18;
            }

            //Player Position in overworld
            if (ModConfig.INSTANCE.showoverworldcoords) {
                Item pos_item = Items.GRASS_BLOCK;

                context.drawItem(pos_item.getDefaultStack(), x +1, iconY);
                Text coords = Text.literal(String.format(" %.0f %.0f %.0f", Math.ceil(pos.getX()) - 1, Math.ceil(pos.getY()), Math.floor(pos.getZ())));
                context.drawText(client.textRenderer, coords, x +18, textY,  0xffffff, false);
                textY += 18;
                iconY += 18;
            }

            //Player Position in Nether
            if (ModConfig.INSTANCE.shownethercoords) {
                Item pos_item = Items.NETHERRACK;
                Text coords;

                context.drawItem(pos_item.getDefaultStack(), x +1, iconY);

                if (player.getWorld().getRegistryKey() == World.NETHER) {
                    coords = Text.literal(String.format(" %.0f %.0f %.0f", Math.ceil(pos.getX()) - 1, Math.ceil(pos.getY()), Math.floor(pos.getZ())));
                } else {
                    //coords = Text.literal(String.format(" %.0f %.0f %.0f", (pos.getX() / 8), pos.getY(), pos.getZ() / 8));
                    coords = Text.literal(String.format(" %.0f %.0f %.0f", Math.ceil(pos.getX() / 8), Math.ceil(pos.getY()), Math.floor(pos.getZ() / 8)));
                }
                context.drawText(client.textRenderer, coords, x +18, textY,  0xffffff, false);
                textY += 18;
                iconY += 18;
            }

            //Light Level
            if (ModConfig.INSTANCE.showblocklightlevel) {
                ItemStack light_item = Items.LIGHT.asItem().getDefaultStack();
                context.drawItem(light_item, x +1, iconY);
                context.drawText(client.textRenderer, Text.literal(" " + String.valueOf(light_level)), x +18, textY,  0xffffff, false);
                textY += 18;
                iconY += 18;
            }
        }
    }
}
