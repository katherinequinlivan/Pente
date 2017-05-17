package projectPackagepentepente;

import java.awt.Color;
import java.awt.Graphics;

public class Square {
	
	private int xLoc, yLoc; //top left corner position of square on board
	private int sWidth;
	private Color boardSquareColor = new Color(255, 209, 26);
	private Color crossHairColor = new Color(150, 111, 51);
	private int squareState = PenteMain.EMPTY;
	
	private int myRow, myCol;
	
	
	public Square(int y, int x, int w, int r, int c) {
		
		xLoc = x;
		yLoc = y;
		sWidth = w;
		myRow = r;
		myCol = c;
		
	}
	
	public void drawMe(Graphics g){
		// Draw a solid square
		g.setColor(boardSquareColor);
		g.fillRect(xLoc, yLoc, sWidth, sWidth);
		
		//Cross Hair
		g.setColor(crossHairColor);
		g.drawLine(xLoc + (int)(sWidth/2), yLoc, xLoc + (int)(sWidth/2), yLoc + sWidth);
		g.drawLine(xLoc, yLoc + (int)(sWidth/2), xLoc + sWidth, yLoc + (int)(sWidth/2));
		
		if(squareState == PenteMain.BLACKSTONE){
			g.setColor(Color.BLACK);
			g.fillOval(xLoc+3, yLoc+3, sWidth-6, sWidth-6);
		}
		if(squareState == PenteMain.WHITESTONE){
			g.setColor(Color.WHITE);
			g.fillOval(xLoc+3, yLoc+3, sWidth-6, sWidth-6);
		}
	}
	
	public void setState(int newState){
		squareState = newState;
	}
	
	public int getState(){
		return squareState;
	}
	
	
	public int getRow(){
		return myRow;
	}
	
	public int getCol(){
		return myCol;
	}
	
	public boolean youClickedMe(int mouseX, int mouseY){
		boolean squareClicked = false;
		
		if(mouseX >= xLoc && mouseX < xLoc + sWidth && mouseY >= yLoc && mouseY < yLoc + sWidth){
			 squareClicked = true;
		} else {
			 squareClicked = false;
		}
		
		
		
		return squareClicked;
	}
	


}
