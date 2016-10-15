package logic;

import utils.Constants;

import java.util.LinkedList;

/**
 * Created by roman on 10/2/16.
 */
public class Cell {
    Person currentMaleOccupier;
    Person currentFemaleOccupier;
    Person nextMaleOccupier;
    Person nextFemaleOccupier;
    private int x, y;
    private Grid grid;

    public Cell(int x, int y, Grid grid){
        currentMaleOccupier = null;
        currentFemaleOccupier = null;
        this.x = x;
        this.y = y;
        this.grid = grid;
    }

    public int getX() {
        return x;
    }

    public int getY(){
        return y;
    }

    public Person getCurrentMaleOccupier(){
        return currentMaleOccupier;
    }

    public Person getCurrentFemaleOccupier(){
        return currentFemaleOccupier;
    }

    public void setCurrentMaleOccupier(Person occupier){
        this.currentMaleOccupier = occupier;
    }

    public void setCurrentFemaleOccupier(Person occupier){ this.currentFemaleOccupier = occupier; }

    public Person getNextMaleOccupier(){
        return nextMaleOccupier;
    }

    public Person getNextFemaleOccupier(){
        return nextFemaleOccupier;
    }

    public void setNextMaleOccupier(Person occupier){
        this.nextMaleOccupier = occupier;
    }

    public void setNextFemaleOccupier(Person occupier){
        this.nextFemaleOccupier = occupier;
    }


    public void setOccupier(Person occupier){
        switch (occupier.getSex()){
            case FEMALE:
                setCurrentFemaleOccupier(occupier);
                break;
            case MALE:
                setCurrentMaleOccupier(occupier);
                break;
        }
    }

    public boolean isEmpty(){
        return currentMaleOccupier == null && currentFemaleOccupier == null;
    }

    public boolean containsPair() { return currentFemaleOccupier != null && currentMaleOccupier != null; }

    public LinkedList<Cell> getNeighbors(){
        LinkedList<Cell> neighbors = new LinkedList<Cell>();
        if(x > 0) neighbors.add(grid.getCell(x - 1, y));
        if(x < Constants.GRID_SIZE - 1) neighbors.add(grid.getCell(x + 1, y));
        if(y > 0) neighbors.add(grid.getCell(x, y - 1));
        if(y < Constants.GRID_SIZE - 1) neighbors.add(grid.getCell(x, y + 1));

        return neighbors;
    }

    public String toString(){
        return "(" + x + ", " + y + ")";
    }
}
