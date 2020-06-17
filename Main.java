import processing.core.*;
/**
 * This Class is the Main class it runs the whole program 
 * @author Quentin Fan-Chiang and Ruven Raizman 
 * June 18 2020
 * Ms.Krasteva 
 * ICS4UO
 */
public class Main extends PApplet {
    int mode; //current scene: 1-mainMenu, 2-exit, 3-instructions, 4-tutorial, 5-game
    int prevFrameMode = 0;
    MainMenuJava menu;
    ExitScreen exit;
    Instructions instructions; 
    TutorialMap tutorialMap;
    Tutorial tutorial;
    boolean musicCheck=true;
    Music play;
    String[] musicList= {"ISP music/2018-05-19_-_Video_Game_Adventure_-_David_Fesliyan.wav","ISP music/2018-06-13_-_Paranoia_-_David_Fesliyan.wav",
    "ISP music/2019-02-25_-_Poisonous_-_David_Fesliyan.wav","ISP music/2019-03-17_-_Too_Crazy_-_David_Fesliyan.wav","ISP music/2019-10-06_-_Villainous_-_David_Fesliyan.wav",
    "ISP music/Slower-Tempo-2020-01-14_-_Evil_Rising_-_David_Fesliyan.wav"};
    Game game;
    boolean debug = false; //toggle graphics debug mode

    /**
     * Class constructor 
     */
    public Main(){
        mode = 1;
        play= new Music(musicList);
        play.playMusic(1);
    }
    /**
     * Return the current mode of the game 
     * @return the current mode of the game 
     */
    public int getMode()
    {
    	return mode;
    }
    /**
     * Sets up the screen size for the PApplet window 
     */
    public void settings(){
       size(1100,900);
    }
    /**
     * Setups the PApplet window it also instantiates the most important classes
     */
    public void setup(){
        frameRate(30);
        background(0);
        surface.setTitle("Virus Game");
        menu = new MainMenuJava(this, this);
        exit = new ExitScreen(this);
        instructions = new Instructions(this, this);
        tutorialMap=new TutorialMap(this,tutorial);
    }

    /**
     * The function which keeps looping and keeps the game going and updating 
     */
    public void draw(){
        if(mode == 1) menu.drawScreen(); //main menu
        else if(mode == 2)
        {
        	if(prevFrameMode != mode)
        	{
        		play.playMusic(2);
        	}
        	exit.drawScreen(); //exit screen
        }
        else if(mode == 3)
        {
        	if(prevFrameMode != mode)
        	{
        		play.playMusic(5);
        	}
        	instructions.drawScreen(); //instructions
        }
        else if (mode==4) 
        {
        	if(prevFrameMode != 4)
        	{
        		play.playMusic(3);
        		tutorial= new Tutorial(this,tutorialMap,this);
        	}
        	tutorial.frame(); // tutorial

        }
        else if(mode == 5 && prevFrameMode!=5) 
        {
        	play.playMusic(2);
        	game = new Game(this, this); //if game mode just started make new game
        }
        else if(mode == 5) game.frame(); //if already in game mode continue displaying game
        else background(0);

        if(debug){ //graphics debug information
            noCursor();
            stroke(232, 16, 255);
            fill(0,0);
            line(mouseX,0,mouseX,height);
            ellipse(mouseX,mouseY,10,10);
            line(0,mouseY,width,mouseY);
            fill(255);
            stroke(255);
            textFont(loadFont("Graph-18.vlw"),18);
            textAlign(LEFT,BOTTOM);
            text("X: "+mouseX+"\nY: "+mouseY+"\nFPS: "+frameRate,0,height);
        }

        prevFrameMode = mode;
    }
    /**
     * Instantiates a Main class
     * @param args
     */
    public static void main(String[] args){
        String[] processingArgs = {"Main"};
        Main mySketch = new Main();
        PApplet.runSketch(processingArgs, mySketch);
    }
}
