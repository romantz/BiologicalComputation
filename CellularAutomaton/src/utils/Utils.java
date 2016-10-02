package utils;

import java.awt.*;

/**
 * Created by roman on 10/2/16.
 */
public class Utils {
    public static Color calculateColorByNumber(int num){

        if(num == Constants.RANDOM_NUMBER_MAX)
            return new Color(0, 0, 255);

        int numRange = num * 6 / Constants.RANDOM_NUMBER_MAX;
        int insideRange = (int)((num - (numRange * ((double)Constants.RANDOM_NUMBER_MAX / (double)6))) * 255 /
                ((double)Constants.RANDOM_NUMBER_MAX / (double)6));

        int red = 0, green = 0, blue = 0;

        switch(numRange){
            case 0:
                red = 0;
                green = insideRange;
                blue = 255;
                break;
            case 1:
                red = 0;
                green = 255;
                blue = 255 - insideRange;
                break;
            case 2:
                red = insideRange;
                green = 255;
                blue = 0;
                break;
            case 3:
                red = 255;
                green = 255 - insideRange;
                blue = 0;
                break;
            case 4:
                red = 255;
                green = 0;
                blue = insideRange;
                break;
            case 5:
                red = 255 - insideRange;
                green = 0;
                blue = 255;
                break;
        }

        System.out.println(red + "," + green + "," + blue + ": " + num +", " + numRange + ", " + insideRange);

        return new Color(red, green, blue);
    }
}
