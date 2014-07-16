
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import static java.lang.System.console;
import java.util.Random;
import org.lwjgl.opengl.ARBFragmentShader;
import org.lwjgl.opengl.ARBVertexShader;
import org.lwjgl.opengl.ARBShaderObjects;
import org.lwjgl.opengl.GL11;
import static org.lwjgl.opengl.GL11.glColor3f;
import org.lwjgl.opengl.GL20;
import org.lwjgl.opengl.GL30;



public class Building {
    float minX;
    float minY;
    float minZ;
    float w;
    float h;
    float d;
    public int program=0;
    public boolean useShader;
    

    Building(float x, float y, float z) {
        setupShaders();

        useShader = true;
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
            //GL20.glUseProgram(program);
            ShapeDraw drawer = new ShapeDraw();
            glColor3f(.5f, .5f, .5f);
            GL11.glPolygonMode(GL11.GL_FRONT_AND_BACK, GL11.GL_LINE);
            drawer.squar(minX, minY, minZ, w, h, d);
            glColor3f(0.1f, 0.1f, 0.1f);
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
    
     private int loadShader(String filename, int type)
   {
      StringBuilder shaderSource = new StringBuilder();
      int shaderID = 0;

      try 
      {
         BufferedReader reader = new BufferedReader(new FileReader(filename));
         String line;
         while ((line = reader.readLine()) != null) 
         {
            shaderSource.append(line).append("\n");
         }
         reader.close();
      }
      catch (IOException e) 
      {
         System.err.println("Could not read file.");
         e.printStackTrace();
         System.exit(-1);
      }

      shaderID = GL20.glCreateShader(type);
      GL20.glShaderSource(shaderID, shaderSource);
      GL20.glCompileShader(shaderID);
      
      String status = GL20.glGetShaderInfoLog(shaderID, 1000);
      System.out.println(status);
      
      if (GL20.glGetShader(shaderID, GL20.GL_COMPILE_STATUS) == GL11.GL_FALSE) 
      {
         System.err.println("Could not compile shader.");
         System.exit(-1);
      }

      //this.exitOnGLError("loadShader");

      return shaderID;
   }
   
   private void setupShaders()
   {
      // Load the vertex shader
      int vsId = this.loadShader("vertex.vert", GL20.GL_VERTEX_SHADER);
      // Load the fragment shader
      int fsId = this.loadShader("fragment.frag", GL20.GL_FRAGMENT_SHADER);

      // Create a new shader program that links both shaders
      program = GL20.glCreateProgram();
      GL20.glAttachShader(program, vsId);
      GL20.glAttachShader(program, fsId);

      // Position information will be attribute 0
      GL20.glBindAttribLocation(program, 0, "in_Position");
      // Color information will be attribute 1
      GL20.glBindAttribLocation(program, 1, "in_Color");
      // Textute information will be attribute 2
      GL20.glBindAttribLocation(program, 2, "in_TextureCoord");
      // Normal information will be attribute 3
      GL20.glBindAttribLocation(program, 3, "in_Normal");

      GL20.glLinkProgram(program);
      GL20.glValidateProgram(program);

      // Get matrices uniform location

      //this.exitOnGLError("setupShaders");
   } 
}
