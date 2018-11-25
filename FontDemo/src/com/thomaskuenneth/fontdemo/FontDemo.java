package com.thomaskuenneth.fontdemo;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.GraphicsEnvironment;
import java.util.Arrays;
import javax.swing.Box;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ListSelectionModel;
import javax.swing.SwingUtilities;

/**
 *
 * @author Thomas Kuenneth
 */
public final class FontDemo extends JFrame {

    private JList<String> fontList;
    private JLabel label;

    public FontDemo() {
        super("Font Demo");
        JPanel cp = new JPanel(new BorderLayout());
        addLabel(cp);
        int index = addFontList(cp);
        fontList.setSelectedIndex(index);
        setContentPane(cp);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        pack();
        setSize(800, 400);
    }

    private void addLabel(JPanel p) {
        label = new JLabel();
        label.setAlignmentX(CENTER_ALIGNMENT);
        Box b = Box.createVerticalBox();
        b.add(Box.createVerticalGlue());
        b.add(label);
        b.add(Box.createVerticalGlue());
        p.add(b, BorderLayout.CENTER);
    }

    private int addFontList(JPanel p) {
        GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
        String[] names = ge.getAvailableFontFamilyNames();
        Arrays.sort(names);
        fontList = new JList<>(names);
        fontList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        fontList.addListSelectionListener((e) -> {
            int index = fontList.getSelectedIndex();
            String name = fontList.getModel().getElementAt(index);
            Font font = Font.decode(name);
            label.setFont(font.deriveFont(72f));
            label.setText(font.getName());
        });
        JScrollPane sp = new JScrollPane(fontList,
                JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
                JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        p.add(sp, BorderLayout.WEST);
        for (int i = 0; i < names.length; i++) {
            if (names[i].toLowerCase().contains("lucida")) {
                return i;
            }
        }
        return (int) (Math.random() * names.length);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            FontDemo fd = new FontDemo();
            fd.setLocationRelativeTo(null);
            fd.setVisible(true);
        });
    }
}
