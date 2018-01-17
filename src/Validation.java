 


public class Validation {
	private int oX,oY,nX,nY,typeOfPiece;
	private Piece[][] currentTable;
	private boolean shouldEat;
	private static boolean isKing;

	public Validation(int originalX,int originalY,int newX , int newY,int typeOfP,boolean isK,Piece[][] x) {
		currentTable=x;
		oX=originalX;
		oY=originalY;
		nX=newX;
		isKing=isK;
		nY=newY;
		typeOfPiece=typeOfP;
	}

	private  boolean checkBounds(){
		if((nX>7 || nX<0) || (nY>7 || nY <0)){
			return false;
		}
		return true;
	}

	private boolean checkIfPopulated(){
		if(currentTable[nX][nY].isPiece() && (currentTable[nX][nY].getType()==1 || currentTable[nX][nY].getType()==2)){
			return false;
		}
		return true;
	}


	private boolean checkIfDiagonalMove(){
		if(isKing){
			if(oX+1==nX || oX-1==nX){
				if(oY+1==nY || oY-1==nY){
					return true;
				}
			}
			else if(oX+2==nX || oX-2==nX){
				if(oY+2==nY || oY-2==nY){
					if(currentTable[(oX+nX)/2][(oY+nY)/2].getType()!=typeOfPiece && (currentTable[(oX+nX)/2][(oY+nY)/2].isPiece())){
						shouldEat=true;
						return true;
					}
				}
			}
		}
		switch(typeOfPiece){
		case 1:
			if(oX+1==nX){
				if(oY+1==nY || oY-1==nY){
					return true;
				}
			}
			else if(oX+2==nX){
				if(oY+2==nY){
					if(currentTable[oX+1][oY+1].getType()!=typeOfPiece && (currentTable[oX+1][oY+1].isPiece())){
						shouldEat=true;
						return true;
					}
				}
				else if(oY-2==nY){
					if(currentTable[oX+1][oY-1].getType()!=typeOfPiece && (currentTable[oX+1][oY-1].isPiece())){
						shouldEat=true;
						return true;
					}
				}
			}
			break;
		case 2:
			if(oX-1==nX){
				if(oY+1==nY || oY-1==nY){
					return true;
				}
			}
			else if(oX-2==nX){
				if(oY+2==nY){
					if(currentTable[oX-1][oY+1].getType()!=typeOfPiece && (currentTable[oX-1][oY+1].isPiece())){
						shouldEat=true;
						return true;
					}
				}
				else if(oY-2==nY){
					if(currentTable[oX-1][oY-1].getType()!=typeOfPiece && (currentTable[oX-1][oY-1].isPiece())){
						shouldEat=true;
						return true;
					}
				}
			}
			break;
		}
		return false;
	}

	public static boolean becomesKing(int nX,int typeOfPiece,boolean isK){
		if(!isK){
			switch(typeOfPiece){
			case 1: 
				if(nX==7){
					return true;
				}
				break;
			case 2:
				if(nX==0){
					return true;
				}
				break;
			}
			return false;
		}
		return false;
	}

	public boolean checkIfValid(){
		shouldEat=false;
		if(checkBounds() && checkIfPopulated() && checkIfDiagonalMove()){
			return true;
		}
		else{
			return false;
		}			
	}

	public boolean getEat(){
		return shouldEat;
	}


}
