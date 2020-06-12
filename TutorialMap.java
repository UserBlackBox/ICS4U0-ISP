import processing.core.PApplet;

public class TutorialMap extends Map
{
	ParkObject[] objs;
	Tutorial events;

	public TutorialMap(PApplet sketch, Tutorial process)
	{
		super(sketch);
		events=process;
		//benches and tables
		objs = new ParkObject[]{ new ParkObject(sketch,2,575,360),new ParkObject(sketch,1,550,520)};
	}

	public void drawScreen()
	{
		sketch.noStroke();
		sketch.fill(97);
		sketch.rect(0,0,1100,900);
		sketch.fill(24,139,24);
		sketch.rect(0,250,1110,400);
		sketch.strokeWeight(25);
		sketch.stroke(97);
		sketch.fill(24,139,24);
		sketch.ellipse(300,550,150,150);
		sketch.noStroke();
		sketch.fill(97);
		sketch.rect(0,400, 1100,100);
		sketch.rect(50,500,50,50);
		sketch.rect(350,350,50,50);
		sketch.rect(850,500,50,50);
		handSanitizer(75,575);
		washroom(275,250);
		hospital(775,550);
		for (ParkObject i : objs) {
			i.drawObject(); //draw benches and tables
		}
//		if(sketch.mousePressed)
//		{
//			System.out.println("THis is x "+sketch.mouseX+"this is mouse y "+sketch.mouseY);
//		}

	}
}	

