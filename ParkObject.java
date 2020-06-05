import processing.core.*;

public class ParkObject {
    PApplet sketch;
    boolean virus; //is carrying virus
    int type; //1 - bench, 2 - table
    int x,y;

    public ParkObject(PApplet sketch, int t, int x, int y){
        type = t;
        this.sketch = sketch;
        this.x = x;
        this.y = y;
    }

    public void drawObject(){
        sketch.noStroke();
        if(type==1) bench(x,y);
        if(type==2) table(x,y);
    }

    public void table(int x,int y) {
        sketch.fill(140,69,22);
        sketch.rect(x,y,25,25);
        sketch.fill(102,41,0);
        sketch.rect(x+3,y+3,19,19);
        sketch.stroke(255,0,0);
        sketch.strokeWeight(2);
        if(virus) {
            sketch.line(x + 7.5f, y + 7.5f, x + 17.5f, y + 17.5f);
            sketch.line(x + 7.5f, y + 17.5f, x + 17.5f, y + 7.5f);
        }
    }
    public void bench(int x, int y){
        sketch.fill(157,82,32);
        sketch.rect(x,y,75,20);
        sketch.stroke(255,0,0);
        sketch.strokeWeight(2);
        if(virus) {
            sketch.line(x + 27.5f, y + 5, x + 40, y + 15);
            sketch.line(x + 27.5f, y + 15, x + 40, y + 5);
        }
    }

    public void setVirus(boolean v){
        virus = v;
    }

    public boolean personInRange(Person p){
        return (type == 2 && p.x <= x + 50 && p.x >= x - 25 && p.y <= y + 50 && p.y >= y - 25) || (type==1 && p.x<=x+100 && p.x>=x-25 && p.y<=y+45 && p.y>=y-25);
    }

    public boolean isClicked(){
        return sketch.mousePressed && (type == 1 && sketch.mouseX >= x && sketch.mouseX <= x+75 && sketch.mouseY >= y && sketch.mouseY <= y+20) || (type == 2 && sketch.mouseX >= x && sketch.mouseX <= x+25 && sketch.mouseY >= y && sketch.mouseY <= y+25);
    }
}
