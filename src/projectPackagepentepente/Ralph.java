package projectPackagepentepente;

import java.util.ArrayList;

import javax.swing.JOptionPane;

public class Ralph {
	
	private PenteGameBoard myBoard;
	private int myStoneColor;
	private int boardWidthSquares;
	private Square[][] theGameBoard;
	
	private boolean timeToMakeAMove = false;
	private boolean moveToMake = false;
	private int moveToDealWithRow;
	private int moveToDealWithCol;
	private int opponentStoneColor;
	
	
	
	
	
	private ArrayList<OpponentGroup> groups4 = new ArrayList<OpponentGroup>();
	private ArrayList<OpponentGroup> groups3 = new ArrayList<OpponentGroup>();
	private ArrayList<OpponentGroup> groups2 = new ArrayList<OpponentGroup>();
	private ArrayList<OpponentGroup> groups1 = new ArrayList<OpponentGroup>();
	
	RalphHelper vanellope;
	
	
	
	public Ralph(PenteGameBoard b, int stoneColor){
		myBoard = b;	
		myStoneColor = stoneColor;
		this.setOpponentStoneColor();
		boardWidthSquares = b.getBoardWidthInSquares();
		theGameBoard = b.getActualGameBoard();
		JOptionPane.showMessageDialog(null,  "Hi, Ralph here. Ready to play!");
		
		vanellope = new RalphHelper(myBoard, opponentStoneColor);
	 }
	
	public void setOpponentStoneColor(){
		if(myStoneColor == PenteMain.BLACKSTONE){
			opponentStoneColor = PenteMain.WHITESTONE;
		} else {
			opponentStoneColor = PenteMain.WHITESTONE;
		}
	}
	
	public Square doComputerMove(int lastMoveRow, int lastMoveCol ){

		this.assesBoard(lastMoveRow, lastMoveCol);
		//System.out.println("Back from asses board ready to move");
		vanellope.assesBoard(lastMoveRow, lastMoveCol);
		
		
		Square nextMove = null;
		nextMove = vanellope.blockItEverybody(vanellope.getVanellopeGroups4(), 4);
		if(nextMove == null){
			nextMove = this.blockItEverybody(groups4, 4);
			if(nextMove == null){
				nextMove = this.blockItEverybody(groups3, 3);
				if(nextMove == null){
					nextMove = captureATwo();
					if(nextMove == null){
						nextMove = this.blockItEverybody(groups2, 2);
						if(nextMove == null){
							nextMove = this.makeARandomMove();
				
						}
					}	
				}
			}
		}
		return nextMove;

	}



	private Square blockItEverybody(ArrayList<OpponentGroup> whatGroup, int whatGroupSize) {
		
		Square nextMove = null;
		System.out.println("In block a four groups4 size is " + groups4.size());
		
		if(whatGroup.size() > 0){
			
			boolean done = false;
			int groupIndex = 0;
			
			while(!done && groupIndex < whatGroup.size()){
				OpponentGroup currentGroup = whatGroup.get(groupIndex);
				Square e1 = whatGroup.get(groupIndex).getEnd1Square();
				Square e2 = whatGroup.get(groupIndex).getEnd2Square();
				
				System.out.println("At top of loop in block a 4 and groupIndex is " + groupIndex);
				groupIndex++;
				
				
				if(currentGroup.getInMiddleGroupStatus() == true){
					nextMove = currentGroup.getInMiddleGroupSquare();
					System.out.println("I have an in Middle group!!!!");
				} else{
				
				if((e1 != null && e1.getState() == PenteMain.EMPTY) && (e2 != null && e2.getState() == PenteMain.EMPTY) ){
					System.out.println("We have found for this opponent group both e1 and e2 are empty" );
					int r = (int)(Math.random() * 100);
					if(r > 50){
						nextMove = e1;
					} else {
						nextMove = e2;
					}	
					done = true;
				} else {
					
					if(whatGroupSize ==4){
					
						if(e1!= null && e1.getState() == PenteMain.EMPTY ){
							System.out.println("e1 is empty in block a 4" );
							nextMove = e1;
							done = true;
						} if(e2 != null && e2.getState() == PenteMain.EMPTY){
							nextMove = e2;
							System.out.println("e2 ia empty in block a 4" );
							done = true;
						}
					}
				}
				
				}
		}
		
	}
		return nextMove;
	}
	
	public Square captureATwo(){
		
		Square nextMove = null;
		
		if(groups2.size() > 0){
			boolean done = false;
			int groupIndex = 0;
			
			while(!done && groupIndex < groups2.size()){
				OpponentGroup currentGroup = groups2.get(groupIndex);
				Square e1 = groups2.get(groupIndex).getEnd1Square();
				Square e2 = groups2.get(groupIndex).getEnd2Square();
				
				System.out.println("At the top of loop in captureATwo and groupIndex is " + groupIndex);
				
				groupIndex++;
				
				//
				
				
				if((e1 != null && e1.getState() == PenteMain.EMPTY) && (e2 != null && e2.getState() == PenteMain.EMPTY) ){
					System.out.println("We have found for this opponent group both e1 and e2 are empty" );
					int r = (int)(Math.random() * 100);
					if(r > 50){
						nextMove = e1;
					} else {
						nextMove = e2;
					}	
					done = true;
				} else {
					
					if(groupIndex  == 2){
					
						if(e1!= null && e1.getState() == PenteMain.EMPTY ){
							System.out.println("e1 ia empty in block a 2" );
							nextMove = e1;
							done = true;
						} if(e2 != null && e2.getState() == PenteMain.EMPTY){
							nextMove = e2;
							System.out.println("e2 ia empty in block a 2" );
							done = true;
						}
					}
					}
				 //
			}
		}
		
		
		return nextMove;
	}
	
	
	public Square specialProcessForThree(OpponentGroup g){
		
		Square squareToReturn = null;
		Square e1 = g.getEnd1Square();
		Square e2 = g.getEnd2Square();
		
		return squareToReturn;
		
	}

	
	
	private Square makeARandomMove() {
		// TODO Auto-generated method stub
		Square placeForMove = null;
		
		boolean done = false;
		int r, c;
		
		while(!done){
			r = (int)(Math.random() * this.boardWidthSquares);
			c = (int)(Math.random() * this.boardWidthSquares);
			
			if( theGameBoard[r][c].getState() == PenteMain.EMPTY){
				placeForMove = theGameBoard[r][c];
				done = true;
			}
			
		}
		
		
		return placeForMove;
	}

	private void assesBoard(int lastMoveRow, int lastMoveCol) {
			// TODO Auto-generated method stub
			
			groups4.clear();
			groups3.clear();
			groups2.clear();
			groups1.clear();
			
			this.lookForGroupsHorizontally(lastMoveRow, lastMoveCol);
			this.lookForGroupsVertically(lastMoveRow, lastMoveCol);
			this.lookForGroupsDiagonallyRight(lastMoveRow, lastMoveCol);
			this.lookForGroupsDiagonallyLeft(lastMoveRow, lastMoveCol);
			this.doInMiddleCheck(4);
			//this.doInMiddleCheck(3);
		}
	
	
	

	private void lookForGroupsHorizontally(int lastMoveRow, int lastMoveCol) {
		
		
		int curCol;
		
		for(int row = 0; row < boardWidthSquares; ++row){
			
			curCol = 0;
			while(curCol < boardWidthSquares){
				
				Square newStart = findOpponentStartHorizontal( row,  curCol);
				
				if(newStart != null ){
					
					OpponentGroup newGroup = new OpponentGroup(OpponentGroup.HORIZONTAL_GROUP);
					
					newGroup.addSquareToGroup(newStart);
					
					int startRow = newStart.getRow();
					int startCol = newStart.getCol();
					if(startCol <= 0){
						newGroup.setEnd1Square(null);
					} else { 
						newGroup.setEnd1Square(theGameBoard[startRow][startCol-1]);
					}
					
					if(startRow == lastMoveRow && startCol == lastMoveCol){
						newGroup.setCurrentMoveIsInThisGroup(true);
						newGroup.setCurrentMoveArrayListLocation(newGroup.getGroupLength()-1);
					}
					
					startCol ++;
					while(startCol < boardWidthSquares && 
							theGameBoard[startRow][startCol].getState() == this.opponentStoneColor){
						
						newGroup.addSquareToGroup(theGameBoard[startRow][startCol]);
						if(startRow == lastMoveRow && startCol == lastMoveCol){
							newGroup.setCurrentMoveIsInThisGroup(true);
							newGroup.setCurrentMoveArrayListLocation(newGroup.getGroupLength()-1);
						}
						startCol ++;
						
					}
					
					if(startCol >= boardWidthSquares){
						newGroup.setEnd2Square(null);
					} else {
						newGroup.setEnd2Square(theGameBoard[startRow][startCol]);
					}
					
					curCol = startCol;
					
					this.addNewGroupToGroupLists(newGroup);
					
					
				}else {
					curCol = boardWidthSquares;
					
				}
				
				
			}
		}
		
		
		
	}
	
	private void lookForGroupsVertically(int lastMoveRow, int lastMoveCol) {
		
		
		for(int col = 0; col < boardWidthSquares; ++col){
			
			 int curRow = 0;
			 
			while(curRow < boardWidthSquares){
				
				Square newStart = findOpponentStartVertical( curRow,  col);
				
				if(newStart != null ){
					
					System.out.println("Hi I found a square and we are at " + newStart.getRow() + ", " + newStart.getCol() );
					
					OpponentGroup newGroup = new OpponentGroup(OpponentGroup.VERTICAL_GROUP);
					
					newGroup.addSquareToGroup(newStart);
					
					int startRow = newStart.getRow();
					int startCol = newStart.getCol();
					if(startRow <= 0){
						newGroup.setEnd1Square(null);
					} else { 
						newGroup.setEnd1Square(theGameBoard[startRow-1][col]);
					}
					
					boolean done = false;
					startRow++;
					while(startRow < boardWidthSquares && !done){
						if(theGameBoard[startRow][col].getState() == this.opponentStoneColor){
							newGroup.addSquareToGroup(theGameBoard[startRow][col]);
							startRow++;
						} else {
							done = true;
						}
					}
					
					if(startRow >= boardWidthSquares){
						newGroup.setEnd2Square(null);
					} else {
						newGroup.setEnd2Square(theGameBoard[startRow][col]);
					}
					
					curRow = startRow;
					this.addNewGroupToGroupLists(newGroup);
					
					
				}else {
					curRow = boardWidthSquares;
					
				}
				
				
			}
		}	
	}
	
	public void lookForGroupsDiagonallyRight(int lastMoveRow, int lastMoveCol) {
		
		for(int row = 0; row < boardWidthSquares-1; ++row){
			
			int curCol = 0;
			int curRow = row;
			
			while(curCol <= boardWidthSquares - row && curRow < boardWidthSquares){
				
				Square groupStart = findOpponentDiagRight1(curRow, curCol, 0);
				
				if(groupStart != null ){
					System.out.println("Hi I found a group start at " + groupStart.getRow() + " , " + groupStart.getCol());
					OpponentGroup newGroup = new OpponentGroup(OpponentGroup.DIAG_RIGHT_GROUP);
					newGroup.addSquareToGroup(groupStart);
					int startRow = groupStart.getRow();
					int startCol = groupStart.getCol();
					
					if(startRow -1 >= 0 && startCol -1 >= 0){
						newGroup.setEnd1Square(theGameBoard[startRow-1][startCol-1]);
					} else {
						newGroup.setEnd1Square(null);
					}
					
					if(startRow == lastMoveRow && startCol == lastMoveCol){
						newGroup.setCurrentMoveIsInThisGroup(true);
						newGroup.setCurrentMoveArrayListLocation(newGroup.getGroupLength());
					}
					
					startCol++;
					startRow++;
					boolean done = false;
		
					
					while(startCol < boardWidthSquares - row && startRow < boardWidthSquares && !done){
						if(theGameBoard[startRow][startCol].getState() == this.opponentStoneColor)	{
							newGroup.addSquareToGroup(theGameBoard[startRow][startCol]);
				
							if(startRow == lastMoveRow && startCol == lastMoveCol){
								newGroup.setCurrentMoveIsInThisGroup(true);
								newGroup.setCurrentMoveArrayListLocation(newGroup.getGroupLength());
							}
							
						startRow++;
						startCol++;
						
						} else {
							done = true;
						}
						
						if(startRow < boardWidthSquares && startCol < boardWidthSquares){
							newGroup.setEnd2Square(theGameBoard[startRow ][startCol]);
						} else {
							newGroup.setEnd2Square(null);
						}
						
						curCol = startCol;
						curRow = startRow;
						this.addNewGroupToGroupLists(newGroup);
	
					}
	
					} else{
						curRow = boardWidthSquares;
					}
					
					
				}
				
		}
		 
		// part 2 of diagonal
		for(int col = 1; col < boardWidthSquares; ++col){
			
			int curCol = col;
			int curRow = 0;
			
			while(curRow <= boardWidthSquares - col && curCol < boardWidthSquares){
				
				
				Square groupStart = findOpponentDiagRight1(curRow, curCol, 0);
				
				if(groupStart != null){
					
					System.out.println("Hi I found a group start at " + groupStart.getRow() + " , " + groupStart.getCol());
					OpponentGroup newGroup = new OpponentGroup(OpponentGroup.DIAG_RIGHT_GROUP);
					newGroup.addSquareToGroup(groupStart);
					
					int startRow = groupStart.getRow();
					int startCol = groupStart.getCol();
					
					
					if(startRow -1 >= 0 && startCol -1 >= 0){
						newGroup.setEnd1Square(theGameBoard[startRow-1][startCol-1]);
					} else {
						newGroup.setEnd1Square(null);
					}
					
					if(startRow == lastMoveRow && startCol == lastMoveCol){
						newGroup.setCurrentMoveIsInThisGroup(true);
						newGroup.setCurrentMoveArrayListLocation(newGroup.getGroupLength());
					}
					
					startCol++;
					startRow++;
					boolean done = false;
					
					while(startCol < boardWidthSquares && startRow < boardWidthSquares - col && !done){
						
						if(theGameBoard[startRow][startCol].getState() == this.opponentStoneColor)	{
							newGroup.addSquareToGroup(theGameBoard[startRow][startCol]);
				
							if(startRow == lastMoveRow && startCol == lastMoveCol){
								newGroup.setCurrentMoveIsInThisGroup(true);
								newGroup.setCurrentMoveArrayListLocation(newGroup.getGroupLength());
							}
						startRow++;
						startCol++;
						} else {
							done = true;
						}
						
					}
					
					if(startRow  < boardWidthSquares && startCol  < boardWidthSquares){
						newGroup.setEnd2Square(theGameBoard[startRow][startCol]);
					} else {
						newGroup.setEnd2Square(null);
					}
					
					curCol = startCol;
					curRow = startRow;
					this.addNewGroupToGroupLists(newGroup);
					
				} else {
					curCol = boardWidthSquares;
				}
				
			}
			
				
		}
	}
	
	public void lookForGroupsDiagonallyLeft(int lastMoveRow, int lastMoveCol) {
		
		for(int row = 0; row < boardWidthSquares; ++row){
					
					int curCol = boardWidthSquares-1;
					int curRow = row;
					
					while(curCol >= row  && curRow < boardWidthSquares){
						
						Square groupStart = findOpponentDiagLeft(curRow, curCol);
						
						if(groupStart != null ){
							System.out.println("Hi I found a group start at " + groupStart.getRow() + " , " + groupStart.getCol());
							OpponentGroup newGroup = new OpponentGroup(OpponentGroup.DIAG_LEFT_GROUP);
							newGroup.addSquareToGroup(groupStart);
							int startRow = groupStart.getRow();
							int startCol = groupStart.getCol();
							
							if(startRow -1 >= 0 && startCol +1 < boardWidthSquares){
								newGroup.setEnd1Square(theGameBoard[startRow-1][startCol+1]);
							} else {
								newGroup.setEnd1Square(null);
							}
							
							if(startRow == lastMoveRow && startCol == lastMoveCol){
								newGroup.setCurrentMoveIsInThisGroup(true);
								newGroup.setCurrentMoveArrayListLocation(newGroup.getGroupLength());
							}
							
							startCol--;
							startRow++;
							boolean done = false;
				
							
							while(startCol >= row && startRow < boardWidthSquares&& !done){
								if(theGameBoard[startRow][startCol].getState() == this.opponentStoneColor)	{
									newGroup.addSquareToGroup(theGameBoard[startRow][startCol]);
						
									if(startRow == lastMoveRow && startCol == lastMoveCol){
										newGroup.setCurrentMoveIsInThisGroup(true);
										newGroup.setCurrentMoveArrayListLocation(newGroup.getGroupLength());
									}
								startRow++;
								startCol--;
								} else {
									done = true;
								}
								
								if(startRow < boardWidthSquares && startCol >= 0){
									newGroup.setEnd2Square(theGameBoard[startRow][startCol]);
								} else {
									newGroup.setEnd2Square(null);
								}
								
								curCol = startCol;
								curRow = startRow;
								this.addNewGroupToGroupLists(newGroup);
								
							} 
				
						} 	else { 
								curRow = boardWidthSquares;
								curCol = row-1;
							}	
					}	
				}
		
		for(int col = boardWidthSquares-2 ; col >= 0; --col ){
					
					int curCol = col;
					int curRow = 0;
					
					while(curRow <= col  && curCol >= 0){
						
						
						Square groupStart = findOpponentDiagLeft(curRow, curCol);
						
						if(groupStart != null){
							
							System.out.println("Hi I found a group start at " + groupStart.getRow() + " , " + groupStart.getCol());
							OpponentGroup newGroup = new OpponentGroup(OpponentGroup.DIAG_LEFT_GROUP);
							newGroup.addSquareToGroup(groupStart);
							
							int startRow = groupStart.getRow();
							int startCol = groupStart.getCol();
							
							
							if(startRow -1 >= 0 && startCol -1 >= 0){
								newGroup.setEnd1Square(theGameBoard[startRow-1][startCol+1]);
							} else {
								newGroup.setEnd1Square(null);
							}
							
							if(startRow == lastMoveRow && startCol == lastMoveCol){
								newGroup.setCurrentMoveIsInThisGroup(true);
								newGroup.setCurrentMoveArrayListLocation(newGroup.getGroupLength());
							}
							
							startCol--;
							startRow++;
							boolean done = false;
							
							while( !done && startCol >=0  && startRow < boardWidthSquares ){
								
								if(theGameBoard[startRow][startCol].getState() == this.opponentStoneColor)	{
									newGroup.addSquareToGroup(theGameBoard[startRow][startCol]);
						
									if(startRow == lastMoveRow && startCol == lastMoveCol){
										newGroup.setCurrentMoveIsInThisGroup(true);
										newGroup.setCurrentMoveArrayListLocation(newGroup.getGroupLength());
									}
								startRow++;
								startCol--;
								} else {
									done = true;
								}
								
							}
							
							if(startRow < boardWidthSquares && startCol  >= 0){
								newGroup.setEnd2Square(theGameBoard[startRow ][startCol]);
							} else {
								newGroup.setEnd2Square(null);
							}
							
							curCol = startCol;
							curRow = startRow;
							this.addNewGroupToGroupLists(newGroup);
							
						} else {
							curCol = -1;
						}
						
					}
						
				}
				
				
				
		
		
	}
		
	 private Square findOpponentDiagRight1(int whatRow, int whatCol, int r) {
		Square opponentStart = null;
		boolean done = false;
		int currentCol = whatCol;
		int currentRow = whatRow;
		
		while(!done && currentCol < boardWidthSquares-r && currentRow < boardWidthSquares){
			if(theGameBoard[currentRow][currentCol].getState() == this.opponentStoneColor ){
				opponentStart = theGameBoard[currentRow][currentCol];
				done = true;
			}
			currentRow++;
			currentCol++;
			
		}
		return opponentStart;
	  }
	 
	 private Square findOpponentDiagLeft(int whatRow, int whatCol) {
			Square opponentStart = null;
			boolean done = false;
			int currentCol = whatCol;
			int currentRow = whatRow;
			
			while(!done && currentCol >=0 && currentRow < boardWidthSquares){
				if(theGameBoard[currentRow][currentCol].getState() == this.opponentStoneColor ){
					opponentStart = theGameBoard[currentRow][currentCol];
					done = true;
				}
				currentCol--;
				currentRow++;
			}
			return opponentStart;
		  }
	 

	public Square findOpponentStartHorizontal(int whatRow, int whatCol){
		 Square opponentStart = null;
		 
		boolean done = false;
		int currentCol = whatCol;
		
		while( !done && currentCol < boardWidthSquares ){ 
			if(theGameBoard[whatRow][currentCol].getState() == this.opponentStoneColor){ //look for stones
				opponentStart = theGameBoard[whatRow][currentCol];
				done = true;
			}
			currentCol ++; 
		}
		 
		
		 return opponentStart;
	 }
	 
	
	 public Square findOpponentStartVertical(int whatRow, int whatCol){
		//System.out.println(" Hello from start of findOpponentStartVertical");
		Square opponentStart = null; boolean done = false; int currentRow = whatRow;
		while( !done && currentRow < boardWidthSquares ){
		if(theGameBoard[currentRow][whatCol].getState() ==
		this.opponentStoneColor){
		opponentStart = theGameBoard[currentRow][whatCol]; done = true;
		} currentRow++;
		}
		//System.out.println(" Hello from bottom of findOpponentStartVertical just about to return a start square");
		return opponentStart;
		}
	 
	 
	 public void doInMiddleCheck( int groupSize ){
		 for(int row = 0; row < boardWidthSquares; ++row){
			 for(int col = 0; col < boardWidthSquares; ++col){ 
				 if(theGameBoard[row][col].getState() == PenteMain.EMPTY){
				 checkForBlockInMiddle(row, col, groupSize);
				 }
			 }
		 }
	 }
	 
	 public void checkForBlockInMiddle(int row, int col, int groupSize){
		 boolean done = false; int[] myDys = {-1, 0, 1};
		         int whichDy = 0;  
		 while(!done && whichDy < 3){
			 checkForBlockInMiddleAllAround(row, col, groupSize, myDys[whichDy], 1 );
			 whichDy++;
		 } 	checkForBlockInMiddleAllAround(row, col, groupSize, 1, 0 );
	}
	 
	 
	 public void checkForBlockInMiddleAllAround(int row, int col, int groupSize, int dy, int dx){
		 int sRow = row;
		 int sCol = col;
		 
		 int howManyRight = 0; 
		 int howManyLeft = 0;
		 
		 int step = 1;
		 
		 while((sCol + (step * dx) < boardWidthSquares) && (sRow + (step * dy) < boardWidthSquares) &&
				 (sCol + (step * dx) >= 0) && (sRow + (step * dy) >= 0) &&
				 (theGameBoard[sRow + (step * dy)][sCol + (step *dx)].getState() == this.opponentStoneColor)){
			 howManyRight++; 
			 step++;	 	 
		 }
		 
		 step = 1; 
		 while((sCol - (step * dx) >= 0) &&  (sRow - (step * dy) >= 0) && (sCol - (step * dx) < boardWidthSquares) && 
				 (sRow - (step * dy) < boardWidthSquares) && (theGameBoard[sRow - (step * dy)][sCol - (step *dx)].getState() == this.opponentStoneColor)){
			 howManyLeft++;
			 step++;
				 }
		 
		 if((howManyRight + howManyLeft) >= groupSize){
			 System.out.println("For square at " + row + ", " + col + " we have group of size of " + (howManyRight + howManyLeft));
			 OpponentGroup newGroup; 
			 if( groupSize == 4 ){
				 String middleGroupText = getMiddleGroupText(dx, dy, 4); 
				 		 newGroup = new OpponentGroup(OpponentGroup.MIDDLE_4_GROUP);
						 newGroup.setGroupRanking(4); 
						 newGroup.setGroupLength(4);
						 newGroup.setGroupTypeText(middleGroupText);
						 } else {
						 String middleGroupText = getMiddleGroupText(dx, dy, 3);
						 newGroup = new OpponentGroup(OpponentGroup.MIDDLE_3_GROUP);
						 newGroup.setGroupRanking(3); 
						 newGroup.setGroupLength(3); 
						 newGroup.setGroupTypeText(middleGroupText);
						 }

						 newGroup.setInMiddleGroupStatus(true); 
						 newGroup.setInMiddleGroupSquare(theGameBoard[row][col]); 
						 this.addNewGroupToGroupLists(newGroup);
		
			 }
		 }
 
	 public String getMiddleGroupText(int dx, int dy, int groupSize){
		 String gs = ""; if(groupSize == 4){ 
			 	gs = "4"; 
			 	} else {
			 		gs = "3"; 
			 		} 
		 String theType = ""; 
		 if(dx == 1){
		 if(dy == 1) theType = "Diag Right"; 
		 if(dy == 0) theType = "Horizontal"; 
		 if(dy == -1) theType = "Diag Left";
		 } else {
		 theType = "Vertical";
		 } return "Middle " + gs + ": " + theType;
	}
	
	 
	 private void addNewGroupToGroupLists(OpponentGroup ng){
		 switch(ng.getGroupLength()){
		 
		 case 1:
			 groups1.add(ng);
			 
			 break;
		 case 2:
			 System.out.println("I have an " + ng.getGroupTypeText() + " Group with two opponent stones");
			 groups2.add(ng);
			 
			 break;
		 case 3:
			 System.out.println("I have an " + ng.getGroupTypeText() + " Group with three opponent stones");
			 groups3.add(ng);
			 break;
		 case 4:
			 System.out.println("I have an " + ng.getGroupTypeText() + " Group with four opponent stones");
			 groups4.add(ng);
			 break; 
			 
		 default:
			System.out.println("I have an " + ng.getGroupTypeText() + " Group with " + ng.getGroupLength() +  " opponent stones");
			System.out.println("Somethings Wrong");
			 break; 
			 
		 } 
	 }
	 
	


}


