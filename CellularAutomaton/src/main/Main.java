package main;

import javax.swing.*;

/**
 * Created by roman on 10/1/16.
 */
public class Main {
    public static void main(String[] args) {
        JFrame window = new JFrame();
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setBounds(30, 30, 300, 300);
        Canvas canvas = new Canvas();
        window.getContentPane().add(canvas);
        window.setVisible(true);

        while(true) {
            try {
                Thread.sleep(1000);
                canvas.setPosx(canvas.getPosx() + 1);
                canvas.repaint();
            } catch (Exception e){
                e.printStackTrace();
            }
        }
    }
}

