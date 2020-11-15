package imageprocessing;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Point;
import java.awt.image.BufferedImage;

import javax.swing.JPanel;

public class ImagePanel extends JPanel{
	BufferedImage inputimage;
	BufferedImage outputimage;
	BufferedImage image;
	BufferedImage magnifier;
	
	Image result;
	Image output;

	int width,height;
	int scale;
	
	Color[][] currentColor;
	
	Point p;

	ImagePanel() {
		setLayout(null);
		setBounds(0, 0, 1440, 540);
		setBackground(Color.white);
	}
	
	public void paintComponent(Graphics g) {
		Graphics2D g2 = (Graphics2D) g;
		
		g.drawImage(inputimage, 0, 0, null);
		g.drawImage(outputimage, 720, 0, null);
		
		if(ButtonsPanel.enlargement == true) {
			g2.setColor(Color.magenta);
			g2.setStroke(new BasicStroke(3));
			g2.drawRect(p.x + 720, p.y, 150, 150);
			g2.drawImage(result, p.x + 720, p.y, 150, 150, null);
		}
		
		if(ButtonsPanel.mosaic == true) {
			g2.drawImage(output, 720, 0, width, height, null);
			ButtonsPanel.mosaic = false;
		}
	}
}