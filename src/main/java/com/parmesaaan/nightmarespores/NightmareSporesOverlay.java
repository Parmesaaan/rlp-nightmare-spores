package com.parmesaaan.nightmarespores;

import lombok.extern.slf4j.Slf4j;
import net.runelite.api.Client;
import net.runelite.api.Perspective;
import net.runelite.api.TileObject;
import net.runelite.api.coords.LocalPoint;
import net.runelite.api.coords.WorldPoint;
import net.runelite.client.ui.overlay.Overlay;
import net.runelite.client.ui.overlay.OverlayLayer;
import net.runelite.client.ui.overlay.OverlayPosition;
import net.runelite.client.ui.overlay.OverlayUtil;
import net.runelite.client.ui.overlay.outline.ModelOutlineRenderer;

import javax.inject.Inject;
import java.awt.*;

@Slf4j
public class NightmareSporesOverlay extends Overlay
{
    private final Client client;
    private final NightmareSporesConfig config;
    private final NightmareSporesPlugin plugin;
    private final ModelOutlineRenderer modelOutlineRenderer;

    @Inject
    private NightmareSporesOverlay(Client client, NightmareSporesConfig config, NightmareSporesPlugin plugin,
                                   ModelOutlineRenderer modelOutlineRenderer)
    {
        this.client = client;
        this.config = config;
        this.plugin = plugin;
        this.modelOutlineRenderer = modelOutlineRenderer;
        setPosition(OverlayPosition.DYNAMIC);
        setPriority(PRIORITY_LOW);
        setLayer(OverlayLayer.ABOVE_SCENE);
    }

    @Override
    public Dimension render(Graphics2D graphics)
    {
        var objects = plugin.getFoundSpores();
        if(objects.isEmpty())
        {
            return null;
        }

        for(ColorSpore spore : objects)
        {
            TileObject object = spore.getTileObject();

            if (object.getPlane() != client.getPlane())
            {
                continue;
            }

            if(config.highlightOutline())
            {
                modelOutlineRenderer.drawOutline(object, config.borderWidth(), config.highlightColor(), 0);
            }

            if(config.highlightDangerTiles())
            {
                WorldPoint worldPoint = object.getWorldLocation();
                LocalPoint localPoint = LocalPoint.fromWorld(client, worldPoint.getX() - 1, worldPoint.getY() - 1);

                if(localPoint == null) {
                    return null;
                }

                LocalPoint centerLocalPoint = new LocalPoint(
                        localPoint.getX() + Perspective.LOCAL_TILE_SIZE,
                        localPoint.getY() + Perspective.LOCAL_TILE_SIZE);

                Polygon tilePoly = Perspective.getCanvasTileAreaPoly(client, centerLocalPoint, 3);

                if(tilePoly != null) {
                    Stroke stroke = new BasicStroke((float) config.borderWidth());
                    OverlayUtil.renderPolygon(graphics, tilePoly, config.highlightColor(), config.fillColor(), stroke);
                }
            }
        }

        return null;
    }
}
