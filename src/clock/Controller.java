package clock;

import javax.sound.sampled.*;
import java.awt.event.*;
import java.io.IOException;
import java.net.URL;
import javax.swing.Timer;

/*acts on both view and model and controls data flow into 
*model objects while updateing the view, keeps both model and view seperate.
*/
public class Controller {
    
    ActionListener listener;
    Timer timer;
    
    Model model;
    View view;
    
    AudioInputStream audioInputStream;
    Clip clip;
    
    boolean alarmOn = false;
    
    public Controller(Model m, View v) {
        model = m;
        view = v;
        
        listener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                model.update();
                try {
                    alarm();
                } catch (QueueUnderflowException ex) {
                }
            }
        };
        
        timer = new Timer(100, listener);
        timer.start();
        
    }
    
    public void setHour(int hour){
        model.setHour(hour);		
    }

    public int getHour(){
        return model.getHour();		
    }
   
    public void setMinute(int minute){
        model.setMinute(minute);		
    }

    public int getMinute(){
        return model.getMinute();		
    }
    
    public void setSecond(int second){
        model.setSecond(second);		
    }

    public int getSecond(){
        return model.getSecond();		
    }

    public final void alarm() throws QueueUnderflowException{
        final int h = model.hour;
        final int ht = model.hourtime;
        final int m = model.minute;
        final int mt = model.minutetime;
        final int s = model.second;
        final int st = model.secondtime;
//        Timer timer2 = new Timer(){
//            public void run(){
//                if (h == ht && m == mt && s == st)
//                    alarmOn = true;
//
//            }
//        };         
    }         
    //used to play an alarm
    public void playalarm() throws QueueUnderflowException{
        if(alarmOn == true){
        try{  
        view.EditDialog();
        URL url = this.getClass().getClassLoader().getResource("../Audio/alarmaudio.wav");
        AudioInputStream audioIn = AudioSystem.getAudioInputStream(url);
        Clip clip = AudioSystem.getClip();
        clip.open(audioIn);
        this.clip.start();
        } catch (UnsupportedAudioFileException | LineUnavailableException | IOException e) {
            e.printStackTrace();
        }
        }
        }
    
    public void stopalarm(){
        this.clip.stop();
    }
}