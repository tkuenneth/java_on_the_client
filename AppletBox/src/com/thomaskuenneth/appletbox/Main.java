package com.thomaskuenneth.appletbox;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.SwingUtilities;

public class Main {
    
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            AppletBox appletBox = new AppletBox(args.length != 1 ? "com.thomaskuenneth.appletbox.DemoApplet" : args[0]);
            appletBox.addWindowListener(new WindowAdapter() {
                
                @Override
                public void windowClosing(WindowEvent e) {
                    appletBox.setVisible(false);
                    appletBox.dispose();
                    System.exit(0);
                }
            });
            appletBox.setLocationRelativeTo(null);
            appletBox.setVisible(true);
        });
    }
}
