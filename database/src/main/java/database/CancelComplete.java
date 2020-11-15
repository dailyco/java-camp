package database;

import java.awt.Color;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class CancelComplete extends JFrame{
	JPanel background = new JPanel();

	JLabel message = new JLabel("탈퇴되었습니다! 처음 화면으로 되돌아갑니다.");

	JButton ok = new JButton("확인");

	CancelComplete() {
		setTitle("Cancel Success!");
		setSize(280, 100);
		setLocation(525, 345);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

		background.setLayout(null);
		background.setBackground(Color.white);

		message.setBounds(27, 13, 250, 20);

		ok.setBounds(100, 40, 70, 30);

		background.add(message);
		background.add(ok);

		add(background);

		setVisible(false);
	}
}
