
import java.util.Random;
import org.lwjgl.opengl.DisplayMode;
import static org.lwjgl.opengl.GL11.GL_LINES;
import static org.lwjgl.opengl.GL11.GL_QUADS;
import static org.lwjgl.opengl.GL11.glBegin;
import static org.lwjgl.opengl.GL11.glColor3f;
import static org.lwjgl.opengl.GL11.glEnd;
import static org.lwjgl.opengl.GL11.glPopMatrix;
import static org.lwjgl.opengl.GL11.glPushMatrix;
import static org.lwjgl.opengl.GL11.glVertex3f;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


/**
 *
 * @author Brandon Johnson
 */

public class Spark {	
    private float x;
    private float y;
    private float z;
    private double dir;
    private double speed;
    private DisplayMode display;
    private double acceleration;
    private long spawnTime;
    private float randcolorR;
    private float randcolorG;
    private float randcolorB;
    
    public Spark(float pX, float pY, float pZ, double pD, double pS, DisplayMode pDisplay, float colorR, float colorG, float colorB)
    {
        x = pX; 
        y = pY;
        z = pZ;
        dir = pD;
        speed = pS;
        display = pDisplay;
        acceleration = 1.0 / speed * 0.5;
        spawnTime = System.currentTimeMillis();
        randcolorR = colorR;
        randcolorG = colorG;
        randcolorB = colorB;
    }

    public float getX() {
        return x;
    }
    public void setX(float x) {
        this.x = x;
    }

    public float getY() {
        return y;
    }

    public void setY(float y) {
        this.y = y;
    }
    
    public double getDir()
    {
        return dir;
    }
    
    public double getSpeed()
    {
        return speed;
    }
    
    public void update(float rquad)
    {
        long currentTime = System.currentTimeMillis();
        long currentLifeLength = currentTime - spawnTime;
        
        int cal = (int)rquad/90;
        
        
            double currentSpeed = speed + acceleration * currentLifeLength;
            double dx = (currentSpeed * Math.cos(Math.toRadians(dir)))/50000;
            double dy = (currentSpeed * Math.sin(Math.toRadians(dir)))/50000;

        if(cal == 1 || cal == -3)
        {
            z += dx;
        }
        else if (cal == 2 || cal == -2)
        {
            x += -dx;
        }
        else if (cal == 3 || cal == -1)
        {
            z += -dx;
            x = 0;
        }
        else 
            x += dx;
           // x += dx;
            y += dy;  
    }
    
    public void draw()
    {
 
        float w =  0.025f;
        float h =  0.025f;
        float d =  0.025f;
        glColor3f(randcolorR, randcolorG, randcolorB);
        ShapeDraw drawer = new ShapeDraw();
        drawer.squar(x, y, z, w, h, d);
    }
    @Override
    public String toString()
    {
        String info = "spark{[x = ";
        info += x+"], [y = "+y+"], [z = "+z+"]}";
        return info;
    }
}
