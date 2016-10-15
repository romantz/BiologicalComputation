package main;

import logic.Cell;
import logic.Grid;
import logic.Person;
import utils.Constants;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JComponent;

/**
 * Created by roman on 10/1/16.
 */


class Canvas extends JComponent {
    Grid grid;
    int posx = 30;

    public Canvas(Grid grid){
        this.grid = grid;
        addMouseListener(new MouseAdapter() {
            public void mouseReleased(MouseEvent e) {
                int cellSize = Constants.WINDOW_SIZE / Constants.GRID_SIZE;
                int x = e.getX() / cellSize;
                int y = e.getY() / cellSize;
                Cell cell = grid.getCell(x, y);

                if (cell != null) {
                    Person femaleOccupier = cell.getCurrentFemaleOccupier();
                    Person maleOccupier = cell.getCurrentMaleOccupier();
                    Person nextFemaleOccupier = cell.getNextFemaleOccupier();
                    Person nextMaleOccupier = cell.getNextMaleOccupier();
                    System.out.println("(" + x + ", " + y + ")");
                    if (femaleOccupier != null) {
                        System.out.println("Female occupier. number: " + femaleOccupier.getNumber() + ", color" + femaleOccupier.getColor());
                    }

                    if (maleOccupier != null) {
                        System.out.println("Male occupier. number: " + maleOccupier.getNumber() + ", color" + maleOccupier.getColor());
                    }

                    if (nextFemaleOccupier != null) {
                        System.out.println("Next female occupier. number: " + nextFemaleOccupier.getNumber() + ", color" + nextFemaleOccupier.getColor());
                    }

                    if (nextMaleOccupier != null) {
                        System.out.println("Next male occupier. number: " + nextMaleOccupier.getNumber() + ", color" + nextMaleOccupier.getColor());
                    }
                }
            }
        });
    }

    public void paint(Graphics g) {
        int cellSize = Constants.WINDOW_SIZE / grid.getSize();

        //g.drawRect (posx, 10, 200, 200);

        g.setColor(Color.darkGray);
        g.fillRect(0, 0, Constants.WINDOW_SIZE, Constants.WINDOW_SIZE);

        g.setColor(Color.GRAY);
        g.drawRect(1, 1, Constants.WINDOW_SIZE - 1, Constants.WINDOW_SIZE - 1);

        for (int i = 1; i < grid.getSize(); i++) {
            g.drawLine(cellSize * i, 0, cellSize * i, Constants.WINDOW_SIZE);
            g.drawLine(0, cellSize * i, Constants.WINDOW_SIZE, cellSize * i);
        }

        for(Person p: grid.getPeople()) {

            g.setColor(p.getColor());
            //g.setColor(new Color(255, 255 * p.getNumber() / 100, 0));

            switch (p.getSex()){
                case MALE:
                    g.fillPolygon(
                            new int[]{p.getX() * cellSize + 1, p.getX() * cellSize + 1, (p.getX() + 1) * cellSize - 1},
                            new int[]{p.getY() * cellSize + 1, (p.getY() + 1) * cellSize - 1, p.getY() * cellSize + 1},
                            3);
                    //g.fillRect(p.getX() * cellSize, p.getY() * cellSize, (cellSize / 2), cellSize);
                    break;
                case FEMALE:
                    g.fillPolygon(
                            new int[]{p.getX() * cellSize + 1, (p.getX() + 1) * cellSize - 1, (p.getX() + 1) * cellSize - 1},
                            new int[]{(p.getY() + 1) * cellSize - 1, (p.getY() + 1) * cellSize - 1, p.getY() * cellSize + 1},
                            3);
                    break;
            }

        }
    }
}