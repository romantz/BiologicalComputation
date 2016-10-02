package logic;

import utils.Constants;
import utils.Sex;

import java.util.LinkedList;

/**
 * Created by roman on 10/2/16.
 */
public class Grid{

    private int size;
    private Cell[][] cells;
    private LinkedList<Person> people;

    public Grid(int size) {
        this.size = size;
        cells = new Cell[size][size];
        people = new LinkedList<Person>();

        for (int i = 0; i < size ; i++){
            for (int j = 0; j < size; j++){
                cells[i][j] = new Cell();
            }
        }
        Person p = new Person(Sex.FEMALE, (int)(Math.random() * (Constants.RANDOM_NUMBER_MAX + 1)));
        people.add(p);
        cells[6][7].setOccupier(p);
    }

    public int getSize() {
        return size;
    }

    public LinkedList<Person> getPeople(){
        return people;
    }

}
