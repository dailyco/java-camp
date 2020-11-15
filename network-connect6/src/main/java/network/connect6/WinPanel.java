package network.connect6;

import java.awt.Color;
import java.awt.Font;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class WinPanel extends JPanel{
	JPanel back = new JPanel();
	JLabel message = new JLabel();
	JLabel image = new JLabel();

	WinPanel(String name, ImageIcon image) {
		setSize(750, 750);
		setBackground(new Color(255, 207, 165));
		
		back.setLayout(null);
		back.setBounds(175, 100, 400, 550);
		back.setBackground(new Color(224, 140, 58));
		
		message.setBounds(90, 400, 300, 100);
		message.setText(name + " WIN!");
		message.setFont(new Font("HanS", Font.BOLD, 50));
		
		this.image.setBounds(136, 120, 128, 140);
		this.image.setBackground(new Color(255, 244, 164));
		this.image.setOpaque(true);
		this.image.setIcon(image);
		
		back.add(message);
		back.add(this.image);
		
		add(back);
		
	}
}
