package network.connect6;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class Screen4 extends JPanel implements MouseListener{
	BufferedImage background;
	
	JButton start = new JButton("시 작 하 기");
	JButton exit = new JButton("나 가 기");
	
	JLabel enter = new JLabel();
	JLabel ready = new JLabel("대전상대가 없습니다. 조금만 기다리세요.");
	
	Screen4() {
		setLayout(null);

		try {
			background = ImageIO.read(new File("image/배경.jpg"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		enter.setBounds(100, 200, 800, 100);
		enter.setFont(new Font("DX새날B", Font.PLAIN, 70));
		
		ready.setBounds(100, 400, 1200, 100);
		ready.setFont(new Font("DX새날B", Font.PLAIN, 70));
		
		start.setBounds(450, 650, 240, 80);
		start.setForeground(new Color(206, 124, 24));
		start.setFont(new Font("DX새날B", Font.PLAIN, 55));
		start.setEnabled(false);
		start.addMouseListener(this);
		
		exit.setBounds(750, 650, 240, 80);
		exit.setForeground(new Color(206, 124, 24));
		exit.setFont(new Font("DX새날B", Font.PLAIN, 55));
		
		add(enter);
		add(ready);
		add(start);
		add(exit);
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
		if(e.getSource() == start)
			start.setForeground(Color.red);
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		if(e.getSource() == start)
			start.setForeground(new Color(206, 124, 24));
	}
}
