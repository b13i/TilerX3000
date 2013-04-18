import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.*;

import android.graphics.Color;

public class Parser {

	 /*
	 *NOTES: 1. need to check how many unique symbols there are and make sure we have enough colors. 
	 *right now there are only three colors hardcoded in. 
	 */
	
	static TreeMap<Character, Color> charToColor = new TreeMap<Character, Color>();
	ArrayList<Tile> allTiles = new ArrayList<Tile>();
	
	public static void main(String[] args) {
	
		Tile tile = new Tile('x', null);
		Tile firstLeft = new Tile('y', null);
		Tile nextLeft = new Tile('z', null);

		tile.setLeft(firstLeft);
		System.out.println(tile.getLeft());
		Tile old = tile.setLeft(nextLeft);
		System.out.println("old left: " + old);
		System.out.println("new left: " + tile.getLeft());
		System.out.println();

		
		char[][] temp = fileToMatrix("Checkerboard.txt");

		printCharMatrix(temp);
		ArrayList<Piece> pieces = makePieces(temp);

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
		queue.add(new Color());
		queue.add(new Color());
		queue.add(new Color());

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
	public static ArrayList<Piece> makePieces(char[][] matrix){
		ArrayList<Piece> allPieces = new ArrayList<Piece>();
		int rows = matrix.length;
		int columns = matrix[0].length;
		
		for (int i =0 ; i < rows; ++i){
			for (int j = 0; j < columns; ++j){
				if (matrix[i][j] == 0){
					continue;
				}
				
				Piece piece = createPiece(matrix, i, j);
				allPieces.add(piece);
			}
		}
		
		return null;
	}
	
	public static Piece createPiece(char[][] m, int row, int col){
		//gonna need symbol, color, pos (0,0) for anchor, 
		char symbol = m[row][col];
		Color color = charToColor.get(symbol);
		
		Tile anchor = new Tile(symbol, color);
		anchor.setX(0);
		anchor.setY(0);
		m[row][col] = 0;
		Piece piece = new Piece(anchor);
		buildPiece(m,row,col,piece,anchor);
		
		return null;
	}
	
	public static void buildPiece(char[][] m, int row, int col, Piece p, Tile tile){
		int leftLimit = 0;
		int upLimit = 0;
		int rightLimit = m[0].length -1;
		int bottomLimit = m.length-1;
		
		//left
		if (!(col-1 < leftLimit)){
			while (m[row][col-1] != 0){
				Tile left = new Tile(m[row][col-1], charToColor.get(m[row][col-1]));
				p.addTileLeft(tile, left);
				m[row][col-1] = 0;
				buildPiece(m, row, col-1, p, left );
			}
		}
		//bottom
		if (!(row+1 > bottomLimit)){
			while (m[row+1][col] != 0){
				Tile below = new Tile(m[row+1][col], charToColor.get(m[row+1][col]));
				p.addTileBelow(tile, below);
				m[row+1][col] = 0;
				buildPiece(m, row+1, col, p, below );
			}
		}
		//right
		if (!(col+1 > rightLimit)){
			while (m[row][col+1] != 0){
				Tile right = new Tile(m[row][col+1], charToColor.get(m[row][col+1]));
				p.addTileRight(tile, right);
				m[row][col+1] = 0;
				buildPiece(m, row, col+1, p, right );
			}
		}
		//up
		if (!(row-1 < upLimit)){
			while (m[row-1][col] != 0){
				Tile above = new Tile(m[row-1][col], charToColor.get(m[row-1][col]));
				p.addTileAbove(tile, above);
				m[row-1][col] = 0;
				buildPiece(m, row-1, col, p, above );
			}
		}
		
	}
	
}
