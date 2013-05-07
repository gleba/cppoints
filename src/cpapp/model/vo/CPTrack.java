package cpapp.model.vo;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

/**
 * Created with IntelliJ IDEA.
 * User: Gleb
 * D/T: 12.04.13 / 10:45
 * Live by the code. Die by the code.
 * ©The Way of the Samurai.
 */
public class CPTrack {
    public String name = "new CPTrack";
    private Date creationTime = new Date();
    private String time = "";
    public String id = "";
    public List<CPoint> points = new ArrayList<CPoint>();

    public CPTrack(String trackName) {
        id = UUID.randomUUID().toString();
        name = trackName;
    }

    public CPTrack() {

    }

    public void addCPoint(CPoint point){
        points.add(point);
    }
    public void setName(String name) {
        this.name = name;
    }

    public String getName(){
        return name;
    }
    public String getTime(){
        return creationTime.toLocaleString();
    }
    public List getContent() {
        return points;
    }
}
