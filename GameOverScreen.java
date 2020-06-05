import processing.core.*;

public class GameOverScreen {
    PApplet sketch;
    PFont font18, font32, font48;
    PImage image1, image2;
    boolean success;
    long time;
    Main runner;

    public GameOverScreen(PApplet sketch, boolean s, long t, Main m){
        this.sketch = sketch;
        font18 = sketch.loadFont("Graph-18.vlw");
        font32 = sketch.loadFont("Graph-32.vlw");
        font48 = sketch.loadFont("Graph-48.vlw");
        image1 = sketch.loadImage("bacteriophage.png");
        image2 = sketch.loadImage("adenovirus.png");
        success = s;
        time = t;
        runner = m;
    }

    public void drawScreen(){
        sketch.background(56,88,128);
        sketch.textFont(font48, 48); //title
        sketch.textAlign(sketch.CENTER, sketch.CENTER);
        sketch.stroke(255);
        sketch.fill(255);
        sketch.text("Virus Game",550,70);

        sketch.textFont(font32, 32);
        sketch.textAlign(sketch.CENTER);
        if(success) {
            long milliseconds = time;
            int minutes = (int) milliseconds / 60000;
            milliseconds = milliseconds % 60000;
            int seconds = (int) milliseconds / 1000;
            sketch.rectMode(PApplet.CENTER);
            sketch.text("Congrats! You achieved full infection 0" + minutes + " minutes and " + seconds + " seconds", 550, 400, 500, 500);
        }else{
            sketch.rectMode(PApplet.CENTER);
            sketch.text("Unfortunately you did not infect enough people before the vaccine was developed.\nPlease try again.", 550, 500, 500, 500);
        }

        sketch.textFont(font18, 18);
        sketch.text("Click anywhere to exit", 550, 890);

        sketch.pushMatrix(); //lower left image
        sketch.translate(-20,500);
        sketch.rotate(PApplet.radians(-30));
        sketch.image(image1,0,150, 90, 170);
        sketch.popMatrix();

        sketch.pushMatrix(); //top right image
        sketch.translate(0,-500);
        sketch.rotate(PApplet.radians(30));
        sketch.image(image1,1150,0);
        sketch.popMatrix();

        sketch.image(image2,60,100,150,150); //top left image
        sketch.pushMatrix(); //bottom right image
        sketch.translate(930,600);
        sketch.rotate(PApplet.radians(-20));
        sketch.image(image2,0,0,100,100);
        sketch.popMatrix();

        if(sketch.mousePressed){
            runner.mode=1;
            runner.prevFrameMode=1;
        }
    }
}
