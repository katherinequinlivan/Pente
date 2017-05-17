package projectPackagepentepente;

import java.util.ArrayList;

public class OpponentGroup {

		
		public static final int HORIZONTAL_GROUP = 1;
		public static final int VERTICAL_GROUP = 2;
		public static final int DIAG_RIGHT_GROUP = 3;
		public static final int DIAG_LEFT_GROUP = 4;
		public static final int MIDDLE_4_GROUP = -4;
		public static final int MIDDLE_3_GROUP = -3;
	
		//Class Data Field
		private ArrayList<Square> groupList;
		private int groupLength;
		private int groupRanking;
		private Square end1Square = null;
		private Square end2Square = null;
		
		private int groupType;
		private String groupTypeText;
		
		private boolean currentMoveIsInGroup = false;
		private int currentMoveArrayListLocation = 1;
		
		private boolean inMiddleGroupStatus = false;
		private Square  inMiddleSquare = null;
		
		
	public OpponentGroup(int gt) {
		
		groupList = new ArrayList<Square>();
		
		groupType = gt;
		this.setGroupTypeToString();
		
	}
	
	public void addSquareToGroup(Square whichSquare){
		groupList.add(whichSquare);
		groupLength++;
		groupRanking++;
	}
	
	public ArrayList<Square> getGroupList(){
		return groupList;
	}
	
	public void setEnd1Square(Square whatSquare){
		end1Square = whatSquare;
	}
	
	public void setEnd2Square(Square whatSquare){
		end2Square = whatSquare;
	}
	
	
	public Square getEnd1Square(){
		return end1Square;
	}

	public Square getEnd2Square(){
		return end2Square;
	}
	
	public int getGroupLength(){
		return groupLength;
	}
	
	public int getGroupRanking(){
		return groupRanking;
	}
	
	public void setGroupRanking(int newRanking){
		groupRanking = newRanking;
	}
	
	public int getOpponentGroupType(){
		return groupType;
	}
	
	
	public void setCurrentMoveIsInThisGroup(boolean setting){
		currentMoveIsInGroup = true;
	}
	
	public boolean getCurrentMoveIsInGroup(){
		return currentMoveIsInGroup;
	}
	
	public void setCurrentMoveArrayListLocation(int arrayListIndex){
		currentMoveArrayListLocation = arrayListIndex;
	}
	
	public int getArrayListSizeFromArray(){
		return groupList.size();
	}
	

	
	/*
	private void setGroupTypeToString(int groupType){
		switch(groupType){
		case HORIZONTAL_GROUP:
			groupTypeText = "Horizontal";
			break;
		case VERTICAL_GROUP:
			groupTypeText = "Vertical";
			break;
		case DIAG_RIGHT_GROUP:
			groupTypeText = "Diagonal Right";
			break;
		case DIAG_LEFT_GROUP:
			groupTypeText = "Diagonal Left";
			break;
		default:
			groupTypeText = "Something is messed up";
			break;
		}
	}
	
	*/
		 public void setGroupLength(int l){
			  groupLength = l;
		 }
		
		public String getGroupTypeText(){
			return groupTypeText;
		}
		
		public void setGroupTypeText(String newText){
			groupTypeText = newText;
		}
		 public void setInMiddleGroupStatus(boolean value){
			 inMiddleGroupStatus = value;
		 }
		 
		 public boolean getInMiddleGroupStatus(){
			 return inMiddleGroupStatus;
		 }
		 
		 public void setInMiddleGroupSquare(Square whatSquare){
			 inMiddleSquare = whatSquare;
		 }
		 
		 public Square getInMiddleGroupSquare(){
			 return inMiddleSquare;
		 }
		 
		
		 private void setGroupTypeToString(){
			 switch(groupType){
			 case MIDDLE_3_GROUP:
				 groupTypeText = "Middle -3";
				 break;
			 case MIDDLE_4_GROUP:
				 groupTypeText = "Middle -4";
				 break;
			 case HORIZONTAL_GROUP:
				 groupTypeText = "Horizontal";
				 break;
			 case VERTICAL_GROUP:
				 groupTypeText = "Vertical";
				 break;
			 case DIAG_RIGHT_GROUP:
				 groupTypeText = "Diagonal Right";
				 break;
			 case DIAG_LEFT_GROUP:
				 groupTypeText = "Diagonal Left";
				 break;
			default:
				groupTypeText = "Something is messed up";
				break;
			 }
		 }
		
		
	}
	
