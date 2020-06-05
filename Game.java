import processing.core.*;
import java.util.*;

public class Game {
    Map m; //map
    PApplet sketch; //PApplet window
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
    long startTime; //time at start of game (milliseconds)
    long elapsedTime = 0; //amount of time in game (milliseconds)
    double percentage; //score
    int hospitalChance = 0; //chance of hospital roll
    boolean hospitalUsed = false; //has the hospital been used
    ParkObject[] objs; //benches and tables
    boolean finished; //has the game finished
    Main main; //main class
    GameOverScreen gos; //game over screen to display

    public Game(PApplet sketch, Main runner){
        this.sketch = sketch;
        m = new Map(sketch); //create map

        boolean[] pathsAssigned = new boolean[paths.length]; //which paths are used
        people = new Person[8]; //level has 8 people
        for(int i=0; i<people.length; i++){
            int rnd; //random path
            do{
                rnd = new Random().nextInt(paths.length);
            }while(pathsAssigned[rnd]); //check that no other person using that path
            people[i] = new Person(sketch, paths[rnd], m, this);
            pathsAssigned[rnd] = true;
        }
        int rand = new Random().nextInt(people.length); //choose random person to spawn on
        people[rand].setVirus(true); //set as carrier
        people[rand].setInfection(100); //set to full infection
        //benches and tables
        objs = new ParkObject[]{new ParkObject(sketch,1, 221,400), new ParkObject(sketch, 1, 221,490), new ParkObject(sketch, 1, 603,776), new ParkObject(sketch, 1, 589,216), new ParkObject(sketch, 2, 640,30), new ParkObject(sketch, 2, 680,30), new ParkObject(sketch, 2, 640,70), new ParkObject(sketch, 2, 680,70), new ParkObject(sketch, 2, 356,237), new ParkObject(sketch, 2, 412,640), new ParkObject(sketch, 2, 834,353), new ParkObject(sketch, 2, 1045,331), new ParkObject(sketch, 2, 1045,371), new ParkObject(sketch, 2, 1045,411), new ParkObject(sketch, 2, 1045,451), new ParkObject(sketch, 2, 28,351), new ParkObject(sketch, 2, 28,501), new ParkObject(sketch, 2, 28,651), new ParkObject(sketch, 2, 28,801)};

        startTime = System.currentTimeMillis(); //get start time
        finished = false; //game over
        main = runner;
    }

    public void frame(){
        if(!finished) {
            sketch.background(24, 139, 24); //clear screen
            m.drawScreen(); //draw map
            for (Person i : people) {
                i.drawPerson(); //draw all people
                if (sketch.frameCount % 5 == 0 && i.infection != 0) i.incrementInfection(1); //increment infection
                if (sketch.frameCount % 5 == 0 && i.virus)
                    i.incrementInfection(1); //double infection speed if virus on person
                i.sanitizer(); //check if in range of sanitizer or washroom and roll for chance or infection decrease
                i.washroom();
                i.hospital(); //check if in range of hospitzl
            }

            for (ParkObject i : objs) {
                i.drawObject(); //draw benches and tables
                if (i.isClicked() && sketch.mouseButton == PApplet.RIGHT) { //if object clicked (jump to object)
                    for (Person j : people) { //find people in range with virus
                        if (i.personInRange(j) && j.virus) { //is person in range and carrier
                            j.setVirus(false); //swap carrier
                            i.setVirus(true);
                            break;
                        }
                    }
                }
            }

            if (sketch.mousePressed) { //check if mouse pressed in game
                outerLoop:
                //set outer loop for double break
                for (Person i : people) { //loop through Person entities
                    if (i.isClicked() && sketch.mouseButton == PApplet.RIGHT) { //if right click on person
                        for (Person j : people) { //loop through people for in range virus carrier
                            if (PApplet.dist(j.getCoor()[0], j.getCoor()[1], i.getCoor()[0], i.getCoor()[1]) < 37 && j.virus) { //if carrier in range to target
                                j.virus = false; //virus jump from one person to other
                                i.virus = true;
                                break outerLoop; //break both loops
                            }
                        }
                        for (ParkObject j : objs) { //loop through objects for carriers
                            if (j.personInRange(i) && j.virus) { //object in range and carrier
                                j.setVirus(false); //jump from object to person
                                i.setVirus(true);
                                break outerLoop;
                            }
                        }
                    }
                    if (i.isClicked() && sketch.mouseButton == PApplet.LEFT && i.infection == 0) { //if left click and target is healthy
                        for (Person j : people) { //look for in range 100% infected Person
                            if (PApplet.dist(j.getCoor()[0], j.getCoor()[1], i.getCoor()[0], i.getCoor()[1]) < 37 && !j.equals(i) && (j.infection >= 100 || j.virus)) { //if virus carrier or fully infected in spread range
                                i.setInfection(5); //start infection on target
                                break outerLoop; //double break
                            }
                        }
                    }
                }
            }

            double infectionSum = 0; //sum of percentages of all people
            for (Person i : people) {
                if (sketch.frameCount % 2 == 0) i.calc(); //calculate person's next coordinates
                infectionSum += i.infection; //sum of Person infections
            }
            percentage = infectionSum / (100 * people.length) * 100; //calculate infection percentage
            sketch.fill(255);
            sketch.textFont(sketch.loadFont("Graph-18.vlw"),18);
            sketch.textAlign(PApplet.LEFT,PApplet.BOTTOM);
            long timeLeft = 600000-elapsedTime;
            int minutes = (int) timeLeft/60000; //calculate minutes left
            timeLeft %= 60000; //remove minutes
            int seconds = (int) timeLeft/1000; //calculate seconds left
            String sSeconds = Integer.toString(seconds); //convert to string
            if(sSeconds.length()==1) sSeconds = "0"+sSeconds; //make two digit
            sketch.text(Math.round(percentage*10.0)/10.0 + "% infected\nTime left: 0"+minutes+":"+sSeconds, 970, 890); //percent and time overlay

            if (percentage > 50 && !hospitalUsed) hospitalChance = 100; //hospital opens halfway through game
            if (hospitalUsed) hospitalChance = 1; //hospital chance drops after first use

            elapsedTime = System.currentTimeMillis() - startTime; //calculate elapsed time
            if (elapsedTime >= 600000){ //if over 10 minutes exit game as fail
                finished = true;
                gos = new GameOverScreen(sketch, true, elapsedTime, main);
            }

            if (percentage >=100){ //if all infected end game
                finished = true;
                gos = new GameOverScreen(sketch, true, elapsedTime, main);
            }
        }
        else if(finished){ //if game finished draw game over screen
            gos.drawScreen();
        }
    }
}
