package com.parmesaaan.nightmarespores;

import lombok.RequiredArgsConstructor;
import lombok.Value;
import net.runelite.api.ObjectComposition;
import net.runelite.api.TileObject;

@Value
@RequiredArgsConstructor
public class ColorSpore
{
    private final TileObject tileObject;
    private final ObjectComposition composition;

}
