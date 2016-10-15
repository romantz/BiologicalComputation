package main;

import logic.Cell;
import logic.Grid;
import logic.Person;
import utils.Constants;
import utils.Sex;

import javax.swing.*;
import java.util.Collection;
import java.util.Collections;
import java.util.LinkedList;

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
        grid.getPeople().forEach(p -> p.move());

        grid.getPeople().forEach(p -> {
            if(p.isSingle() && p.getNextCell() == null) {
                LinkedList<Cell> neighbors = p.getNeighborCells();
                for(Cell n: neighbors){
                    Person other = null;
                    switch(p.getSex()){
                        case MALE:
                            other = n.getCurrentFemaleOccupier();
                            break;
                        case FEMALE:
                            other = n.getCurrentMaleOccupier();
                            break;
                    }

                    if(other != null){
                        if(other.getNextCell() == null) {
                            if (other.isSingle()) {
                                p.setNextCell(other.getCurrentCell());
                                other.setNextCell(other.getCurrentCell());
                                break;
                            }
                            else if (Constants.CAN_CHANGE_PARTNER && other.getMatchValue() < p.getMatchValue(other)) {
                                other.setNextCell(p.getCurrentCell());
                                p.setNextCell(p.getCurrentCell());
                                break;
                            }
                        }
                    }
                }

                if (p.getNextCell() == null) {
                    Cell nextCellByLastDir = grid.getCell(p.getX() + p.getLastXDir(), p.getY() + p.getLastYDir());
                    if(Math.random() < Constants.CHANCE_TO_KEEP_GOING_IN_THE_SAME_DIRECTION && (p.getLastXDir() != 0 || p.getLastYDir() != 0) && nextCellByLastDir != null && nextCellByLastDir.isEmpty()){
                        if((p.getSex() == Sex.MALE && nextCellByLastDir.getNextMaleOccupier() == null) ||
                            (p.getSex() == Sex.FEMALE && nextCellByLastDir.getNextFemaleOccupier() == null))
                            p.setNextCell(nextCellByLastDir);
                    }
                    else {
                        Collections.shuffle(neighbors);
                        for (Cell n : neighbors) {
                            if (p.getSex() == Sex.MALE) {
                                if (n.getNextMaleOccupier() == null)
                                    if (n.getCurrentMaleOccupier() == null || n.getCurrentMaleOccupier().getNextCell() != null)
                                        p.setNextCell(n);
                                break;
                            } else if (n.getNextFemaleOccupier() == null)
                                if (n.getCurrentFemaleOccupier() == null || n.getCurrentFemaleOccupier().getNextCell() != null)
                                    p.setNextCell(n);
                            break;
                        }
                    }
                    if (p.getNextCell() == null) {
                        p.setNextCell(p.getCurrentCell());
                    }
                }
            }
        });
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
