import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.*;

import javax.swing.*;

public class Parser {

	 /*
	 *NOTES: 1. need to check how many unique symbols there are and make sure we have enough colors. 
	 *right now there are only three colors hardcoded in. 
	 */
	
	static TreeMap<Character, Color> charToColor = new TreeMap<Character, Color>();
	ArrayList<Tile> allTiles = new ArrayList<Tile>();
	
	public static void main(String[] args) {
	
		Tile tile = new Tile('x');
		Tile firstLeft = new Tile('y');
		Tile nextLeft = new Tile('z');

		tile.setLeft(firstLeft);
		System.out.println(tile.getLeft());
		Tile old = tile.setLeft(nextLeft);
		System.out.println("old left: " + old);
		System.out.println("new left: " + tile.getLeft());
		System.out.println();

		
		char[][] temp = fileToMatrix("Checkerboard.txt");

		printCharMatrix(temp);

	}

	public static char[][] fileToMatrix(String filename) {

		Scanner reader = null;
		File file = null;

		try {
			file = new File(filename);
			reader = new Scanner(file);
		} catch (FileNotFoundException e) {
			System.out.println("File not found");
		}

		int rows = 0;
		int columns = 0;
		String line;
		// counts rows and columns to set up matrix.
		while (reader.hasNextLine()) {
			line = reader.nextLine();

			if (line.length() > columns) {
				columns = line.length();
			}
			++rows;
		}

		// matrix to upload char vals representing pieces
		char[][] matrix = new char[rows][columns];

		for (int x = 0; x < rows; ++x) {
			for (int y = 0; y < columns; ++y) {
				matrix[x][y] = 0;
			}
		}

		reader.close();

		try {
			reader = new Scanner(file);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			System.out.println("File not found");
			e.printStackTrace();

		}

		// colors to work with, only two for now.
		LinkedList<Color> queue = new LinkedList<Color>();
		queue.add(Color.blue);
		queue.add(Color.orange);
		queue.add(Color.green);

		System.out.println("queue size: " + queue.size());
		// parsing text into matrix, and getting what characters correspond to
		// what color
		int inRow = 0;
		

		while (reader.hasNextLine()) {
			line = reader.nextLine();
			int length = line.length();
			for (int i = 0; i < length; ++i) {
				char symbol = line.charAt(i);
				if (symbol != ' '){
					matrix[inRow][i] = symbol;
					if (!charToColor.containsKey(line.charAt(i))) {
						System.out
								.println("Removing element from queue. symbol: "
										+ symbol);
						Color temp = queue.remove();
						System.out.println(temp);
						charToColor.put(symbol, temp);

					}
				}
				
			}
			inRow++;
		}

		return matrix;
	}

	
	
	public static void printCharMatrix(char[][] matrix) {
		int rows = matrix.length;
		int columns = matrix[0].length;
		for (int x = 0; x < rows; ++x) {
			for (int y = 0; y < columns; ++y) {
				char value = matrix[x][y];
				if (value == 0) {
					System.out.print(" ");
				} else {
					System.out.print(value);
				}
			}
			System.out.println();

		}
	}
	
	
	//TODO unfinished. do later. 
	public ArrayList<Tile> makePieces(char[][] matrix){
		int rows = matrix.length;
		int columns = matrix[0].length;
		
		for (int i =0 ; i < rows; ++i){
			for (int j = 0; j < columns; ++j){
				if (matrix[i][j] == 0){
					continue;
				}
				
				Tile anchor = new Tile(matrix[i][j]);
				Piece piece = new Piece(anchor);
				//after finding an anchor, go through recursively on all sides of it and keep attaching 
				//to the previous pieces. make sure to check that you don't look outside of matrix.
			}
		}
		
		return null;
	}
}
