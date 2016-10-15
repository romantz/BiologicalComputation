package logic;

import utils.Constants;
import utils.Sex;
import utils.Utils;

import java.awt.*;
import java.util.LinkedList;

/**
 * Created by roman on 10/2/16.
 */
public class Person {

    private int number;
    private Sex sex;
    private Color color;
    private boolean foundPartner;
    private Cell currentCell;
    private Cell nextCell;

    public Person(Sex sex, int number, Cell cell){
        this.sex = sex;
        this.number = number;
        currentCell = cell;
        nextCell = cell;
        color = Utils.calculateColorByNumber(number);
        foundPartner = false;
        currentCell.setOccupier(this);
    }

    public Sex getSex(){
        return sex;
    }

    public int getX(){
        return currentCell.getX();
    }

    public int getY(){
        return currentCell.getY();
    }

    public int getNumber() {
        return number;
    }

    public boolean isOpositeSex(Person other){
        return other.getSex() != this.getSex();
    }

    public Color getColor(){
        return color;
    }

    public void setNextCell(Cell cell){
        nextCell = cell;
        switch (sex) {
            case MALE:
                nextCell.setNextMaleOccupier(this);
                break;
            case FEMALE:
                nextCell.setNextFemaleOccupier(this);
                break;
        }
    }

    public Cell getNextCell(){
        return nextCell;

    }

    public void setCurrentCell(Cell cell) {
        this.currentCell = cell;
    }

    public Cell getCurrentCell(){
        return currentCell;
    }

    public void move(){
        if(nextCell != null) {
            System.out.println(sex + " at " + currentCell + " moving to " + nextCell);
            switch (sex) {
                case MALE:
                    currentCell.setNextMaleOccupier(null);
                    nextCell.setNextMaleOccupier(null);
                    currentCell.setCurrentMaleOccupier(null);
                    nextCell.setCurrentMaleOccupier(this);
                    break;
                case FEMALE:
                    currentCell.setNextFemaleOccupier(null);
                    nextCell.setNextFemaleOccupier(null);
                    currentCell.setCurrentFemaleOccupier(null);
                    nextCell.setCurrentFemaleOccupier(this);
                    break;
            }
            currentCell = nextCell;
            nextCell = null;
        }
    }

    public boolean isSingle(){
        return getOppositeSexOccupier() == null;
    }

    private Person getOppositeSexOccupier(){
        if (sex == Sex.MALE)
            return currentCell.getCurrentFemaleOccupier();
        return currentCell.getCurrentMaleOccupier();
    }

    public int getMatchValue(){
        return Constants.RANDOM_NUMBER_MAX - Math.abs(getNumber() - getOppositeSexOccupier().getNumber());
    }

    public int getMatchValue(Person other){
        return Constants.RANDOM_NUMBER_MAX - Math.abs(getNumber() - other.getNumber());
    }

    public void applyMove(){
    //    setCurrentCell(getNextCell());
    }

    public LinkedList<Cell> getNeighborCells(){
        return currentCell.getNeighbors();
    }
}
