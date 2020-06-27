package easterEggs;



import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

import java.io.File;




public class Music {

	public static void playMusic(String nameFile) {
		try {
			//music=new FileInputStream(new File(nameFile));
			AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(new File(nameFile).getAbsoluteFile());
	        Clip clip = AudioSystem.getClip();
	        clip.open(audioInputStream);
	        clip.start();
			
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
}
