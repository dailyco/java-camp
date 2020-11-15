package drawboard;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Shape;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Line2D;
import java.awt.geom.Rectangle2D;

import javax.swing.JPanel;

public class Canvas extends JPanel {
	Memory memory = new Memory();
	Sketch sketch = new Sketch();

	Line2D.Double line;
	Rectangle2D.Double rectangle;
	Ellipse2D.Double elipse;

	Point start, end;

	public Canvas() {
		MyMouseListener listener = new MyMouseListener();

		addMouseListener(listener);
		addMouseMotionListener(listener);
	}

	class MyMouseListener extends MouseAdapter {

		public void mousePressed(MouseEvent e) {

			if(Buttons.erase == true || Buttons.draw[0] == true) {
				ColorFrame.colorChange = false;
				start = e.getPoint();
				sketch.sketch.add(start);
				sketch.next = sketch.sketch.size()-1;
				sketch.start.add(sketch.sketch.size()-1);
			}

			if(Buttons.draw[1] == true) {
				ColorFrame.colorChange = false;
				start = e.getPoint();
			}

			if(Buttons.draw[2] == true) {
				ColorFrame.colorChange = false;
				start = e.getPoint();
			}

			if(Buttons.draw[3] == true) {
				ColorFrame.colorChange = false;
				start = e.getPoint();
			}

		}

		public void mouseDragged(MouseEvent e) {

			if(Buttons.erase == true || Buttons.draw[0]  == true) {
				end = e.getPoint();
				sketch.sketch.add(end);
				repaint();
			}

			if (Buttons.draw[1] == true) {
				end = e.getPoint();
				line = new Line2D.Double(start.x, start.y, end.x, end.y);
				repaint();
			}

			if(Buttons.draw[2] == true) {
				end = e.getPoint();
				rectangle = new Rectangle2D.Double(Math.min(start.x, end.x), Math.min(start.y, end.y), Math.abs(start.x - end.x), Math.abs(start.y - end.y));
				repaint();
			}

			if(Buttons.draw[3] == true) {
				end = e.getPoint();
				elipse = new Ellipse2D.Double(Math.min(start.x, end.x), Math.min(start.y, end.y), Math.abs(start.x - end.x), Math.abs(start.y - end.y));
				repaint();
			}
		}

		public void mouseReleased(MouseEvent e) {

			if (Buttons.erase == true) {
				memory.memory.push(null);
				memory.colorMemory.push(Color.white);
				memory.thicknessMemory.push(Stroke.thick);
				sketch.end.add(sketch.sketch.size()-1);
			}

			if (Buttons.draw[0] == true) {
				memory.memory.push(null);
				memory.colorMemory.push(ColorFrame.color);
				memory.thicknessMemory.push(Stroke.thick);
				sketch.end.add(sketch.sketch.size()-1);
			}

			if (Buttons.draw[1] == true) {
				memory.memory.push(line);
				memory.colorMemory.push(ColorFrame.color);
				memory.thicknessMemory.push(Stroke.thick);
			}

			if(Buttons.draw[2] == true) {
				memory.memory.push(rectangle);
				memory.colorMemory.push(ColorFrame.color);
				memory.thicknessMemory.push(Stroke.thick);
			}

			if(Buttons.draw[3] == true) {
				memory.memory.push(elipse);
				memory.colorMemory.push(ColorFrame.color);
				memory.thicknessMemory.push(Stroke.thick);
			}
		}

	}

	public void paintComponent(Graphics g) {
		super.paintComponent(g);

		Graphics2D g2 = (Graphics2D)g;
		
		if(Buttons.clear == true) {
			Clear clearShape = new Clear();
			
			g2.setColor(Color.white);
			g2.setStroke(new BasicStroke(0));
			g2.draw(clearShape);
			memory.memory.push(clearShape);
			memory.colorMemory.push(Color.white);
			memory.thicknessMemory.push(0);
			Buttons.clear = false;
		}

		else {
			int sketchNum = 0;

			for(int i = 0; i < memory.memory.size(); i++) {
				g2.setColor(memory.colorMemory.get(i));
				g2.setStroke(new BasicStroke(memory.thicknessMemory.get(i)));

				if(memory.memory.get(i)  == null) {
					for (int j = sketch.start.get(sketchNum); j < sketch.end.get(sketchNum)-1; j++)
						g2.drawLine(sketch.sketch.get(j).x, sketch.sketch.get(j).y, sketch.sketch.get(j+1).x, sketch.sketch.get(j+1).y);

					sketchNum++;
				}
				else if(memory.memory.get(i).getClass().getSimpleName().equals("Clear"))
					g2.fill((Shape) memory.memory.get(i));
				else
					g2.draw((Shape)memory.memory.get(i));
			}

			g2.setColor(ColorFrame.color);
			g2.setStroke(new BasicStroke(Stroke.thick));

			if(start == null)
				return;

			if(ColorFrame.colorChange == true)
				;
			else {
				
				//지우개
				if(Buttons.erase == true) {
					g2.setColor(Color.white);
					for (int i = sketch.next; i < sketch.sketch.size() -1; i++)
						g2.drawLine(sketch.sketch.get(i).x, sketch.sketch.get(i).y, sketch.sketch.get(i+1).x, sketch.sketch.get(i+1).y);
				}

				//스케치
				if(Buttons.draw[0] == true) {
					for (int i = sketch.next; i < sketch.sketch.size() -1; i++)
						g2.drawLine(sketch.sketch.get(i).x, sketch.sketch.get(i).y, sketch.sketch.get(i+1).x, sketch.sketch.get(i+1).y);
				}

				//라인
				if(Buttons.draw[1] == true)
					g2.draw(line);

				//사각형
				if (Buttons.draw[2] == true)
					g2.draw(rectangle);

				//타원
				if (Buttons.draw[3] == true)
					g2.draw(elipse);
			}

		}
	}

}
