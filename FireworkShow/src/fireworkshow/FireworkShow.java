/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fireworkshow;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.Timer;
/**
 *
 * @author Brandon Johnson
 */
public class FireworkShow extends JFrame implements Runnable {

    public FireworkPanel panel;
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
		new Timer(33, new ActionListener() {
			FireworkShow f = new FireworkShow();
			
		    public void actionPerformed(ActionEvent e) {
		    	if (f.panel.sparksLeft() > 0) {
		    		f.repaint();
		    	}
		    }
		}).start();
	}
    
        public void run() {
            this.repaint();
        }
    
    
    
    public FireworkShow() {
        super();
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);

        this.panel = new FireworkPanel();

        this.setContentPane(panel);

        this.pack();
        this.validate();
        this.setVisible(true);
    }

}
