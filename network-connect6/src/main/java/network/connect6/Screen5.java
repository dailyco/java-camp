package network.connect6;

import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JPanel;

public class Screen5 extends JPanel implements MouseListener{
	BufferedImage background;
	
	Board board = new Board();
	
	Player1Panel player1 = new Player1Panel();
	Player2Panel player2 = new Player2Panel();
	
	void setPanel1(String name, ImageIcon image) {
		player1.name.setText("Name : " + name);
		player1.image.setIcon(image);
	}
	
	void setPanel2(String name, ImageIcon image) {
		player2.name.setText("Name : " + name);
		player2.image.setIcon(image);
	}
	
	Screen5() {
		setLayout(null);

		try {
			background = ImageIO.read(new File("image/배경.jpg"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		add(player1);
		add(player2);
		add(board);
	}
	
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		
		g.drawImage(background, 0, 0, 1440, 800, null);
	}
	
	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

}
