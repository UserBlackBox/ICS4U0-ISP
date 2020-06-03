
import processing.core.PApplet;

public class Map{
    PApplet sketch;
    
    public Map()
    {
    	
    }
    public Map(PApplet sketch)
    {
    	this.sketch = sketch;
    }
	public void handSanitizer(int x,int y)
	{
		sketch.fill(0);
		sketch.ellipse(x,y,50,50);
		sketch.fill(255);
		sketch.ellipse(x,y,40,40);
	}
	public void table(int x,int y)
	{
		
		sketch.fill(140,69,22);
		sketch.rect(x,y,25,25);
		sketch.fill(102,41,0);
		sketch.rect(x+3,y+3,19,19);
	}
	public void tree(int x,int y)
	{
		sketch.fill(137,211,16);
		sketch.ellipse(x,y,40,40);
		sketch.fill(121,227,28);
		sketch.ellipse(x,y,30,30);
	}
	public void washroom(int x, int y)
	{
		//washroom 
		sketch.fill(255);
		sketch.rect(x,y,200,100);
		sketch.fill(0);
		sketch.rect(x+5,y+5,190,90);
		sketch.fill(255);
		sketch.strokeWeight(1);
		sketch.stroke(255);
				//male
		sketch.ellipse(x+35,y+20,20,20);
		sketch.line(x+35,y+30,x+35,y+68);
		sketch.line(x+15,y+40,x+55,y+40);
		sketch.line(x+35,y+68,x+20,y+98);
		sketch.line(x+35,y+68,x+50,y+98);
		sketch.line(x+120,y,x+62,y+95);
				//female
		sketch.ellipse(x+148,y+20,20,20);
		sketch.triangle(x+148,y+30,x+128,y+71,x+168,y+71);
		sketch.line(x+128,y+40,x+168,y+40);
		sketch.line(x+138,y+60,x+138,y+95);
		sketch.line(x+158,y+60,x+158,y+95);
		sketch.noStroke();
	}
	public void hospital(int x,int y)
	{
		//hospital
		sketch.stroke(255);
		sketch.rect(x,y,200,100);
		sketch.fill(219,15,15);
		sketch.rect(x+5,y+5,190,90);
		sketch.fill(255);
		sketch.line(x+100,y+15,x+100,y+90);
		sketch.line(x+63,y+50,x+138,y+50);
		sketch.noStroke();
	}
	public void drawScreen()
	{ 
		sketch.noStroke();
		sketch.fill(24,139,24);//dark green
		sketch.rect(0,0,1100,900);
		sketch.fill(97,97,97); //grey;
		// big road down
		sketch.rect(110,0,100,900);
		//big road left
		sketch.rect(0,110,1100,100);
		//roads completing the big rectangle
		sketch.rect(900,100,50,800);
		sketch.rect(110,715,1000,50);
		//road around lake
		sketch.strokeWeight(50);
		sketch.stroke(97,97,97);
		sketch.fill(24,139,24);
		sketch.ellipse(550,450,450,300);
		//loop road with hand sanitizer
		sketch.strokeWeight(25);
		sketch.ellipse(450,75,100,125);
		sketch.line(706,569,904,718);
		sketch.line(209,715,370,565);
		sketch.line(206,205,358,347);
		sketch.line(724,333,903,205);
		sketch.noStroke();
		// four roads connected to the center
		sketch.fill(97);
		sketch.rect(509,616,50,100);
		sketch.rect(509,186,50,100);
		sketch.rect(797,430,103,50);
		sketch.rect(200,430,103,50);
		//lake in the middle
		sketch.fill(0,170,255);
		sketch.ellipse(550,450,170,125);
		//Hand Sanitizer
		handSanitizer(400,450);
		handSanitizer(700,450);
		handSanitizer(987,494);
		handSanitizer(643,678);
		handSanitizer(69,240);
		handSanitizer(445,76);
		//tables
		table(640,30);
		table(680,30);
		table(640,70);
		table(680,70);
		table(356,237);
		table(412,640);
		table(834,353);
		for(int i=0;i<4;i++)
		{
			table(1045,331+i*40);
			table(28,351+i*150);
		}
		//benches
		sketch.fill(157,82,32);
		sketch.rect(221,400,75,20);
		sketch.rect(221,490,75,20);
		sketch.rect(603,776,100,20);
		sketch.rect(589,216,100,20);
		//trees
		tree(252,576);
		tree(828,553);
		tree(286,53);
		tree(488,847);
		tree(1038,268);
		tree(1040,602);
		washroom(215,775);
		hospital(825,0);
	}
}
