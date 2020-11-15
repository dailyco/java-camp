package database;

import java.awt.Color;
import java.awt.Font;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class Menu extends JPanel{
	JButton image = new JButton(new ImageIcon("image/female.png"));
	
	JLabel nickname = new JLabel();
	JLabel welcome = new JLabel("님 환영합니다!");

	JButton info = new JButton("내 정보");
	JButton edit = new JButton("수정");
	JButton search = new JButton("조회");
	JButton cancel = new JButton("탈퇴");
	JButton logout = new JButton("로그아웃");

	Menu() {
		setBackground(Color.pink);
		setLayout(null);
		
		welcome.setBounds(140, 15, 400, 20);
		welcome.setFont(new Font("돋움", Font.PLAIN, 20));
		
		image.setBounds(85, 100, 256, 256);
		image.setOpaque(false);
		image.setBorderPainted(false);
		
		info.setBounds(65, 398, 140, 50);
		search.setBounds(235, 398, 140, 50);
		edit.setBounds(65, 460, 140, 50);
		cancel.setBounds(235, 460, 140, 50);
		logout.setBounds(150, 530, 140, 50);
		
		info.setFont(new Font(null, Font.BOLD, 15));
		search.setFont(new Font(null, Font.BOLD, 15));
		edit.setFont(new Font(null, Font.BOLD, 15));
		cancel.setFont(new Font(null, Font.BOLD, 15));
		logout.setFont(new Font(null, Font.BOLD, 15));

		add(welcome);
		add(image);
		add(info);
		add(search);
		add(edit);
		add(cancel);
		add(logout);
	}
	
	public void setLabel(String nickname) {
		
		this.nickname.setBounds(17, 17, 200, 20);
		this.nickname.setFont(new Font("Serif", Font.BOLD|Font.ITALIC, 25));
		this.nickname.setForeground(Color.white);
		this.nickname.setText(nickname);
		add(this.nickname);
		
	}
	
	public void setImage(String gender) {
		
		if(gender.equals("F"))
			image.setIcon(new ImageIcon("image/female.png"));
		else
			image.setIcon(new ImageIcon("image/male.png"));
		
	}
}
