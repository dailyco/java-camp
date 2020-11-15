package database;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class MyInfo extends JPanel{
JPanel buttonPanel = new JPanel();
	
	JLabel title = new JLabel("My Information");
	JLabel nickname = new JLabel("Nickname");
	JLabel id = new JLabel("ID");
	JLabel password = new JLabel("Password");
	
	JLabel nnText = new JLabel();
	JLabel pwText = new JLabel();
	JLabel idText = new JLabel();
	
	JButton edit = new JButton("수정");
	JButton back = new JButton("뒤로가기");
	
	MyInfo() {
		setBackground(Color.pink);
		setLayout(null);
		
		title.setBounds(47, 75, 350, 50);
		title.setFont(new Font("Serif", Font.BOLD, 50));
		title.setForeground(new Color(204, 000, 153));
		
		nickname.setBounds(75, 160, 310, 50);
		nickname.setFont(new Font(null, Font.BOLD, 20));
		
		id.setBounds(75, 240, 310, 50);
		id.setFont(new Font(null, Font.BOLD, 20));
		
		password.setBounds(75, 320, 310, 50);
		password.setFont(new Font(null, Font.BOLD, 20));
		
		nnText.setBounds(80, 205, 280, 30);
		nnText.setBackground(Color.white);
		nnText.setOpaque(true);
		nnText.setFont(new Font(null, Font.PLAIN, 23));
		
		idText.setBounds(80, 285, 280, 30);
		idText.setBackground(Color.white);
		idText.setOpaque(true);
		idText.setFont(new Font(null, Font.PLAIN, 23));
		
		pwText.setBounds(80, 365, 280, 30);
		pwText.setBackground(Color.white);
		pwText.setOpaque(true);
		pwText.setFont(new Font(null, Font.PLAIN, 23));
		
		buttonPanel.setLayout(new GridLayout(1, 2));
		buttonPanel.setBackground(Color.pink);
		buttonPanel.setBounds(105, 460, 230, 40);
		buttonPanel.add(edit);
		buttonPanel.add(back);
		
		add(title);
		add(id);
		add(password);
		add(nickname);
		add(idText);
		add(pwText);
		add(nnText);
		add(buttonPanel);
	}
	
	public void set(User user) {
		nnText.setText(user.nickname);
		idText.setText(user.id);
		pwText.setText(user.password);
	}
}
