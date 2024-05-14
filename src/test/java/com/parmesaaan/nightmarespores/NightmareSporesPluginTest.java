package com.parmesaaan.nightmarespores;

import net.runelite.client.RuneLite;
import net.runelite.client.externalplugins.ExternalPluginManager;

public class NightmareSporesPluginTest
{
	public static void main(String[] args) throws Exception
	{
		ExternalPluginManager.loadBuiltin(NightmareSporesPlugin.class);
		RuneLite.main(args);
	}
}