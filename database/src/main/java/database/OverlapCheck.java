package database;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class OverlapCheck extends JFrame implements ActionListener{
	JPanel background = new JPanel();

	JLabel message = new JLabel("ID 중복확인을 해주세요!");

	JButton ok = new JButton("확인");

	OverlapCheck() {
		setTitle("Error!");
		setSize(200, 100);
		setLocation(570, 300);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

		background.setLayout(null);
		background.setBackground(Color.white);

		message.setBounds(40, 10, 300, 20);

		ok.setBounds(65, 40, 70, 30);
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
