
import static org.lwjgl.opengl.GL11.GL_LINES;
import static org.lwjgl.opengl.GL11.GL_QUADS;
import static org.lwjgl.opengl.GL11.glBegin;
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
 * @author Jeff
 */
public class ShapeDraw {
    
    /*
     * please insure that the position is set at the bottom left
    */
    public boolean squar(float x, float y, float z, 
                          float w, float h, float d)
    {        
        glPushMatrix();
        {
            glBegin(GL_QUADS);
            {
                //
                //front
                glVertex3f(x, y, z);
                glVertex3f(x + w, y, z);
                glVertex3f(x + w, y + h, z);
                glVertex3f(x, y + h, z);
                //left side
                glVertex3f(x, y, z);
                glVertex3f(x, y, z + d);
                glVertex3f(x, y + h, z + d);
                glVertex3f(x, y, z);
                //back side
                glVertex3f(x, y, z + d);
                glVertex3f(x + w, y, z + d);
                glVertex3f(x + w, y + h, z + d);
                glVertex3f(x, y + h, z + d);
                //rigt side
                glVertex3f(x + w, y, z);
                glVertex3f(x + w, y, z + d);
                glVertex3f(x + w, y + h, z + d);
                glVertex3f(x + w, y + h, z);
                //top side
                glVertex3f(x, y + h, z);
                glVertex3f(x + w, y + h, z);
                glVertex3f(x + w, y + h, z + d);
                glVertex3f(x, y + h, z + d);
                //bottom
                glVertex3f(x, y, z);
                glVertex3f(x + w, y, z);
                glVertex3f(x + w, y, z + d);
                glVertex3f(x, y, z + d);              
            }
            glEnd();
        }
        glPopMatrix();
        return true;
    }
    
    public boolean drawOutline()
    {
        glPushMatrix();
        {
        glBegin(GL_LINES); 
        {
                                 
            glVertex3f( 1.0f, 1.0f, 1.0f);         // Top Right Of The Quad (Front)
            glVertex3f(-1.0f, 1.0f, 1.0f);         // Top Left Of The Quad (Front)
            glVertex3f(-1.0f,-1.0f, 1.0f);         // Bottom Left Of The Quad (Front)
            glVertex3f( 1.0f,-1.0f, 1.0f);         // Bottom Right Of The Quad (Front)
           //left back line
            glVertex3f(-1.0f, 1.0f, 1);
            glVertex3f(-1.0f, -1.0f, 1);
            //right back line
            glVertex3f(1.0f, 1.0f, 1);
            glVertex3f(1.0f, -1.0f, 1);
            //top back line         
            glVertex3f( 1.0f,-1.0f,-1.0f);         // Bottom Left Of The Quad (Back)
            glVertex3f(-1.0f,-1.0f,-1.0f);         // Bottom Right Of The Quad (Back)
            //bottom back line
            glVertex3f(-1.0f, 1.0f,-1.0f);         // Top Right Of The Quad (Back)
            glVertex3f( 1.0f, 1.0f,-1.0f);         // Top Left Of The Quad (Back)
            //left back line
            glVertex3f(-1.0f, 1.0f, -1);
            glVertex3f(-1.0f, -1.0f, -1);
            //right back line
            glVertex3f(1.0f, 1.0f, -1);
            glVertex3f(1.0f, -1.0f, -1);
            //glColor3f(0.0f,0.0f,1.0f);             // Set The Color To Blue
            glVertex3f(-1.0f, 1.0f, 1.0f);         // Top Right Of The Quad (Left)
            glVertex3f(-1.0f, 1.0f,-1.0f);         // Top Left Of The Quad (Left)
            glVertex3f(-1.0f,-1.0f,-1.0f);         // Bottom Left Of The Quad (Left)
            glVertex3f(-1.0f,-1.0f, 1.0f);         // Bottom Right Of The Quad (Left)
            //glColor3f(1.0f,0.0f,1.0f);             // Set The Color To Violet
            glVertex3f( 1.0f, 1.0f,-1.0f);         // Top Right Of The Quad (Right)
            glVertex3f( 1.0f, 1.0f, 1.0f);         // Top Left Of The Quad (Right)
            glVertex3f( 1.0f,-1.0f, 1.0f);         // Bottom Left Of The Quad (Right)
            glVertex3f( 1.0f,-1.0f,-1.0f);         // Bottom Right Of The Quad (Right)
        }
        glEnd();                                       // Done Drawing The Quad
        }
        glPopMatrix();
        return true;
    }
}
