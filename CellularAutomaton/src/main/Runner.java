package main;

import logic.Grid;
import utils.Constants;

import javax.swing.*;

/**
 * Created by roman on 10/1/16.
 */
public class Runner {
    Canvas canvas;
    Grid grid;

    public Runner(Grid grid){
        canvas = init(grid);
        this.grid = grid;
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

    public Canvas init(Grid grid){
        JFrame window = new JFrame();
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        window.setBounds(Constants.WINDOW_X_LOCATION,
                Constants.WINDOW_Y_LOCATION,
                Constants.WINDOW_SIZE,
                Constants.WINDOW_SIZE + 22);

        Canvas canvas = new Canvas(grid);
        window.getContentPane().add(canvas);
        window.setVisible(true);

        return canvas;
    }

}
