package database;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

public class Join extends JPanel{
	JPanel buttonPanel = new JPanel();
	
	JLabel title = new JLabel("JOIN");
	JLabel id = new JLabel("ID");
	JLabel password = new JLabel("Password");
	JLabel nickname = new JLabel("Nickname");
	
	JTextField idText = new JTextField("");
	JPasswordField pwText = new JPasswordField("");
	JTextField nnText = new JTextField("");
	
	JButton checkOverlap = new JButton("중복확인");
	JButton ok = new JButton("확인");
	JButton no = new JButton("취소");
	
	boolean check;
	
	Join() {
		setBackground(Color.pink);
		setLayout(null);
		
		title.setBounds(155, 70, 300, 50);
		title.setFont(new Font("Serif", Font.BOLD, 50));
		title.setForeground(new Color(204, 000, 153));
		
		id.setBounds(75, 150, 310, 50);
		id.setFont(new Font(null, Font.BOLD, 20));
		
		password.setBounds(75, 230, 310, 50);
		password.setFont(new Font(null, Font.BOLD, 20));
		
		nickname.setBounds(75, 340, 310, 50);
		nickname.setFont(new Font(null, Font.BOLD, 20));
		
		idText.setBounds(80, 195, 280, 45);
		idText.setFont(new Font(null, Font.PLAIN, 20));
		idText.setBackground(Color.white);
		
		pwText.setBounds(80, 275, 280, 45);
		pwText.setFont(new Font(null, Font.PLAIN, 20));
		pwText.setBackground(Color.white);
		
		nnText.setBounds(80, 385, 280, 45);
		nnText.setFont(new Font(null, Font.PLAIN, 20));
		nnText.setBackground(Color.white);
		
		checkOverlap.setBounds(280, 155, 80, 35);
		
		buttonPanel.setLayout(new GridLayout(1, 2));
		buttonPanel.setBackground(Color.pink);
		buttonPanel.setBounds(105, 460, 230, 40);
		buttonPanel.add(ok);
		buttonPanel.add(no);
		
		add(title);
		add(checkOverlap);
		add(id);
		add(password);
		add(nickname);
		add(idText);
		add(pwText);
		add(nnText);
		add(buttonPanel);
	}
	
}
