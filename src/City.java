
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import org.lwjgl.opengl.ARBFragmentShader;
import org.lwjgl.opengl.ARBShaderObjects;
import org.lwjgl.opengl.ARBVertexShader;
import org.lwjgl.opengl.GL11;


public class City {
    
    private final List<Building> city = new ArrayList<Building>();
    
    
    public City()
    {
        
        float tempX;
        float tempZ;
        for(int i = 0; i < 10; i++)
        {
            Random generator = new Random();
            tempX = (generator.nextFloat()) - .85f;
            tempZ = (generator.nextFloat()) - .75f;
            Building cube = new Building(tempX, -1, tempZ);
            city.add(cube);
        }
    }
    
    public boolean drawCity()
    {
        for (Building build : city)
        {
            build.drawBuilding(); 
        }
        return true;
    }
    
    @Override
    public String toString()
    {
        String s = "City{";
        for (Building b : city)
        {
            s += b.toString();
        }
        return s + "}";
    }
    
}
