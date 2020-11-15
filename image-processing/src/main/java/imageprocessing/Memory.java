package imageprocessing;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.util.Stack;

public class Memory {
	Stack<BufferedImage> memory = new Stack<BufferedImage>();
	Stack<BufferedImage> redoMemory = new Stack<BufferedImage>();

	Stack<Color[][]> colorMemory = new Stack<Color[][]>();
	Stack<Color[][]> redoColorMemory = new Stack<Color[][]>();
	
	Stack<Integer> sliderMemory = new Stack<Integer>();
	Stack<Integer> redoSliderMemory = new Stack<Integer>();
}