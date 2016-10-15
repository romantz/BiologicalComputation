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
    long iterations = 0;

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
                iterations++;

            } catch (Exception e){
                e.printStackTrace();
            }
        }
    }

    public void recalculateLocations() {

        for(Person p: grid.getPeople()){
            p.move();
        }

        int totalHappiness = 0;
        for(Person p: grid.getPeople()) {
            totalHappiness += p.getMatchValue();
        }

        if(iterations % 10 == 0)
            System.out.println("Total happiness value is " + totalHappiness /
                    (double)(Constants.RANDOM_NUMBER_MAX * Constants.NUMBER_OF_PEOPLE_OF_EACH_GENDER * 2));

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
                    decideNextCell(p, neighbors, false);
                }
            } else if (!p.isSingle()) {
                if(p.getNextCell() == null && p.getPartner().getNextCell() == null) {
                    LinkedList<Cell> neighbors = p.getNeighborCells();
                    boolean swapped = false;
                    if(Constants.CAN_SWAP_PARTNERS){
                        for(Cell n: neighbors) {
                            Person otherFemale = n.getCurrentFemaleOccupier();
                            Person otherMale = n.getCurrentMaleOccupier();

                            if(n.containsPair() && otherFemale.getNextCell() == null &&
                                    otherMale.getNextCell() == null){
                                Person currentFemale = p.getCurrentCell().getCurrentFemaleOccupier();
                                Person currentMale = p.getCurrentCell().getCurrentMaleOccupier();

                                if(currentFemale.getMatchValue() + otherFemale.getMatchValue() <
                                        currentFemale.getMatchValue(otherMale) + currentMale.getMatchValue(otherFemale)){
                                    currentFemale.setNextCell(otherFemale.getCurrentCell());
                                    currentMale.setNextCell(currentMale.getCurrentCell());
                                    otherFemale.setNextCell(currentFemale.getCurrentCell());
                                    otherMale.setNextCell(otherMale.getCurrentCell());
                                    currentFemale.setSwapping();
                                    otherFemale.setSwapping();
                                    swapped = true;
                                }
                            }
                        }
                    }
                    if(Constants.CAN_MOVE_TOGETHER_WITH_PARTNER && !swapped && Math.random() < Constants.CHANCE_MOVE_AS_A_PAIR) {
                        Cell nextCell = decideNextCell(p, neighbors, true);
                        p.getPartner().setNextCell(nextCell);
                    }
                }
            }
        });
    }

    private Cell decideNextCell(Person p, LinkedList<Cell> neighbors, boolean movingAsPair){
        Cell nextCellByLastDir = grid.getCell(p.getX() + p.getLastXDir(), p.getY() + p.getLastYDir());
        if(Math.random() < Constants.CHANCE_TO_KEEP_GOING_IN_THE_SAME_DIRECTION && (p.getLastXDir() != 0 || p.getLastYDir() != 0)
                && nextCellByLastDir != null && nextCellByLastDir.isEmpty()){
            if(movingAsPair) {
                if (nextCellByLastDir.getNextMaleOccupier() == null &&
                        nextCellByLastDir.getNextFemaleOccupier() == null) {
                    p.setNextCell(nextCellByLastDir);
                    return nextCellByLastDir;
                }
            }
            else if(!movingAsPair &&
                    ((p.getSex() == Sex.MALE && nextCellByLastDir.getNextMaleOccupier() == null) ||
                    (p.getSex() == Sex.FEMALE && nextCellByLastDir.getNextFemaleOccupier() == null))) {
                p.setNextCell(nextCellByLastDir);
                return nextCellByLastDir;
            }
        }
        else {
            Collections.shuffle(neighbors);
            for (Cell n : neighbors) {
                if(movingAsPair){
                    if(n.isEmpty() && n.getNextMaleOccupier() == null && n.getNextFemaleOccupier() == null){
                        p.setNextCell(n);
                        return n;
                    }
                } else {
                    if (p.getSex() == Sex.MALE) {
                        if (n.getNextMaleOccupier() == null)
                            if (n.getCurrentMaleOccupier() == null || n.getCurrentMaleOccupier().getNextCell() != null) {
                                p.setNextCell(n);
                                return n;
                            }
                        break;
                    } else if (n.getNextFemaleOccupier() == null)
                        if (n.getCurrentFemaleOccupier() == null || n.getCurrentFemaleOccupier().getNextCell() != null) {
                            p.setNextCell(n);
                            return n;
                        }
                    break;
                }
            }
        }
        if (p.getNextCell() == null) {
            p.setNextCell(p.getCurrentCell());
        }

        return p.getCurrentCell();
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
