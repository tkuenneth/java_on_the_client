package com.thomaskuenneth.webstartapp;

import java.awt.Dimension;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.net.URL;
import javax.jnlp.BasicService;
import javax.jnlp.ServiceManager;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.SwingUtilities;

/**
 * Important: Request unrestricted access MUST be enabled.
 *
 * @author tkuenneth
 */
public final class WebStartApp extends JFrame {

    public WebStartApp() {
        super("WebStartApp");
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        addWindowListener(new WindowAdapter() {

            @Override
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });
        Box box = Box.createVerticalBox();
        box.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        JLabel label = new JLabel("Hello World");
        label.setAlignmentX(CENTER_ALIGNMENT);
        box.add(label);
        box.add(Box.createRigidArea(new Dimension(0, 10)));
        JButton button = new JButton("https://openjdk.java.net/");
        button.setAlignmentX(CENTER_ALIGNMENT);
        button.addActionListener((e) -> {
            try {
                BasicService bs = (BasicService) ServiceManager.lookup("javax.jnlp.BasicService");
                bs.showDocument(new URL(button.getText()));
            } catch (Exception ex) {
                // we do not catch MalformedURLException and
                // UnavailableServiceException to avoid ClassNotFoundException
                // if UnavailableServiceException is not on classpath
            }
        });
        box.add(button);
        setContentPane(box);
        pack();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            WebStartApp app = new WebStartApp();
            app.setLocationRelativeTo(null);
            app.setVisible(true);
        });
    }
}
