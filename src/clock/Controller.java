package clock;

import java.applet.*;
import javax.sound.sampled.*;
import java.awt.event.*;
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
    
    public Controller(Model m, View v) {
        model = m;
        view = v;
        
        listener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                model.update();
            }
        };
        
        timer = new Timer(100, listener);
        timer.start();
    }
    
    //used to update labels, example would be the size of the queue
    public void updateView(){				
        model.changeText();
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
    
    public void alarmtime(){
    for(int i = 0; i< 10; i++) {
    try {
        Thread.sleep(1000);
    } catch(InterruptedException ie) {}
        alarm();
    }
    }

    //used to play an alarm
    public void alarm(){
        URL url = getClass().getResource("/Audio/alarmaudio.wav");
        AudioClip clip = Applet.newAudioClip(url);
        for(int i = 0; i< 10; i++) {
            try {
                clip.play();
                Thread.sleep(5000);
            } catch(InterruptedException ie) {}
                clip.stop();
            }
        }   
}
