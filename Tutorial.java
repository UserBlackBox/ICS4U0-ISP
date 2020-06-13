import java.util.Random;
import processing.core.PApplet;
import processing.core.PShape;

public class Tutorial 
{

	PApplet sketch;
	TutorialMap m;
	Main main;
	Person[] people; //person objects in game
	int[][][] paths = new int[][][]{
		{{3,435}, {258,435}, {223,563}, {296,627}, {373,545}, {322,436}, {1089,455}},
		{{68,520}, {68,490}},
		{{89,413}, {165,413}},
		{{372,369}, {372,408}},
		{{886,474}, {870,535}},
		{{450,480},{165,480},{165,435},{80,435}}
	};
    long startTime;   //in milliseconds
    long elapsedTime;
    long endTime;
    int fps=30;
    boolean start; //did the tutorial screen run 
	boolean finished; //has the tutorial finished
	boolean gates[]=new boolean[5];
	TutorialScreens gg;
	public Tutorial(PApplet sketch,TutorialMap m,Main runner)
	{
		this.sketch=sketch;
		people = new Person[6]; //level has 8 people
		people[0]=new Person(sketch,paths[0],this);
		people[0].setVirus(true);
		for(int i=1; i<people.length; i++){
			people[i] = new Person(sketch, paths[i], this);
		}
		startTime = System.currentTimeMillis(); //get start time
		elapsedTime = System.currentTimeMillis() - startTime;
		finished = false; //game over
		start=false;
		for(int i=0;i<gates.length;i++)
		{
			gates[i]=true;
		}
		main = runner;
		this.m=m;
		m.drawScreen();
		gg=new TutorialScreens(sketch,runner);
	}
	
	public void frame()
	{
		if(!finished) {
			sketch.background(24, 139, 24); //clear screen
			m.drawScreen(); //draw map
			for (Person i : people) {
				i.drawPerson(); //draw all people
				if (sketch.frameCount % 5 == 0 && i.infection != 0) i.incrementInfection(1); //increment infection
				if (sketch.frameCount % 5 == 0 && i.virus)
					i.incrementInfection(1); //double infection speed if virus on person
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
		}
		for (Person i : people) {
			if (sketch.frameCount % 2 == 0) i.calc(); //calculate person's next coordinates
		}
		elapsedTime = System.currentTimeMillis() - startTime;
		if(elapsedTime<20000 && gates[0] )
		{
			//System.out.println(elapsedTime);
			slide(" You may only infect people inside your range showed by the orange circle."+
					" Infect and jump to another person by right clicking and only infect a person by left clicking. X marks the spot(you);)" 
					,"How to infect");
			sketch.fill(200,0,0);
			sketch.rect(131,341,20,40);	
			sketch.triangle(111, 381, 141, 396, 171, 381);
			sketch.fill(0);
			sketch.text("Try left clicking this person when you are in range. In the tutorial you may have to click the person a few times and a bit longer to infect him", 5, 250, 150, 350);			
			if(people[2].isInfected())
			{
				endTime=elapsedTime;
				fps=30;
				sketch.frameRate(fps);
				gates[0]=false;
			}
			if(fps!=1 && elapsedTime>2000)
			{
				fps=fps-1;
				sketch.frameRate(fps);
			}
			if(elapsedTime<20000&&elapsedTime>19000)
			{
				fps=30;
				sketch.frameRate(fps);
				gates[0]=false;
				people[2].setInfection(10);
				endTime=elapsedTime;
			}
		}
		else if(elapsedTime<endTime+20000 && elapsedTime>endTime+3000 && gates[1] && !gates[0])
		{
			
			if(elapsedTime>endTime+8000)
			{
				people[2].setInfection(100);
			}
			if(elapsedTime>24000)
			{
				people[2].setInfection(100);
			}
			slide(" After a while an infected person will beomce fully infected (they are an orange person)."
					+" These people can then also infect others with your help."
					+" To infect somebody just left click them."
					,"Virus Spread");
			sketch.fill(200,0,0);
			sketch.rect(131,341,20,40);	
			sketch.triangle(111, 381, 141, 396, 171, 381);
			sketch.fill(0);
			sketch.text("Try left clicking this person when you are in range. In the tutorial you may have to click the person a few times and a bit longer to infect him", 5, 250, 150, 350);			
			if(people[5].isInfected())
			{
				endTime=elapsedTime;
				fps=30;
				sketch.frameRate(fps);
				gates[1]=false;
			}
			if(fps!=1 && elapsedTime>endTime+5600)
			{
				fps=fps-1;
				sketch.frameRate(fps);
			}
			if(elapsedTime<endTime+20000&&elapsedTime>endTime+18000)
			{
				endTime=elapsedTime;
				fps=30;
				sketch.frameRate(fps);
				gates[1]=false;
			}
			
		}
		else if(elapsedTime<endTime+20000 && elapsedTime>endTime+3000 && gates[2] && !gates[1])
		{
			slide(" Once people are infected its not a dead end for them."
					+ " Their are different methods for them to try and get rid of the virus to."
					,"People can heal too");
			sketch.text("Hand Sanitizer = least effective, most usable",25,600,100,650);
			sketch.text("Washroom = middle effectiveness, middle usablity",100,250,200,350);
			sketch.text("Hospital = Highly effective, rare usability",525,550,600,625);
			if(elapsedTime<endTime+20000&&elapsedTime>endTime+18000)
			{
				endTime=elapsedTime;
				finished=true;
			}
		}
		else if (finished==true){
			gg.drawScreenOut();
		}
		
	}
	private void slide(String mainText,String title)
	{
		sketch.fill(126,54,52);
		sketch.rect(775,255,300,140);
		sketch.fill(124,118,113);
		sketch.rect(780,260,290,130);
		sketch.fill(0);
		sketch.textSize(15);
		sketch.text(mainText,780,260,290,130);
		sketch.textAlign(sketch.CENTER);
		sketch.textSize(18);
		sketch.text(title,500,260,300,50);
		sketch.textAlign(sketch.LEFT);
		sketch.textSize(12);
	}
}
