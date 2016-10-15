package logic;

/**
 * Created by roman on 10/2/16.
 */
public class Cell {
    Person maleOccupier;
    Person femaleOccupier;
    private int x, y;

    public Cell(int x, int y){
        maleOccupier = null;
        femaleOccupier = null;
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public int getY(){
        return y;
    }

    public Person getMaleOccupier(){
        return maleOccupier;
    }

    public Person getFemaleOccupier(){
        return femaleOccupier;
    }

    public void setMaleOccupier(Person occupier){
        this.maleOccupier = occupier;
    }

    public void setFemaleOccupier(Person occupier){
        this.femaleOccupier = occupier;
    }

    public void setOccupier(Person occupier){
        switch (occupier.getSex()){
            case FEMALE:
                setFemaleOccupier(occupier);
                break;
            case MALE:
                setMaleOccupier(occupier);
                break;
        }
    }

    public boolean isEmpty(){
        return maleOccupier == null && femaleOccupier == null;
    }
}
