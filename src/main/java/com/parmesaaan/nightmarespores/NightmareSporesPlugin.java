package com.parmesaaan.nightmarespores;

import com.google.inject.Provides;
import javax.inject.Inject;
import lombok.extern.slf4j.Slf4j;
import net.runelite.api.Client;
import net.runelite.client.config.ConfigManager;
import net.runelite.client.plugins.Plugin;
import net.runelite.client.plugins.PluginDescriptor;

@Slf4j
@PluginDescriptor(
	name = "Nightmare Spores"
)
public class NightmareSporesPlugin extends Plugin
{
	@Inject
	private Client client;

	@Inject
	private NightmareSporesConfig config;

	@Override
	protected void startUp() throws Exception
	{
		log.info("Started Nightmare Spores");
	}

	@Override
	protected void shutDown() throws Exception
	{
		log.info("Stopped Nightmare Spores");
	}

	@Provides
	NightmareSporesConfig provideConfig(ConfigManager configManager)
	{
		return configManager.getConfig(NightmareSporesConfig.class);
	}
}
