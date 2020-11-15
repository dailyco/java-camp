package database;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class InitDisplay extends JPanel{
	JPanel buttonPanel = new JPanel();
	
	JLabel title = new JLabel("DATABASE");
	JLabel subTitle = new JLabel("Information");
	
	JButton join = new JButton("가입하기");
	JButton login = new JButton("로그인");
	
	InitDisplay() {
		setBackground(Color.pink);
		setLayout(null);
		
		buttonPanel.setBackground(Color.pink);
		buttonPanel.setBounds(95, 400, 250, 50);
		buttonPanel.setLayout(new GridLayout(1, 2));
		buttonPanel.add(join);
		buttonPanel.add(login);
		
		title.setBounds(80, 160, 300, 50);
		title.setFont(new Font("Serif", Font.BOLD, 50));
		title.setForeground(new Color(204, 000, 153));
		
		subTitle.setBounds(125, 200, 200, 40);
		subTitle.setFont(new Font("Serif", Font.BOLD, 35));
		subTitle.setForeground(Color.white);
		
		add(title);
		add(subTitle);
		add(buttonPanel);
	}
	
}
