package main;

import java.awt.Graphics;
import javax.swing.JComponent;
import javax.swing.JFrame;

/**
 * Created by roman on 10/1/16.
 */


class Canvas extends JComponent {
    int posx = 30;

    public void paint(Graphics g) {
        g.drawRect (posx, 10, 200, 200);
    }

    public int getPosx() { return posx; }
    public void setPosx(int newPosx) { posx = newPosx; }
}