package com.parmesaaan.nightmarespores;

import net.runelite.client.config.Config;
import net.runelite.client.config.ConfigGroup;
import net.runelite.client.config.ConfigItem;

@ConfigGroup("nightmarespores")
public interface NightmareSporesConfig extends Config
{
	@ConfigItem(
			keyName = "enabled",
			name = "Enabled",
			description = "Enable highlighting Nightmare spores"
	)
	default Boolean enabled() {
		return true;
	}
}
