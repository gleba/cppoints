package cpapp.model.vo;

/**
 * Created with IntelliJ IDEA.
 * User: Gleb
 * D/T: 12.04.13 / 10:47
 * Live by the code. Die by the code.
 * ©The Way of the Samurai.
 */

/**
 * CheckPoint
 */
public class CPoint {
    public String id;
    public String name;
    public CPoint(String id, String name) {
        this.id = id;
        this.name = name;
    }


    public String getName() {
        return name;
    }
}
