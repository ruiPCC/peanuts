package org.academiadecodigo.bootcamp.experiment;

import com.sun.org.apache.regexp.internal.RE;
import org.academiadecodigo.simplegraphics.graphics.Rectangle;

/**
 * Created by codecadet on 25/10/2018.
 */
public class VicsekFractal {
    private Rectangle[] rectangles;
    private double xCoordenate;
    private double yCoordenate;
    private double size;
    private int level; //must be greater or equal to zero

    public VicsekFractal(double xCoordenate, double yCoordenate, double size, int level) {

        Rectangle inicialRectangle = new Rectangle(xCoordenate,yCoordenate,size,size);
        this.rectangles = new Rectangle[]{inicialRectangle};
        this.level = 0;
        this.rectangles = this.replicate(level).rectangles;
        this.xCoordenate = xCoordenate;
        this.yCoordenate = yCoordenate;
        this.size = size;
        this.level = level;
    }



    public VicsekFractal replicate(int level) {
        VicsekFractal output = this;
        for (int i = 0; i < level; i++) {
            output.replicateOnce();
        }
        return output;
    }

    public void replicateOnce() {
        Rectangle[] rectangles = new Rectangle[5*this.rectangles.length];
        for (int i = 0; i < this.rectangles.length ; i++) {
            rectangles[5*i] = new Rectangle(this.rectangles[i].getX(),this.rectangles[i].getY(),
                    this.rectangles[i].getWidth()/3,this.rectangles[i].getHeight()/3);
            rectangles[5*i+1] = new Rectangle(this.rectangles[i].getX()+2*this.rectangles[i].getWidth()/3,
                    this.rectangles[i].getY(), this.rectangles[i].getWidth()/3,this.rectangles[i].getHeight()/3);
            rectangles[5*i+2] = new Rectangle(this.rectangles[i].getX()+this.rectangles[i].getWidth()/3,
                    this.rectangles[i].getY()+this.rectangles[i].getHeight()/3,
                    this.rectangles[i].getWidth()/3,this.rectangles[i].getHeight()/3);
            rectangles[5*i+3] = new Rectangle(this.rectangles[i].getX(),
                    this.rectangles[i].getY()+2*this.rectangles[i].getHeight()/3,
                    this.rectangles[i].getWidth()/3,this.rectangles[i].getHeight()/3);
            rectangles[5*i+4] = new Rectangle(this.rectangles[i].getX()+2*this.rectangles[i].getWidth()/3,
                    this.rectangles[i].getY()+2*this.rectangles[i].getHeight()/3,
                    this.rectangles[i].getWidth()/3,this.rectangles[i].getHeight()/3);
        }
        this.rectangles = rectangles;
    }

    public void draw() {
        for (Rectangle rect:this.rectangles) {
            rect.draw();
        }
    }

}
