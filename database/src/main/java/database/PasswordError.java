package database;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class PasswordError extends JFrame implements ActionListener{
	JPanel background = new JPanel();

	JLabel message = new JLabel("비밀번호는 4자리 이상 입력해주세요!");

	JButton ok = new JButton("확인");

	PasswordError() {
		setTitle("Error!");
		setSize(250, 100);
		setLocation(570, 300);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

		background.setLayout(null);
		background.setBackground(Color.white);

		message.setBounds(33, 10, 300, 20);

		ok.setBounds(85, 40, 70, 30);
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
