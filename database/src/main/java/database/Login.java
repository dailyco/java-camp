package database;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

public class Login extends JPanel{
	JPanel buttonPanel = new JPanel();
	
	JButton title = new JButton("Login");
	JLabel id = new JLabel("ID");
	JLabel password = new JLabel("Password");
	
	JTextField idText = new JTextField("");
	JPasswordField pwText = new JPasswordField("");
	
	JButton ok = new JButton("확인");
	JButton no = new JButton("취소");
	
	Login() {
		setBackground(Color.pink);
		setLayout(null);
		
		title.setBounds(100, 100, 250, 50);
		title.setFont(new Font("Serif", Font.BOLD, 50));
		title.setForeground(new Color(204, 000, 153));
		title.setBorderPainted(false);
		
		id.setBounds(75, 170, 310, 50);
		id.setFont(new Font(null, Font.BOLD, 20));
		
		password.setBounds(75, 270, 310, 50);
		password.setFont(new Font(null, Font.BOLD, 20));
		
		idText.setBounds(80, 215, 280, 45);
		idText.setFont(new Font(null, Font.PLAIN, 20));
		idText.setBackground(Color.white);
		
		pwText.setBounds(80, 315, 280, 45);
		pwText.setFont(new Font(null, Font.PLAIN, 20));
		pwText.setBackground(Color.white);
		
		buttonPanel.setLayout(new GridLayout(1, 2));
		buttonPanel.setBackground(Color.pink);
		buttonPanel.setBounds(105, 410, 230, 40);
		buttonPanel.add(ok);
		buttonPanel.add(no);
		
		add(title);
		add(id);
		add(password);
		add(idText);
		add(pwText);
		add(buttonPanel);
	}
	
}
