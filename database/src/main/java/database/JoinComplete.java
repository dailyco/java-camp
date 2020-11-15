package database;

import java.awt.Color;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class JoinComplete extends JFrame {
	JPanel background = new JPanel();
	JPanel buttonPanel = new JPanel();
	
	JLabel message = new JLabel("회원가입이 완료되었습니다! 로그인 하시겠습니까?");
	
	JButton yes = new JButton("예");
	JButton no = new JButton("아니오");
	
	JoinComplete() {
		setTitle("Completed!");
		setSize(300, 110);
		setLocation(515, 345);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		
		background.setLayout(null);
		background.setBackground(Color.white);
		
		message.setBounds(25, 15, 300, 20);
		
		buttonPanel.setLayout(new GridLayout(1, 2));
		buttonPanel.setBounds(75, 50, 150, 30);
		buttonPanel.setBackground(Color.white);
		
		buttonPanel.add(yes);
		buttonPanel.add(no);

		background.add(message);
		background.add(buttonPanel);
		
		add(background);
		
		setVisible(false);
	}
	
}
