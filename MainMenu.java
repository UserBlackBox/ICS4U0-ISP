import processing.core.*;

public class MainMenu {
    PApplet sketch; //processing sketch screen to draw on
    Main runner;
    PFont font12, font18, font24, font32, font48;
    boolean mouse = false;
    PImage image1, image2;

    /**
     * Constructor to make the main menu object
     * @param sketch PApplet window to draw to
     * @param runner Main class to change mode variable
     */
    public MainMenu(PApplet sketch, Main runner){
        this.sketch = sketch;
        this.runner = runner;
        font12 = sketch.loadFont("Graph-12.vlw");
        font18 = sketch.loadFont("Graph-18.vlw");
        font24 = sketch.loadFont("Graph-24.vlw");
        font32 = sketch.loadFont("Graph-32.vlw");
        font48 = sketch.loadFont("Graph-48.vlw");
        image1 = sketch.loadImage("bacteriophage.png");
        image2 = sketch.loadImage("adenovirus.png");
    }

    /**
     * Draws the main menu scene
     */
    public void drawMenu(){
        sketch.background(56,88,128);
        sketch.textFont(font48, 48);
        sketch.textAlign(sketch.CENTER, sketch.CENTER);
        sketch.stroke(255);
        sketch.fill(255);
        sketch.rectMode(sketch.CORNER);
        sketch.text("Virus Game",550,70);

        sketch.textFont(font32, 32); //tutorial button
        sketch.stroke(124,158,178);
        sketch.fill(124,158,178);
        if(sketch.mouseX <=750 && sketch.mouseX >= 350 && sketch.mouseY <= 300 && sketch.mouseY >= 200){ //cursor highlight
            sketch.fill(152,194,218);
            sketch.stroke(152,194,218);
            if(sketch.mousePressed) mouse = true;
            if(!sketch.mousePressed && mouse){ //trigger scene change on mouse release
                runner.mode = 4;
            }
        }
        sketch.rect(350,200,400,100,10);
        sketch.fill(255);
        sketch.stroke(255);
        sketch.text("Tutorial", 550,250);

        sketch.stroke(124,158,178); //start game button
        sketch.fill(124,158,178);
        if(sketch.mouseX <=750 && sketch.mouseX >= 350 && sketch.mouseY <= 450 && sketch.mouseY >= 350){
            sketch.fill(152,194,218);
            sketch.stroke(152,194,218);
            if(sketch.mousePressed) mouse = true;
            if(!sketch.mousePressed && mouse){
                runner.mode = 5;
            }
        }
        sketch.rect(350,350,400,100,10);
        sketch.fill(255);
        sketch.stroke(255);
        sketch.text("Start Game", 550,400);

        sketch.stroke(124,158,178); //instructions button
        sketch.fill(124,158,178);
        if(sketch.mouseX <=750 && sketch.mouseX >= 350 && sketch.mouseY <= 600 && sketch.mouseY >= 500){
            sketch.fill(152,194,218);
            sketch.stroke(152,194,218);
            if(sketch.mousePressed) mouse = true;
            if(!sketch.mousePressed && mouse){
                runner.mode = 3;
            }
        }
        sketch.rect(350,500,400,100,10);
        sketch.fill(255);
        sketch.stroke(255);
        sketch.text("Instructions", 550,550);

        sketch.fill(200,0,0); //exit button
        sketch.stroke(200,0,0);
        if(PApplet.dist(1025,825,sketch.mouseX,sketch.mouseY)<=50){
            sketch.fill(255,0,0);
            sketch.stroke(255,0,0);
            if(sketch.mousePressed) mouse = true;
            if(!sketch.mousePressed && mouse){
                runner.mode = 2;
            }
        }
        sketch.ellipse(1025,825,100,100);
        sketch.fill(255);
        sketch.stroke(255);
        sketch.text("EXIT", 1025, 825);

        if(!sketch.mousePressed) mouse=false; //reset mouse boolean if released

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
    }
}
