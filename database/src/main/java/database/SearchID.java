package database;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class SearchID extends JFrame implements ActionListener{
	JPanel background = new JPanel();

	JLabel message = new JLabel();

	JButton ok = new JButton("확인");

	public SearchID(String id) {
		setTitle("ID");
		setSize(250, 100);
		setLocation(540, 300);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

		background.setLayout(null);
		background.setBackground(Color.white);

		message.setBounds(40, 10, 300, 20);
		message.setText("당신의 id는 '"+ id + "' 입니다!");

		ok.setBounds(90, 40, 70, 30);
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
