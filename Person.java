import processing.core.PApplet;
import java.util.*;

public class Person {
    int[][] path; //walking paths
    int infection; //percentage infected
    boolean virus; //is the virus riding this person
    float x, y; //current coordinates
    int pathIndex; //which index in path are they walking to currently
    PApplet sketch;
    double speed = 1;

    public Person(PApplet sketch, int[][] p){
        int rnd = new Random().nextInt(p.length);
        path = p;
        x = path[rnd][0];
        y = path[rnd][1];
        if(rnd == p.length-1) pathIndex = 0;
        else pathIndex = rnd+1;
        virus = false;
        infection = 0;
        this.sketch = sketch;
        speed = 1 + (3 - 1) * new Random().nextDouble();
    }

    public void drawPerson(){
        sketch.fill(255,243,0);
        sketch.stroke(0);
        sketch.strokeWeight(1);
        sketch.ellipse(x,y,25,25);
        sketch.fill(255,106,0);
        sketch.strokeWeight(0);
        int diameter = (int) (25*(infection/100.0));
        sketch.ellipse(x,y,diameter,diameter);
        sketch.stroke(255,0,0);
        sketch.strokeWeight(2);
        if(virus){
            sketch.line(x+10,y-10,x-10,y+10);
            sketch.line(x-10,y-10,x+10,y+10);
        }
        if(infection==100 || virus){
            sketch.stroke(255,106,0,80);
            sketch.fill(255,106,0,20);
            sketch.ellipse(x,y,50,50);
        }
    }

    public void setVirus(boolean b){
        virus = b;
    }

    public void setInfection(int p){
        infection = p;
    }

    public void incrementInfection(int p){
        if(infection+p > 100) infection = 100; //cant infect more than 100%
        else infection+=p;
    }

    public void decreaseInfection(int p){
        if(infection-p < 0) infection = 0; //if decreasing lower than 0 set to 0
        else infection-=p;
    }

    public void calc(){
        if(x == path[pathIndex][0] && y == path[pathIndex][1]){ //check if arrived at target
            if(pathIndex == path.length-1) pathIndex = 0; //if at end of path set target to beginning
            else pathIndex+=1; //set to next target
            return;
        }
        if(PApplet.dist(x,y,path[pathIndex][0],path[pathIndex][1]) < 2){ //x,y are floats so correct to int if close enough to target
            x = path[pathIndex][0];
            y = path[pathIndex][1];
            return;
        }

        double d = Math.sqrt(Math.pow(path[pathIndex][0]-x,2)+Math.pow(path[pathIndex][1]-y,2)); //calculate distance between current and target
        double t = speed/d; //calculate distance ratio to next point
        x = (float) ((1-t)*x+t*path[pathIndex][0]); //calculate points
        y = (float) ((1-t)*y+t*path[pathIndex][1]);
    }

    public float[] getCoor(){
        return new float[]{x,y};
    }

    public boolean isClicked(){
        return (sketch.mousePressed && PApplet.dist(sketch.mouseX,sketch.mouseY,x,y) <= 25);
    }

}
