import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
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
    private final String windowTitle = "Fireworks Cube";
    private boolean f1 = false;
    private float tranZ = -6;   // negitive is further into the screen
    private float rotY = 1;     // positive is pressing to the rigth
    private float rotX = 1;     // should be right on...
    private float rquad;                // Angle For The Quad     ( NEW )
    private Boolean zoomed;
    private DisplayMode displayMode;
   // private FireworkShow fShow;
    private List<Spark> sparks;
    
    //determins if there is a firework
    private boolean fg = false;
    
    /*Here is the city its created near the last line of initGl()
    * doesnt hold anything until then
    */
    private City city;
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
        zoomed = false;
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
        
        if(Keyboard.isKeyDown(Keyboard.KEY_SPACE))
        {
            if(tranZ < -3.5)
            {
                while(tranZ < -3.5)
                {
                   tranZ += .01;
                   render();
                   Display.update();
                   Display.sync(1050);
                }
            }
            else
            {
                while(tranZ > -7)
                {    
                    tranZ-= .01;
                    render();
                    Display.update();
                    Display.sync(1050);
                }
            }
        }
        
        if(Keyboard.isKeyDown(Keyboard.KEY_W))
        {
            if(tranZ < -6)
            {
                rotY = 0;
                rotX = 1;
                float temp = rquad;
                
                while(rquad - temp  < 30)
                {    
                    rquad+=.1;
                    render();
                    Display.update();
                    Display.sync(1050);
                }
            }
        }
        if(Keyboard.isKeyDown(Keyboard.KEY_S))
        {
            if(tranZ < -6)
            {
                rotY = 0;
                rotX = 1;
                float temp = rquad;
                
                while(temp - rquad   < 30)
                {    
                    rquad-=.1;
                    render();
                    Display.update();
                    Display.sync(1050);
                }
            }
        }
        
        if(tranZ < -4)
        {
            if(Keyboard.isKeyDown(Keyboard.KEY_D))
            {
                /*
                this is code if we want the user to control how far to turn
                rotY = -1;
                rquad-=.1;
                */
                rotX =0;
                rotY = 1;
                rquad -=.1;
                float temp = rquad;
                while (temp - rquad < 90)
                {
                    rquad-=.1;
                    render();
                    Display.update();
                    Display.sync(1050);
                    if (rquad < -360)
                    {
                        rquad = 0;
                        break;
                    }
                }
            }
            else if (Keyboard.isKeyDown(Keyboard.KEY_A))
            {
                /* this is code that will allow the rotation but doesnt 
                make it do quit what i think we want it to
                rotY = 1;
                rquad+=.1;
                        */
                rotX =0;
                rotY = 1;
                rquad+=.1;
                float temp = rquad;
                while (rquad - temp < 90)
                {
                    rquad+=.1;
                    render();
                    Display.update();
                    Display.sync(1050);
                    if (rquad > 360)
                    {
                        rquad = 0;
                        break;
                    }
                }
            }
            else 
            {
                rquad-=0;
                //rotY = 0;
            }
        }
        else
        {
            if(Mouse.isButtonDown(0))
            {
                Firework fws = new Firework(displayMode);
                sparks = fws.createFirework((float)Mouse.getX(), (float)Mouse.getY(), rquad);

                fg = true;
                for(int i = 0; i < 600; i++)
                {
                    render();
                    Display.update();
                    Display.sync(1600);

                }
                fg = false;
            }
        }
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
        float x =  0.0f;
        float y = -1.0f;
        float z =  0.0f;
        float w =  0.5f;
        float h =  1.0f;
        float d =  0.5f;
        glLoadIdentity(); // Reset The Current Modelview Matrix
        glTranslatef(0.0f,0.0f,(float)tranZ); // Move/translate the cube back

        glRotatef(rquad, (float)rotX,(float)rotY,0.0f); // Rotate The Quad On The Y axis ( NEW )
        if(fg)
        {
            fireworkDraw();
        } 
        ShapeDraw drawer = new ShapeDraw();
        glColor3f(0.45f,.85f,1.0f);
        drawer.drawOutline();
        //apply the lighting
        // if we come up with a way to randomize these numbers and the 
        // number of sets then we can create the "random" landscape

        
        x = -0.2f;
        z = 0.8f;
        w = .2f;
        h = .6f;
        d = .2f;
        //glColor3f(0.2f, 0.2f, 0.2f);
        //drawer.squar(x, y, z, w, h, d);
        city.drawCity();
        return true;
    }
    
    private boolean fireworkDraw()
    {
        for(Spark s : sparks)
        {
            s.draw();
            s.update(rquad);
        }
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
        
        //Creating the city!! hope this will work
        city = new City();
    }
    private static void cleanup() {
        Display.destroy();
    }
}
