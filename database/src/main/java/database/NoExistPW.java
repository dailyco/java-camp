package database;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class NoExistPW extends JFrame implements ActionListener{
	JPanel background = new JPanel();

	JLabel message = new JLabel("Password가 잘못되었습니다!");

	JButton ok = new JButton("확인");

	NoExistPW() {
		setTitle("Error");
		setSize(205, 100);
		setLocation(585, 300);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

		background.setLayout(null);
		background.setBackground(Color.white);

		message.setBounds(25, 10, 250, 20);

		ok.setBounds(60, 40, 70, 30);
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
