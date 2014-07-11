
import java.awt.Graphics2D;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import org.lwjgl.opengl.DisplayMode;


/**
 *
 * @author Brandon Johnson
 */
public class Firework {
    private Random generator = new Random();
    private DisplayMode display;
    
    public Firework(DisplayMode pd)
    {
        display = pd;
    }
    public List<Spark> createFirework(float x, float y, float rquad)
    {
        float z = 0;
        x = (x  - (display.getWidth() / 2))/display.getWidth()*2 * 1.85f;
        y =  (y - (display.getHeight() / 2))/display.getHeight()*2 * 1.45f;
        int cal = (int)rquad/90;
        if(cal == 1 || cal == -3)
        {
            z = x;
            x = 0;
        }
        else if (cal == 2 || cal == -2)
        {
            x = -x;
        }
        else if (cal == 3 || cal == -1)
        {
            z = -x;
            x = 0;
        }
        List<Spark>sparks = new ArrayList<Spark>();
        for(int i = 0; i < 50; i++)
        {
            double d = 360 * generator.nextDouble();
            double s = 10 * generator.nextDouble() + 5;
            Spark temp; 
            temp = new Spark(x, y, z, d, s, display);
            sparks.add(temp);
        }
        return sparks;
    }
}
