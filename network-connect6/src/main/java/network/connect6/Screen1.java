package network.connect6;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class Screen1 extends JPanel{
	BufferedImage background;

	JLabel title = new JLabel("CONNECT6");

	JButton start = new JButton("시 작 하 기");

	Screen1() {
		setLayout(null);

		try {
			background = ImageIO.read(new File("image/배경.jpg"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		title.setBounds(385, 190, 800, 250);
		title.setForeground(new Color(255, 195, 35));
		title.setFont(new Font("HanS", Font.PLAIN, 150));

		start.setBounds(590, 550, 240, 80);
		start.setForeground(new Color(206, 124, 24));
		start.setFont(new Font("DX새날B", Font.PLAIN, 55));

		add(title);
		add(start);
	}

	public void paintComponent(Graphics g) {
		g.drawImage(background, 0, 0, 1440, 800, null);
	}
}
