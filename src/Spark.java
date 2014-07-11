
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
    public Spark(float pX, float pY, float pZ, double pD, double pS, DisplayMode pDisplay)
    {
        x = pX; 
        y = pY;
        z = pZ;
        dir = pD;
        speed = pS;
        display = pDisplay;
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
    
    public void draw()
    {
 
        float w =  0.05f;
        float h =  0.05f;
        float d =  0.05f;
        glColor3f(1f, 0f, 0f);
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
