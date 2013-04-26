package cpapp.model;

/**
 * Created with IntelliJ IDEA.
 * User: Gleb
 * Date: 09.04.13
 * Time: 6:35
 * Когда простота поднимается в твоем разуме, не следуй за умствованием; покойся в состоянии простоты.
 */

import jssc.*;
import tools.Prop;
import tools.S;
import tools.events.SEvents;
import tools.events.SListener;

import java.util.Timer;
import java.util.TimerTask;


public class SerialPortsService {

    public String[] avaliableList;
    public String selectedPort = null;
    public Boolean open =false;

    public SerialPortsService() {

       checkPors();
       selectedPort = Prop.getProp("selectedPort");
        Boolean found = false;
        if (selectedPort!=null){
            for (String pn : avaliableList){
                if (selectedPort.equals(pn)){
                    found = true;
                }
            }
        };
        if (!found){
            if (avaliableList.length>0){
                selectedPort = avaliableList[0];
                S.log("Текущий порт "+selectedPort);
            }else{
                selectedPort ="-";
                S.log("Нет доступных COM-портов");
            }
        }
    }

    public void checkPors(){
        try{
        avaliableList = SerialPortList.getPortNames();
        } catch (Exception er){
            S.log(er.toString());
        }
    }

    private SerialPort serialPort;

    public void setPort(String port) {
        S.log("Выбран порт " + port);
        Prop.saveProp("selectedPort", port);
        this.selectedPort = port;
    }

    public String getSelectedPort() {
        return selectedPort;
    }

    public void setSelectedPort(String selectedPort) {
        this.selectedPort = selectedPort;
    }

    public void openPort()  {
        if(selectedPort!=null){
            S.log("Открываем порт: "+selectedPort);
            serialPort = new SerialPort(selectedPort);
            try{
                serialPort.openPort();
                serialPort.setParams(SerialPort.BAUDRATE_9600,
                        SerialPort.DATABITS_8,
                        SerialPort.STOPBITS_1,
                        SerialPort.PARITY_NONE);
                serialPort.addEventListener(new PortReader(), SerialPort.MASK_RXCHAR |
                        SerialPort.MASK_RXFLAG |
                        SerialPort.MASK_CTS |
                        SerialPort.MASK_DSR |
                        SerialPort.MASK_RLSD
                );
                open = true;
                SEvents.only.call(SListener.OPENCLOSE, open);
                S.log("Порт " + selectedPort + " открыт");
            } catch (Exception ex) {
                S.log("Не получается открыть "+selectedPort+", возможно порт занят другой программой");
            }
        } else{
            S.log("Порт не выбран");
        }
    }

    public void closePort()  {
        if(serialPort.isOpened())
        try {
            if(serialPort.closePort()){
                open = false;
                SEvents.only.call(SListener.OPENCLOSE,open);
                S.log("Порт успешно закрыт.");
            }
            else {
                S.log("Не получается закрыть порт");
            }
        }
        catch (Exception ex) {
            S.log(ex);
        }
    }

    public void sendString(String s) {
        try{
            S.log("Отправка комманды " + s);
            serialPort.writeBytes(s.getBytes());
            SEvents.only.call(SListener.ONSEND,s);
        } catch (Exception ex){
            S.log(ex);
        }
    }




    private class PortReader implements SerialPortEventListener {
        private String str = "";
        private long count=0;
        private long countTimer=0;
        Timer timer;
        TimerTask task;

        private Boolean reading = false;
        public void serialEvent(SerialPortEvent event) {

            if(event.isRXFLAG()||event.isRXCHAR())
                if (event.getEventValue() > 0){

                try {
                    //Получаем ответ от устройства, обрабатываем данные и т.д.

                    byte[] buffer = serialPort.readBytes(event.getEventValue());
                    str += new String(buffer);

                    count++;
                    if (!reading){
                        reading = true;
                        timer = new Timer();
                        task = new TimerTask() {
                            public void run()
                            {
                                countTimer++;
                                if (countTimer==count){
                                    S.trace("-----\n" +str);
                                    SEvents.only.call(SListener.ONDATA,str);
                                    str = "";
                                    countTimer = 0;
                                    count = 0;
                                    task.cancel();
                                    timer.cancel();
                                    timer = null;
                                    task = null;
                                    reading = false;
                                }
                            }
                        };
                        timer.scheduleAtFixedRate(task,30,200);
                    }
                }
                catch (SerialPortException ex) {
                    System.out.println(ex);
                }
            }


        }
    }
}

