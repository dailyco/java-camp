package connect6;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class Screen2 extends JPanel implements ActionListener{
	BufferedImage background;

	JPanel player1 = new JPanel();
	JPanel player2 = new JPanel();

	Player p1 = new Player();
	Player p2 = new Player();

	ImageIcon image1 = new ImageIcon("image/big boy.png");
	ImageIcon image2 = new ImageIcon("image/big girl.png");
	ImageIcon image3 = new ImageIcon("image/big man.png");
	ImageIcon image4 = new ImageIcon("image/big woman.png");

	JButton avartaChoose1 = new JButton(image1);
	JButton avartaChoose2 = new JButton(image1);

	JLabel nameLabel1 = new JLabel("Name");
	JLabel nameLabel2 = new JLabel ("Name");
	JLabel player1L = new JLabel("PLAYER1");
	JLabel player2L = new JLabel("PLAYER2");

	JTextField name1 = new JTextField();
	JTextField name2 = new JTextField();

	JButton start = new JButton("시 작 하 기");

	int p1image = 1;
	int p2image = 1;

	Screen2() {
		setLayout(null);

		try {
			background = ImageIO.read(new File("image/배경.jpg"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		player1.setLayout(null);
		player2.setLayout(null);
		player1.setBounds(220, 120, 400, 480);
		player2.setBounds(800, 120, 400, 480);
		player1.setBackground(new Color(249, 173, 82));
		player2.setBackground(new Color(249, 173, 82));

		avartaChoose1.setBounds(70, 60, 256, 256);
		avartaChoose2.setBounds(70, 60, 256, 256);
		avartaChoose1.setBorderPainted(false);
		avartaChoose2.setBorderPainted(false);
		avartaChoose1.addActionListener(this);
		avartaChoose2.addActionListener(this);

		nameLabel1.setBounds(25, 370, 200, 50);
		nameLabel2.setBounds(25, 370, 200, 50);
		nameLabel1.setFont(new Font("Serif", Font.BOLD, 50));
		nameLabel2.setFont(new Font("Serif", Font.BOLD, 50));

		name1.setBounds(160, 365, 220, 60);
		name2.setBounds(160, 365, 220, 60);
		name1.setFont(new Font("Serif", Font.PLAIN, 40));
		name2.setFont(new Font("Serif", Font.PLAIN, 40));

		player1L.setBounds(270, 30, 350, 150);
		player2L.setBounds(840, 30, 350, 150);
		player1L.setFont(new Font("HanS", Font.BOLD, 60));
		player2L.setFont(new Font("HanS", Font.BOLD, 60));
		player1L.setForeground(new Color(224, 140, 58));
		player2L.setForeground(new Color(224, 140, 58));

		player1.add(avartaChoose1);
		player1.add(nameLabel1);
		player1.add(name1);
		player2.add(avartaChoose2);
		player2.add(nameLabel2);
		player2.add(name2);

		start.setBounds(590, 650, 240, 80);
		start.setForeground(new Color(206, 124, 24));
		start.setFont(new Font("DX새날B", Font.PLAIN, 55));

		add(player1L);
		add(player2L);
		add(player1);
		add(player2);
		add(start);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		JButton button = (JButton)e.getSource();

		if(button == avartaChoose1) {
			if(p1image == 1) {
				p1image = 2;
				avartaChoose1.setIcon(image2);
			}
			else if(p1image == 2) {
				p1image = 3;
				avartaChoose1.setIcon(image3);
			}
			else if(p1image == 3) {
				p1image = 4;
				avartaChoose1.setIcon(image4);
			}
			else if(p1image == 4) {
				p1image = 1;
				avartaChoose1.setIcon(image1);
			}
		}

		if(button == avartaChoose2) {
			if(p2image == 1) {
				p2image = 2;
				avartaChoose2.setIcon(image2);
			}
			else if(p2image == 2) {
				p2image = 3;
				avartaChoose2.setIcon(image3);
			}
			else if(p2image == 3) {
				p2image = 4;
				avartaChoose2.setIcon(image4);
			}
			else if(p2image == 4) {
				p2image = 1;
				avartaChoose2.setIcon(image1);
			}
		}
	}
	
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		
		g.drawImage(background, 0, 0, 1440, 800, null);
		g.fillOval(520, 50, 60, 60);
		g.setColor(Color.white);
		g.fillOval(1110, 50, 60, 60);
	}

}