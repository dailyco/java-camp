package network.connect6;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class Notice extends JFrame implements ActionListener{
	JPanel background = new JPanel();

	JLabel message = new JLabel("이름을 다시 입력해주세요!");

	JButton ok = new JButton("확인");

	Notice() {
		setTitle("Error!");
		setSize(230, 100);
		setLocation(600, 320);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

		background.setLayout(null);
		background.setBackground(Color.white);

		message.setBounds(55, 10, 150, 20);

		ok.addActionListener(this);
		ok.setBounds(80, 40, 70, 30);

		background.add(message);
		background.add(ok);

		add(background);

		setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if(e.getSource() == ok)
			this.dispose();
	}
}
