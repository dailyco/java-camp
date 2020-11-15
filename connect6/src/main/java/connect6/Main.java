package connect6;

import java.awt.Color;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;

public class Main extends JFrame implements ActionListener{
	Screen1 screen1 = new Screen1();
	Screen2 screen2 = new Screen2();
	Screen3 screen3 = new Screen3();

	Main() {
		setTitle("육목");
		setSize(1440, 800);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		screen1.start.addActionListener(this);
		screen2.start.addActionListener(this);
		screen3.restart.addActionListener(this);

		add(screen1);

		setVisible(true);
	}

	public static void main(String args[]){
		new Main();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		JButton button = (JButton) e.getSource();

		if(button == screen1.start) {
			remove(screen1);
			add(screen2);
			revalidate();
			repaint();
		}

		if(button == screen2.start)
			settingPlayer();

		if(button == screen3.restart) {
			remove(screen3);
			initScreen3();
			add(screen3);
			revalidate();
			repaint();
		}
			
	}

	void settingPlayer() {

		if(screen2.name1.getText().equals("") || screen2.name2.getText().equals("") || screen2.name1.getText().equals(screen2.name2.getText()))
			new Notice();
		else {
			screen2.p1.name = screen2.name1.getText();
			screen2.p2.name = screen2.name2.getText();

			switch(screen2.p1image) { 
			case 1: 
				screen2.p1.image = new ImageIcon("image/small boy.png");
				break;
			case 2:
				screen2.p1.image = new ImageIcon("image/small girl.png");
				break;
			case 3: 
				screen2.p1.image = new ImageIcon("image/small man.png");
				break;
			case 4:
				screen2.p1.image = new ImageIcon("image/small woman.png");
				break;
			}

			switch(screen2.p2image) {
			case 1: 
				screen2.p2.image =new ImageIcon("image/small boy.png");
				break;
			case 2:
				screen2.p2.image = new ImageIcon("image/small girl.png");
				break;
			case 3: 
				screen2.p2.image = new ImageIcon("image/small man.png");
				break;
			case 4:
				screen2.p2.image = new ImageIcon("image/small woman.png");
				break;
			}
			
			screen2.name1.setText("");
			screen2.name2.setText("");

			settingScreen3();
			remove(screen2);
			add(screen3);
			revalidate();
			repaint();
		}
	}

	void settingScreen3() {
		Player1Panel panel1 = new Player1Panel(screen2.p1);
		Player2Panel panel2 = new Player2Panel(screen2.p2);
		screen3.player1 = panel1;
		screen3.player2 = panel2;
		screen3.clip = panel2.sound.clip;
		screen3.timer1 = panel1.timer;
		screen3.timer2 = panel2.timer;
		screen3.board.p1 = screen2.p1;
		screen3.board.p2 = screen2.p2;
	}
	
	void initScreen3() {
		screen3.board.memory.clear();
		screen3.board.colorMemory.clear();
		
		for(int i = 0; i < 19; i++)
			for (int j = 0; j < 19; j++)
				screen3.board.board[i][j] = 0;

		screen3.board.remove(screen3.board.winPanel);
		screen3.board.board[9][9] = 1;
		screen3.board.colorMemory.add(Color.black);
		screen3.board.memory.add(new Point(357, 357));
		screen3.board.color = Color.white;
		screen3.board.p1Count = 0;
		screen3.board.p2Count = 0;
		
		screen3.add(screen3.start);
		screen3.remove(screen3.board);
	}

}