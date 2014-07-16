
import java.util.Random;
import org.lwjgl.opengl.GL11;
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
        GL11.glPolygonMode(GL11.GL_FRONT_AND_BACK, GL11.GL_LINE);
        drawer.squar(minX, minY, minZ, w, h, d);
        glColor3f(0.3f, 0.3f, 0.3f);
        GL11.glPolygonMode(GL11.GL_FRONT_AND_BACK, GL11.GL_FILL);
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
