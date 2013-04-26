package cpapp.model.vo;

/**
 * Created with IntelliJ IDEA.
 * User: Gleb
 * D/T: 12.04.13 / 7:00
 * Live by the code. Die by the code.
 * ©The Way of the Samurai.
 */
public class StoredCommand{
    public String code;
    public String message;
    public StoredCommand(String code,String message){
        this.code = code;
        this.message = message;
    }
}
