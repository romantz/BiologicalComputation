package logic;

import utils.Sex;

/**
 * Created by roman on 10/2/16.
 */
public class Person {

    private int number;
    private Sex sex;

    public Person(Sex sex, int number){
        this.sex = sex;
        this.number = number;
    }

    public Sex getSex(){
        return sex;
    }

}
