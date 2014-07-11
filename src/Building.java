
import java.util.Random;
import static org.lwjgl.opengl.GL11.glColor3f;


public class Building {
    float minX;
    float minY;
    float minZ;
    float w;
    float h;
    float d;

    Building(float x, float y, float z) {

         minX = x;
         minY = y;
         minZ = z;
     
         Random generator = new Random();
         w = (generator.nextFloat());
         h = (generator.nextFloat()) * 1.5f;
         d = .5f;
    }
    
    public boolean drawBuilding()
    {
        ShapeDraw drawer = new ShapeDraw();
        glColor3f(.5f, .5f, .5f);
        drawer.squar(minX, minY, minZ, w, h, d);
        glColor3f(.1f, .1f, .1f);
        drawer.squar(minX, minY, minZ, w, h, d);
        return true;
    }
    
    @Override
    public String toString()
    {
        String s = "Building{[x = "+minX+"], [h = "+h+"], [z = "+minZ+"]";
        return s + ", [d = "+d+"]} \n";
    }
}
