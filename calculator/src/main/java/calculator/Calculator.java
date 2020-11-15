package calculator;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.*;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import java.util.Stack;

public class Calculator implements ActionListener, KeyListener {
	private JFrame frame = new JFrame("계산기");
	private JFrame notice = new JFrame("알림");
	private JFrame list = new JFrame("최근 계산 목록");
	private JPanel computed = new JPanel();
	private JPanel numPnl = new JPanel();
	private JPanel oprPnl = new JPanel();
	private JPanel cntPnl = new JPanel();
	private JPanel key = new JPanel();
	private JLabel message = new JLabel("키보드 모드가 실행됩니다!", JLabel.CENTER);
	private JLabel made = new JLabel(" © Opencoding ", JLabel.RIGHT);
	private JLabel output = new JLabel(" ", JLabel.RIGHT);
	private JButton[] button = new JButton[20];
	
	private Stack<String> stack = new Stack<String>();
	private double result;
	private boolean completed = false;
	private boolean keyMode = false;

	private void createFrame() {
		frame.setSize(330,330);
		frame.setLocation(300, 150);
		
		notice.setSize(200, 100);
		notice.setLocation(350, 220);
		notice.add(message);

		list.setSize(300, 250);
		list.setLocation(700, 150);
		list.add(computed);
		
		output.setHorizontalAlignment(JTextField.RIGHT);
		output.setBackground(Color.WHITE);
		output.setOpaque(true);
		output.setFont(new Font(null, Font.PLAIN, 40));
		
		made.setBackground(Color.WHITE);
		made.setOpaque(true);
	}
	
	private void setButton() {
		for(int i = 0; i < 10; i++)
			button[i] = new JButton(""+i);
		button[10] = new JButton(".");
		button[11] = new JButton("=");

		button[12] = new JButton("÷");
		button[13] = new JButton("×");
		button[14] = new JButton("-");
		button[15] = new JButton("+");

		button[16] = new JButton("C");
		button[17] = new JButton("♥");
		button[18] = new JButton("✮");
		button[19] = new JButton("←");

		for(int i = 10; i <= 15; i++)
			button[i].setForeground(Color.BLUE);
		button[19].setForeground(Color.BLUE);
		button[16].setForeground(Color.RED);
		button[17].setForeground(Color.MAGENTA);
		button[18].setForeground(Color.MAGENTA);

		for(int i = 0; i <= 15; i++)
			button[i].setPreferredSize(new Dimension(65, 40));
		for(int i = 16; i< button.length; i++)
			button[i].setPreferredSize(new Dimension(65, 40));
	}
	
	private void setPnl() {
		computed.setLayout(new GridLayout(0, 1));
		numPnl.setLayout(new GridLayout(4, 3, 10, 10));
		oprPnl.setLayout(new GridLayout(4, 1, 10, 10));
		cntPnl.setLayout(new GridLayout(1, 4, 10, 10));
		computed.setBackground(new Color(255, 221, 221));
		numPnl.setBackground(Color.PINK);
		oprPnl.setBackground(Color.PINK);
		cntPnl.setBackground(Color.PINK);

		for(int i = 1; i <= 10; i++)
			numPnl.add(button[i]);
		numPnl.add(button[0]);
		numPnl.add(button[11]);

		for(int i = 12; i <= 15; i++)
			oprPnl.add(button[i]);

		for(int i = 16; i < button.length; i++)
			cntPnl.add(button[i]);
		
		key.add(cntPnl);
		key.add(numPnl, "West");
		key.add(oprPnl, "East");
		key.setBackground(Color.PINK);
	}
	
	private void addFrame() {
		frame.add(output, "North");
		frame.add(key, "Center");
		frame.add(made, "South");
	}
	
	private void clear() {
		if(completed == true) {
			output.setText(" ");
			completed = false;
			stack.clear();
		}
	}
	
	private void calculateAddSbtr() {
		for(int i = 1; i < stack.size(); i += 2) {
			switch(stack.get(i)) {
			case "+":
				result = Double.parseDouble(stack.get(i-1)) + Double.parseDouble(stack.get(i+1));
				stack.add(i-1, String.valueOf(result));
				stack.remove(i+2);
				stack.remove(i+1);
				stack.remove(i);
				i -= 2;
				break;
			case "-":
				result = Double.parseDouble(stack.get(i-1)) - Double.parseDouble(stack.get(i+1));
				stack.add(i-1, String.valueOf(result));
				stack.remove(i+2);
				stack.remove(i+1);
				stack.remove(i);
				i -= 2;
				break;
			}
		}
	}
	
	private void calculateMltpDivs() {
		for(int i = 1; i < stack.size(); i += 2) {
			switch(stack.get(i)) {
			case "×":
				result = Double.parseDouble(stack.get(i-1)) * Double.parseDouble(stack.get(i+1));
				stack.add(i-1, String.valueOf(result));
				stack.remove(i+2);
				stack.remove(i+1);
				stack.remove(i);
				i -= 2;
				break;
			case "÷":
				result = Double.parseDouble(stack.get(i-1)) / Double.parseDouble(stack.get(i+1));
				stack.add(i-1, String.valueOf(result));
				stack.remove(i+2);
				stack.remove(i+1);
				stack.remove(i);
				i -= 2;
				break;
			}
		}
	}
	
	private void setEvent() {
		KeyListener k_num = new KeyListener() {
			@Override
			public void keyPressed(KeyEvent e) {
				// TODO Auto-generated method stub
				
				switch(e.getKeyCode()) {
				case 48: case 49: case 50: case 51: case 52: case 53: 
				case 54: case 55: case 56: case 57:
					button[e.getKeyCode()-48].doClick();
					break;
				case 46:
					button[10].doClick();
					break;
				case 10:
					button[11].doClick();
					break;
				case 47:
					button[12].doClick();
					break;
				case 44:
					button[13].doClick();
					break;
				case 45:
					button[14].doClick();
					break;
				case 61:
					button[15].doClick();
					break;
				case 67:
					button[16].doClick();
					break;
				case 16:
					button[17].doClick();
					break;
				case 8:
					button[19].doClick();
					break;
				}
			}

			@Override
			public void keyTyped(KeyEvent e) {
				// TODO Auto-generated method stub
			}

			@Override
			public void keyReleased(KeyEvent e) {
				// TODO Auto-generated method stub
			}
		};
		
		ActionListener num = new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				clear();
				String str = ((JButton)e.getSource()).getText();
				String curStr = output.getText();
				
				if(curStr.equals(" ")) {
					output.setText(str);
					stack.push(str);
				}
				else if((curStr.endsWith(".") || stack.peek().contains(".")) && str.equals("."))
				output.setText(curStr);
				else {
					output.setText(curStr+str);
					switch(stack.peek()) {
					case "+": case "-": case "×": case "÷":
						stack.push(str);
						break;
					default:
						stack.push(stack.pop()+str);
					}
				}
			}
		};
		
		ActionListener init = new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				output.setText(" ");
				stack.clear();
			}
		};
		
		ActionListener opr = new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				if(completed == true)
					completed = false;
				
				String str = ((JButton)e.getSource()).getText();
				String curStr = output.getText();
				
				if(curStr.equals(" ") || curStr.endsWith("+") || curStr.endsWith("-") || curStr.endsWith("×") || curStr.endsWith("÷"))
					output.setText(curStr);
				else {
					output.setText(curStr+str);
					stack.push(str);
				}
			}
		};
		
		ActionListener resultopr = new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				String string;
				
				string = getStack();
				calculateMltpDivs();
				calculateAddSbtr();
				string = string+"="+stack.peek();
				
				JLabel str = new JLabel(string, JLabel.RIGHT);
				computed.add(str);
				
				output.setText(String.valueOf(stack.peek()));
				completed = true;
			}
		};
		
		ActionListener backspace = new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				if(completed == true)
					completed = false;
				
				String curStr = output.getText();
				
				if(curStr.length() == 1) {
					curStr = " ";
					stack.clear();
				}
				else if(!curStr.equals(" ")) {
					curStr = output.getText().substring(0, curStr.length()-1);
					if(stack.peek().length() == 1)
						stack.pop();
					else
						stack.push(stack.peek().substring(0, stack.pop().length()));
				}
				output.setText(curStr);
			}
		};
		
		ActionListener myEvent = new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				if(keyMode == false) {
					for(int i = 0; i < button.length; i++)
						button[i].addKeyListener(k_num);
					keyMode = true;
					message.setText("키보드 모드가 실행됩니다!");
					notice.setVisible(true);
					notice.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
				}
				else {
					for(int i = 0; i < button.length; i++)
						button[i].removeKeyListener(k_num);
					keyMode = false;
					message.setText("키보드 모드가 종료됩니다!");
					notice.setVisible(true);
					notice.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
				}
			}
		};
		
		ActionListener myEvent2 = new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				list.setVisible(true);
				list.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
			}
		};
		
		for(int i = 0; i<= 10; i++)
			button[i].addActionListener(num);
		for(int i = 12; i <= 15; i++)
			button[i].addActionListener(opr);
		button[11].addActionListener(resultopr);
		button[16].addActionListener(init);
		button[17].addActionListener(myEvent);
		button[18].addActionListener(myEvent2);
		button[19].addActionListener(backspace);
	}
	
	public void showStack() {
		for (String a : stack)
			System.out.printf(a+" ");
		System.out.println();
	}
	
	public String getStack() {
		String str = "";
		
		for (String a : stack)
			str = str + a;
		
		return str;
	}
	
	public Calculator() {
		createFrame();
		setButton();
		setEvent();
		setPnl();
		addFrame();
	}

	public void displayFrame() {
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
	public static void main(String args[]){
		Calculator calculator = new Calculator();
		
		calculator.displayFrame();
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}
	
}
