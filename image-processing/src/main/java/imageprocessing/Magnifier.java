package imageprocessing;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.image.BufferedImage;

import javax.swing.JPanel;

public class Magnifier extends JPanel{
	BufferedImage image;

	Point p;
	
	int scale;

	Magnifier(BufferedImage image) {
		setSize(200, 200);
		setLocation(0, 0);
		setBackground(Color.blue);
		this.image = image;
		repaint();
	}

	public void paintComponent(Graphics g) {
		g.drawRect(0,  0, 50, 50);
		g.drawImage(image, 0, 0, 100, 100, this);
	}
}