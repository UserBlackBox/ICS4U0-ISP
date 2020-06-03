import processing.core.*;

public class Main extends PApplet {
    int mode; //current scene: 1-mainMenu, 2-exit, 3-instructions, 4-tutorial, 5-game
    MainMenu menu;
    ExitScreen exit;
    Instructions instructions;
    Map map;
    Map tutorial;
    boolean debug = false; //toggle graphics debug mode

    public Main(){
        mode = 1;
    }

    public void settings(){
        size(1100,900);
    }

    public void setup(){
        frameRate(30);
        background(0);
        surface.setTitle("Virus Game");
        menu = new MainMenu(this, this);
        exit = new ExitScreen(this);
        instructions = new Instructions(this, this);
        map=new Map(this,this);
        tutorial = new Tutorial (this,this);
        draw();
    }

    public void draw(){
        if(mode == 1) menu.drawMenu(); //main menu
        else if(mode == 2) exit.drawScreen(); //exit screen
        else if(mode == 3) instructions.drawScreen();
        else if (mode==4)tutorial.drawScreen();
        else if(mode == 5) map.drawScreen();
        else background(0);

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
        String[] processingArgs = {"Main"};
        Main mySketch = new Main();
        PApplet.runSketch(processingArgs, mySketch);
    }
}
