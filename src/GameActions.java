 


public class GameActions {

	public static Piece[][] removeAt(int toDeleteX,int toDeleteY,Piece[][] allPieces){
		Piece[][] tempPieces= new Piece[8][8];
		for(int i=0;i<8;i++){
			for(int j=0;j<8;j++){
				if(i==toDeleteX && j==toDeleteY){
					tempPieces[i][j]=new Man(i,j); 
					tempPieces[i][j].setImage(4);
				}
				else{
					tempPieces[i][j]=allPieces[i][j];
				}
			}
		}
		return tempPieces;
	}

	public static Piece[][] moveTo(int oldX,int oldY,int newX,int newY,Piece[][] allPieces){
		Piece[][] tempPieces;
		Piece[][] tempFinalPieces= new Piece[8][8];
		Piece tempPiece=allPieces[oldX][oldY];
		tempPieces=removeAt(oldX,oldY,allPieces);
		for(int i=0;i<8;i++){
			for(int j=0;j<8;j++){
				if(i==newX && j==newY){
					tempPiece.setNewPos(newX, newY);
					tempFinalPieces[i][j]=tempPiece; 
				}
				else{
					tempFinalPieces[i][j]=tempPieces[i][j];
				}
			}
		}
		return tempFinalPieces;
	}

}
