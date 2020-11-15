package connect6;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.lang.reflect.Array;
import java.util.ArrayList;

import javax.swing.JLabel;
import javax.swing.JPanel;

import javafx.scene.shape.Ellipse;

public class Board extends JPanel implements MouseListener, MouseMotionListener{
	ArrayList<Point> memory = new ArrayList<Point>();
	ArrayList<Color> colorMemory = new ArrayList<Color>();
	
	WinPanel winPanel;
	
	Point p = new Point(0, 0);
	static Color color = Color.white;
	
	Player p1, p2;
	
	int[][] board = new int[19][19];
	int row, column;
	static int p1Count, p2Count;
	int win;
	
	boolean check;
	
	Board() {
		setSize(750, 750);
		setBackground(new Color(255, 207, 165));
		
		board[9][9] = 1;
		colorMemory.add(Color.black);
		memory.add(new Point(357, 357));
		
		this.addMouseListener(this);
		this.addMouseMotionListener(this);
	}
	
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		
		for(int i = 24; i < this.getWidth(); i+= 39) {
			g.drawLine(i, 24, i, 726);
			g.drawLine(24, i, 726,i);
		}
		
		for(int i = 138; i < this.getWidth(); i += 234) {
			g.fillOval(i, 138, 6, 6);
			g.fillOval(i, 372, 6, 6);
			g.fillOval(i, 606, 6, 6);
		}
		
		for (int i = 0; i < memory.size(); i++) {
				g.setColor(colorMemory.get(i));
				g.fillOval(memory.get(i).x, memory.get(i).y, 36, 36);
			}
		
		if(14 <= p.x % 39 && p.x % 39 <= 34 && 14 <= p.y % 39 && p.y % 39 <= 34) {
			column = p.x / 39;
			row = p.y / 39;
			
			if(board[column][row] != 0) {
				check = false;
			}
			else {
				g.setColor(color);
				g.fillOval(39*column + 6, 39*row + 6, 36, 36);
				
				if(check) {
					memory.add(new Point(39*column + 6, 39*row +6));
					colorMemory.add(color);
					colorSet();
					if(checkFinish()) {
						if(win == 1) {
							add(winPanel= new WinPanel(p1));
							Screen3.timer1.stop();
							Screen3.timer2.stop();
							Player1Panel.second = 30;
							Player2Panel.second = 30;
						}
						else {
							add(winPanel= new WinPanel(p2));
							Screen3.timer1.stop();
							Screen3.timer2.stop();
							Player1Panel.second = 30;
							Player2Panel.second = 30;
						}
					}
					check = false;
				}
			}
		}
		
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		p = e.getPoint();
		check = true;
		repaint();
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseDragged(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseMoved(MouseEvent e) {
		// TODO Auto-generated method stub
		p = e.getPoint();
		repaint();
	}
	
	void colorSet() {
		
		if(color == Color.white) {
			p2Count++;
			board[column][row] = 2;
		}
		else {
			p1Count++;
			board[column][row] = 1;
		}
		
		if(p2Count == 2) {
			color = Color.black;
			p2Count = 0;
			Screen3.player1.repaint();
			Screen3.player2.repaint();
		}
		
		if(p1Count == 2) {
			color = Color.white;
			p1Count = 0;
			Screen3.player1.repaint();
			Screen3.player2.repaint();
		}
	}

	boolean checkFinish() {
		win = board[column][row];
		int count = 1;
		
		int i = column + 1;
		while (i < 19 && board[i][row] == win) {
			count++;
			i++;
		}
		
		i = column - 1;
		while (i > 0 && board[i][row] == win) {
			count++;
			i--;
		}
		
		if(count >= 6)
			return true;
		else
			count = 1;
		
		i = row + 1;
		while (i < 19 && board[column][i] == win) {
			count++;
			i++;
		}
		
		i = row - 1;
		while (i > 0 && board[column][i] == win) {
			count++;
			i--;
		}
		
		if(count >= 6)
			return true;
		else
			count = 1;
		
		i = 1;
		while (column + i < 19 && row + i < 19 && board[column + i][row + i] == win) {
			count++;
			i++;
		}
		
		i = 1;
		while (column - i > 0 && row - i > 0 && board[column - i][row - i] == win) {
			count++;
			i++;
		}
		
		if(count >= 6)
			return true;
		else
			count = 1;
		
		i = 1;
		while (column + i < 19 && row - i > 0 && board[column + i][row - i] == win) {
			count++;
			i++;
		}
		
		i = 1;
		while (column - i > 0 && row + i < 19 && board[column - i][row + i] == win) {
			count++;
			i++;
		}
		
		if(count >= 6)
			return true;
		else
			return false;
	}
}