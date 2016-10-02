package main;

import logic.Grid;
import utils.Constants;

import java.awt.*;
import javax.swing.JComponent;
import javax.swing.JFrame;

/**
 * Created by roman on 10/1/16.
 */


class Canvas extends JComponent {
    Grid grid;
    int posx = 30;

    public Canvas(Grid grid){
        this.grid = grid;
    }

    public void paint(Graphics g) {
        //g.drawRect (posx, 10, 200, 200);

        g.setColor(Color.darkGray);
        g.fillRect(0, 0, Constants.WINDOW_SIZE, Constants.WINDOW_SIZE);

        g.setColor(Color.GRAY);
        g.drawRect(1, 1, Constants.WINDOW_SIZE - 1, Constants.WINDOW_SIZE - 1);
        int coord = Constants.WINDOW_SIZE / grid.getSize();
        for (int i = 1; i < grid.getSize(); i++) {
            g.drawLine(coord * i, 0, coord * i, Constants.WINDOW_SIZE);
            g.drawLine(0, coord * i, Constants.WINDOW_SIZE, coord * i);
        }
    }

    public int getPosx() { return posx; }
    public void setPosx(int newPosx) { posx = newPosx; }
}