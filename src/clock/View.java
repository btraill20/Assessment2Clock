package clock;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.InputEvent;
import javax.swing.*;
import java.util.Observer;
import java.util.Observable;
import java.util.logging.Level;
import java.util.logging.Logger;
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
                Savedialog();
            }
            });
        menu.add(alarm);
        
        JMenuItem savedalarm = new JMenuItem("Saved Alarms");
        savedalarm.setAccelerator(KeyStroke.getKeyStroke('S',InputEvent.CTRL_MASK));
        savedalarm.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ev) {
              SavedDialog();
            }
            });
        menu.add(savedalarm);
        
        //menu item to delete any exsisting alarms, if none exsist then will popiup with a message.
        JMenuItem deletealarm = new JMenuItem("Delete Alarm");
        deletealarm.setAccelerator(KeyStroke.getKeyStroke('D',InputEvent.CTRL_MASK));
        deletealarm.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ev) {
                DeleteDialog();
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
                Savedialog();
            }
        });
        
        JButton button2 = new JButton("Saved ALarms");
        button2.setPreferredSize(new Dimension(150, 75));
        button2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
              //calls a function to show a dialog box ("printing the queue") of all saved alarms
              SavedDialog();
            }
        });
        
        JButton button3 = new JButton("Delete Alarms");
        button3.setPreferredSize(new Dimension(150, 75));
        button3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
              //a pop-up dialog to allow the user to delete a alarm
              DeleteDialog();
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
 
    //method for saving alarms through a dialog.
    public void Savedialog(){
        int priority = 0;
        JPanel SApanel = new JPanel();
        lb = new Label("Input Alarm Below");
        dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        dialog.setModal(true);
            
        final SpinnerModel hour =  new SpinnerNumberModel (12, 0, 23, 1); 
        final JSpinner hspinner = new JSpinner(hour);   
        hspinner.setBounds(100,100,50,30);    
        SApanel.add(hspinner, BorderLayout.CENTER); 
            
        final SpinnerModel minute =  new SpinnerNumberModel (1, 1, 60, 1); 
        final JSpinner mspinner = new JSpinner(minute);   
        mspinner.setBounds(100,100,50,30);    
        SApanel.add(mspinner, BorderLayout.CENTER);    
            
        final SpinnerModel second =  new SpinnerNumberModel (1, 1, 60, 1); 
        final JSpinner sspinner = new JSpinner(second);   
        sspinner.setBounds(100,100,50,30);    
        SApanel.add(sspinner, BorderLayout.CENTER);  
            
        //adds a save alarm button to the frame
        JButton saveButton  = new JButton("Save Alarm");
        SApanel.add(saveButton, BorderLayout.SOUTH);
        saveButton.setPreferredSize(new Dimension(100, 50));
        saveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                q = new UnsortedListqueue<>(8);
                Object o1 = hspinner.getValue();
                Object o2 = mspinner.getValue();
                Object o3 = sspinner.getValue();
                Number n1 = (Number) o1;
                Number n2 = (Number) o2;
                Number n3 = (Number) o3;
                int i1 = n1.intValue();
                int i2 = n2.intValue();
                int i3 = n3.intValue();
                Alarms alarm = new Alarms(i1,i2,i3);
                //need to review priority increment as it sets all alarms at priority 1
                int Pvalue = 0;
                int priority = ++Pvalue;
                System.out.println("Adding " + alarm.getAlarm() + " with priority " + priority);
                try {
                    q.add(alarm, priority);
                } catch (QueueOverflowException e) {
                    System.out.println("Add operation failed: " + e);
                }
            }
        });

        //adds a close button to the frame
        JButton closeButton  = new JButton("Close");
        SApanel.add(closeButton, BorderLayout.SOUTH);
        closeButton.setPreferredSize(new Dimension(100, 50));
        closeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                dialog.dispose();
            }
        });
            
        dialog.setPreferredSize(new Dimension(250, 175));
        dialog.add(SApanel, BorderLayout.CENTER);
        dialog.add(lb, BorderLayout.NORTH);
        dialog.pack();
        dialog.setLocation(200, 200);
        dialog.setTitle("Set Alarm");
        dialog.setVisible(true);
    }
    
    //method for displaying a disalog to delete any saved alarms
    public void DeleteDialog(){
        JPanel SApanel = new JPanel();
        lb = new Label("Select alarm to remove");
        dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        dialog.setModal(true);
            
        //adds a save alarm button to the frame
        JButton saveButton  = new JButton("Confirm Delete");
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
        dialog.add(lb, BorderLayout.PAGE_START);
        
        dialog.pack();
        dialog.setLocation(200, 200);
        dialog.setTitle("Remove Alarm");
        dialog.setVisible(true);
    }
    
        //method for displaying all current alarms if any exsist
        public void SavedDialog(){
            JPanel SApanel = new JPanel();
            lb = new Label("All current alarms below");
            dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
            dialog.setModal(true);
            
            JTextField textfield = new JTextField(10);
            SApanel.add(textfield, BorderLayout.CENTER);

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
            dialog.setTitle("Currently saved alarms");
            dialog.setVisible(true);
    }
}
