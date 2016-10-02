package main;

import logic.Grid;
import logic.Person;
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
                canvas.repaint();
                recalculateLocations();

            } catch (Exception e){
                e.printStackTrace();
            }
        }
    }

    public void recalculateLocations() {
        for (Person p: grid.getPeople()) {
            switch (p.getSex()){
                case MALE:
                    grid.getCell(p.getX(), p.getY()).setMaleOccupier(null);
                    grid.getCell(p.getNewCoord().getX(), p.getNewCoord().getY()).setMaleOccupier(p);
                    break;
                case FEMALE:
                    grid.getCell(p.getX(), p.getY()).setFemaleOccupier(null);
                    grid.getCell(p.getNewCoord().getX(), p.getNewCoord().getY()).setFemaleOccupier(p);
                    break;
            }

            p.applyMove();
            if(p.getX() < Constants.GRID_SIZE - 1){
                p.move(1,0);
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
