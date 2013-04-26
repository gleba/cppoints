package cpapp.model.vo;

import java.util.ArrayList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: Gleb
 * D/T: 12.04.13 / 10:45
 * Live by the code. Die by the code.
 * ©The Way of the Samurai.
 */
public class CPTrack {
    public String name = "new CP track";
    public List<CPoint> points = new ArrayList<CPoint>();

    public List getContent() {
        return points;
    }
}
