package com.thomaskuenneth.appletbox;

import java.applet.Applet;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import javax.swing.Timer;

public class DemoApplet extends Applet {

    private Timer timer;
    private Color col;

    @Override
    public void init() {
        super.init();
        col = Color.red;
        if (timer == null) {
            timer = new Timer(2000, (e) -> {
                if (Color.red.equals(col)) {
                    col = Color.blue;
                } else if (Color.blue.equals(col)) {
                    col = Color.green;
                } else if (Color.green.equals(col)) {
                    col = Color.yellow;
                } else {
                    col = Color.red;
                }
                repaint();
            });
        }
        timer.setInitialDelay(0);
        timer.setRepeats(true);
    }

    @Override
    public void destroy() {
        super.destroy();
        if (timer != null) {
            timer.stop();
            timer = null;
        }
    }

    @Override
    public void start() {
        super.start();
        if (timer != null) {
            timer.start();
        }
    }

    @Override
    public void stop() {
        super.stop();
        if (timer != null) {
            timer.stop();
        }
    }

    @Override
    public void paint(Graphics g) {
        Dimension d = getSize();
        int w = d.width;
        int h = d.height;
        int centerX = w / 2;
        int centerY = h / 2;
        g.setColor(Color.white);
        g.drawRect(0, 0, w, h);
        g.setColor(col);
        for (int x = 0; x < w - 1; x += 4) {
            g.drawLine(centerX, centerY, x, 0);
            g.drawLine(centerX, centerY, x, h - 1);
        }
        for (int y = 0; y < h - 1; y += 4) {
            g.drawLine(centerX, centerY, 0, y);
            g.drawLine(centerX, centerY, w - 1, y);
        }
    }
}
