//Abby Bruskin

//Slime
//This piece is able to move and capture any pieces up and 2 two to the left or right, and down and two to the left or right
import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import javax.imageio.ImageIO;

//you will need to implement two functions in this file.
public class Slime extends Piece{

    
    public Slime(boolean isWhite, String img_file) {
        super(isWhite, img_file);
    }
    
    
    public String toString() {
		if (super.getColor())
			return "A white Slime";
		else
			return "A black Slime";
	}
    
    
    
    
    // TO BE IMPLEMENTED!
    //return a list of every square that is "controlled" by this piece. A square is controlled
    //if the piece capture into it legally.
    //precondition: the board and pieces are properly intialized
    //postcondition: returns an arraylist of squares that the piece on start controls (up 1 & 2 left, up 1 & 2 right, down 1 & 2 right, down 1 & 2 left) if they are in bounds
    public ArrayList<Square> getControlledSquares(Square[][] board, Square start) {
    	ArrayList<Square> controlled = new ArrayList<Square>();
    	if ((((start.getCol()+2)<8)&& ((start.getRow()+1)<8))) {
    		controlled.add(board[start.getRow()+1][start.getCol()+2]);
    	}
    	if ((((start.getCol()+2)<8)&& ((start.getRow()-1)>=0))) {
    		controlled.add(board[start.getRow()-1][start.getCol()+2]);
    	}
    	if ((((start.getCol()-2)>=0)&& ((start.getRow()+1)<8))) {
    		controlled.add(board[start.getRow()+1][start.getCol()-2]);
    	}
    	if ((((start.getCol()-2)>=0)&& ((start.getRow()-1)>=0))) {
    		controlled.add(board[start.getRow()-1][start.getCol()-2]);
    	}
    	
    	return controlled;
    }
    	
    
    

    //TO BE IMPLEMENTED!
    //implement the move function here
    //it's up to you how the piece moves, but at the very least the rules should be logical and it should never move off the board!
    //returns an arraylist of squares which are legal to move to
    //please note that your piece must have some sort of logic. Just being able to move to every square on the board is not
    //going to score any points.
  //precondition: the board and pieces are properly intialized
    //postcondition: returns an arraylist of squares that the piece on start can move to (up 1 & 2 left, up 1 & 2 right, down 1 & 2 right, down 1 & 2 left) if they are in bounds and not occupied by a piece of the same color
    public ArrayList<Square> getLegalMoves(Board b, Square start){
    	ArrayList<Square> moves = new ArrayList<Square>();
    	if (((start.getCol()+2)<8)&& ((start.getRow()+1)<8)) {
    		if(b.getSquareArray()[start.getRow()+1][start.getCol()+2].getOccupyingPiece()== null || b.getSquareArray()[start.getRow()+1][start.getCol()+2].getOccupyingPiece().getColor()!=super.getColor()) {
    		moves.add(b.getSquareArray()[start.getRow()+1][start.getCol()+2]);
    	}
    	}
    	if ((((start.getCol()+2)<8)&& ((start.getRow()-1)>0))) {
    		if(b.getSquareArray()[start.getRow()-1][start.getCol()+2].getOccupyingPiece()== null || b.getSquareArray()[start.getRow()-1][start.getCol()+2].getOccupyingPiece().getColor()!=super.getColor()) {
        		moves.add(b.getSquareArray()[start.getRow()-1][start.getCol()+2]);
    	}
    	}
    	if ((((start.getCol()-2)>0)&& ((start.getRow()+1)<8))) {
    		if(b.getSquareArray()[start.getRow()+1][start.getCol()-2].getOccupyingPiece()== null || b.getSquareArray()[start.getRow()+1][start.getCol()-2].getOccupyingPiece().getColor()!=super.getColor()) {
        		moves.add(b.getSquareArray()[start.getRow()+1][start.getCol()-2]);
    	}
    	}
    	if ((((start.getCol()-2)>0)&& ((start.getRow()-1)>0))) {
    		if(b.getSquareArray()[start.getRow()-1][start.getCol()-2].getOccupyingPiece()== null || b.getSquareArray()[start.getRow()-1][start.getCol()-2].getOccupyingPiece().getColor()!=super.getColor()) {
        		moves.add(b.getSquareArray()[start.getRow()-1][start.getCol()-2]);
    	}
    	}
    	
    	return moves;
    }
}

