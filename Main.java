import processing.core.PApplet;

public class Main extends PApplet {
    public void settings(){
        size(500,500);
    }

    public void setup(){
        background(0);
    }

    public static void main(String[] args){
        String[] processingArgs = {"Main"};
        Main mySketch = new Main();
        PApplet.runSketch(processingArgs, mySketch);
    }
}
