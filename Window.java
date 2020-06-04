import processing.core.*;

public class Window extends PApplet{
    boolean debug = false; //toggle graphics debug mode
    Game game;

    public void settings(){
        size(1100,900);
    }

    public void setup(){
        frameRate(30);
        background(0);
        surface.setTitle("Virus Game");
        game = new Game(this, 1);
        draw();
    }

    public void draw(){
        game.frame();

        if(debug){ //graphics debug information
            noCursor();
            stroke(232, 16, 255);
            fill(0,0);
            line(mouseX,0,mouseX,height);
            ellipse(mouseX,mouseY,10,10);
            line(0,mouseY,width,mouseY);
            fill(255);
            stroke(255);
            textFont(loadFont("Graph-18.vlw"),18);
            textAlign(LEFT,BOTTOM);
            text("X: "+mouseX+"\nY: "+mouseY+"\nFPS: "+frameRate,0,height);
        }
    }

    public static void main(String[] args){
        String[] processingArgs = {"Window"};
        Window mySketch = new Window();
        PApplet.runSketch(processingArgs, mySketch);
    }
}
