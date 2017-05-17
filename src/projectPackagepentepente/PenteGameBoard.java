package projectPackagepentepente;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class PenteGameBoard extends JPanel implements MouseListener{
	
	private int bWidthPixels;
	private int bWidthSquares;
	private int bSquareWidth;
	private int currentTurn = PenteMain.BLACKSTONE;
	//Color boardSquareColor = new Color(150, 111, 51);
	
	//for testing purposes
	//Square testSquare;
	
	Square [][] theBoard;
	
	private int whiteStoneCaptures = 0;
	private int blackStoneCaptures = 0;
	
	Ralph computerMoveGenerator = null;
	boolean playingAgainstRalph = false;
	int ralphStoneColor;
	
	
	public PenteGameBoard( int bWPixel, int bWSquares){
		
		bWidthPixels = bWPixel - 10;
		bWidthSquares = bWSquares;
		//compute the width of b squares
		bSquareWidth = (int)Math.ceil((bWidthPixels/bWidthSquares)) +2;
		 
		 this.setSize(bWidthPixels, bWidthPixels);
		 this.setBackground(Color.CYAN);
		 
		 //testSquare = new Square(0, 0, bSquareWidth);
		 theBoard = new Square[bWidthSquares][bWidthSquares];
		 
		 for(int row = 0; row < bWidthSquares; ++ row){
			 for(int col = 0; col < bWidthSquares; ++col){
				 theBoard [row][col] = new Square((row*bSquareWidth), (col* bSquareWidth) , bSquareWidth, row, col);
			 }
		 } 
		
		 
		 //set the first stone
		 theBoard[(int)(bWidthSquares/2)][(int)(bWidthSquares/2)].setState(PenteMain.BLACKSTONE);
		 
		 String computerAnswer = JOptionPane.showInputDialog("Hi, would you like to play against the computer? (y or n)");
		 if(computerAnswer.equals("y") || 
			computerAnswer.equals("Y") || 
			computerAnswer.equals("yes") || 
			computerAnswer.equals("Yes")){
			 computerMoveGenerator = new Ralph(this, currentTurn);
			 playingAgainstRalph = true; 
			 ralphStoneColor = currentTurn;
		 }
		 
		 this.changeTurn();
		 
		 this.addMouseListener(this);
	}
	
	//Overriding method
	public void paintComponent(Graphics g){
		
		g.setColor(Color.CYAN);
		g.fillRect(0, 0, bWidthPixels, bWidthPixels);
		
		//testSquare.drawMe(g);
		for(int row = 0; row < bWidthSquares; ++ row){
			 for(int col = 0; col < bWidthSquares; ++col){
				 theBoard [row][col].drawMe(g);
			 }
		}
		
		
	}
	
	public void changeTurn(){
		//if currentTurn is black stone, make it white stone
		// if currentTurn is white Stone, make it black stone
		
		if(currentTurn == PenteMain.BLACKSTONE){
			currentTurn = PenteMain.WHITESTONE;	
		} else {
			currentTurn = PenteMain.BLACKSTONE;
		}
		
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		System.out.println("You clicked at [" + e.getX() + ", " + e.getY() + "]");
		playGame(e);
		repaint();
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	
	public void playGame(MouseEvent e){
		
		Square s = findSquare(e.getX(), e.getY());
		if(s != null){
			if(s.getState() == PenteMain.EMPTY){
				s.setState(currentTurn);
				this.repaint();
				this.checkForCaptures(s);
				this.repaint();
				this.checkForWinOnCaptures();
				this.checkForWin2(s);
				this.changeTurn();
				if(playingAgainstRalph == true && currentTurn == ralphStoneColor){
					Square cs = computerMoveGenerator.doComputerMove(s.getRow(), s.getCol());
					cs.setState(currentTurn);
					this.repaint();
					this.checkForCaptures(s);
					this.checkForWinOnCaptures();
					this.checkForWin2(s);
					this.changeTurn();
					this.requestFocus();
				}
			} else {
				JOptionPane.showMessageDialog(null, "there's already a stone here!");
			}
		} else { JOptionPane.showMessageDialog(null, "you didn't click on a square");
			
		}
	}
	


	public Square findSquare(int mouseX, int mouseY){
		Square clickedOnSquare = null;
		
		for(int row = 0; row < bWidthSquares; ++ row){
			 for(int col = 0; col < bWidthSquares; ++col){
				 if(theBoard[row][col].youClickedMe(mouseX, mouseY) == true){
					 clickedOnSquare = theBoard [row][col];
				 }
			 }
		}
		
		return clickedOnSquare;	
	}
	
	public void checkForCaptures(Square s){
		
		/*int FLAT_Right = {0,1};
		int UP = -1;
		int DOWN = */
		
		int sRow = s.getRow();
		int sCol = s.getCol();
		int theOpposite = this.getTheOppisiteState(s);
		
		for(int dy = -1; dy <= 1; ++dy){
			if((dy > 0 && sRow < bWidthSquares - 3) || (dy < 0 && sRow >= 3) || dy == 0){
		for(int dx = -1; dx <= 1; ++ dx){
		
			if((dx > 0 && sCol < bWidthSquares - 3) || (dx < 0 && sCol >= 3) || dx == 0){
				if(theBoard[sRow + (1 * dy)][sCol + (1 *dx) ].getState() == theOpposite){	
					if(theBoard[sRow + (2 * dy)][sCol + (2 *dx)].getState() == theOpposite)
					if(theBoard[sRow + (3 * dy)][sCol+ (3 * dx)].getState() == currentTurn){
						this.takeStones(sRow + (1 * dy),  sCol + (1 *dx),  sRow + (2 * dy), sCol + (2 *dx), currentTurn);
						repaint();
					}
				}
				
			}
			}
		}
		}
		}

	
	public int getTheOppisiteState(Square s){
		if(s.getState() == PenteMain.BLACKSTONE){
			return PenteMain.WHITESTONE;
		} else {
			return PenteMain.BLACKSTONE;
		}

	}
	
	public void takeStones(int r1,int c1,int r2,int c2,int taker){
		// to clear stones
		theBoard[r1][c1].setState(PenteMain.EMPTY);
		theBoard[r2][c2].setState(PenteMain.EMPTY);
		
		if(taker == PenteMain.BLACKSTONE){
			++blackStoneCaptures;
		} else {
			++whiteStoneCaptures;
		}
		
		this.checkForWinOnCaptures();
	}
	
	public void checkForWinOnCaptures(){
		if(blackStoneCaptures >= 5){
			JOptionPane.showMessageDialog(null, "black wins!! with " + blackStoneCaptures + " captures" );
		}
		if(whiteStoneCaptures >= 5){
			JOptionPane.showMessageDialog(null, "white wins!! with " + whiteStoneCaptures + " captures" );
		}
	}
	
	public void checkForWin(Square s){
		
		int sRow = s.getRow();
		int sCol = s.getCol();
		
		for(int dy = -1; dy <= 1; ++dy){
			if((dy > 0 && sRow < bWidthSquares - 4) || (dy < 0 && sRow >= 4) || dy == 0){
		for(int dx = -1; dx <= 1; ++ dx){
			if(!(dx == 0 && dy ==0)){
			if((dx > 0 && sCol < bWidthSquares - 4) || (dx < 0 && sCol >= 4) || dx == 0){
				if(theBoard[sRow + (1 * dy)][sCol + (1 *dx) ].getState() == currentTurn){
					if(theBoard[sRow + (2 * dy)][sCol + (2 *dx)].getState() == currentTurn){
					if(theBoard[sRow + (3 * dy)][sCol+ (3 * dx)].getState() == currentTurn){
						if(theBoard[sRow + (4 * dy)][sCol+ (4 * dx)].getState() == currentTurn){
						this.weHaveAWinner();
						repaint();
						}
					}
					}
				}
			}
			}
		}
			}
		}	
	}
	public void weHaveAWinner() {
		String theWinner = null;
		if(currentTurn == PenteMain.BLACKSTONE){
			 theWinner = "Blackstone Player!!";
		} else {
			 theWinner = "Whitestone Player!!";
		}
		
		JOptionPane.showMessageDialog(null, "Congratulations " + theWinner + " you win!!");
	}
	
	public void checkForWin2(Square s){
		
		boolean done = false;
		int[] myDys = {-1, 0, 1};
		int whichDy = 0;
		
		while(!done && whichDy < 3){
			if (checkForWinAllInOne(s,myDys[whichDy], 1 ) == true){
				weHaveAWinner();
				done = true;
			}
			whichDy++;
		}
		if(!done){
			if (checkForWinAllInOne(s, 1, 0) == true){
				weHaveAWinner();
			}
		}
		
	}
	
	public boolean checkForWinAllInOne(Square s, int dy, int dx){
		
		boolean isThereAWin = false;
		int sRow = s.getRow();
		int sCol = s.getCol();
		
		int howManyRight = 0;
		int howManyLeft = 0;
		
		int step = 1;
		while((sCol + (step * dx) < bWidthSquares) && (sRow + (step * dy) < bWidthSquares) 
				&& (sCol + (step *dx) >=0) && (sRow + (step * dy) >= 0) && 
				(theBoard[sRow + (step * dy)] [sCol + (step *dx)].getState() == currentTurn)){
			howManyRight ++;
			step ++;
		}
		step = 1;
		while((sCol - (step * dx) < bWidthSquares) && (sRow - (step * dy) < bWidthSquares) 
				&& (sCol - (step *dx) >=0) && (sRow - (step * dy) >= 0) && 
				(theBoard[sRow - (step * dy)] [sCol - (step *dx)].getState() == currentTurn)){
			howManyLeft ++;
			step ++;
		}
		if((howManyRight + howManyLeft + 1) >= 5){
			isThereAWin = true;
		}
		return isThereAWin;
		
	}
	
	public int getBoardWidthInSquares(){
		return bWidthSquares;
	}
	
	public Square[][] getActualGameBoard(){
		return theBoard;
	}

	

}








