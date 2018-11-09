package com.thomaskuenneth.appletbox;

import java.applet.Applet;
import java.applet.AppletContext;
import java.applet.AppletStub;
import java.applet.AudioClip;
import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Image;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JLabel;

public final class AppletBox extends JFrame implements AppletContext, AppletStub {

    static final Dimension DEFAULT_SIZE = new Dimension(400, 400);

    final JLabel status;
    final Map<String, InputStream> streams;
    final Applet _applet;

    AppletBox(String appletName) {
        super(appletName);
        streams = new HashMap();
        Container cp = getContentPane();
        cp.setLayout(new BorderLayout());
        status = new JLabel();
        status.setBorder(BorderFactory.createEmptyBorder(2, 4, 2, 4));
        cp.add(BorderLayout.SOUTH, status);
        _applet = loadApplet(appletName, DEFAULT_SIZE);
        if (_applet != null) {
            cp.add(BorderLayout.CENTER, _applet);
            pack();
            status.setSize(getSize().width, status.getSize().height);
            showStatus(String.format("%s loaded", appletName));
        }
        addWindowListener(new WindowAdapter() {

            @Override
            public void windowClosing(WindowEvent e) {
                _applet.stop();
                _applet.destroy();
            }

            @Override
            public void windowOpened(WindowEvent e) {
                _applet.init();
                _applet.start();
            }
        });
    }

    @Override
    public Applet getApplet(String an) {
        return _applet;
    }

    @Override
    public Enumeration<Applet> getApplets() {
        ArrayList<Applet> applets = new ArrayList<>();
        applets.add(_applet);
        return Collections.enumeration(applets);
    }

    @Override
    public AudioClip getAudioClip(URL u) {
        return null;
    }

    @Override
    public Image getImage(URL u) {
        return null;
    }

    @Override
    public void showDocument(URL u) {
    }

    @Override
    public void showDocument(URL u, String frame) {
    }

    @Override
    public void showStatus(String msg) {
        if (msg == null) {
            msg = "";
        }
        status.setText(msg);
    }

    @Override
    public void setStream(String key, InputStream stream) throws IOException {
        streams.put(key, stream);
    }

    @Override
    public InputStream getStream(String key) {
        return streams.get(key);
    }

    @Override
    public Iterator<String> getStreamKeys() {
        return streams.keySet().iterator();
    }

    @Override
    public void appletResize(int w, int h) {
    }

    @Override
    public AppletContext getAppletContext() {
        return this;
    }

    @Override
    public URL getCodeBase() {
        return getClass().getResource(".");
    }

    @Override
    public URL getDocumentBase() {
        return getCodeBase();
    }

    @Override
    public String getParameter(String name) {
        return null;
    }

    @Override
    public boolean isActive() {
        return true;
    }

    Applet loadApplet(String appletName, Dimension size) {
        Applet applet = null;
        try {
            Class ac = Class.forName(appletName);
            applet = (Applet) ac.newInstance();
            applet.setSize(size);
            applet.setPreferredSize(size);
            applet.setStub(this);
        } catch (ClassNotFoundException e) {
            showStatus(String.format("Could not load %s", appletName));
        } catch (IllegalAccessException | InstantiationException e) {
            showStatus(String.format("Could not instantiate %s", appletName));
        }
        return applet;
    }
}
