
import processing.core.PApplet;
public class Map  extends PApplet{

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
	public void draw()
	{ 
		noStroke();
		fill(24,139,24);
		rect(0,0,1100,900);
		fill(97,97,97);
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
		//washroom 
		fill(255);
		rect(215,775,200,100);
		fill(0);
		rect(220,780,190,90);
		fill(255);
		strokeWeight(1);
		stroke(255);
		//male
		ellipse(250,795,20,20);
		line(250,805,250,843);
		line(230,815,270,815);
		line(250,843,235,873);
		line(250,843,265,873);
		line(335,780,277,870);
		//female
		ellipse(363,795,20,20);
		triangle(363,805,343,846,383,846);
		line(343,815,383,815);
		line(353,841,353,871);
		line(373,841,373,871);
		//hospital
		rect(825,0,200,100);
		fill(219,15,15);
		rect(830,5,190,90);
		fill(255);
		line(925,15,925,90);
		line(888,50,963,50);
		if(mousePressed)
		{
		System.out.println("This is mouse x "+mouseX);
		System.out.println("This is mouse y "+mouseY);
		}
		
	}
	public static void main(String[] args) {
		String[] processingArgs= {"MyMap"};
		Map map = new Map();
		PApplet.runSketch(processingArgs, map);

	}

}
