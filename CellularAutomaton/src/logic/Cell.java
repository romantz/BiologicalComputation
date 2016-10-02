package logic;

/**
 * Created by roman on 10/2/16.
 */
public class Cell {
    Person maleOccupier;
    Person femaleOccupier;

    public Cell(){
        maleOccupier = null;
        femaleOccupier = null;
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
