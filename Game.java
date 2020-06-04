import processing.core.*;
import java.util.*;

public class Game {
    Map m;
    Tutorial t;
    PApplet sketch;
    int level;
    int[][][] paths = new int[][][]{
            {{200,205}, {372,357}, {506,306}, {720,340}, {910,200}, {910,735}, {175,735}},
            {{20,160}, {150,175}, {160,860}, {150,175}},
            {{170,30}, {170,170}, {485,133}, {499,57}, {444,14}, {404,47}, {405,145}, {1060,157}, {170,170}},
            {{190,170}, {455,135}, {402,77}, {445,16}, {500,60}, {530,205}, {545,290}, {785,455}, {925,440}, {910,722}, {712,581}, {525,616}, {528,737}, {630,772}, {185,740}, {178,191}},
            {{151,890}, {198,725}, {376,557}, {525,610}, {708,569}, {910,727}, {145,745}},
            {{553,300}, {770,427}, {707,552}, {548,601}, {386,552}, {347,373}},
            {{924,163}, {916,748}, {185,735}, {150,167}, {448,142}, {526,197}, {539,301}, {723,340}, {886,216}},
            {{200,205}, {365,249}, {537,301}, {717,343}, {786,453}, {925,450}, {926,188}, {190,184}},
            {{173,451}, {326,456}, {375,551}, {525,605}, {708,565}, {907,727}, {180,741}},
            {{718,347}, {910,200}, {530,175}, {530,285}}
    }; //possible person walking paths
    Person[] people; //person objects in game

    public Game(PApplet sketch, int l){
        this.sketch = sketch;
        level = l;
        if(l==1) m = new Map(sketch);
        if(l==2) m = new Tutorial();

        boolean[] pathsAssigned = new boolean[paths.length]; //which paths are used
        if(l == 1){
            people = new Person[8]; //level has 8 people
            for(int i=0; i<people.length; i++){
                int rnd; //random path
                do{
                    rnd = new Random().nextInt(paths.length);
                }while(pathsAssigned[rnd]); //check that no other person using that path
                people[i] = new Person(sketch, paths[rnd]);
                pathsAssigned[rnd] = true;
            }
            int rand = new Random().nextInt(people.length);
            people[rand].setVirus(true);
            people[rand].setInfection(100);
        }
    }

    public void frame(){
        m.drawScreen(); //draw map
        for(Person i:people){
            i.drawPerson(); //draw all people
            if(sketch.frameCount % 5==0 && i.infection !=0) i.incrementInfection(1); //increment infection
            if(sketch.frameCount % 5==0 && i.virus) i.incrementInfection(1); //double infection speed if virus on person
        }

        if(sketch.mousePressed){
            outerLoop: for(int i=0;i<people.length;i++){
                if(people[i].isClicked() && sketch.mouseButton == PApplet.RIGHT){
                    for(Person j: people){
                        if(PApplet.dist(j.getCoor()[0],j.getCoor()[1],people[i].getCoor()[0],people[i].getCoor()[1])<37 && j.virus){
                            j.virus = false;
                            people[i].virus = true;
                            break outerLoop;
                        }
                    }
                }
                if(people[i].isClicked() && sketch.mouseButton == PApplet.LEFT && people[i].infection==0){
                    for(Person j: people){
                        if(PApplet.dist(j.getCoor()[0],j.getCoor()[1],people[i].getCoor()[0],people[i].getCoor()[1])<37 && !j.equals(people[i]) && (j.infection>=100 || j.virus)){
                            people[i].setInfection(5);
                            break outerLoop;
                        }
                    }
                }
            }
        }

        for(Person i:people){
            if(sketch.frameCount % 2==0) i.calc(); //calculate person's next coordinates
        }
    }
}
