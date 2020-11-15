package network.connect6;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.ImageIcon;
import javax.swing.JButton;

public class BackgroundSound extends JButton implements ActionListener{
	ImageIcon speaker = new ImageIcon("image/speaker.png");
	ImageIcon mute = new ImageIcon("image/mute.png");

	AudioInputStream sound;
	
	Clip clip;

	boolean click = true;

	BackgroundSound() {
		setBounds(240, 695, 64, 64);
		
		try {
			sound = AudioSystem.getAudioInputStream(new File("music.wav"));
			clip = AudioSystem.getClip();
			clip.open(sound);
			clip.start();
		} catch (UnsupportedAudioFileException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (LineUnavailableException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		setIcon(speaker);
		setBorderPainted(false);
		addActionListener(this);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
		click = (!click);
		
		if(click) {
			clip.start();
			this.setIcon(speaker);
		}
		else {
			clip.stop();
			this.setIcon(mute);
		}
			
	}
}
