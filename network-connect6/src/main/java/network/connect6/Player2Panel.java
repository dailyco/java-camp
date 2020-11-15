package network.connect6;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.PrintWriter;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.Timer;

public class Player2Panel extends JPanel implements ActionListener{
	static PrintWriter writer;
	
	JLabel name = new JLabel();
	JLabel image = new JLabel();
	JLabel time = new JLabel(new ImageIcon("image/clock.png"), JLabel.LEFT);
	
	Timer timer = new Timer(1000, this);
	
	BackgroundSound sound = new BackgroundSound();
	
	int second = 30;
	
	Player2Panel() {
		setBounds(1120, 0, 320, 800);
		setBackground(new Color(224, 140, 58));
		setLayout(null);
		
		name.setBounds(95, 50, 300, 100);
		name.setFont(new Font("Serif", Font.BOLD, 20));
		
		image.setBounds(96, 120, 128, 140);
		image.setBackground(new Color(255, 244, 164));
		image.setOpaque(true);
		
		time.setBounds(40, 420, 220, 32);
		
		add(name);
		add(image);
		add(time);
		add(sound);
	}
	
	public void paintComponent(Graphics g) {
		super.paintComponent(g);

		g.setColor(Color.white);
		g.fillOval(135, 300, 50, 50);
		
		Graphics2D g2 = (Graphics2D) g;
		
		if(Board.player == 1) {
			if(!Board.myturn) {
				g.setColor(new Color(255, 244, 164));
				g.fillRect(80,  426, second * 6, 20);
				g2.setStroke(new BasicStroke(5));
				g2.setColor(Color.white);
				g2.drawRect(65, 75, 188, 320);
			}
			else {
				g.setColor(new Color(255, 244, 164));
				g.fillRect(80,  426, 180, 20);
				second = 30;
			}
		}
		else {
			if(Board.myturn) {
				g.setColor(new Color(255, 244, 164));
				g.fillRect(80,  426, second * 6, 20);
				g2.setStroke(new BasicStroke(5));
				g2.setColor(Color.white);
				g2.drawRect(65, 75, 188, 320);
			}
			else {
				g.setColor(new Color(255, 244, 164));
				g.fillRect(80,  426, 180, 20);
				second = 30;
			}
		}
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if(timer == e.getSource()) {
			second--;
			repaint();
			if(second == 0 && Board.myturn) {
				Board.myturn = false;
				Board.count = 0;
				writer.println("[REPAINT]");
				writer.println("[CHANGE]");
			}
		}
	}
}
