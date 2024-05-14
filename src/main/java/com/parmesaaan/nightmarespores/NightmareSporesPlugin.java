package com.parmesaaan.nightmarespores;

import com.google.gson.Gson;
import com.google.inject.Provides;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import net.runelite.api.*;
import net.runelite.api.events.GameObjectDespawned;
import net.runelite.api.events.GameObjectSpawned;
import net.runelite.api.events.GameStateChanged;
import net.runelite.client.callback.ClientThread;
import net.runelite.client.config.ConfigManager;
import net.runelite.client.eventbus.Subscribe;
import net.runelite.client.plugins.Plugin;
import net.runelite.client.plugins.PluginDescriptor;
import net.runelite.client.ui.components.colorpicker.ColorPickerManager;
import net.runelite.client.ui.overlay.OverlayManager;

import javax.inject.Inject;
import java.util.*;

@Slf4j
@PluginDescriptor(
		name = "Nightmare Spores"
)
public class NightmareSporesPlugin extends Plugin
{
	public static final String CONFIG_GROUP = "nightmarespores";


	@Inject
	private Client client;

	@Inject
	private ClientThread clientThread;

	@Inject
	private ConfigManager configManager;

	@Inject
	private OverlayManager overlayManager;

	@Inject
	private ColorPickerManager colorPickerManager;

	@Inject
	private NightmareSporesOverlay overlay;

	@Inject
	private NightmareSporesConfig config;

	@Inject
	private Gson gson;

	private final Set<Integer> SPORE_IDS = new HashSet<>(Arrays.asList(ObjectID.SPORE, ObjectID.SPORE_37739));

	@Getter(AccessLevel.PACKAGE)
	private final List<TileObject> foundSpores = new ArrayList<>();

	@Provides
	NightmareSporesConfig provideConfig(ConfigManager configManager) {
		return configManager.getConfig(NightmareSporesConfig.class);
	}

	@Override
	protected void startUp() {
		overlayManager.add(overlay);
	}

	@Override
	protected void shutDown() {
		overlayManager.remove(overlay);
	}

	@Subscribe
	public void onGameObjectSpawned(GameObjectSpawned event) {
		checkObject(event.getGameObject());
	}

	@Subscribe
	public void onGameObjectDespawned(GameObjectDespawned event) {
		foundSpores.removeIf(spore -> spore == event.getGameObject());
	}

	@Subscribe
	public void onGameStateChanged(GameStateChanged gameStateChanged)
	{
		GameState gameState = gameStateChanged.getGameState();
		if (gameState == GameState.LOADING)
		{
			foundSpores.clear();
		}
	}

	private void checkObject(TileObject object) {
		ObjectComposition objectComposition = client.getObjectDefinition(object.getId());
		if(SPORE_IDS.contains(objectComposition.getId())) {
			log.debug("Found spore {}", object);
			foundSpores.add(object);
		}
	}
}
