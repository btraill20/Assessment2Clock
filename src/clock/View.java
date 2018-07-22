package clock;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.InputEvent;
import javax.swing.*;
import java.util.Observer;
import java.util.Observable;


public class View implements Observer {
    
    Label lb;
    JLabel l;
    ClockPanel panel;
    
    PriorityQueue<Alarms> q;

    public View(Model model) {
        q = new UnsortedListqueue<>(8);
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
        
        JMenuItem savedalarm = new JMenuItem("Edit Alarms");
        savedalarm.setAccelerator(KeyStroke.getKeyStroke('S',InputEvent.CTRL_MASK));
        savedalarm.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ev) {
              EditDialog();
            }
            });
        menu.add(savedalarm);
        
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
        
        //Adds a button to make new alarms
        JButton button = new JButton("Set Alarm");
        button.setBackground(new Color(59, 89, 182));
        button.setForeground(Color.WHITE);
        button.setFont(new Font("Courier New", Font.PLAIN, 12));
        button.setPreferredSize(new Dimension(150, 50));
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                Savedialog();
            }
        });
        
        JButton button2 = new JButton("Edit Alarms");
        button2.setBackground(new Color(59, 89, 182));
        button2.setForeground(Color.WHITE);
        button2.setFont(new Font("Courier New", Font.PLAIN, 12));
        button2.setPreferredSize(new Dimension(150, 50));
        button2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
              //calls a new dialog to be created for the user to edit all current alarms
              EditDialog();
            }
        });
        
        JButton button3 = new JButton("Exit");
        button3.setBackground(new Color(59, 89, 182));
        button3.setForeground(Color.WHITE);
        button3.setFont(new Font("Courier New", Font.PLAIN, 12));
        button3.setPreferredSize(new Dimension(150, 50));
        button3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
              //exits the program
              System.exit(0);
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
        panel.setPreferredSize(new Dimension(300, 200));
        pane.add(panel, BorderLayout.CENTER);

        //sets up a panel for the label to show the amount of saved alarms
        JPanel labelpane = new JPanel();
        l = new JLabel("Number of saved alarms:"+q.size()); 
        l.setFont(new Font("Courier New", Font.ITALIC, 12));
        l.setPreferredSize(new Dimension(175, 50));
        l.setForeground(Color.blue);
        labelpane.add(l);
        pane.add(labelpane,BorderLayout.SOUTH);
        
        //makes the frame visible and location
        frame.setLocation(100, 100);
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
        final JDialog dialog = new JDialog();
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
            
        //adds a save alarm button 
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
                    dialog.dispose();
                } catch (QueueOverflowException e) {
                    System.out.println("Add operation failed: " + e);
                }
            }
        });

        //adds a close button
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
    
        //method for Editing all current alarms
        public void EditDialog(){
        q = new UnsortedListqueue<>(8);
        JPanel Epanel = new JPanel();
        final JDialog dialog2 = new JDialog();
        lb = new Label("Edit your alarm below");
        dialog2.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        dialog2.setModal(true);
        
        DefaultListModel<String> model = new DefaultListModel<>();
        model.addElement(toString());
        final JList<String> list = new JList<>(model);
        
        list.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);
        list.setVisibleRowCount(5);
        list.setPreferredSize(new Dimension(200,100));
        
        JScrollPane listScroller = new JScrollPane(list);
        listScroller.setPreferredSize(new Dimension(100, 80));
        Epanel.add(list, BorderLayout.CENTER);
        Epanel.add(new JScrollPane(list));

        //adds a edit button
        JButton editButton  = new JButton("Edit");
        Epanel.add(editButton, BorderLayout.SOUTH);
        editButton.setPreferredSize(new Dimension(75, 50));
        editButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                editalarmdialog();
            }
        });
        
        //adds a Delete button
        JButton deleteButton  = new JButton("Delete");
        Epanel.add(deleteButton, BorderLayout.SOUTH);
        deleteButton.setPreferredSize(new Dimension(75, 50));
        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                q = new UnsortedListqueue<>(8);
                try {
                    String time = q.head().getAlarm();
                    System.out.println("Removing " + time + " from the head of the queue");
                    q.remove();
                } catch (QueueUnderflowException e) {
                    System.out.println("Can't remove head of queue: " + e);
                }
            }
        });
            
        //adds a close button
        JButton closeButton  = new JButton("Close");
        Epanel.add(closeButton, BorderLayout.SOUTH);
        closeButton.setPreferredSize(new Dimension(75, 50));
        closeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                dialog2.dispose();
            }
        });
        
        dialog2.setPreferredSize(new Dimension(400, 250));
        dialog2.add(Epanel, BorderLayout.CENTER);
        dialog2.add(lb, BorderLayout.NORTH);
        dialog2.pack();
        dialog2.setLocation(200, 200);
        dialog2.setTitle("Edit personal alarm");
        dialog2.setVisible(true);
    }
 
    //inner dialog for changing alarms already made.
    public void editalarmdialog(){
        JPanel pane = new JPanel();
        final JDialog innerdialog = new JDialog();
        lb = new Label("Change your saved alarm below or cancel");
        Label label = new Label("select new time for your alarm");
        innerdialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        innerdialog.setModal(true);

        final SpinnerModel hour =  new SpinnerNumberModel (12, 0, 23, 1); 
        final JSpinner hspinner = new JSpinner(hour);   
        hspinner.setBounds(100,100,50,30);    
        pane.add(hspinner, BorderLayout.CENTER); 
            
        final SpinnerModel minute =  new SpinnerNumberModel (1, 1, 60, 1); 
        final JSpinner mspinner = new JSpinner(minute);   
        mspinner.setBounds(100,100,50,30);    
        pane.add(mspinner, BorderLayout.CENTER);    
            
        final SpinnerModel second =  new SpinnerNumberModel (1, 1, 60, 1); 
        final JSpinner sspinner = new JSpinner(second);   
        sspinner.setBounds(100,100,50,30);    
        pane.add(sspinner, BorderLayout.CENTER);  
        
        //adds a save alarm button 
        JButton saveButton  = new JButton("Save Alarm");
        pane.add(saveButton, BorderLayout.SOUTH);
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
                    innerdialog.dispose();
                } catch (QueueOverflowException e) {
                    System.out.println("Add operation failed: " + e);
                }
            }
        });
            
        //adds a close button
        JButton closeButton  = new JButton("Cancel");
        pane.add(closeButton, BorderLayout.SOUTH);
        closeButton.setPreferredSize(new Dimension(100, 50));
        closeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                innerdialog.dispose();
            }
        });
        
        innerdialog.setPreferredSize(new Dimension(250, 200));
        innerdialog.add(pane, BorderLayout.CENTER);
        innerdialog.add(lb, BorderLayout.NORTH);
        innerdialog.add(label, BorderLayout.NORTH);
        innerdialog.pack();
        innerdialog.setLocation(200, 200);
        innerdialog.setTitle("Edit Personal Alarm");
        innerdialog.setVisible(true);
    }
   
}
