package logic;

/**
 * Created by roman on 10/2/16.
 */
public class Cell {
    Person occupier;

    public Cell(){
        occupier = null;
    }

    public Person getOccupier(){
        return occupier;
    }

    public void setOccupier(Person occupier){
        this.occupier = occupier;
    }
}
