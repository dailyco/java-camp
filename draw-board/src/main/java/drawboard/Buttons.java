package drawboard;

import java.awt.Color;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;

public class Buttons implements MouseListener {
	JButton[] toolbuttons = new JButton[9];
	JButton[] drawbuttons = new JButton[4];
	static CanvasGroup canvas = new CanvasGroup();
	Stroke stroke = new Stroke();
	static boolean clear;
	static boolean erase;
	static boolean text;
	static boolean[] draw = new boolean[4];
	
	int canvasNum = 0;

	Buttons() {

		for(int i = 0; i < toolbuttons.length; i++) {
			toolbuttons[i] = new JButton(new ImageIcon("icon/"+i+".png"));
			toolbuttons[i].setBorderPainted(false);
			toolbuttons[i].addMouseListener(this);
		}

		for(int i = 0; i < drawbuttons.length; i++) {
			drawbuttons[i] = new JButton(new ImageIcon("icon/3-"+i+".png"));
			drawbuttons[i].setBorderPainted(false);
			drawbuttons[i].addMouseListener(this);
		}
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		JButton button = (JButton)e.getSource();

		if (button == toolbuttons[0])
			undo();

		if (button == toolbuttons[1])
			redo();

		if (button == toolbuttons[2]) {
			setInit();
			addCanvas();
		}
		
		if (button == toolbuttons[3]) {
			ColorFrame colorFrame = new ColorFrame();
		}

		if (button == toolbuttons[5])
			setEraser();

		if (button == toolbuttons[6]) {
			setText();
			TextBoard textBoard = new TextBoard();
		}

		if (button == toolbuttons[7]) {
			setInit();
			clear = true;
			canvas.getSelectedComponent().repaint();
		}

		if (button == toolbuttons[8]) {
			setInit();
			CheckFrame check = new CheckFrame();
		}

		if (button == drawbuttons[0])
			setDraw(button);

		if (button == drawbuttons[1])
			setDraw(button);

		if (button == drawbuttons[2])
			setDraw(button);

		if (button == drawbuttons[3])
			setDraw(button);
	}

	@Override
	public void mousePressed(MouseEvent e) {
		
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

	private void setEraser() {
		erase = true;
		text = false;

		toolbuttons[5].setBorderPainted(true);
		toolbuttons[6].setBorderPainted(false);

		for(int i = 0; i < drawbuttons.length; i++) {
			draw[i] = false;
			drawbuttons[i].setBorderPainted(false);
		}

	}

	private void setText() {
		erase = false;
		text = true;

		toolbuttons[5].setBorderPainted(false);
		toolbuttons[6].setBorderPainted(true);

		for(int i = 0; i < drawbuttons.length; i++) {
			draw[i] = false;
			drawbuttons[i].setBorderPainted(false);
		}
	}

	private void setDraw(JButton button) {
		erase = false;
		text = false;

		toolbuttons[5].setBorderPainted(false);
		toolbuttons[6].setBorderPainted(false);

		for(int i = 0; i < drawbuttons.length; i++) {
			if (button == drawbuttons[i]) {
				draw[i] = true;
				drawbuttons[i].setBorderPainted(true);
			}
			else {
				draw[i] = false;
				drawbuttons[i].setBorderPainted(false);
			}
		}

	}

	private void setInit() {
		erase = false;
		text = false;

		toolbuttons[5].setBorderPainted(false);
		toolbuttons[6].setBorderPainted(false);
		
		for(int i = 0; i < drawbuttons.length; i++) {
			draw[i] = false;
			drawbuttons[i].setBorderPainted(false);
		}

	}

	private void addCanvas() {
		Canvas newCanvas = new Canvas();

		newCanvas.setBackground(Color.white);
		newCanvas.setOpaque(true);

		canvas.add("canvas"+canvasNum, newCanvas);
		canvasNum++;
	}

	private void undo() {
		ColorFrame.colorChange = true;
		
		if(Memory.memory.isEmpty() == true)
			;
		else {
			if(Memory.memory.peek() == null) {
				Sketch.redoStart.push(Sketch.start.pop());
				Sketch.redoEnd.push(Sketch.end.pop());
			}
			
			Memory.redoMemory.push(Memory.memory.pop());
			Memory.redoColorMemory.push(Memory.colorMemory.pop());
			Memory.redoThicknessMemory.push(Memory.thicknessMemory.pop());
			
			canvas.getSelectedComponent().repaint();
		}
	}
	
	private void redo() {
		ColorFrame.colorChange = true;
		
		if(Memory.redoMemory.isEmpty() == true)
			;
		else {
			if(Memory.redoMemory.peek() == null) {
				Sketch.start.push(Sketch.redoStart.pop());
				Sketch.end.push(Sketch.redoEnd.pop());
			}
			
			Memory.memory.push(Memory.redoMemory.pop());
			Memory.colorMemory.push(Memory.redoColorMemory.pop());
			Memory.thicknessMemory.push(Memory.redoThicknessMemory.pop());
			
			canvas.getSelectedComponent().repaint();
		}
	}
	
}