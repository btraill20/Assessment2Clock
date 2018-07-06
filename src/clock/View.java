package clock;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import javax.swing.*;
import java.util.Observer;
import java.util.Observable;

public class View implements Observer {
    
    ClockPanel panel;
    
    public View(Model model) {
        JFrame frame = new JFrame();
        panel = new ClockPanel(model);
        //frame.setContentPane(panel);
        frame.setTitle("Java Clock");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        
        // I've just put a single button in each of the border positions:
        // PAGE_START (i.e. top), PAGE_END (bottom), LINE_START (left) and
        // LINE_END (right). You can omit any of these, or replace the button
        // with something else like a label or a menu bar. Or maybe you can
        // figure out how to pack more than one thing into one of those
        // positions. This is the very simplest border layout possible, just
        // to help you get started.
        
        //for holding the clock panel
        Container pane = frame.getContentPane();
        
        //for setting up the menu bar
        JMenuBar menubar = new JMenuBar();
        
        //setting the name of the menu in the menubar
        JMenu menu = new JMenu("Alarm-Menu");
        
        //for adding items within the menu
        JMenuItem alarm = new JMenuItem("Set-Alarm");
        alarm.setAccelerator(KeyStroke.getKeyStroke('A',InputEvent.CTRL_MASK));
        alarm.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ev) {
              System.exit(0);
            }
            });
        menu.add(alarm);
        
        JMenuItem savedalarm = new JMenuItem("Saved Alarms");
        savedalarm.setAccelerator(KeyStroke.getKeyStroke('S',InputEvent.CTRL_MASK));
        savedalarm.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ev) {
              System.exit(0);
            }
            });
        menu.add(savedalarm);
        
        //menu item to delete any exsisting alarms, if none exsist then will popiup with a message.
        JMenuItem deletealarm = new JMenuItem("Delete Alarm");
        deletealarm.setAccelerator(KeyStroke.getKeyStroke('D',InputEvent.CTRL_MASK));
        deletealarm.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ev) {
              
            }
            });
        menu.add(deletealarm);
        
        //menu item to quit the program
        JMenuItem quit = new JMenuItem("Quit Program");
        quit.setAccelerator(KeyStroke.getKeyStroke('Q',InputEvent.CTRL_MASK));
        quit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ev) {
              System.exit(0);
            }
            });
        menu.add(quit);

        //for adding the menu bar to the frame
        menubar.add(menu);
        frame.setJMenuBar(menubar);
        
        //setting up the subpanel to hold the buttons
        JPanel subPanel = new JPanel();
        
        //For setting up buttons on the inital panel with sizing and text.
        JButton button = new JButton("Set Alarm");
        button.setPreferredSize(new Dimension(150, 75));
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
               
            }
        });
        
        JButton button2 = new JButton("Saved ALarms");
        button2.setPreferredSize(new Dimension(150, 75));
        button2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
              //go to function to set alarms
            }
        });
        
        JButton button3 = new JButton("Delete Alarms");
        button3.setPreferredSize(new Dimension(150, 75));
        button3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
              //go to function to set alarms
            }
        });
        
        //adds the buttons to the subpanel
        subPanel.add(button);
        subPanel.add(button2);      
        subPanel.add(button3);
        
        //sub panel for inserting multiple buttons beside the clock
        subPanel.setPreferredSize(new Dimension(150, 150));
        pane.add(subPanel, BorderLayout.LINE_START);
  
        //size of the main clock panel
        panel.setPreferredSize(new Dimension(275, 275));
        pane.add(panel, BorderLayout.CENTER);

        //makes the frame visible
        frame.pack();
        frame.setVisible(true);
    }
    
    //updates the sizing of the window of the program.
    @Override
    public void update(Observable o, Object arg) {
        panel.repaint();
    }
    
}
