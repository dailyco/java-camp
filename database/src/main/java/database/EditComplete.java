package database;

import java.awt.Color;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class EditComplete extends JFrame{
JPanel background = new JPanel();
	
	JLabel message = new JLabel("수정이 완료되었습니다!");
	
	JButton ok = new JButton("확인");
	
	EditComplete() {
		setTitle("Edit Success!");
		setSize(230, 100);
		setLocation(550, 320);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		
		background.setLayout(null);
		background.setBackground(Color.white);
		
		message.setBounds(55, 10, 150, 20);
		
		ok.setBounds(80, 40, 70, 30);

		background.add(message);
		background.add(ok);
		
		add(background);
		
		setVisible(false);
	}
}
