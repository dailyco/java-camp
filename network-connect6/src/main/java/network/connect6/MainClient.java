package network.connect6;

import java.awt.Color;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class MainClient extends JFrame implements Runnable, ActionListener{
	Socket socket;
	BufferedReader reader;
	PrintWriter writer;

	static ArrayList<Point> memory = new ArrayList<Point>();
	static ArrayList<Color> colorMemory = new ArrayList<Color>();
	static int[][] board = new int[19][19];

	String name, cName;
	ImageIcon image, cImage;
	Color color, cColor;
	int cPlayer;
	int roomNum;

	Screen1 screen1 = new Screen1();
	Screen2 screen2 = new Screen2();
	Screen3 screen3 = new Screen3();
	Screen4 screen4 = new Screen4();
	Screen5 screen5;

	void connect() { // 연결
		try {
			socket = new Socket("127.0.0.1", 7777);
			reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			writer = new PrintWriter(socket.getOutputStream(), true);
			new Thread(this).start();
			Board.writer = writer;
			Player1Panel.writer = writer;
			Player2Panel.writer = writer;
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	void setExit() {
		roomNum = 0;
		writer.println("[BACK]");
		
		remove(screen4);
		add(screen3);
		revalidate();
		repaint();
	}

	void goToWaitRoom() {

		if(screen2.name.getText().equals("")) {
			new Notice();
		}
		else {
			String name;
			ImageIcon image;

			name = screen2.name.getText();

			switch(screen2.image) { 
			case 1: 
				image = new ImageIcon("image/small boy.png");
				break;
			case 2:
				image = new ImageIcon("image/small girl.png");
				break;
			case 3: 
				image = new ImageIcon("image/small man.png");
				break;
			default:
				image = new ImageIcon("image/small woman.png");
			}

			this.name = name;
			this.image = image;

			writer.println("[NAME]" + name);
			writer.println("[IMAGE]" + image);

			remove(screen2);
			add(screen3);
			revalidate();
			repaint();
		}

	}

	void backToWaitRoom() {
		memory.clear();
		colorMemory.clear();
		
		for(int i = 0; i < 19; i++)
			for (int j = 0; j < 19; j++)
				board[i][j] = 0;

		screen5.board.remove(screen5.board.winPanel);
		board[9][9] = 1;
		colorMemory.add(Color.black);
		memory.add(new Point(357, 357));
		
		screen4.start.setEnabled(false);
		screen4.start.setBorderPainted(true);
		screen4.start.setOpaque(false);
		screen4.start.addMouseListener(screen4);
		screen4.ready.setText("대전상대가 없습니다. 조금만 기다리세요.");
		
		screen5.player2.sound.clip.stop();
		screen5.player2.sound.setIcon(screen5.player2.sound.mute);
		screen5.board.count = 0;
		writer.println("[TIMER]");
		
		remove(screen5);
		add(screen3);
		revalidate();
		repaint();
	}

	void setStart() {
		screen4.start.setBackground(Color.black);
		screen4.start.setOpaque(true);
		screen4.start.setBorderPainted(false);
		screen4.start.removeMouseListener(screen4);
		writer.println("[START]");
	}

	void setBoard() {
		screen5 = new Screen5();

		if(color == Color.black) {
			screen5.setPanel1(name, image);
			screen5.setPanel2(cName, cImage);
			screen5.player1.timer.start();
			screen5.player2.timer.start();
			screen5.board.name = name;
			screen5.board.image = image;
			screen5.board.color = this.color;
			screen5.board.player = 1;
			cColor = Color.white;
			cPlayer = 2;
		}
		else {
			screen5.setPanel1(cName, cImage);
			screen5.setPanel2(name, image);
			screen5.player1.timer.start();
			screen5.player2.timer.start();
			screen5.board.name = name;
			screen5.board.image = image;
			screen5.board.color = this.color;
			screen5.board.myturn = true;
			screen5.board.player = 2;
			cColor = Color.black;
			cPlayer = 1;
		}

		remove(screen4);
		add(screen5);
		revalidate();
		repaint();
	}

	void initScreen5() {
		memory.clear();
		colorMemory.clear();
		
		for(int i = 0; i < 19; i++)
			for (int j = 0; j < 19; j++)
				board[i][j] = 0;

		screen5.board.remove(screen5.board.winPanel);
		board[9][9] = 1;
		colorMemory.add(Color.black);
		memory.add(new Point(357, 357));
		
		screen5.player1.timer.start();
		screen5.player2.timer.start();
		
		screen5.board.initSet();
		screen5.board.repaint();
	}
	
	MainClient() {
		setTitle("육목");
		setSize(1440, 800);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		board[9][9] = 1;
		colorMemory.add(Color.black);
		memory.add(new Point(357, 357));

		screen1.start.addActionListener(this);
		screen2.goWait.addActionListener(this);
		screen3.room1.addActionListener(this);
		screen3.room2.addActionListener(this);
		screen3.room3.addActionListener(this);
		screen3.room4.addActionListener(this);
		screen3.room5.addActionListener(this);
		screen3.room6.addActionListener(this);
		screen4.start.addActionListener(this);
		screen4.exit.addActionListener(this);
		Player1Panel.restart.addActionListener(this);

		addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent we) {
				writer.println("[EXIT]");
				System.exit(0);
			}
		});

		add(screen1);

		setVisible(true);
	}

	public static void main(String[] args) {
		MainClient client = new MainClient();
		client.connect();
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

		if(button == screen2.goWait)
			goToWaitRoom();

		if(button == screen3.room1)
			writer.println("[ROOM]1");

		if(button == screen3.room2)
			writer.println("[ROOM]2");
		
		if(button == screen3.room3)
			writer.println("[ROOM]3");

		if(button == screen3.room4)
			writer.println("[ROOM]4");
		
		if(button == screen3.room5)
			writer.println("[ROOM]5");

		if(button == screen3.room6)
			writer.println("[ROOM]6");

		if(button == screen4.start)
			setStart();
		
		if(button == screen4.exit)
			setExit();
		
		if(button == Player1Panel.restart)
			writer.println("[RESTART]");
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		String message;

		try {
			while((message = reader.readLine()) != null) {

				if(message.startsWith("[ROOM]")) {
					screen4.enter.setText(message.substring(6) + "번 방에 입장하셨습니다.");
					roomNum = Integer.parseInt(message.substring(6));
					remove(this.screen3);
					add(this.screen4);
					revalidate();
					repaint();
				}

				if(message.startsWith("[READY]")) {
					screen4.ready.setText(message.substring(7) + "님과 대결을 시작합니다.");
					screen4.start.setEnabled(true);
				}

				if(message.startsWith("[Enter]")) {
					screen4.ready.setText(message.substring(7) + "님이 들어왔습니다. 대결을 시작합니다.");
					screen4.start.setEnabled(true);
				}

				if(message.startsWith("[FULL]"))
					screen3.message.setText("방이 가득찼습니다. 다른 방을 선택하거나 조금 기다려주세요.");

				if(message.startsWith("[CNAME]"))
					cName = message.substring(7);

				if(message.startsWith("[CIMAGE]"))
					cImage = new ImageIcon(message.substring(8));

				if(message.startsWith("[COLOR]")) {
					String color = message.substring(7);

					if(color.equals("BLACK"))
						this.color = Color.black;
					else
						this.color = Color.white;

					setBoard();
				}

				if(message.startsWith("[STONE]")) {
					String temp = message.substring(7);
					
					int column = Integer.parseInt(temp.substring(0,temp.indexOf(" ")));
					int row = Integer.parseInt(temp.substring(temp.indexOf(" ")+1));
					
					colorMemory.add(cColor);
					memory.add(new Point(39*column + 6, 39*row +6));
					board[column][row] = cPlayer;
					screen5.board.repaint();
				}
				
				if(message.startsWith("[CHANGE]")) {
					for(int i = 0; i < 200; i++)
						System.out.println(i);
					Board.myturn = true;
					screen5.board.repaint();
				}

				if(message.startsWith("[FINISH]")) {
					Player1Panel.restart.setEnabled(true);
					screen5.board.add(screen5.board.winPanel = new WinPanel(cName, cImage));
					screen5.board.repaint();
				}
				
				if(message.startsWith("[RESTART]")) {
					int answer = JOptionPane.showConfirmDialog(null, "상대방이 재시작을 요청했습니다. 재시작하시겠습니까?\n \t\t거절할시 대기실로 이동합니다.", "재시작 확인", JOptionPane.YES_NO_OPTION, JOptionPane.PLAIN_MESSAGE);
					if(answer == 0) {
						writer.println("[RESET]");
						initScreen5();
					}
					else if(answer == 1) {
						writer.println("[WAIT]");
						backToWaitRoom();
					}
				}
				
				if(message.startsWith("[RESET]"))
					initScreen5();
				
				if(message.startsWith("[GOBACK]")) {
					writer.println("GOBACK");
					backToWaitRoom();
				}
				
				if(message.startsWith("[TIMER]")) {
					screen5.player1.timer.stop();
					screen5.player2.timer.stop();
				}
				
				if(message.startsWith("[REPAINT]"))
					screen5.board.repaint();
				
				if(message.startsWith("[BACK]")) {
					screen4.ready.setText(message.substring(6) + "님이 나갔습니다.");
					screen4.start.setEnabled(false);
				}
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
