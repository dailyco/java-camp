package connect6;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.sound.sampled.Clip;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.Timer;

public class Screen3 extends JPanel implements MouseListener{
	BufferedImage background;
	
	Board board = new Board();
	
	static JPanel player1 = new JPanel();
	static JPanel player2 = new JPanel();
	
	JButton start = new JButton("START!");
	static JButton restart = new JButton();
	
	Clip clip;
	static Timer timer1, timer2;
	
	Screen3() {
		setLayout(null);
		player1.setLayout(null);
		player2.setLayout(null);

		try {
			background = ImageIO.read(new File("image/배경.jpg"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		board.setBounds(345, 12, 750, 750);
		
		start.setBounds(550, 300, 300, 100);
		start.setFont(new Font("DX새날B", Font.BOLD, 60));
		start.addMouseListener(this);

		add(start);
	}
	
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		
		g.drawImage(background, 0, 0, 1440, 800, null);
		add(player1);
		add(player2);
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		if(e.getSource() == start) {
			remove(start);
			add(board);
			clip.start();
			Player2Panel.second = 30;
			timer1.start();
			timer2.start();
		}
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		if (e.getSource() == start)
			start.setForeground(Color.red);
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		if (e.getSource() == start)
			start.setForeground(Color.black);
	}
	
}