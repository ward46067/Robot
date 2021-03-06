package robot.devices;

import com.github.sarxos.webcam.Webcam;
import com.github.sarxos.webcam.WebcamPanel;
import java.awt.Dimension;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import robot.util.Log;
import robot.Robot;

public class Camera {
    private boolean connected, wasConnected;
    private Log log;
    private String name;
    private Webcam cam;
    private Dimension size = new Dimension(320, 240);
    private WebcamPanel pan;
    private JPanel jPanel;
    private String title;
    private JLabel panTitle;
    
    public Camera(String n){
        name = n;
        title = name;
        log = new Log(name);
        
        log.write("Added a camera named: " + name);
        
        Webcam.setAutoOpenMode(true);
        
        Robot.add(this);
    }
    
    public void connect(){
        log.write("Connecting");
        if(Webcam.getWebcams().size() > 1){
            log.write("Multiple Webcams detected!");
            ArrayList<Webcam> matchCams = new ArrayList();
            
            log.write("Listing available cameras");
            for(int i = 0; i < Webcam.getWebcams().size(); i++){
                if(Webcam.getWebcams().get(i).getName().substring(0, Webcam.getWebcams().get(i).getName().length() - 2).equals(name)){
                    matchCams.add(Webcam.getWebcams().get(i));
                }
                log.write(Webcam.getWebcams().get(i).getName());
            }
            
            if(matchCams.size() == 1){
                cam = matchCams.get(0);
                cam.setViewSize(size);
                log.write("Connected to camera " + cam.getName());
                connected = true;
                wasConnected = true;
            } else if(matchCams.size() > 1){
                log.write("Multiple webcams with match names detected!");
                
                String msg = "Multiple camera's with the same name detected. Please select the camera below";
                for(int i = 0; i < matchCams.size(); i++){
                    msg += "\n" + i + ": " + matchCams.get(i).getName();
                }
                
                int index = Integer.parseInt(JOptionPane.showInputDialog(msg));
                cam = matchCams.get(index);
                log.write("Connected to camera " + cam.getName());
                connected = true;
                wasConnected = true;
            } else if(matchCams.size() == 0){
                log.Error("Camera called " + name + " was not found");
            }
            
        } else if(Webcam.getWebcams().size() == 1){
            cam = Webcam.getWebcams().get(0);
            log.write("Connected to camera " + cam.getName());
            connected = true;
            wasConnected = true;
        } else if(Webcam.getWebcams().size() == 0){
            log.Error("No cameras detected");
        }
        
        //System.out.println("Done");
        
        try{
            pan = new WebcamPanel(cam);
        } catch(Exception e) {
            log.crtError("Couldn't add camera to panel.");
            Logger.getLogger(Camera.class.getName()).log(Level.SEVERE, null, e);
        }
        
        log.write("Finished connecting");
        
        if(connected){
            //System.out.println(cam.getCustomViewSizes().length);
            for(int i = 0; i < cam.getViewSizes().length; i++){
                //System.out.println(cam.getCustomViewSizes()[i]);
            }
        }
        
    }
    
    public void close(){
        cam.close();
    }
    
    public void setSize(Dimension d){
        size = d;
    }
    
    public void setTitle(String t){
        
    }
    
    public void displayFPS(boolean b){
        pan.setFPSDisplayed(b);
    }
    
    public void debugMode(boolean b){
        pan.setDisplayDebugInfo(b);
    }
    
    public void displaySize(boolean b){
        pan.setImageSizeDisplayed(b);
    }
    
    public void mirrored(boolean b){
        pan.setMirrored(b);
    }
    
    public void setMaxSize(Dimension d){
        pan.setMaximumSize(d);
    }
    
    public void setMinSize(Dimension d){
        pan.setMaximumSize(d);
    }
    
    public List<Webcam> possibleCameras(){
        return Webcam.getWebcams();
    }
    public Webcam getCamera(){
        return cam;
    }
    
    public WebcamPanel getCameraPanel(){
        return pan;
    }
    
    public String getCamName(){
        return cam.getName();
    }
    
    public String getName(){
        return name;
    }
    
    public boolean connected(){
        return connected;
    }
    
    public boolean wasConnected(){
        return wasConnected;
    }
    
    public Dimension getSize(){
        return size;
    }
    
    public Dimension[] getPossibleSizes(){
        return cam.getCustomViewSizes();
    }
    
    public double getFPS(){
        return cam.getFPS();
    }
    
    public Dimension getMaxSize(){
        return pan.getMaximumSize();
    }
    
    public Dimension getMinSize(){
        return pan.getMinimumSize();
    }
    
    public BufferedImage getImage(){
        return cam.getImage();
    }
    
    
    
    

}
