package cpapp.model;

import cpapp.model.vo.*;

import java.util.ArrayList;
import java.util.List;

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
    public CPTrack track = new CPTrack();
    public ArrayList<MPoint> mapped = new ArrayList<MPoint>();

    public void write() {
        System.out.print("data write");
    }

    public void mapTrack(){
        Boolean allDpFound = true;
        Boolean dfound;
        MPoint mp;

        //TODO its fake data
        fullDemoTrack();

        if (track.points.size()>0){
            for (CPoint cp : track.points){
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

    public void fullDemoTrack(){
        track = new CPTrack();
        track.points.add(new CPoint("47566","Старт"));
        track.points.add(new CPoint("4561","Весёлый холм"));
        track.points.add(new CPoint("46901","Затейный"));
        track.points.add(new CPoint("3790","Горка"));
        track.points.add(new CPoint("2617","Мост"));
        track.points.add(new CPoint("65443","Новый лес"));
        track.points.add(new CPoint("54776","Внезапная поляна"));
        track.points.add(new CPoint("55155","Базовый лагерь"));
        track.points.add(new CPoint("61257","Финиш"));
        track.points.add(new CPoint("0","Магазин"));
    }


}
