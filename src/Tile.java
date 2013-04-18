import java.awt.Color;
import java.util.ArrayList;

public class Tile {

	/*
	 * NOTES piece object has anchor node. anchor node has pointers pointing to
	 * where next one is node
	 */

	// attributes
	private char symbol;
	private Color color;
	private ArrayList<Tile> adjTiles; //currently not used.
	private int xPos;
	private int yPos;
	private Tile left;
	private Tile right;
	private Tile above;
	private Tile below;

	
	// TODO finish
	public Tile() {
		symbol = 0;
		adjTiles = new ArrayList<Tile>();
		left = null;
		right = null;
		above = null;
		below = null;
		xPos = 0;
		yPos = 0;
	}
	
	public Tile (char sym){
		this.symbol = sym;
		adjTiles = new ArrayList<Tile>();
		left = null;
		right = null;
		above = null;
		below = null;
		xPos = 0;
		yPos = 0;
	}
	
	
	public int getX(){
		return xPos;
	}
	public void setX(int x){
		xPos = x;
	}
	
	public int getY(){
		return yPos;
	}
	public void setY(int y){
		yPos = y;
	}
	public Color getColor() {
		return color;
	}

	public void setColor(Color c) {
		this.color = c;
	}

	public Tile getLeft() {
		return left;
	}

	
	public Tile setLeft(Tile left) {
		Tile old = this.left;
		this.left = left;
		return old;
	}

	public Tile getRight() {
		return right;
	}

	public Tile setRight(Tile right) {
		Tile old = this.right;
		this.right = right;
		return old;
	}

	public Tile getAbove() {
		return above;
	}

	public Tile setAbove(Tile above) {
		Tile old = this.above;
		this.above = above;
		return old;
	}

	public Tile getBelow() {
		return below;
	}

	public Tile setBelow(Tile below) {
		Tile old = this.below;
		this.below = below;
		return old;
	}
	
	
	public String toString(){
		String s = "symbol: " + symbol;
		return s;
		
	}

}
