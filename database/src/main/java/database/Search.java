package database;

import java.awt.Color;
import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class Search extends JPanel{
	JLabel title = new JLabel("Search");
	
	JButton id = new JButton("ID");
	JButton password = new JButton("Password");
	JButton nickname = new JButton("Nickname");
	
	JButton back = new JButton("뒤로가기");
	
	Search() {
		setBackground(Color.pink);
		setLayout(null);
		
		title.setBounds(135, 70, 300, 50);
		title.setFont(new Font("Serif", Font.BOLD, 50));
		title.setForeground(new Color(204, 000, 153));
		
		id.setBounds(115, 180, 200, 50);
		id.setFont(new Font(null, Font.BOLD, 20));
		
		password.setBounds(115, 250, 200, 50);
		password.setFont(new Font(null, Font.BOLD, 20));
		
		nickname.setBounds(115, 320, 200, 50);
		nickname.setFont(new Font(null, Font.BOLD, 20));
		
		back.setBounds(160, 430, 100, 40);
		
		add(title);
		add(id);
		add(password);
		add(nickname);
		add(back);
	}
}
