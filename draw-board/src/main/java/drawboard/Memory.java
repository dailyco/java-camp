package drawboard;

import java.awt.Color;
import java.util.Stack;

public class Memory {
	static Stack<Object> memory = new Stack<Object>();
	static Stack<Color> colorMemory = new Stack<Color>();
	static Stack<Integer> thicknessMemory = new Stack<Integer>();
	
	static Stack<Object> redoMemory = new Stack<Object>();
	static Stack<Color> redoColorMemory = new Stack<Color>();
	static Stack<Integer> redoThicknessMemory = new Stack<Integer>();
}