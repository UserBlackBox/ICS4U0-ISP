import java.awt.*;
import java.awt.event.*;
import java.awt.GridBagLayout;
import javax.swing.*;
import processing.core.*;

public class MainMenuJava extends JPanel implements ActionListener {
	JButton tutorialButton,gameButton,instructionsButton,exitButton;
	GridBagConstraints gbc;
	JPanel p;
	JFrame frame;
	JLabel title;
	Main runner;
	PApplet sketch;

	public MainMenuJava(Main runner, PApplet sketch){
		this.runner=runner;
		p=new JPanel();
		frame= new JFrame("Virus Game");
		tutorialButton = new JButton("Tutorial");
		gameButton= new JButton("Game");
		instructionsButton= new JButton("Instructions");
		exitButton=new JButton("Exit");
		title= new JLabel("Virus Game");
		this.sketch = sketch;
	}

	public void drawScreen(){
		title.setForeground(Color.white);
		title.setFont(new Font("Graph-48.vlw",Font.BOLD,18));
		gbc= new GridBagConstraints();
		gbc.insets= new Insets(15,15,15,15);
		frame.setLayout(new GridBagLayout());
		gbc.weightx=1;
		gbc.weighty=0.1;
		gbc.gridx=0;
		gbc.gridy=1;
		frame.add(title,gbc);
		gbc.weighty=1;
		gbc.gridx=0;
		gbc.gridy=2;
		gbc.fill=GridBagConstraints.BOTH;
		frame.add(tutorialButton,gbc);
		gbc.gridx=0;
		gbc.gridy=3;
		frame.add(gameButton,gbc);
		gbc.gridx=0;
		gbc.gridy=4;
		frame.add(instructionsButton,gbc);
		gbc.weightx=0.1;
		gbc.weighty=0.1;
		gbc.gridx=0;
		gbc.gridy=5;
		gbc.anchor=GridBagConstraints.LINE_END;
		gbc.gridwidth=1;
		gbc.fill=GridBagConstraints.NONE;
		gbc.insets= new Insets(0,0,0,0);
		frame.add(exitButton,gbc);
		//		frame.add(new JLabel(new ImageIcon("adenovirus.png")));
		//		frame.add(new JLabel(new ImageIcon("bacteriophage.png")));
		frame.setSize(500,500);
		frame.getContentPane().setBackground(new Color(56,88,128));
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		tutorialButton.addActionListener(this);
		gameButton.addActionListener(this);
		instructionsButton.addActionListener(this);
		exitButton.addActionListener(this);
		runner.prevFrameMode = 1;
		runner.play.playMusic(1);
		sketch.frame.setVisible(false); //hide PApplet window
		sketch.noLoop(); //stop PApplet draw() loop
        frame.setLocationRelativeTo(null); // makes the screen centered 
		frame.setVisible(true); //show main menu window
	}


	public void actionPerformed(ActionEvent e) {
		if(e.getSource()== gameButton)
		{
			runner.mode=5;
			//frame.getContentPane().setBackground(Color.black);
		}
		else if(e.getSource()== tutorialButton)
		{
			runner.mode=4;
			//frame.getContentPane().setBackground(Color.blue);
		}
		else if(e.getSource()== instructionsButton)
		{
			runner.mode=3;
			//frame.getContentPane().setBackground(Color.white);
		}
		else if(e.getSource()== exitButton)
		{
			runner.mode=2;
			//frame.getContentPane().setBackground(Color.red);
		}
		frame.setVisible(false); //hide main menu window
		sketch.loop(); //start PApplet draw() loop
		sketch.frame.setVisible(true); //show PApplet window
	}

}
