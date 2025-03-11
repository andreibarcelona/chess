//Andrei Barcelona
// 3/10/25
// Project is chess. My goal was to code Long Knight which moves three squares vertically or horizontally unlike the usual two squares.

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import javax.swing.*;

//You will be implmenting a part of a function and a whole function in this document. Please follow the directions for the 
//suggested order of completion that should make testing easier.
@SuppressWarnings("serial")
public class Board extends JPanel implements MouseListener, MouseMotionListener {
	// Resource location constants for piece images
    private static final String RESOURCES_WBISHOP_PNG = "wbishop.png";
	private static final String RESOURCES_BBISHOP_PNG = "bbishop.png";
	private static final String RESOURCES_WKNIGHT_PNG = "wknight.png";
	private static final String RESOURCES_BKNIGHT_PNG = "bknight.png";
	private static final String RESOURCES_WROOK_PNG = "wrook.png";
	private static final String RESOURCES_BROOK_PNG = "brook.png";
	private static final String RESOURCES_WKING_PNG = "wking.png";
	private static final String RESOURCES_BKING_PNG = "bking.png";
	private static final String RESOURCES_BQUEEN_PNG = "bqueen.png";
	private static final String RESOURCES_WQUEEN_PNG = "wqueen.png";
	private static final String RESOURCES_WPAWN_PNG = "wpawn.png";
	private static final String RESOURCES_BPAWN_PNG = "bpawn.png";
    private static final String LONG_KNIGHT_PNG = "BlongKnight.png";
    private static final String WLONG_KNIGHT_PNG = "WlongKnight.png";

	
	// Logical and graphical representations of board
	private final Square[][] board;
    private final GameWindow g;
 
    //contains true if it's white's turn.
    private boolean whiteTurn;

    //if the player is currently dragging a piece this variable contains it.
    private Piece currPiece;
    private Square fromMoveSquare;
    
    //used to keep track of the x/y coordinates of the mouse.
    private int currX;
    private int currY;
    

    
    public Board(GameWindow g) {
        this.g = g;
        board = new Square[8][8];
        setLayout(new GridLayout(8, 8, 0, 0));

        this.addMouseListener(this);
        this.addMouseMotionListener(this);

         // Board Constructor
    // Preconditions:
    // - GameWindow g must not be null.
    // - Image resources must be available.
    // - Square and Piece classes must be defined.
    // Postconditions:
    // - An 8x8 board of Square objects is created.
    // - Squares alternate between white and black.
    // - Pieces are initialized using initializePieces.
    // - whiteTurn is set to true.
    // - Event listeners for mouse actions are added.
        board[0][0]= new Square(this, true, 0,0);
      //for (.....)  
      for ( int row = 0; row < 8; row++)
      {
        for ( int col = 0; col < 8; col++) 
        {
          if( row  % 2 == 0 && col % 2 == 0 || row % 2 == 1 && col % 2 == 1 )
          {
            board[row][col]= new Square(this, true,row, col);
          }
          else 
          {
            board[row][col]= new Square(this, false, row, col);
          }
          this.add(board[row][col]); 
        }
      }
//        	populate the board with squares here. Note that the board is composed of 64 squares alternating from 
//        	white to black.

        initializePieces();

        this.setPreferredSize(new Dimension(400, 400));
        this.setMaximumSize(new Dimension(400, 400));
        this.setMinimumSize(this.getPreferredSize());
        this.setSize(new Dimension(400, 400));

        whiteTurn = true;

    }

    
	   // initializePieces Method
    // Preconditions:
    // - board must be a non-null 8x8 array of Square objects.
    // - Image resources for pieces must be available.
    // - Piece and Square classes must support methods like put().
    // Postconditions:
    // - A black long knight is placed at (0,0).
    // - A white knight is placed at (7,7).
    // - All other squares remain empty unless expanded.
    private void initializePieces() {
    	
    	board[0][0].put(new Piece(false, LONG_KNIGHT_PNG));
        board[7][7].put(new Piece(true, WLONG_KNIGHT_PNG));
        board[0][7].put(new Piece(false, LONG_KNIGHT_PNG));
        board[7][0].put(new Piece(true, WLONG_KNIGHT_PNG));
        
    }

    public Square[][] getSquareArray() {
        return this.board;
    }

    public boolean getTurn() {
        return whiteTurn;
    }

    public void setCurrPiece(Piece p) {
        this.currPiece = p;
    }

    public Piece getCurrPiece() {
        return this.currPiece;
    }

    @Override
    public void paintComponent(Graphics g) {
     
        for (int x = 0; x < 8; x++) {
            for (int y = 0; y < 8; y++) {
                Square sq = board[x][y];
                if(sq == fromMoveSquare)
                	 sq.setBorder(BorderFactory.createLineBorder(Color.blue));
                sq.paintComponent(g);
                
            }
        }
    	if (currPiece != null) {
            if ((currPiece.getColor() && whiteTurn)
                    || (!currPiece.getColor()&& !whiteTurn)) {
                final Image img = currPiece.getImage();
                g.drawImage(img, currX, currY, null);
            }
        }
        
    }

    @Override
    public void mousePressed(MouseEvent e) {
        currX = e.getX();
        currY = e.getY();

        Square sq = (Square) this.getComponentAt(new Point(e.getX(), e.getY()));

        if (sq.isOccupied()) {
            currPiece = sq.getOccupyingPiece();
            fromMoveSquare = sq;
            if (!currPiece.getColor() && whiteTurn)
                return;
            if (currPiece.getColor() && !whiteTurn)
                return;
            sq.setDisplay(false);
        }
        repaint();
    }

       // mouseReleased Method
    // Preconditions:
    // - currPiece must not be null.
    // - fromMoveSquare must reference a valid square containing currPiece.
    // - getLegalMoves must return a valid list of potential destination squares.
    // Postconditions:
    // - If the move is legal:
    //   - currPiece is moved to endSquare.
    //   - fromMoveSquare is cleared.
    //   - Turn alternates between White and Black.
    // - If the move is illegal:
    //   - currPiece remains at its original position.
    // - Borders for all squares are cleared.
    // - currPiece is set to null.
    // - The board is repainted.
    @Override
    public void mouseReleased(MouseEvent e) {
        Square endSquare = (Square) this.getComponentAt(new Point(e.getX(), e.getY()));
        
        //using currPiece
        for(Square[] row: board){
            for(Square s: row){
                s.setBorder(null);
            }
        }
   
            // true = white, false = black
           if(currPiece.getColor() && whiteTurn || !currPiece.getColor() && !whiteTurn) {
            if(currPiece.getLegalMoves(this, fromMoveSquare).contains(endSquare)){
                endSquare.put(currPiece);
                fromMoveSquare.removePiece();
                 whiteTurn = !whiteTurn;
             }
        }

        fromMoveSquare.setDisplay(true);
        currPiece = null;
        repaint();
    }

    @Override
    public void mouseDragged(MouseEvent e) {
        currX = e.getX() - 24;
        currY = e.getY() - 24;
        if (currPiece != null)
        {
            for(Square s: currPiece.getLegalMoves(this, fromMoveSquare)) {
            s.setBorder(BorderFactory.createLineBorder(Color.red));
            }
        }
        repaint();
    }

    @Override
    public void mouseMoved(MouseEvent e) {
    }

    @Override
    public void mouseClicked(MouseEvent e) {
    }

    @Override
    public void mouseEntered(MouseEvent e) {
    }

    @Override
    public void mouseExited(MouseEvent e) {
    }

}