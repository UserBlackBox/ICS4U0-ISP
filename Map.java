
import processing.core.PApplet;
public class Map extends PApplet{

	public void settings()
	{
		size(1100,900);
	}
	
	public void handSanitizer(int x,int y)
	{
		fill(0);
		ellipse(x,y,50,50);
		fill(255);
		ellipse(x,y,40,40);
	}
	public void table(int x,int y)
	{
		
		fill(140,69,22);
		rect(x,y,25,25);
		fill(102,41,0);
		rect(x+3,y+3,19,19);
	}
	public void tree(int x,int y)
	{
		fill(137,211,16);
		ellipse(x,y,40,40);
		fill(121,227,28);
		ellipse(x,y,30,30);
	}
	public void washroom(int x, int y)
	{
		//washroom 
				fill(255);
				rect(x,y,200,100);
				fill(0);
				rect(x+5,y+5,190,90);
				fill(255);
				strokeWeight(1);
				stroke(255);
				//male
				ellipse(x+35,y+20,20,20);
				line(x+35,y+30,x+35,y+68);
				line(x+15,y+40,x+55,y+40);
				line(x+35,y+68,x+20,y+98);
				line(x+35,y+68,x+50,y+98);
				line(x+120,y,x+62,y+95);
				//female
				ellipse(x+148,y+20,20,20);
				triangle(x+148,y+30,x+128,y+71,x+168,y+71);
				line(x+128,y+40,x+168,y+40);
				line(x+138,y+60,x+138,y+95);
				line(x+158,y+60,x+158,y+95);
				noStroke();
	}
	public void hospital(int x,int y)
	{
		//hospital
		stroke(255);
				rect(x,y,200,100);
				fill(219,15,15);
				rect(x+5,y+5,190,90);
				fill(255);
				line(x+100,y+15,x+100,y+90);
				line(x+63,y+50,x+138,y+50);
				noStroke();
	}
	public void draw()
	{ 
		noStroke();
		fill(24,139,24);//dark green
		rect(0,0,1100,900);
		fill(97,97,97); //grey;
		// big road down
		rect(110,0,100,900);
		//big road left
		rect(0,110,1100,100);
		//roads completing the big rectangle
		rect(900,100,50,800);
		rect(110,715,1000,50);
		//road around lake
		strokeWeight(50);
		stroke(97,97,97);
		fill(24,139,24);
		ellipse(550,450,450,300);
		//loop road with hand sanitizer
		strokeWeight(25);
		ellipse(450,75,100,125);
		line(706,569,904,718);
		line(209,715,370,565);
		line(206,205,358,347);
		line(724,333,903,205);
		noStroke();
		// four roads connected to the center
		fill(97);
		rect(509,616,50,100);
		rect(509,186,50,100);
		rect(797,430,103,50);
		rect(200,430,103,50);
		//lake in the middle
		fill(0,170,255);
		ellipse(550,450,170,125);
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
		fill(157,82,32);
		rect(221,400,75,20);
		rect(221,490,75,20);
		rect(603,776,100,20);
		rect(589,216,100,20);
		//trees
		tree(252,576);
		tree(828,553);
		tree(286,53);
		tree(488,847);
		tree(1038,268);
		tree(1040,602);
		washroom(215,775);
		hospital(825,0);
		if(mousePressed)
		{
		System.out.println("This is mouse x "+mouseX);
		System.out.println("This is mouse y "+mouseY);
		}
		
	}
	public static void main(String[] args) {
		String[] processingArgs= {"MyMap"};
		Map map = new Map();
		Map t = new Tutorial();
		//PApplet.runSketch(processingArgs, map);
PApplet.runSketch(processingArgs, t);
	}

}
