public class Piece {

	Tile anchor; // anchor should always be top left piece. even after the
					// rotations.
	int size;

	public Piece(Tile anchor) {
		this.anchor = anchor;
		size = 1;
	}
	
	
	
	
	
	
	//I decided not to have to reset the anchor. 
//	public void resetAnchor(Tile addend){
//		//new tile is above anchor
//		if (addend.getY() < anchor.getY()){
//			anchor = addend;
//			resetPositions(addend);
//		}
//		//new tile is to the left and on same level.
//		else if (addend.getX() < anchor.getX() && addend.getY() == anchor.getY()){
//			anchor = addend;
//			resetPositions(addend);
//		}
//	}
//	
//	public void resetPositions(Tile newBase){
//		newBase.setX(0);
//		newBase.setY(0);
//		resetPosRecurs(newBase);
//	}
//
//	public void resetPosRecurs(Tile tile){
//		Tile left = tile.getLeft();
//		Tile above = tile.getAbove();
//		Tile right = tile.getRight();
//		Tile below = tile.getBelow();
//		
//		if (left != null){
//			left.setX(tile.getX() -1);
//			left.setY(tile.getY());
//			resetPosRecurs(left);
//		}
//		if (above != null){
//			above.setX(tile.getX());
//			above.setY(tile.getY() -1);
//			resetPosRecurs(above);
//			
//		}
//		if (right != null){
//			right.setX(tile.getX()  +1 );
//			right.setY(tile.getY());
//			resetPosRecurs
//		}
//	}
	
	public void addTileAbove(Tile base, Tile addend) {
		base.setAbove(addend);
		addend.setBelow(base);
		addend.setX(base.getX());
		addend.setY(base.getY() - 1);
		++size;
		//resetAnchor();
	}

	public void addTileRight(Tile base, Tile addend) {
		base.setRight(addend);
		addend.setLeft(base);
		addend.setX(base.getX() + 1);
		addend.setY(base.getY());
		++size;
		//resetAnchor()....not needed because adding to the right won't be your new anchor.
	}

	public void addTileBelow(Tile base, Tile addend) {
		base.setBelow(addend);
		addend.setAbove(base);
		addend.setX(base.getX());
		addend.setY(base.getY() + 1);
		++size;
		
		//resetAnchor not needed because adding below won't be your new anchor
	}

	public void addTileLeft(Tile base, Tile addend) {
		base.setLeft(addend);
		addend.setRight(base);
		addend.setX(base.getX() - 1);
		addend.setY(base.getY());
		++size;
		//resetAnchor();
		
	}

}
