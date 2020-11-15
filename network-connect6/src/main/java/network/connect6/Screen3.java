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

public class Screen3 extends JPanel{
	BufferedImage background;
	
	JButton room1 = new JButton("1번 방");
	JButton room2 = new JButton("2번 방");
	JButton room3 = new JButton("3번 방");
	JButton room4 = new JButton("4번 방");
	JButton room5 = new JButton("5번 방");
	JButton room6 = new JButton("6번 방");
	
	JLabel message = new JLabel();
	
	Screen3() {
		setLayout(null);

		try {
			background = ImageIO.read(new File("image/배경.jpg"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		room1.setBounds(195, 180, 250, 200);
		room1.setForeground(new Color(206, 124, 24));
		room1.setFont(new Font("DX새날B", Font.PLAIN, 80));
		
		room2.setBounds(605, 180, 250, 200);
		room2.setForeground(new Color(206, 124, 24));
		room2.setFont(new Font("DX새날B", Font.PLAIN, 80));
		
		room3.setBounds(995, 180, 250, 200);
		room3.setForeground(new Color(206, 124, 24));
		room3.setFont(new Font("DX새날B", Font.PLAIN, 80));
		
		room4.setBounds(195, 440, 250, 200);
		room4.setForeground(new Color(206, 124, 24));
		room4.setFont(new Font("DX새날B", Font.PLAIN, 80));
		
		room5.setBounds(605, 440, 250, 200);
		room5.setForeground(new Color(206, 124, 24));
		room5.setFont(new Font("DX새날B", Font.PLAIN, 80));
		
		room6.setBounds(995, 440, 250, 200);
		room6.setForeground(new Color(206, 124, 24));
		room6.setFont(new Font("DX새날B", Font.PLAIN, 80));
		
		message.setBounds(185, 70, 1300, 100);
		message.setForeground(new Color(255, 154, 30));
		message.setFont(new Font("DX새날B", Font.PLAIN, 70));
		
		add(room1);
		add(room2);
		add(room3);
		add(room4);
		add(room5);
		add(room6);
		add(message);
	}
	
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		
		g.drawImage(background, 0, 0, 1440, 800, null);
	}
}
