package clock;

import java.awt.*;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.ParseException;
import javax.swing.text.MaskFormatter;
import javax.swing.text.NumberFormatter;

public class SetAlarm {
    
    PriorityQueue<Alarms> q;
    JFormattedTextField Time  = null;
    MaskFormatter timeFormatter  = null; 
    
    public SetAlarm(){
        final JFrame alarmframe = new JFrame();
        alarmframe.setTitle("Set Alarm");
        alarmframe.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        Container contentPane = alarmframe.getContentPane();
        
        JPanel panel = new JPanel();
        
        JLabel label  = new JLabel("Set personal Alarm.");
        alarmframe.add(label, BorderLayout.NORTH);
        
        NumberFormat numFormat = new DecimalFormat("$#0,000.00"); 
        NumberFormatter  numFormatter  = new NumberFormatter(numFormat); 
        Time = new JFormattedTextField(numFormatter);
        try  {
            timeFormatter = new MaskFormatter("###-##-####");
            timeFormatter.setPlaceholderCharacter('0');
            Time = new JFormattedTextField(timeFormatter);
        }
        catch (ParseException e)  {
            e.printStackTrace();
        }
                
        alarmframe.add(Time, BorderLayout.NORTH);
        
        //adds a close button to the frame
        JButton closeButton  = new JButton("Close Window");
        contentPane.add(closeButton, BorderLayout.SOUTH);
        closeButton.setPreferredSize(new Dimension(100, 100));
        closeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
              alarmframe.dispose();
            }
        });
        
        //adds a save alarm button to the frame
        JButton saveButton  = new JButton("Save Alarm");
        contentPane.add(saveButton, BorderLayout.SOUTH);
        saveButton.setPreferredSize(new Dimension(100, 100));
        saveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
            q = new UnsortedListqueue<>(8);
            }
        });

        panel.setPreferredSize(new Dimension(250, 200));
        contentPane.add(panel, BorderLayout.CENTER);
        
        alarmframe.pack();
        alarmframe.setVisible(true); 
    }
    
}
