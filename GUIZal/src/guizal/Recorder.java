/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package guizal;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JToggleButton;
import java.awt.GridLayout;

/**
 *
 * @author Krzysztof
 */
public class Recorder extends JFrame {
    
        private JButton[] buttons;
        private JToggleButton record = new JToggleButton("Recording", new ImageIcon("recording.png"));
        private JButton play = new JButton("Play");
        private List<Object> playlist = new ArrayList<>();
        
        public Recorder() {
            ActionListener recordAction = (ActionEvent ae) -> {
                playlist.add(ae.getSource());
            };
            buttons = new JButton[10];
           
            record.setSelectedIcon(new ImageIcon("started.jpg"));
            record.addActionListener((ActionEvent ae) -> {
                if (((JToggleButton) ae.getSource()).isSelected()){
                    play.setEnabled(false);
                    playlist.clear();
                    for (int i = 0; i < 10; i++) {
                        buttons[i].addActionListener(recordAction);
                    }
                } else {
                    for (int i = 0; i < 10; i++) {
                        buttons[i].removeActionListener(recordAction);
                    }
                    if (playlist.size() > 0) {
                        play.setEnabled(true);
                    }
                }
            });
            
            play.addActionListener((ActionEvent ae) -> {
                System.out.print("\n");
                playlist.forEach(item -> ((JButton) item).doClick());
            });
            
            JPanel pcon = new JPanel(new FlowLayout(FlowLayout.RIGHT));
            pcon.setBorder(BorderFactory.createLineBorder(Color.blue));
            pcon.add(play);
            pcon.add(record);
            getContentPane().add(pcon, "South");
            
            ActionListener buttAct = (ActionEvent ae) -> {
              System.out.println(ae.getActionCommand());
            };
            
            JPanel p = new JPanel(new GridLayout(3, 0));
            for (Integer i = 0; i < 10; i++) {
                buttons[i] = new JButton(i.toString());
                buttons[i].addActionListener(buttAct);
                p.add(i.toString(), buttons[i]);
            }
            
            getContentPane().add(p, "Center");
            getContentPane().setVisible(true);
        }
        
}
