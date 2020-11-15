package network.connect6;

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

	JPanel player = new JPanel();

	ImageIcon image1 = new ImageIcon("image/big boy.png");
	ImageIcon image2 = new ImageIcon("image/big girl.png");
	ImageIcon image3 = new ImageIcon("image/big man.png");
	ImageIcon image4 = new ImageIcon("image/big woman.png");

	JButton avartaChoose = new JButton(image1);

	JLabel nameLabel = new JLabel("Name");
	JLabel playerL = new JLabel("PLAYER INFO");

	JTextField name = new JTextField();

	JButton goWait = new JButton("대 기 실 로");

	int image = 1;

	Screen2() {
		setLayout(null);

		try {
			background = ImageIO.read(new File("image/배경.jpg"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		player.setLayout(null);
		player.setBounds(520, 120, 400, 480);
		player.setBackground(new Color(249, 173, 82));

		avartaChoose.setBounds(70, 60, 256, 256);
		avartaChoose.setBorderPainted(false);
		avartaChoose.addActionListener(this);

		nameLabel.setBounds(25, 370, 200, 50);
		nameLabel.setFont(new Font("Serif", Font.BOLD, 50));

		name.setBounds(160, 365, 220, 60);
		name.setFont(new Font("Serif", Font.PLAIN, 40));

		playerL.setBounds(530, 30, 400, 150);
		playerL.setFont(new Font("HanS", Font.BOLD, 60));
		playerL.setForeground(new Color(224, 140, 58));

		player.add(avartaChoose);
		player.add(nameLabel);
		player.add(name);

		goWait.setBounds(607, 650, 240, 80);
		goWait.setForeground(new Color(206, 124, 24));
		goWait.setFont(new Font("DX새날B", Font.PLAIN, 55));

		add(playerL);
		add(player);
		add(goWait);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		JButton button = (JButton)e.getSource();

		if(button == avartaChoose) {
			if(image == 1) {
				image = 2;
				avartaChoose.setIcon(image2);
			}
			else if(image == 2) {
				image = 3;
				avartaChoose.setIcon(image3);
			}
			else if(image == 3) {
				image = 4;
				avartaChoose.setIcon(image4);
			}
			else if(image == 4) {
				image = 1;
				avartaChoose.setIcon(image1);
			}
		}
	}
	
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		
		g.drawImage(background, 0, 0, 1440, 800, null);
	}
}
