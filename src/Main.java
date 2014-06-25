import java.io.IOException;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.lwjgl.LWJGLException;
import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;
import org.lwjgl.opengl.GL11;
import static org.lwjgl.opengl.GL11.*;

import static org.lwjgl.util.glu.GLU.gluOrtho2D;
import static org.lwjgl.util.glu.GLU.gluPerspective;

/**
 * @author jediTofu
 * @see <a href="http://lwjgl.org/">LWJGL Home Page</a>
 */
public class Main {
private boolean done = false;
    private boolean fullscreen = false;
    private final String windowTitle = "NeHe's OpenGL Lesson 5 for LWJGL (3D Shapes)";
    private boolean f1 = false;
    private float tranZ = -6;   // negitive is further into the screen
    private float rotY = 1;     // positive is pressing to the rigth
    private float rtri;                 // Angle For The Triangle ( NEW )
    private float rquad;                // Angle For The Quad     ( NEW )
    private DisplayMode displayMode;

    public static void main(String args[]) {
        boolean fullscreen = false;
        if(args.length>0) {
            if(args[0].equalsIgnoreCase("fullscreen")) {
                fullscreen = true;
            }
        }

        Main l5 = new Main();
        l5.run(fullscreen);
    }
    public void run(boolean fullscreen) {
        this.fullscreen = fullscreen;
        try {
            init();
            while (!done) {
                mainloop();
                render();
                Display.update();
                //Display.sync(120);
            }
            cleanup();
        }
        catch (Exception e) {
            e.printStackTrace();
            System.exit(0);
        }
    }
    private void mainloop() {
        if(Keyboard.isKeyDown(Keyboard.KEY_ESCAPE)) {       // Exit if Escape is pressed
            done = true;
        }
        if(Display.isCloseRequested()) {                     // Exit if window is closed
            done = true;
        }
        if(Keyboard.isKeyDown(Keyboard.KEY_F1) && !f1) {    // Is F1 Being Pressed?
            f1 = true;                                      // Tell Program F1 Is Being Held
            switchMode();                                   // Toggle Fullscreen / Windowed Mode
        }
        if(!Keyboard.isKeyDown(Keyboard.KEY_F1)) {          // Is F1 Being Pressed?
            f1 = false;
        }
        if(Keyboard.isKeyDown(Keyboard.KEY_S))
        {
            if(tranZ < -3.5)
            tranZ += .01;
        }
        if(Keyboard.isKeyDown(Keyboard.KEY_W))
        {
            if(tranZ > -7)
            tranZ-= .01;
        }
        if(tranZ < -4)
        {
            if(Keyboard.isKeyDown(Keyboard.KEY_A))
            {
                rotY = -1;
                rquad-=.1;
            }
            else if (Keyboard.isKeyDown(Keyboard.KEY_D))
            {
                rotY = 1;
                rquad+=.1;
            }
            else 
            {
                rquad-=0;
                rotY = 0;
            }
        }
//        else
  //      {
    //        rquad = 0;
      //  }
        
    }

    private void switchMode() {
        fullscreen = !fullscreen;
        try {
            Display.setFullscreen(fullscreen);
        }
        catch(Exception e) {
            e.printStackTrace();
        }
    }

    private boolean render() {
        glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT);        
       
        glLoadIdentity();                          // Reset The Current Modelview Matrix
        glTranslatef(0.0f,0.0f,(float)tranZ);             // Move/translate the cube back
        glRotatef(rquad, 0.0f,(float)1,0.0f);               // Rotate The Quad On The X axis ( NEW )
        glColor3f(0.5f,0.5f,1.0f);                 // Set The Color To Blue One Time Only
        glBegin(GL_QUADS);                        // Draw A Quad
            glColor3f(0.0f,1.0f,0.0f);             // Set The Color To Green
            glVertex3f( 1.0f, 1.0f,-1.0f);         // Top Right Of The Quad (Top)
            glVertex3f(-1.0f, 1.0f,-1.0f);         // Top Left Of The Quad (Top)
            glVertex3f(-1.0f, 1.0f, 1.0f);         // Bottom Left Of The Quad (Top)
            glVertex3f( 1.0f, 1.0f, 1.0f);         // Bottom Right Of The Quad (Top)
            glColor3f(1.0f,0.5f,0.0f);             // Set The Color To Orange
            glVertex3f( 1.0f,-1.0f, 1.0f);         // Top Right Of The Quad (Bottom)
            glVertex3f(-1.0f,-1.0f, 1.0f);         // Top Left Of The Quad (Bottom)
            glVertex3f(-1.0f,-1.0f,-1.0f);         // Bottom Left Of The Quad (Bottom)
            glVertex3f( 1.0f,-1.0f,-1.0f);         // Bottom Right Of The Quad (Bottom)
            glColor3f(1.0f,0.0f,0.0f);             // Set The Color To Red
            glVertex3f( 1.0f, 1.0f, 1.0f);         // Top Right Of The Quad (Front)
            glVertex3f(-1.0f, 1.0f, 1.0f);         // Top Left Of The Quad (Front)
            glVertex3f(-1.0f,-1.0f, 1.0f);         // Bottom Left Of The Quad (Front)
            glVertex3f( 1.0f,-1.0f, 1.0f);         // Bottom Right Of The Quad (Front)
            glColor3f(1.0f,1.0f,0.0f);             // Set The Color To Yellow
            glVertex3f( 1.0f,-1.0f,-1.0f);         // Bottom Left Of The Quad (Back)
            glVertex3f(-1.0f,-1.0f,-1.0f);         // Bottom Right Of The Quad (Back)
            glVertex3f(-1.0f, 1.0f,-1.0f);         // Top Right Of The Quad (Back)
            glVertex3f( 1.0f, 1.0f,-1.0f);         // Top Left Of The Quad (Back)
            glColor3f(0.0f,0.0f,1.0f);             // Set The Color To Blue
            glVertex3f(-1.0f, 1.0f, 1.0f);         // Top Right Of The Quad (Left)
            glVertex3f(-1.0f, 1.0f,-1.0f);         // Top Left Of The Quad (Left)
            glVertex3f(-1.0f,-1.0f,-1.0f);         // Bottom Left Of The Quad (Left)
            glVertex3f(-1.0f,-1.0f, 1.0f);         // Bottom Right Of The Quad (Left)
            glColor3f(1.0f,0.0f,1.0f);             // Set The Color To Violet
            glVertex3f( 1.0f, 1.0f,-1.0f);         // Top Right Of The Quad (Right)
            glVertex3f( 1.0f, 1.0f, 1.0f);         // Top Left Of The Quad (Right)
            glVertex3f( 1.0f,-1.0f, 1.0f);         // Bottom Left Of The Quad (Right)
            glVertex3f( 1.0f,-1.0f,-1.0f);         // Bottom Right Of The Quad (Right)
        glEnd();                                       // Done Drawing The Quad
        return true;
    }
    private void createWindow() throws Exception {
        Display.setFullscreen(fullscreen);
        DisplayMode d[] = Display.getAvailableDisplayModes();
        for (int i = 0; i < d.length; i++) {
            if (d[i].getWidth() == 640
                && d[i].getHeight() == 480
                && d[i].getBitsPerPixel() == 32) {
                displayMode = d[i];
                break;
            }
        }
        Display.setDisplayMode(displayMode);
        Display.setTitle(windowTitle);
        Display.create();
    }
    private void init() throws Exception {
        createWindow();

        initGL();
    }

    private void initGL() {
        glEnable(GL_TEXTURE_2D); // Enable Texture Mapping
        glShadeModel(GL_SMOOTH); // Enable Smooth Shading
        glClearColor(0.0f, 0.0f, 0.0f, 0.0f); // Black Background
        glClearDepth(1.0); // Depth Buffer Setup
        glEnable(GL_DEPTH_TEST); // Enables Depth Testing
        glDepthFunc(GL_LEQUAL); // The Type Of Depth Testing To Do

        glMatrixMode(GL_PROJECTION); // Select The Projection Matrix
        glLoadIdentity(); // Reset The Projection Matrix

        // Calculate The Aspect Ratio Of The Window
        gluPerspective(
          45.0f,
          (float) displayMode.getWidth() / (float) displayMode.getHeight(),
          0.1f,
          100.0f);
        glMatrixMode(GL_MODELVIEW); // Select The Modelview Matrix

        // Really Nice Perspective Calculations
        glHint(GL_PERSPECTIVE_CORRECTION_HINT, GL_NICEST);
    }
    private static void cleanup() {
        Display.destroy();
    }
}
