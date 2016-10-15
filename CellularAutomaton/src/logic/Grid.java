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
                cells[i][j] = new Cell(j, i, this);
            }
        }

        for (int i = 0; i < Constants.NUMBER_OF_PEOPLE_OF_EACH_GENDER; i++) {
            generatePerson(Sex.FEMALE, randomCoord(), randomCoord(), (int)(Math.random() * (Constants.RANDOM_NUMBER_MAX + 1)));
            generatePerson(Sex.MALE, randomCoord(), randomCoord(), (int)(Math.random() * (Constants.RANDOM_NUMBER_MAX + 1)));
        }
    }

    public void generatePerson(Sex sex, int x, int y, int number){
        Cell cell = cells[y][x];
        while (!cell.isEmpty()){
            x = randomCoord();
            y = randomCoord();
            cell = cells[y][x];
        }

        System.out.println("Created a " + sex + " at " + cell);
        Person p = new Person(sex, number, cells[y][x]);
        people.add(p);

    }

    private int randomCoord(){
        return (int)(Math.random() * (Constants.GRID_SIZE));
    }

    public int getSize() {
        return size;
    }

    public LinkedList<Person> getPeople(){
        return people;
    }

    public Cell getCell(int x, int y){
        if(x >= 0 && x < Constants.GRID_SIZE && y >= 0 && y < Constants.GRID_SIZE){
            return cells[y][x];
        }
        return null;
    }

}
