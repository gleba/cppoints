package cpapp.model.vo;

import com.thoughtworks.xstream.XStream;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Created with IntelliJ IDEA.
 * User: Gleb
 * D/T: 12.04.13 / 8:04
 * Live by the code. Die by the code.
 * ©The Way of the Samurai.
 */
public class Storage {
    public int version = 1;
//    public ArrayList<Session> sessions = new ArrayList<Session>();
    public ArrayList <CPTrack> cpTracks = new ArrayList<CPTrack>();

    private BufferedWriter bw = null;
    private File file;
    private String fileName = "checkpoints.xml";
    public List getContent(){
        return  cpTracks;
    }

    public Storage(){
        file = new File(fileName);
        if (file.exists()){
            try {
                String xml = read();
                XStream xs = new XStream();
                cpTracks = (ArrayList<CPTrack>) xs.fromXML(xml);
                System.out.println(cpTracks);
                System.out.println(xml);
            } catch (IOException e) {
                System.out.println(e.toString());
            }
        }
    }


    public void write() {
        XStream xs = new XStream();
        String text = xs.toXML(cpTracks);
        if (!file.exists())
            file.delete();
        try {
            FileOutputStream outputStream = new FileOutputStream(file);
            outputStream.write(text.getBytes());
        } catch (IOException e) {
            System.out.println(e.toString());
        }
    }

    private String read() throws IOException {
        StringBuilder fileContents = new StringBuilder((int)file.length());
        Scanner scanner = new Scanner(file);
        String lineSeparator = System.getProperty("line.separator");
        try {
            while(scanner.hasNextLine()) {
                fileContents.append(scanner.nextLine() + lineSeparator);
            }
            return fileContents.toString();
        } finally {
            scanner.close();
        }
    }
}
