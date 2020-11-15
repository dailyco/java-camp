package connect6;

import java.awt.Color;
import java.awt.Font;

import javax.swing.JLabel;
import javax.swing.JPanel;

public class WinPanel extends JPanel{
	JPanel back = new JPanel();
	JLabel message = new JLabel();
	JLabel image = new JLabel();

	WinPanel(Player player) {
		setSize(750, 750);
		setBackground(new Color(255, 207, 165));
		
		back.setLayout(null);
		back.setBounds(175, 100, 400, 550);
		back.setBackground(new Color(224, 140, 58));
		
		message.setBounds(90, 400, 300, 100);
		message.setText(player.name + " WIN!");
		message.setFont(new Font("HanS", Font.BOLD, 50));
		
		image.setBounds(136, 120, 128, 140);
		image.setBackground(new Color(255, 244, 164));
		image.setOpaque(true);
		image.setIcon(player.image);
		
		back.add(message);
		back.add(image);
		
		add(back);
		
	}
	
}