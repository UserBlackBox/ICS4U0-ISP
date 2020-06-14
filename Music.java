import java.io.BufferedInputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import javax.sound.sampled.*;

public class Music {

	String[] album;
	String musicLocation;
	String musicRunning;
	boolean musicCheck;
	Clip clip;
	public Music (String[] album)
	{
		this.album=album;
		musicCheck=false;
	}
	
//	private DataLine.Info path(File location)
//	{
//		DataLine.Info info;
//	}
	public void playMusic (int i)
	{
		if(musicCheck)
		{
			clip.close();
		}

		try {
			musicLocation=album[i];
			musicRunning=album[i];
			InputStream musicPath = getClass().getResourceAsStream(musicLocation);
			musicPath = new BufferedInputStream(musicPath);
			AudioInputStream audioInput = AudioSystem.getAudioInputStream(musicPath);
			AudioFormat format = audioInput.getFormat();
			DataLine.Info info = new DataLine.Info(Clip.class, format);
			clip = (Clip) AudioSystem.getLine(info);
			clip.open(audioInput);
			clip.start();
			clip.loop(Clip.LOOP_CONTINUOUSLY);

		}
		catch(Exception ex) {
			ex.printStackTrace();
		}
		musicCheck=true;
		//System.out.println("I am playing track "+i);
		}
	
}
