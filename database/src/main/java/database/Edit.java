package database;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

public class Edit extends JPanel implements ActionListener{
	JPanel buttonPanel = new JPanel();

	JLabel title = new JLabel("Edit");

	JTextField idText = new JTextField("");
	JPasswordField pwText = new JPasswordField("");
	JTextField nnText = new JTextField("");

	JButton id = new JButton("ID");
	JButton password = new JButton("Password");
	JButton nickname = new JButton("Nickname");
	JButton checkOverlap = new JButton("중복확인");
	
	JButton ok = new JButton("확인");
	JButton back = new JButton("뒤로가기");

	boolean idCheck;
	boolean pwCheck;
	boolean nnCheck;
	boolean check;

	Edit() {
		setBackground(Color.pink);
		setLayout(null);

		title.setBounds(165, 70, 300, 50);
		title.setFont(new Font("Serif", Font.BOLD, 50));
		title.setForeground(new Color(204, 000, 153));

		id.setBounds(75, 150, 75, 40);
		id.setBackground(Color.black);
		id.setFont(new Font(null, Font.BOLD, 20));
		id.addActionListener(this);

		password.setBounds(75, 245, 170, 40);
		password.setBackground(Color.black);
		password.setFont(new Font(null, Font.BOLD, 20));
		password.addActionListener(this);

		nickname.setBounds(75, 340, 170, 40);
		nickname.setBackground(Color.black);
		nickname.setFont(new Font(null, Font.BOLD, 20));
		nickname.addActionListener(this);

		idText.setBounds(80, 195, 280, 45);
		idText.setFont(new Font(null, Font.PLAIN, 20));
		idText.setBackground(Color.white);

		pwText.setBounds(80, 290, 280, 45);
		pwText.setFont(new Font(null, Font.PLAIN, 20));
		pwText.setBackground(Color.white);

		nnText.setBounds(80, 385, 280, 45);
		nnText.setFont(new Font(null, Font.PLAIN, 20));
		nnText.setBackground(Color.white);

		checkOverlap.setBounds(275, 155, 80, 35);

		buttonPanel.setLayout(new GridLayout(1, 2));
		buttonPanel.setBackground(Color.pink);
		buttonPanel.setBounds(105, 460, 230, 40);
		buttonPanel.add(ok);
		buttonPanel.add(back);

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

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		JButton button = (JButton)e.getSource();
		
		if(button == id) {
			idCheck = (!idCheck);
			
			if(idCheck == true)
				id.setOpaque(true);
			else
				id.setOpaque(false);
			
		}
		
		if(button == password) {
			pwCheck = (!pwCheck);
			
			if(pwCheck == true)
				password.setOpaque(true);
			else
				password.setOpaque(false);
		}
		
		if(button == nickname) {
			nnCheck = (!nnCheck);
			
			if(nnCheck == true)
				nickname.setOpaque(true);
			else
				nickname.setOpaque(false);
		}
		
	}
}
