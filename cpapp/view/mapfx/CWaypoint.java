package cpapp.view.mapfx;

import org.jdesktop.swingx.mapviewer.DefaultWaypoint;
import org.jdesktop.swingx.mapviewer.GeoPosition;

import java.awt.*;

/**
 * Created with IntelliJ IDEA.
 * User: YP
 * D/T: 21:50 / 25.04.13
 * Live by the code. Die by the code.
 * ©The Way of the Samurai.
 */
public class CWaypoint extends DefaultWaypoint
{
    private final String label;
    private final Color color;

    /**
     * @param label the text
     * @param color the color
     * @param coord the coordinate
     */
    public CWaypoint(String label, Color color, GeoPosition coord)
    {
        super(coord);
        this.label = label;
        this.color = color;
    }

    /**
     * @return the label text
     */
    public String getLabel()
    {
        return label;
    }

    /**
     * @return the color
     */
    public Color getColor()
    {
        return color;
    }


}
