package clock;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.InputEvent;
import javax.swing.*;
import java.util.Observer;
import java.util.Observable;
import javax.swing.text.MaskFormatter;

public class View implements Observer {
    
    private JDialog dialog = new JDialog();
    private Label lb;
    ClockPanel panel;
    PriorityQueue<Alarms> q;
    JFormattedTextField Time  = null;
    MaskFormatter timeFormatter  = null; 
    
    
    public View(Model model) {
        JFrame frame = new JFrame();
        panel = new ClockPanel(model);
        //frame.setContentPane(panel);
        frame.setTitle("Java Clock");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        //for holding the clock panel
        Container pane = frame.getContentPane();

          //for setting up the menu bar
        JMenuBar menubar = new JMenuBar();
        
        //setting the name of the menu in the menubar
        JMenu menu = new JMenu("Alarm-Menu");
                //for adding the menu bar to the frame
        menubar.add(menu);
        frame.setJMenuBar(menubar);
        
        //for adding items within the menu
        JMenuItem alarm = new JMenuItem("Set-Alarm");
        alarm.setAccelerator(KeyStroke.getKeyStroke('A',InputEvent.CTRL_MASK));
        alarm.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ev) {
                dialog();
            }
            });
        menu.add(alarm);
        
        JMenuItem savedalarm = new JMenuItem("Saved Alarms");
        savedalarm.setAccelerator(KeyStroke.getKeyStroke('S',InputEvent.CTRL_MASK));
        savedalarm.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ev) {
              
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
      
        //setting up the subpanel to hold the buttons
        JPanel subPanel = new JPanel();
        
        //For setting up buttons on the inital panel with sizing and text.
        JButton button = new JButton("Set Alarm");
        button.setPreferredSize(new Dimension(150, 75));
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                dialog();
            }
        });
        
        JButton button2 = new JButton("Saved ALarms");
        button2.setPreferredSize(new Dimension(150, 75));
        button2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
              //calls a function to show a dialog box ("printing the queue") of all saved alarms
            }
        });
        
        JButton button3 = new JButton("Delete Alarms");
        button3.setPreferredSize(new Dimension(150, 75));
        button3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
              //a pop-up dialog to allow the user to delete a alarm
              
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
 
    public void dialog(){
            JPanel SApanel = new JPanel();
            lb = new Label("Input Alarm Below");
            dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
            dialog.setModal(true);
            
            JTextField textfield = new JTextField(10);
            SApanel.add(textfield);
            
            //adds a save alarm button to the frame
            JButton saveButton  = new JButton("Save Alarm");
            SApanel.add(saveButton, BorderLayout.SOUTH);
            saveButton.setPreferredSize(new Dimension(100, 100));
            saveButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent actionEvent) {
                q = new UnsortedListqueue<>(8);
                }
            });

            //adds a close button to the frame
            JButton closeButton  = new JButton("Close");
            SApanel.add(closeButton, BorderLayout.SOUTH);
            closeButton.setPreferredSize(new Dimension(100, 100));
            closeButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent actionEvent) {
                dialog.dispose();
                }
            });
            
            dialog.add(SApanel, BorderLayout.CENTER);
            dialog.add(lb, BorderLayout.NORTH);
        
            dialog.pack();
            dialog.setLocation(200, 200);
            dialog.setTitle("Set Alarm");
            dialog.setVisible(true);
    }
    
}
