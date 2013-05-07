package cpapp.model;

import cpapp.model.vo.*;
import tools.Prop;
import tools.events.SEvents;
import tools.events.SListener;

import java.util.ArrayList;

/**
 * Created with IntelliJ IDEA.
 * User: Gleb
 * D/T: 12.04.13 / 8:01
 * Live by the code. Die by the code.
 * ©The Way of the Samurai.
 */
public class DataService {
    public Session session = new Session();
//    public Storage sgorage;
    public CPTrack cpTrack = new CPTrack();
    public ArrayList<CPTrack> cpTracks = new ArrayList<CPTrack>();
    public ArrayList<MPoint> mapped = new ArrayList<MPoint>();

    public Storage storage;

    public DataService() {
        storage = new Storage();
        cpTracks = storage.cpTracks;
        if (cpTracks.isEmpty()){
            addTrack(fullDemoTrack());
        } else {
            cpTrack = cpTracks.get(Prop.getInt("SelectedCPIndex",0));
            SEvents.only.call(SListener.CP_CHANGE);
        }
    }



    public void mapTrack(){
        Boolean allDpFound = true;
        Boolean dfound;
        MPoint mp;


        if (cpTrack.points.size()>0){
            for (CPoint cp : cpTrack.points){
                dfound = false;
                for (DPoint dp : session.devicePoints){
                    if (dp.id.equals(cp.id)){
                        mp = new MPoint(dp,cp);
                        mapped.add(mp);
                        dfound = true;
                    }
                }
                if (!dfound){
                    mp = new MPoint(cp);
                    mapped.add(mp);
                    allDpFound = false;
                }
            }
        }
        if (!allDpFound){
            for (DPoint dp : session.devicePoints){
                dfound = false;
                for (MPoint mPoint : mapped){
                    if (dp.id.equals(mPoint.id)){
                        dfound = true;
                    }
                }
                if (!dfound){
                    mp = new MPoint(dp);
                    mapped.add(mp);
                }
            }
        }

    }

    public CPTrack fullDemoTrack(){
        CPTrack democpTrack = new CPTrack();
        democpTrack.name = "Demo track";
        democpTrack.points.add(new CPoint("47566","Старт"));
        democpTrack.points.add(new CPoint("4561","Весёлый холм"));
        democpTrack.points.add(new CPoint("46901","Затейный"));
        democpTrack.points.add(new CPoint("3790","Горка"));
        democpTrack.points.add(new CPoint("2617","Мост"));
        democpTrack.points.add(new CPoint("65443","Новый лес"));
        democpTrack.points.add(new CPoint("54776","Внезапная поляна"));
        democpTrack.points.add(new CPoint("55155","Базовый лагерь"));
        democpTrack.points.add(new CPoint("61257","Финиш"));
        democpTrack.points.add(new CPoint("0","Магазин"));
        return democpTrack;
    }


    public void addTrack(CPTrack track) {
        cpTrack = track;
        cpTracks.add(cpTrack);
        storage.write();
        SEvents.only.call(SListener.CPLIST_CHANGE);
    }

    public void remTrack(CPTrack cpTrack) {
        storage.cpTracks.remove(cpTrack);
        SEvents.only.call(SListener.CPLIST_CHANGE);
        storage.write();;
    }

    public void setCPTrack(CPTrack CPTrack) {
        this.cpTrack = CPTrack;
        SEvents.only.call(SListener.CP_CHANGE);
    }


}
