package cpapp.view.mapfx;

import org.jdesktop.swingx.JXMapViewer;
import org.jdesktop.swingx.OSMTileFactoryInfo;
import org.jdesktop.swingx.VirtualEarthTileFactoryInfo;
import org.jdesktop.swingx.input.CenterMapListener;
import org.jdesktop.swingx.input.PanKeyListener;
import org.jdesktop.swingx.input.PanMouseInputListener;
import org.jdesktop.swingx.input.ZoomMouseWheelListenerCursor;
import org.jdesktop.swingx.mapviewer.DefaultTileFactory;
import org.jdesktop.swingx.mapviewer.GeoPosition;
import org.jdesktop.swingx.mapviewer.TileFactoryInfo;
import org.jdesktop.swingx.mapviewer.WaypointPainter;
import org.jdesktop.swingx.painter.CompoundPainter;
import org.jdesktop.swingx.painter.Painter;
import tools.Prop;
import tools.events.SEvents;
import tools.events.SListener;

import javax.swing.event.MouseInputAdapter;
import javax.swing.event.MouseInputListener;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created with IntelliJ IDEA.
 * User: YP
 * D/T: 19:21 / 25.04.13
 * Live by the code. Die by the code.
 * ©The Way of the Samurai.
 */
public class JXMapController extends JXMapViewer {
    final public Set<CWaypoint> waypoints ;
    final public WaypointPainter<CWaypoint> waypointPainter;

    public JXMapController(){
        // Create a TileFactoryInfo for OpenStreetMap
        TileFactoryInfo osmTileFactoryInfo = new OSMTileFactoryInfo();
        DefaultTileFactory tileFactory = new DefaultTileFactory(osmTileFactoryInfo);
        setTileFactory(tileFactory);
//        mapViewer.set();
        // Use 8 threads in parallel to load the tiles
        tileFactory.setThreadPoolSize(8);

        setZoom(Prop.getInt("mapzoom", 7));
        setAddressLocation(new GeoPosition(50.11, 8.68));

        waypoints = new HashSet<CWaypoint>();
        waypointPainter = new WaypointPainter<CWaypoint>();
        waypointPainter.setWaypoints(waypoints);
        waypointPainter.setRenderer(new FancyWaypointRenderer());

        List<Painter<JXMapViewer>> painters = new ArrayList<Painter<JXMapViewer>>();
//		painters.add(routePainter);
        painters.add(waypointPainter);
        final CompoundPainter<JXMapViewer> painter = new CompoundPainter<JXMapViewer>(painters);
        this.setOverlayPainter(painter);

        this.addMouseListener(new MouseInputAdapter() {
            public void mouseClicked(MouseEvent e) {
                Point point = e.getPoint();
                Point2D pixelcoord1 = point;
                GeoPosition geoPos = convertPointToGeoPosition(pixelcoord1);
                CWaypoint wp = new CWaypoint("ss", new Color(0xFF1E34), geoPos);
                waypoints.add(wp);
                waypointPainter.setWaypoints(waypoints);
                setCenter(getCenter());
                SEvents.only.call(SListener.NEWPOINT,wp);
            }
        });




    }
    public void bindMouse(){
        MouseInputListener mia = new PanMouseInputListener(this);
        this.addMouseListener(mia);
        this.addMouseMotionListener(mia);

        this.addMouseListener(new CenterMapListener(this));

        this.addMouseWheelListener(new ZoomMouseWheelListenerCursor(this));

        this.addKeyListener(new PanKeyListener(this));
    }
}
