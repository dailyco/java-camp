package network.connect6;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.io.PrintWriter;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

public class Board extends JPanel implements MouseListener, MouseMotionListener{
	static PrintWriter writer;
	WinPanel winPanel;

	Point p = new Point(0, 0);
	Color color;
	
	String name;
	ImageIcon image;

	int column, row;
	static int player, count;

	static boolean myturn;
	boolean check;

	void initSet() {
		count = 0;
		
		if(color == Color.black)
			myturn = false;
		else
			myturn = true;
	}
	
	void turnSet() {
		count++;

		if(count == 2) {
			myturn = false;
			count = 0;
			writer.println("[CHANGE]");
		}
	}

	boolean checkFinish() {
		int count = 1;

		int i = column + 1;
		while (i < 19 && MainClient.board[i][row] == player) {
			count++;
			i++;
		}

		i = column - 1;
		while (i > 0 && MainClient.board[i][row] == player) {
			count++;
			i--;
		}

		if(count >= 6)
			return true;
		else
			count = 1;

		i = row + 1;
		while (i < 19 && MainClient.board[column][i] == player) {
			count++;
			i++;
		}

		i = row - 1;
		while (i > 0 && MainClient.board[column][i] == player) {
			count++;
			i--;
		}

		if(count >= 6)
			return true;
		else
			count = 1;

		i = 1;
		while (column + i < 19 && row + i < 19 && MainClient.board[column + i][row + i] == player) {
			count++;
			i++;
		}

		i = 1;
		while (column - i > 0 && row - i > 0 && MainClient.board[column - i][row - i] == player) {
			count++;
			i++;
		}

		if(count >= 6)
			return true;
		else
			count = 1;

		i = 1;
		while (column + i < 19 && row - i > 0 && MainClient.board[column + i][row - i] == player) {
			count++;
			i++;
		}

		i = 1;
		while (column - i > 0 && row + i < 19 && MainClient.board[column - i][row + i] == player) {
			count++;
			i++;
		}

		if(count >= 6)
			return true;
		else
			return false;
	}

	Board() {
		setSize(750, 750);
		setBounds(345, 12, 750, 750);
		setBackground(new Color(255, 207, 165));

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

		for (int i = 0; i < MainClient.memory.size(); i++) {
			g.setColor(MainClient.colorMemory.get(i));
			g.fillOval(MainClient.memory.get(i).x, MainClient.memory.get(i).y, 36, 36);
		}

		if(myturn) {
			if(14 <= p.x % 39 && p.x % 39 <= 34 && 14 <= p.y % 39 && p.y % 39 <= 34) {
				column = p.x / 39;
				row = p.y / 39;

				if(MainClient.board[column][row] != 0) {
					check = false;
				}
				else {
					g.setColor(color);
					g.fillOval(39*column + 6, 39*row + 6, 36, 36);

					if(check) {
						MainClient.memory.add(new Point(39*column + 6, 39*row +6));
						MainClient.colorMemory.add(color);
						MainClient.board[column][row] = player;

						writer.println("[STONE]" + column + " " + row);

						if(checkFinish()) {
							Player1Panel.restart.setEnabled(true);
							add(winPanel = new WinPanel(name, image));
							writer.println("[FINISH]");
							writer.println("[TIMER]");
						}

							turnSet();

						check = false;
					}
				}
			}
		}
	}

	@Override
	public void mouseDragged(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseMoved(MouseEvent e) {
		// TODO Auto-generated method stub
		if(!myturn) return;

		p = e.getPoint();
		repaint();
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		if(!myturn) return;

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
}
