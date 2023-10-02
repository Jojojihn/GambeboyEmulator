package main.gui;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Objects;

public class GUI extends JFrame{
    private int xOffset, yOffset;
    private boolean dragSpace;
    JLabel screen = new JLabel("Screen");

    public GUI() {
        super("Gambeboy");
        setSize(340,570);
        setUndecorated(true);
        setBackground(new Color(0, 40, 0, 0));
        JLabel overlay = new JLabel();
        BufferedImage overlayImage = null;
        try {
            overlayImage = ImageIO.read(Objects.requireNonNull(GUI.class.getResourceAsStream("/images/Gambeboy.png")));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        setLayout(null);
        overlay.setIcon(new ImageIcon(overlayImage));
        overlay.setBounds(0,0,340,570);
        JPanel screenPanel = new JPanel();
        screenPanel.setBackground(new Color(0,40,0,255));
        screenPanel.setBounds(74,67+18,178,165);
        screen.setBounds(74,67+18,178,165);
        screen.setBackground(new Color(0,40,0,255));
        add(screen);
        add(overlay);
        add(screenPanel);

        overlay.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                xOffset = e.getX();
                yOffset = e.getY();
                dragSpace= yOffset <= 45&&yOffset>=17;
                if(xOffset>=41&&xOffset<=65&&yOffset<=22){
                    System.exit(69);
                }
            }
        });
        overlay.addMouseMotionListener(new MouseAdapter() {
            @Override
            public void mouseDragged(MouseEvent e) {
                if(dragSpace) {
                    int newX = e.getXOnScreen() - xOffset;
                    int newY = e.getYOnScreen() - yOffset;
                    setLocation(newX, newY);
                }
            }
        });

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
        setAlwaysOnTop(true);

    }

    public void renderScreen(){
        //TODO: Implement the changing of screen Icon (argument given to function)
        screen.setText(screen.getText()+"A");
        revalidate();
    }

}
