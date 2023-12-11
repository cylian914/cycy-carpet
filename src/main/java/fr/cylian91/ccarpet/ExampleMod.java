package fr.cylian91.ccarpet;

import carpet.CarpetExtension;
import carpet.CarpetServer;
import carpet.utils.Translations;
import fr.cylian91.ccarpet.mixin.DisableMaxMixin;
import io.netty.util.Recycler;
import net.fabricmc.api.ModInitializer;
import net.minecraft.client.MinecraftClient;
import net.minecraft.server.MinecraftServer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import fr.cylian91.ccarpet.Logger.RegisterLogger;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ExampleMod implements ModInitializer, CarpetExtension {
	// This logger is used to write text to the console and the log file.
	// It is considered best practice to use your mod id as the logger's name.
	// That way, it's clear which mod wrote info, warnings, and errors.
	public static boolean cancel = false;
    public static final Logger LOGGER = LoggerFactory.getLogger("cycys-carpet");
	public static List Pending=new ArrayList<>();public static ArrayDeque queue=new ArrayDeque();
	public static long depth=0;
	public static boolean bl=false;
	public static boolean bl2z=false;
	//public MinecraftClient client = MinecraftClient.getInstance();
	@Override
	public void onInitialize() {
		// This code runs as soon as Minecraft is in a mod-load-ready state.
		// However, some things (like resources) may still be uninitialized.
		// Proceed with mild caution.
		CarpetServer.manageExtension(new ExampleMod());
	}

	@Override
	public void onGameStarted() {
		CarpetServer.settingsManager.parseSettingsClass(settings.class);

	}
	@Override
	public void onServerLoaded(MinecraftServer server){
		//LoggerRegistry.registerLogger("Update", HUDLogger.Log("update",null, null));
	}
	@Override
	public Map<String, String> canHasTranslations(String lang){
		return Translations.getTranslationFromResourcePath("assets/cycys-carpet/lang/en_us.json");

	}
	@Override
	public void registerLoggers(){
		RegisterLogger.register();
	}
	@Override
	public void onServerLoadedWorlds(MinecraftServer server){
		cancel=true;
	}
}