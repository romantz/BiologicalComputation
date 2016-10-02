package main;

import javax.swing.*;

import logic.Grid;
import utils.*;

/**
 * Created by roman on 10/1/16.
 */
public class Main {

    public static void main(String[] args) {
        Grid grid = new Grid(Constants.GRID_SIZE);
        Runner runner = new Runner(grid);
        runner.run();

    }
}

