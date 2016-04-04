package test;



import com.ward.Console;
import robot.*;

public class main {
    public static void main(String[] args) throws InterruptedException {
        Console c = new Console();
        c.build(500, 500, "Robot Testing");
        
        Joystick j = new Joystick("Logitech Extreme 3D");
        j.connect();
        
        Arduino a = new Arduino("Uno", 115200);
        //a.connect();
        
        Motor m = new Motor("One", 3, a);
        Servo s = new Servo("Servo 1", 3, a);
        
        while(true){
            m.setValueAxisValue(j.getX());
            System.out.println(a.getOutput());
            Thread.sleep(10);
        }
        
    }
}
