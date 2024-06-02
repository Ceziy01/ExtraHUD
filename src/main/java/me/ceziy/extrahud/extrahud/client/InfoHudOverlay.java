package me.ceziy.extrahud.extrahud.client;

import me.ceziy.extrahud.extrahud.ExtraHUD;
import net.fabricmc.fabric.api.client.rendering.v1.HudRenderCallback;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.Position;
import net.minecraft.world.World;

public class InfoHudOverlay implements HudRenderCallback {
    private static final Identifier TEXTURE = new Identifier(ExtraHUD.MOD_ID,
            "textures/custom_hud/thing.png");

    @Override
    public void onHudRender(DrawContext context, float tickDelta) {
        int x = 0;
        int y = 0;
        MinecraftClient client = MinecraftClient.getInstance();

        //context.drawTexture(TEXTURE, x -94, y -54, 0, 0, 12, 12, 12, 12);
        PlayerEntity player = client.player;
        Position pos = player.getPos();

        //Position
        Item pos_item;
        pos_item = Items.GRASS_BLOCK;
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

        //Light
        int light_level = client.world.getLightLevel(player.getBlockPos());
        ItemStack light_item = Items.LIGHT.asItem().getDefaultStack();
        context.drawItem(light_item, x +1, y +19);
        context.drawText(client.textRenderer, Text.literal(" " + String.valueOf(light_level)), x +18, y +23,  0xffffff, false);
    }
}
