/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fireworkshow;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.awt.geom.Ellipse2D;


/**
 *
 * @author Brandon Johnson
 */
public class Firework implements Spark {
    public double MAX_SPEED = 10;
    public double ACCELERATION;
    
    private final int MAX_RADIUS = 5;
    
    private final int MAX_DIAMETER = 2 * MAX_RADIUS;
    
    private double direction;
    private long spawnTime;
    private Color c;
    
    private double x;
    private double y;
    
    private Ellipse2D.Double spark;
    private FireworkPanel parent;
    
    private long LIFESPAN;
    
    public Firework(FireworkPanel parent, double direction, double x, double y, Color c, long LIFESPAN, double MAX_SPEED) {
        this.direction = direction;
        this.parent = parent;
        this.c = c;
	this.LIFESPAN = LIFESPAN;
		
	this.MAX_SPEED = MAX_SPEED;
		
	this.x = x - MAX_RADIUS;
	this.y = y - MAX_RADIUS;
	this.spark = new Ellipse2D.Double(0, 0, MAX_DIAMETER, MAX_DIAMETER);
		
	this.spawnTime = System.currentTimeMillis();
		
	this.ACCELERATION = - 1.0 / LIFESPAN * MAX_SPEED * 1.1;
    }
    
    private void step() {
        long currentTime = System.currentTimeMillis();
        long currentLifeLength = currentTime - spawnTime;
        
        if (currentLifeLength < LIFESPAN) {
            double currentSpeed = MAX_SPEED + ACCELERATION * currentLifeLength;
            
            double dx = currentSpeed * Math.cos(Math.toRadians(direction));
            double dy = currentSpeed * Math.sin(Math.toRadians(direction));
            
            spark.x += dx;
            spark.y += dy;
            
            double shrink = 1 - ((double)currentLifeLength / LIFESPAN);
            
            spark.height = MAX_DIAMETER * shrink;
            spark.width = MAX_DIAMETER * shrink;
        }
        else {
            if (parent.sparksLeft() == 1) {
                parent.repaint();
            }
            parent.removeSpark(this);
        }
    }
    
    public void draw (Graphics2D g2d) {
        step();
        
        g2d.setColor(c);
        double loops = 70;
        
        for (int i = (int)loops; i > 0; i--) {
            double scale = ((double)i) / loops;
            AffineTransform at = AffineTransform.getTranslateInstance(x, y);
            
            at.scale(scale, scale);
            Color newColor = new Color (c.getRed(), c.getGreen(), c.getBlue(), Math.min((int)Math.round(255 * (1/scale)), 255));
            g2d.setColor(newColor);
            g2d.fill(at.createTransformedShape(spark));
        }
            
    }
    
    
}
