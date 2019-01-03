package org.academiadecodigo.bootcamp.experiment;

import com.sun.org.apache.regexp.internal.RE;
import org.academiadecodigo.simplegraphics.graphics.Color;
import org.academiadecodigo.simplegraphics.graphics.Rectangle;

/**
 * Created by codecadet on 29/10/2018.
 */
public class TreeFractal {

    private Rectangle[] ramagem;
    private Rectangle[] folhagem;
    private double xCoordenate;
    private double yCoordenate;
    private double width;
    private double height;
    private double leftCopaX = 0 + Math.random()*0.1;
    private double leftCopaY = 0.25 + Math.random()*0.1;
    private double leftCopaWidth = 0.5 + Math.random()*0.1;
    private double leftCopaHeight = 0.5 + Math.random()*0.1;
    private double topCopaX = 0.1 + Math.random()*0.1;
    private double topCopaY = 0+ Math.random()*0.1;
    private double topCopaWidth = 1-2*topCopaX;
    private double topCopaHeight = 0.4 + Math.random()*0.1;
    private double rightCopaX = 0.6 + Math.random()*0.1;
    private double rightCopaY = 0.4 + Math.random()*0.1;
    private double rightCopaWidth = 1 - rightCopaX;
    private double rightCopaHeight = 0.35 + Math.random()*0.1;
    private double trunkWidth = 0.02;
    private int level;

    public TreeFractal(double xCoordenate, double yCoordenate, double width, double height, int level) {

        this.xCoordenate = xCoordenate;
        this.yCoordenate = yCoordenate;
        this.width = width;
        this.height = height;

        //primaryTree(xCoordenate, yCoordenate, width, height);

        this.level = level;
        this.replicate(level);
        //this.ramagem = this.replicate(level).ramagem;
        //this.folhagem = this.replicate(level).folhagem;
    }


    private void primaryTree(double xCoordenate, double yCoordenate, double width, double height) {

        double specificRightCopaX = xCoordenate+rightCopaX*width;
        double specificRightCopaY = yCoordenate+rightCopaY*height;
        double specificRightCopaWidth = width*rightCopaWidth;
        double specificRightCopaHeight = height*rightCopaHeight;
        Rectangle rightCopa = new Rectangle(specificRightCopaX, specificRightCopaY, specificRightCopaWidth, specificRightCopaHeight);

        double specificTopCopaX = xCoordenate+topCopaX*width;
        double specificTopCopaY = yCoordenate+topCopaY*height;
        double specificTopCopaWidth = width*topCopaWidth;
        double specificTopCopaHeight = height*topCopaHeight;
        Rectangle topCopa = new Rectangle(specificTopCopaX, specificTopCopaY, specificTopCopaWidth, specificTopCopaHeight);

        double specificLeftCopaX = xCoordenate+leftCopaX*width;
        double specificLeftCopaY = yCoordenate+leftCopaY*height;
        double specificLeftCopaWidth = width*leftCopaWidth;
        double specificLeftCopaHeight = height*leftCopaHeight;
        Rectangle leftCopa = new Rectangle(specificLeftCopaX, specificLeftCopaY, specificLeftCopaWidth, specificLeftCopaHeight);

        this.folhagem = new Rectangle[]{rightCopa,topCopa,leftCopa};

        double centralTrunkX = xCoordenate+(0.5-trunkWidth/2)*width;
        double centralTrunkY = yCoordenate+(topCopaY+topCopaHeight)*height;
        double centralTrunkWidth = width*trunkWidth;
        double centralTrunkHeight = height*(1-topCopaHeight-topCopaY);
        Rectangle centralTrunk = new Rectangle(centralTrunkX, centralTrunkY, centralTrunkWidth, centralTrunkHeight);

        double leftTrunkX = specificLeftCopaX+leftCopaWidth*(width-trunkWidth*width)/2;
        double leftTrunkY = specificLeftCopaY+specificLeftCopaHeight;
        double leftTrunkWidth = centralTrunkX - leftTrunkX;
        double leftTrunkHeight = trunkWidth*height;
        Rectangle leftTrunk = new Rectangle(leftTrunkX, leftTrunkY, leftTrunkWidth, leftTrunkHeight);

        double rightTrunkX = centralTrunkX+centralTrunkWidth;
        double rightTrunkY = specificRightCopaY+specificRightCopaHeight;
        double rightTrunkWidth = specificRightCopaX+specificRightCopaWidth/2+specificRightCopaWidth*trunkWidth/2-rightTrunkX;
        double rightTrunkHeight = trunkWidth*height;
        Rectangle rightTrunk = new Rectangle(rightTrunkX, rightTrunkY, rightTrunkWidth, rightTrunkHeight);

        this.ramagem = new Rectangle[]{rightTrunk,centralTrunk,leftTrunk};

    }

    private /*TreeFractal*/ void replicate(int level) {
        //TreeFractal output = this;
        primaryTree(xCoordenate, yCoordenate, width, height);

        for (int i = 0; i < level; i++) {
            this.replicateOnce();
        }
        //return output;
    }

    private void replicateOnce() {
        Rectangle[] newFolhagem = new Rectangle[this.folhagem.length*3];
        Rectangle[] newRamagem = new Rectangle[this.folhagem.length*3+this.ramagem.length];

        for (int i = 0; i < ramagem.length; i++) {
            newRamagem[i] = ramagem[i];
        }

        for (int i = 0; i < this.folhagem.length; i++) {
            TreeFractal ramo =
                    new TreeFractal(folhagem[i].getX(),folhagem[i].getY(),folhagem[i].getWidth(),folhagem[i].getHeight(),0);
            for (int j = 0; j < 3; j++) {
                newFolhagem[3*i+j] = ramo.folhagem[j];
                newRamagem[this.ramagem.length + 3*i+j] = ramo.ramagem[j];
            }
        }
        this.ramagem = newRamagem;
        this.folhagem = newFolhagem;
    }

    public void draw() {
        for (Rectangle ramo: ramagem) {
            ramo.setColor(Color.RED);
            ramo.draw();
            ramo.fill();
        }
        for (Rectangle folha: folhagem) {
            folha.setColor(Color.GREEN);
            folha.draw();
            folha.fill();
        }
    }

    public int getFolhagemLength(){
        return folhagem.length;
    }

    public int getRamagemLength() {
        return ramagem.length;
    }

    public int getLevel() {
        return level;
    }
}
