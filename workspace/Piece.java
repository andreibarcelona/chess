
import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import javax.imageio.ImageIO;

//you will need to implement two functions in this file.
public class Piece {
    private final boolean color;
    private BufferedImage img;
    
    public Piece(boolean isWhite, String img_file) {
        this.color = isWhite;
        
        try {
            if (this.img == null) {
              this.img = ImageIO.read(getClass().getResource(img_file));
            }
          } catch (IOException e) {
            System.out.println("File not found: " + e.getMessage());
          }
    }
    
    

    
    public boolean getColor() {
        return color;
    }
    
    public Image getImage() {
        return img;
    }
    
    public void draw(Graphics g, Square currentSquare) {
        int x = currentSquare.getX();
        int y = currentSquare.getY();
        
        g.drawImage(this.img, x, y, null);
    }
    
    
// Method: getControlledSquares
// Purpose: Returns a list of squares controlled by the piece located at the `start` square.
// A square is controlled if the piece can legally move into that square according to its movement rules.

public ArrayList<Square> getControlledSquares(Square[][] board, Square start) {
    // Preconditions:
    // - The `board` is a valid 8x8 array of `Square` objects.
    // - The `start` square must be a valid square on the board (row and column between 0 and 7).
    // - The piece on the `start` square must be a valid piece (non-null).
    
    ArrayList<Square> controlledSquares = new ArrayList<>();
    int currentRow = start.getRow();
    int currentCol = start.getCol();

    // Check all 8 possible knight moves (3 squares in one direction, 1 square in the other)
    
    // Move 1: Up 3, Right 1
    if (currentRow - 3 >= 0 && currentCol + 1 < 8) {
        controlledSquares.add(board[currentRow - 3][currentCol + 1]);
    }

    // Move 2: Up 3, Left 1
    if (currentRow - 3 >= 0 && currentCol - 1 >= 0) {
        controlledSquares.add(board[currentRow - 3][currentCol - 1]);
    }

    // Move 3: Up 1, Right 3
    if (currentRow - 1 >= 0 && currentCol + 3 < 8) {
        controlledSquares.add(board[currentRow - 1][currentCol + 3]);
    }

    // Move 4: Up 1, Left 3
    if (currentRow - 1 >= 0 && currentCol - 3 >= 0) {
        controlledSquares.add(board[currentRow - 1][currentCol - 3]);
    }

    // Move 5: Down 3, Right 1
    if (currentRow + 3 < 8 && currentCol + 1 < 8) {
        controlledSquares.add(board[currentRow + 3][currentCol + 1]);
    }

    // Move 6: Down 3, Left 1
    if (currentRow + 3 < 8 && currentCol - 1 >= 0) {
        controlledSquares.add(board[currentRow + 3][currentCol - 1]);
    }

    // Move 7: Down 1, Right 3
    if (currentRow + 1 < 8 && currentCol + 3 < 8) {
        controlledSquares.add(board[currentRow + 1][currentCol + 3]);
    }

    // Move 8: Down 1, Left 3
    if (currentRow + 1 < 8 && currentCol - 3 >= 0) {
        controlledSquares.add(board[currentRow + 1][currentCol - 3]);
    }

    // Postcondition:
    // - Returns a list of squares that the piece can control based on its movement rules.
    // - The squares are within board boundaries, and the piece can legally move to them.
    return controlledSquares;
}

// Method: getLegalMoves
// Purpose: Returns a list of legal moves for the piece located at the `start` square.
// A legal move is a square that is either unoccupied or occupied by an opponent's piece.



//The piece Long knight moves 3 squares vertically or horizontally and two the left/right unlike the usual two squares

public ArrayList<Square> getLegalMoves(Board b, Square start) {
    // Preconditions:
    // - The `board` is a valid 8x8 array of `Square` objects.
    // - The `start` square must be a valid square on the board (row and column between 0 and 7).
    // - The piece on the `start` square must be a valid piece (non-null).
    // - The piece must be either a white or black piece, and the color must be consistent with the current turn (white = true, black = false).
    
    ArrayList<Square> moves = new ArrayList<>();
    Square[][] board = b.getSquareArray();
    int currentRow = start.getRow();
    int currentCol = start.getCol();
    boolean pieceColor = start.getOccupyingPiece().getColor();  // Piece color (true = white, false = black)

    // Check all 8 possible knight moves (3 squares in one direction, 1 square in the other)

    // Move 1: Up 3, Right 1
    if (currentRow - 3 >= 0 && currentCol + 1 < 8) {
        Square target = board[currentRow - 3][currentCol + 1];
        if (!target.isOccupied() || target.getOccupyingPiece().getColor() != pieceColor) {
            moves.add(target);
        }
    }

    // Move 2: Up 3, Left 1
    if (currentRow - 3 >= 0 && currentCol - 1 >= 0) {
        Square target = board[currentRow - 3][currentCol - 1];
        if (!target.isOccupied() || target.getOccupyingPiece().getColor() != pieceColor) {
            moves.add(target);
        }
    }

    // Move 3: Up 1, Right 3
    if (currentRow - 1 >= 0 && currentCol + 3 < 8) {
        Square target = board[currentRow - 1][currentCol + 3];
        if (!target.isOccupied() || target.getOccupyingPiece().getColor() != pieceColor) {
            moves.add(target);
        }
    }

    // Move 4: Up 1, Left 3
    if (currentRow - 1 >= 0 && currentCol - 3 >= 0) {
        Square target = board[currentRow - 1][currentCol - 3];
        if (!target.isOccupied() || target.getOccupyingPiece().getColor() != pieceColor) {
            moves.add(target);
        }
    }

    // Move 5: Down 3, Right 1
    if (currentRow + 3 < 8 && currentCol + 1 < 8) {
        Square target = board[currentRow + 3][currentCol + 1];
        if (!target.isOccupied() || target.getOccupyingPiece().getColor() != pieceColor) {
            moves.add(target);
        }
    }

    // Move 6: Down 3, Left 1
    if (currentRow + 3 < 8 && currentCol - 1 >= 0) {
        Square target = board[currentRow + 3][currentCol - 1];
        if (!target.isOccupied() || target.getOccupyingPiece().getColor() != pieceColor) {
            moves.add(target);
        }
    }

    // Move 7: Down 1, Right 3
    if (currentRow + 1 < 8 && currentCol + 3 < 8) {
        Square target = board[currentRow + 1][currentCol + 3];
        if (!target.isOccupied() || target.getOccupyingPiece().getColor() != pieceColor) {
            moves.add(target);
        }
    }

    // Move 8: Down 1, Left 3
    if (currentRow + 1 < 8 && currentCol - 3 >= 0) {
        Square target = board[currentRow + 1][currentCol - 3];
        if (!target.isOccupied() || target.getOccupyingPiece().getColor() != pieceColor) {
            moves.add(target);
        }
    }

    // Postcondition:
    // - Returns a list of squares that the piece can legally move to based on its movement rules.
    // - A square is considered legal if it is within the board boundaries, not occupied by a piece of the same color,
    //   and adheres to the movement rules of the piece.
    // - If no legal moves are found, the list will be empty.
    return moves;
}
}