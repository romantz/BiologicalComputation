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
    private Coord coord;
    private Coord newCoord;

    public Person(Sex sex, int number, int x, int y){
        this.sex = sex;
        this.number = number;
        coord = new Coord(x, y);
        newCoord = new Coord(x, y);
        color = Utils.calculateColorByNumber(number);
        foundPartner = false;
    }

    public Sex getSex(){
        return sex;
    }

    public int getX(){
        return coord.getX();
    }

    public int getY(){
        return coord.getY();
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

    public boolean getFoundPartner() {
        return foundPartner;
    }

    public void setFoundPartner(boolean foundPartner){
        this.foundPartner = foundPartner;
    }

    public void setNewCoord(Coord coord){
        newCoord = coord;
    }

    public Coord getNewCoord(){
        return newCoord;
    }

    public void setCoord(Coord coord) {
        this.coord = new Coord(coord.getX(), coord.getY());
    }

    public void move(int deltaX, int deltaY){
        setNewCoord(new Coord(getX() + deltaX, getY() + deltaY));
    }

    public void applyMove(){
        setCoord(getNewCoord());
    }

}
