package logic;

import utils.Sex;
import utils.Utils;

import java.awt.*;

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
        nextCell = null;
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

    public void setNextLocation(Cell cell){
        nextCell = cell;
    }

    public Cell getNextLocation(){
        return nextCell;
    }

    public void setCurrentLocation(Cell cell) {
        this.currentCell = cell;
    }

    public void move(int deltaX, int deltaY){
    //    setNextLocation(new Coord(getX() + deltaX, getY() + deltaY));
    }

    public void applyMove(){
    //    setCurrentLocation(getNextLocation());
    }

}
