 


import java.util.ArrayList;

public class Hal9000 {
	private  Piece[][] currentBoard;
	private static int myColor;
	private static int userColor;
	private  ArrayList<int[]> myMoveable;
	private  ArrayList<int[]> userMoveable;
	private Node<Piece [][]> root ;
	private Node<Piece [][]> currentPlan;

	public Hal9000(Piece[][] allPieces,int c,int c2){
		myColor=c;
		userColor=c2;
		currentBoard=allPieces;
		myMoveable=findMoveable(myColor,currentBoard);
		userMoveable=findMoveable(userColor,currentBoard);

	}

	public  Piece[][] getNextMove(){
		root=new Node(currentBoard,null,0);
		int depth=4;
		updateMyPieces(currentBoard);
		root=popul(depth,root,0,myColor,0);

		for(int i=0;i<root.getChildren().size();i++){
			if(!checkIfLose(myColor, root.getChildren().get(i).getBoard())){
				currentPlan=getBestOption(root);

				ArrayList<Piece[][]> myPlan=new ArrayList<Piece[][]>();
				while(currentPlan.getParent().getParent()!=null){
					myPlan.add(currentPlan.getBoard());
					currentPlan=currentPlan.getParent();
				}
				myPlan.add(currentPlan.getBoard());
				return myPlan.get(myPlan.size()-1);
			}
		}
		return null;
	}

	private boolean checkIfLose(int player,Piece[][] board){
		if(findMoveable(player,board).isEmpty()){
			return true;
		}
		return false;
	}

	private static  boolean[][] updateMyPieces(Piece[][] cB){
		boolean[][] myPieces=new boolean[8][8];
		for(int i=0;i<8;i++){
			for(int j=0;j<8;j++){
				if(cB[i][j].getType()==myColor){
					myPieces[i][j]=true;
				}
				else if(cB[i][j].getType()==userColor){
					myPieces[i][j]=false;
				}
				else{
					myPieces[i][j]=false;
				}
			}
		}
		return myPieces;
	}

	public static  boolean[][] updateUserPieces(Piece[][] cB){
		boolean[][] userPieces=new boolean[8][8];
		for(int i=0;i<8;i++){
			for(int j=0;j<8;j++){
				if(cB[i][j].getType()==userColor){
					userPieces[i][j]=true;
				}
				else if(cB[i][j].getType()==myColor){
					userPieces[i][j]=false;
				}
				else{
					userPieces[i][j]=false;
				}
			}
		}
		return userPieces;
	}

	public  void updateBoard(Piece[][] cB){
		currentBoard=cB;
		myMoveable=findMoveable(myColor,currentBoard);
		userMoveable=findMoveable(userColor,currentBoard);
	}

	public static  ArrayList<int[]> findMoveable(int currentUser,Piece[][] currentBoard){
		boolean[][] x;
		ArrayList<int[]> moveablePieces=new ArrayList<int[]>(1);
		int normalMove,eatMove;
		if(currentUser==userColor){
			x=updateUserPieces(currentBoard);
		}
		else{
			x=updateMyPieces(currentBoard);
		}
		if(currentUser==1){
			normalMove=1;
			eatMove=2;
		}
		else{
			normalMove=-1;
			eatMove=-2;
		}

		outerloop :for(int i=0;i<8;i++){
			for(int j=0;j<8;j++){
				if(x[i][j]){
					if(currentBoard[i][j].isKing){
						Validation v= new Validation(i,j,i-eatMove,j+2,currentUser,currentBoard[i][j].isKing,currentBoard);
						if(v.checkIfValid()){
							moveablePieces.add(new int[]{i,j,i-eatMove,j+2,1,currentUser});
							break outerloop;
						}

						v= new Validation(i,j,i-eatMove,j-2,currentUser,currentBoard[i][j].isKing,currentBoard);
						if(v.checkIfValid()){
							moveablePieces.add(new int[]{i,j,i-eatMove,j-2,1,currentUser});
							break outerloop;
						}		

						v= new Validation(i,j,i-normalMove,j+1,currentUser,currentBoard[i][j].isKing,currentBoard);
						if(v.checkIfValid()){
							moveablePieces.add(new int[]{i,j,i-normalMove,j+1,0,0,currentUser});

						}
						v= new Validation(i,j,i-normalMove,j-1,currentUser,currentBoard[i][j].isKing,currentBoard);
						if(v.checkIfValid()){

							moveablePieces.add(new int[]{i,j,i-normalMove,j-1,0,0,currentUser});

						}
					}

					Validation v= new Validation(i,j,i+eatMove,j+2,currentUser,currentBoard[i][j].isKing,currentBoard);
					if(v.checkIfValid()){
						moveablePieces.add(new int[]{i,j,i+eatMove,j+2,1,currentUser});
						break outerloop;
					}

					v= new Validation(i,j,i+eatMove,j-2,currentUser,currentBoard[i][j].isKing,currentBoard);
					if(v.checkIfValid()){
						moveablePieces.add(new int[]{i,j,i+eatMove,j-2,1,currentUser});
						break outerloop;
					}

					v= new Validation(i,j,i+normalMove,j+1,currentUser,currentBoard[i][j].isKing,currentBoard);
					if(v.checkIfValid()){
						moveablePieces.add(new int[]{i,j,i+normalMove,j+1,0,currentUser});
					}

					v= new Validation(i,j,i+normalMove,j-1,currentUser,currentBoard[i][j].isKing,currentBoard);
					if(v.checkIfValid()){
						moveablePieces.add(new int[]{i,j,i+normalMove,j-1,0,currentUser});
					}


				}
			}
		}

		boolean eatAtLeastOne=false;
		for(int i=0;i<moveablePieces.size();i++){
			if(moveablePieces.get(i)[4]==1){
				eatAtLeastOne=true;
			}
		}
		for(int i=0;i<moveablePieces.size();i++){
			if(eatAtLeastOne){
				if(moveablePieces.get(i)[4]==0){
					moveablePieces.remove(i);
					i--;
				}
			}
		}

		return moveablePieces;
	}

	private Node<Piece[][]> popul(int depth,Node<Piece[][]> rt,int generations,int color,int score){
		Node<Piece[][]> root =rt;
		boolean[][] pieces = null;
		if(generations<depth && root!=null){	
			if(color==myColor){
				pieces=updateMyPieces(root.getBoard());
			}
			else{
				pieces=updateUserPieces(root.getBoard());
			}
			ArrayList<Piece[][]> allCurrentBoards = getAllPossibleBoards(root.getBoard(), pieces,color);
			for(int i=0;i<allCurrentBoards.size();i++){
				Node tempChild=new Node(allCurrentBoards.get(i),root,getScore(allCurrentBoards.get(i), root.getBoard(),score,generations));
				if(color==1){
					root.addChild(popul(depth,tempChild,generations+1,2,tempChild.getPoints()));
				}
				else{
					root.addChild(popul(depth,tempChild,generations+1,1,tempChild.getPoints()));
				}
			}
		}
		else{
			return null;
		}
		return root;
	}

	private ArrayList<Piece[][]> getAllPossibleBoards(Piece[][] initialBoard,boolean[][] piecePositions,int player){
		ArrayList<Piece[][]> allBoards=new  ArrayList<Piece[][]>();
		ArrayList<int[]> currentMoveable=findMoveable(player, initialBoard);
		for(int i=0;i<currentMoveable.size();i++){
			Piece[][] temp;
			if(currentMoveable.get(i)[4]==1){
				temp=GameActions.removeAt((currentMoveable.get(i)[0]+currentMoveable.get(i)[2])/2, (currentMoveable.get(i)[1]+currentMoveable.get(i)[3])/2, initialBoard);
				temp=GameActions.moveTo(currentMoveable.get(i)[0], currentMoveable.get(i)[1], currentMoveable.get(i)[2], currentMoveable.get(i)[3], temp);
			}
			else{
				temp=GameActions.moveTo(currentMoveable.get(i)[0], currentMoveable.get(i)[1], currentMoveable.get(i)[2], currentMoveable.get(i)[3], initialBoard);
			}
			if(Validation.becomesKing(currentMoveable.get(i)[2], player,temp[currentMoveable.get(i)[0]][currentMoveable.get(i)[1]].isKing)){
				temp[currentMoveable.get(i)[2]][currentMoveable.get(i)[3]]=new King(currentMoveable.get(i)[2],currentMoveable.get(i)[3]);
				temp[currentMoveable.get(i)[2]][currentMoveable.get(i)[3]].setImage(player);
			}
			allBoards.add(temp);
		}
		return allBoards;
	}

	private int getScore(Piece[][]finalBoard,Piece[][]initialBoard,int sc,int gen){
		int nMPiecesBefore = 0,nUPiecesBefore=0,nMPiecesAfter=0,nUPiecesAfter=0,mKingsBefore = 0,uKingsBefore=0,uKingsAfter=0,mKingsAfter=0,score=sc,generation=gen,mPiecesLost=0,uPiecesLost=0,mKingsDiff=0,uKingsDiff=0;
		for(int i=0;i<8;i++){
			for(int j=0;j<8;j++){
				if(updateMyPieces(initialBoard)[i][j]){
					if(initialBoard[i][j].isKing){
						mKingsBefore++;
					}
					nMPiecesBefore++;
				}
				if(updateUserPieces(initialBoard)[i][j]){
					if(initialBoard[i][j].isKing){
						uKingsBefore++;
					}
					nUPiecesBefore++;
				}
				if(updateMyPieces(finalBoard)[i][j]){
					if(finalBoard[i][j].isKing){
						mKingsAfter++;
					}
					nMPiecesAfter++;
				}
				if(updateUserPieces(finalBoard)[i][j]){
					if(finalBoard[i][j].isKing){
						uKingsAfter++;
					}
					nUPiecesAfter++;
				}
			}
		}

		mKingsDiff=mKingsAfter-mKingsBefore;
		uKingsDiff=uKingsAfter-uKingsBefore;
		mPiecesLost=nMPiecesAfter-nMPiecesBefore;
		uPiecesLost=nUPiecesAfter-nUPiecesBefore;


		if(generation<=1){
			score+=30*(mPiecesLost);
			score-=25*(uPiecesLost);
			score-=45*(uKingsDiff);	
			score+=45*(mKingsDiff);
		}
		else if(generation>1 && generation <3){
			score+=15*(mPiecesLost);
			score-=25*(uPiecesLost);
			score-=35*(uKingsDiff);	
			score+=45*(mKingsDiff);
		}
		else{
			score+=5*(mPiecesLost);
			score-=10*(uPiecesLost);
			score-=20*(uKingsDiff);	
			score+=20*(mKingsDiff);
		};

		if(nUPiecesAfter==0){
			score=999;
		}
		else if(nMPiecesAfter==0){
			score=-999;
		}
		return score;
	}

	private Node<Piece[][]> getBestOption(Node<Piece[][]> rt){
		Node root = rt;
		Node tempChild = null;
		Node bestO=null;
		if(root!=null &&  !root.getChildren().isEmpty() && root.getChildren().get(0)!=null){
			for(int i=1;i<root.getChildren().size();i++){
				if(((Node<Piece[][]>) root.getChildren().get(i)).getPoints()>((Node<Piece[][]>) root.getChildren().get(i-1)).getPoints()){
					tempChild=(Node) root.getChildren().get(i);
				}
				else{
					tempChild=(Node) root.getChildren().get(i-1);
				}
			}
			if(root.getChildren().size()==1){
				tempChild=(Node) root.getChildren().get(0);
			}
			bestO=getBestOption(tempChild);
		}
		else{
			return root;
		}
		return bestO;
	}

}
