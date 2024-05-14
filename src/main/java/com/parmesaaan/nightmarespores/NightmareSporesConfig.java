package com.parmesaaan.nightmarespores;

import net.runelite.client.config.Config;
import net.runelite.client.config.ConfigGroup;
import net.runelite.client.config.ConfigItem;

import java.awt.*;

@ConfigGroup(NightmareSporesPlugin.CONFIG_GROUP)
public interface NightmareSporesConfig extends Config
{

	@ConfigItem(
			position = 0,
			keyName = "highlightoutline",
			name = "Spore outline",
			description = "Highlight the outline of the spore"
	)
	default boolean highlightOutline()
	{
		return false;
	}

	@ConfigItem(
			position = 1,
			keyName = "highlightdangertiles",
			name = "Danger tiles",
			description = "Highlight the danger tiles of the spore"
	)
	default boolean highlightDangerTiles()
	{
		return true;
	}

	@ConfigItem(
			position = 2,
			keyName = "highlightcolor",
			name = "Highlight color",
			description = "The colour of the highlight outline/tiles"
	)
	default Color highlightColor()
	{
		return Color.red;
	}

	@ConfigItem(
			position = 3,
			keyName = "fillcolor",
			name = "Fill color",
			description = "The fill colour of the highlight tiles"
	)
	default Color fillColor()
	{
		return new Color(0, 0, 0, 50);
	}

	@ConfigItem(
			position = 4,
			keyName = "borderwidth",
			name = "Border width",
			description = "The width of the highlight outline/tile borders"
	)
	default int borderWidth()
	{
		return 2;
	}
}
