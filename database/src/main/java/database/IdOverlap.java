package database;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class IdOverlap extends JFrame implements ActionListener{
	JPanel background = new JPanel();
	
	JLabel message = new JLabel("ID가 중복됩니다! 다른 아이디를 설정해주세요.");
	
	JButton ok = new JButton("확인");
	
	IdOverlap() {
		setTitle("ID Succes");
		setSize(270, 100);
		setLocation(535, 300);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		
		background.setLayout(null);
		background.setBackground(Color.white);
		
		message.setBounds(20, 10, 300, 20);
		
		ok.setBounds(100, 40, 70, 30);
		ok.addActionListener(this);

		background.add(message);
		background.add(ok);
		
		add(background);
		
		setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		dispose();
	}
}
