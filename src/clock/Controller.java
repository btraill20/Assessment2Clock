package clock;

import javax.sound.sampled.*;
import java.awt.event.*;
import java.io.IOException;
import java.net.URL;
import javax.swing.Timer;

/*acts on both view and model and controls data flow into 
*model objects while updateing the view, keeps both model and view seperate.
*/
public class Controller<T> {
    
    ActionListener listener, listenerA;
    Timer timer, timerA;
    
    Model model;
    View view;
    
    AudioInputStream audioInputStream;
    Clip clip;
    
    boolean alarmOn = false;
    private Nodes<T> top;
    
    public Controller(Model m, View v) throws QueueUnderflowException {
        model = m;
        view = v;
        
        alarm();
        
        listener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                model.update();
                
            }
        };
        
        timer = new Timer(100, listener);
        timer.start();
        
    }



    public final void alarm() throws QueueUnderflowException{
        for(Nodes<T> node = top; node != null; node = node.getNext()){
            final int ht = node.getHour();
            final int mt = node.getMinute();
            final int st = node.getSecond();
            final int h = model.hour;
            final int m = model.minute;
            final int s = model.second; 
            listenerA = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {          
                if (h == ht && m == mt && s == st){
                    alarmOn = true;
                    try {
                        playalarm();
                        view.alarmdialog();
                    } catch (QueueUnderflowException ex) {
                    }
                    
                }
                }
            };
        timerA = new Timer(100, listenerA);
        timerA.start(); 
        }
    }         
    //used to play an alarm
    public void playalarm() throws QueueUnderflowException{
        if(alarmOn == true){
            try{  
            view.alarmdialog();
            URL url = this.getClass().getClassLoader().getResource("/Audio/alarmaudio.wav");
            AudioInputStream audioIn = AudioSystem.getAudioInputStream(url);
            Clip clip = AudioSystem.getClip();
            clip.open(audioIn);
            this.clip.start();
            } catch (UnsupportedAudioFileException | LineUnavailableException | IOException e) {
            }
        }
    }
}