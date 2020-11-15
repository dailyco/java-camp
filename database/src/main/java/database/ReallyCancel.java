package database;

import java.awt.Color;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class ReallyCancel extends JFrame{
	JPanel background = new JPanel();
	JPanel buttonPanel = new JPanel();
	
	JLabel message = new JLabel("'예' 버튼을누르면 정말로 탈퇴됩니다. 탈퇴하시겠습니까?");
	
	JButton yes = new JButton("예");
	JButton no = new JButton("아니오");
	
	ReallyCancel() {
		setTitle("Notice");
		setSize(305, 110);
		setLocation(515, 345);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		
		background.setLayout(null);
		background.setBackground(Color.white);
		
		message.setBounds(12, 18, 300, 20);
		
		buttonPanel.setLayout(new GridLayout(1, 2));
		buttonPanel.setBounds(75, 45, 150, 30);
		buttonPanel.setBackground(Color.white);
		
		buttonPanel.add(yes);
		buttonPanel.add(no);

		background.add(message);
		background.add(buttonPanel);
		
		add(background);
		
		setVisible(false);
	}
	
}
