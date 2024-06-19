package me.ceziy.extrahud.extrahud.config;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class ModConfig {
    private static final Gson GSON = new GsonBuilder().setPrettyPrinting().create();

    public static ModConfig INSTANCE = new ModConfig();

    private static final Path CONFIG_PATH = Paths.get("config", "extrahud.json");

    public boolean showhud = true;
    public boolean shownethercoords = false;
    public String coordinatestype = "Dimentional";
    public String lighttype = "Mixed";
    public String timetype = "24h";

    public static void loadConfig() {
        if (Files.exists(CONFIG_PATH)) {
            try (FileReader reader = new FileReader(CONFIG_PATH.toFile())) {
                INSTANCE = GSON.fromJson(reader, ModConfig.class);
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            saveConfig();
            loadConfig();
        }
    }

    public static void saveConfig() {
        try (FileWriter writer = new FileWriter(CONFIG_PATH.toFile())) {
            GSON.toJson(INSTANCE, writer);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
