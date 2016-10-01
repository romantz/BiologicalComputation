package main;

import utils.Constants;

import javax.swing.*;

/**
 * Created by roman on 10/1/16.
 */
public class Runner {
    Canvas canvas;

    public Runner(){
        canvas = init();
    }

    public void run(){
        while(true) {
            try {
                Thread.sleep(Constants.SLEEP_MILIS);
                canvas.setPosx(canvas.getPosx() + 1);
                canvas.repaint();
            } catch (Exception e){
                e.printStackTrace();
            }
        }
    }

    public Canvas init(){
        JFrame window = new JFrame();
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        window.setBounds(Constants.WINDOW_X_LOCATION,
                Constants.WINDOW_Y_LOCATION,
                Constants.WINDOW_WIDTH,
                Constants.WINDOW_HEIGHT);

        Canvas canvas = new Canvas();
        window.getContentPane().add(canvas);
        window.setVisible(true);

        return canvas;
    }

}
