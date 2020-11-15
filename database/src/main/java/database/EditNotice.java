package database;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class EditNotice extends JFrame implements ActionListener{
	JPanel background = new JPanel();

	JLabel message = new JLabel("수정을 원하는 정보의 버튼을 선택하고 수정해주세요!");

	JButton ok = new JButton("확인");

	EditNotice() {
		setTitle("Notice");
		setSize(300, 100);
		setLocation(520, 340);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

		background.setLayout(null);
		background.setBackground(Color.white);

		message.setBounds(18, 10, 300, 20);

		ok.setBounds(115, 40, 70, 30);
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
