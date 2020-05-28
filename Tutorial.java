
public class Tutorial extends Map
{
	public void draw()
	{
	this.settings();
	fill(24,139,24);
	rect(0,250,1110,400);
	strokeWeight(25);
	stroke(97);
	fill(24,139,24);
	ellipse(300,550,150,150);
	noStroke();
	fill(97);
	rect(0,400, 1100,100);
	rect(50,500,50,50);
	rect(350,350,50,50);
	rect(850,500,50,50);
	handSanitizer(75,575);
	washroom(275,250);
	hospital(775,550);
	table(575,360);
	fill(157,82,32);
	rect(550,520,75,20);
	fill(0);
	}
	public void slide()
	{
		fill(126,54,52);
		rect(775,255,300,140);
		fill(124,118,113);
		rect(780,260,290,130);
	}
	//info slide
	public void pause(String mainText,String title)
	{
		slide();
		fill(0);
		text(mainText,780,260,290,130);
		text(title,480,260,300,50);		
		while(true)
		{
			delay(1);
			if(mousePressed)
			{
				break;
			}
		}
		
	}
}
