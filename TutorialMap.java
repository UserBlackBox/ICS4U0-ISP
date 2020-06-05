import processing.core.PApplet;

public class TutorialMap extends Map
{


    public TutorialMap(PApplet sketch)
    {
    	super(sketch);
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
	table(575,360);
	sketch.fill(157,82,32);
	sketch.rect(550,520,75,20);
	sketch.fill(0);
	}
	
	public void slide()
	{
		sketch.fill(126,54,52);
		sketch.rect(775,255,300,140);
		sketch.fill(124,118,113);
		sketch.rect(780,260,290,130);
	}
	//info slide
	public void pause(String mainText,String title)
	{
		slide();
		sketch.fill(0);
		sketch.text(mainText,780,260,290,130);
		sketch.text(title,480,260,300,50);
		while(true)
		{
			sketch.delay(1);
			if(sketch.mousePressed)
			{
				break;
			}
		}
		
	}
}
