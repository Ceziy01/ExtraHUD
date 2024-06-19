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

            //RenderSystem.setShaderColor(0f, 0f, 0f, 0.5f);
            //context.drawTexture(BACK, x, y, 0, 0, 90, 40);
            //RenderSystem.setShaderColor(1f, 1f, 1f, 1f);

            //Player Position and Dimention
            String coordstype = ModConfig.INSTANCE.coordinatestype;
            if (!coordstype.equals("Off")) {
                Item pos_item;

                if (coordstype.equals("Dimentional")) {
                    if (player.getWorld().getRegistryKey() == World.NETHER) {
                        pos_item = Items.NETHERRACK;
                    } else if (player.getWorld().getRegistryKey() == World.END) {
                        pos_item = Items.END_STONE;
                    } else {
                        pos_item = Items.GRASS_BLOCK;
                    }
                } else {
                    pos_item = Items.GRASS_BLOCK;
                }

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
                    coords = Text.literal(String.format(" %.0f %.0f %.0f", Math.ceil(pos.getX() / 8), Math.ceil(pos.getY()), Math.floor(pos.getZ() / 8)));
                }
                context.drawText(client.textRenderer, coords, x +18, textY,  0xffffff, false);
                textY += 18;
                iconY += 18;
            }

            //Light Level
            String light_type = ModConfig.INSTANCE.lighttype;
            if (!light_type.equals("Off")) {
                int block_light_level = client.world.getLightLevel(LightType.BLOCK, BlockPos.ofFloored(pos));
                int sky_light_level = client.world.getLightLevel(LightType.SKY, BlockPos.ofFloored(pos));
                int light_level;

                if (light_type.equals("Mixed")) {
                    light_level = Math.max(block_light_level, sky_light_level);
                } else if (light_type.equals("Block")) {
                    light_level = block_light_level;
                } else {
                    light_level = sky_light_level;
                }

                ItemStack light_item = Items.LIGHT.asItem().getDefaultStack();
                context.drawItem(light_item, x +1, iconY);
                context.drawText(client.textRenderer, Text.literal(" " + String.valueOf(light_level)), x +18, textY,  0xffffff, false);
                textY += 18;
                iconY += 18;
            }

            String time_type = ModConfig.INSTANCE.timetype;
            if (!time_type.equals("Off")) {
                String formatedTime = (time_type.equals("24h")) ? getTime24(client.world) : getTime12(client.world);

                context.drawItem(Items.CLOCK.asItem().getDefaultStack(), x + 1, iconY);
                context.drawText(client.textRenderer, Text.literal(" " + formatedTime), x + 18, textY, 0xffffff, false);
                textY += 18;
                iconY += 18;
            }
        }
    }

    public String getTime24(World world) {
        long time = world.getTimeOfDay() % 24000;

        int hours = (int)((time / 1000 + 6) % 24);
        int minutes = (int)(60 * (time % 1000) / 1000);

        return String.format("%02d:%02d", hours, minutes);
    }

    public String getTime12(World world) {
        long time = world.getTimeOfDay() % 24000;

        int hours = (int)((time / 1000 + 6) % 24);
        int minutes = (int)(60 * (time % 1000) / 1000);

        String period = (hours >= 12) ? "PM" : "AM";

        hours = hours % 12;
        if (hours == 0) {
            hours = 12;
        }

        return String.format("%02d:%02d %s", hours, minutes, period);
    }
}
