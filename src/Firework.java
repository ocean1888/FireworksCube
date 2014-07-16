
import java.nio.FloatBuffer;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import org.lwjgl.BufferUtils;
import org.lwjgl.opengl.DisplayMode;


/**
 *
 * @author Brandon Johnson
 */
public class Firework {
    private Random generator = new Random();
    private DisplayMode display;
    private float randcolorR;
    private float randcolorG;
    private float randcolorB;
    private FloatBuffer fireworkColor = BufferUtils.createFloatBuffer(4);
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
        Random rand = new Random();
        randcolorR = rand.nextFloat();
        randcolorG = rand.nextFloat();
        randcolorB = rand.nextFloat();
        List<Spark>sparks = new ArrayList<Spark>();
        for(int i = 0; i < 150; i++)
        {
            double d = 360 * generator.nextDouble();
            double s = 10 * generator.nextDouble() + 5;
            Spark temp; 
            temp = new Spark(x, y, z, d, s, display,randcolorR, randcolorG, randcolorB);
            sparks.add(temp);
        }
        return sparks;
    }
    FloatBuffer getFireworkColor()
    {
        fireworkColor.put(randcolorR).put(randcolorG).put(randcolorB).put(1.0f).flip();
        return fireworkColor;
    }
}
