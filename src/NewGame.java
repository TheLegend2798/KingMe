import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Label;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.nio.channels.ShutdownChannelGroupException;
import java.util.ArrayList;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.SwingUtilities;
import javax.swing.Timer;
import java.awt.Font;

public class NewGame {
	
	public static Piece[][] allPieces;
	private static int originalX,originalY,newX,newY,userColor,computerColor,currentTurn,selectedType,userID,numOfMoves;
	private static long userTimeTaken;	
	public static JTextArea allMovesArea;
	private static JPanel gamePanel,gameSubPanel,showMovesPanel;
	private static JFrame gameFrame;
	private static Hal9000 hal;
	private static boolean isKing;
	private static JLabel movesLbl,turnLbl;
	private static Stopwatch timeLbl;
	
	public NewGame(int uID,String uName){
		userColor=1;
		computerColor=2;
		userID=uID;
		originalX=-1;
		originalY=-1;
		newX=-1;
		newY=-1;
		selectedType=-1;
		
		currentTurn=1;
		allPieces = new Piece[8][8];
		setPieces();		
		gameFrame = new JFrame();
		gameFrame.getContentPane().setLayout(new BorderLayout(0, 0));
	
		Background bg = new Background();
		gameFrame.getContentPane().add(bg);
		bg.setLayout(new BoxLayout(bg, BoxLayout.LINE_AXIS));
		
		Component rigidArea = Box.createRigidArea(new Dimension(20, 20));
		bg.add(rigidArea);
		
		showMovesPanel = new JPanel();
		showMovesPanel.setOpaque(false);
		bg.add(showMovesPanel);
		showMovesPanel.setLayout(new BoxLayout(showMovesPanel, BoxLayout.PAGE_AXIS));
		
		Component verticalGlue = Box.createVerticalGlue();
		showMovesPanel.add(verticalGlue);
		
		JScrollPane scrollPane = new JScrollPane();
		showMovesPanel.add(scrollPane);
		
		allMovesArea = new JTextArea();
		allMovesArea.setRows(10);
		allMovesArea.setFont(new Font("Harlow Solid Italic", Font.PLAIN, 13));
		allMovesArea.setEditable(false);
		scrollPane.setViewportView(allMovesArea);
		
		Component verticalGlue_6 = Box.createVerticalGlue();
		showMovesPanel.add(verticalGlue_6);
		
		turnLbl = new JLabel();
		turnLbl.setIcon(new ImageIcon("Resources/myTurnS.png")); 
		showMovesPanel.add(turnLbl);
				
		Component verticalGlue_1 = Box.createVerticalGlue();
		showMovesPanel.add(verticalGlue_1);
		
		gameSubPanel = new JPanel();
		gameSubPanel.setOpaque(false);
		bg.add(gameSubPanel);
		gameSubPanel.setLayout(new BoxLayout(gameSubPanel, BoxLayout.X_AXIS));
		
		Component horizontalGlue_1 = Box.createHorizontalGlue();
		gameSubPanel.add(horizontalGlue_1);
		
		gamePanel = new JPanel();
		gamePanel.setMaximumSize(new Dimension(600, 600));
		gameSubPanel.add(gamePanel);
		gamePanel.setLayout(new GridLayout(8, 8, 0, 0));
		
		
		redrawGrid();	
		
		Component horizontalGlue = Box.createHorizontalGlue();
		gameSubPanel.add(horizontalGlue);
		
		Component horizontalGlue_3 = Box.createHorizontalGlue();
		horizontalGlue_3.setMaximumSize(new Dimension(5000, 0));
		bg.add(horizontalGlue_3);
				
		
		Component horizontalGlue_2 = Box.createHorizontalGlue();
		bg.add(horizontalGlue_2);
		
		JPanel statsPanel = new JPanel();
		bg.add(statsPanel);
		statsPanel.setOpaque(false);
		statsPanel.setLayout(new BoxLayout(statsPanel, BoxLayout.PAGE_AXIS));
		
		Component verticalGlue_4 = Box.createVerticalGlue();
		statsPanel.add(verticalGlue_4);
		
		timeLbl = new Stopwatch();
		statsPanel.add(timeLbl);
		
		Component verticalGlue_2 = Box.createVerticalGlue();
		statsPanel.add(verticalGlue_2);
		
		movesLbl = new JLabel("0");
		movesLbl.setHorizontalAlignment(JLabel.CENTER);
		statsPanel.add(movesLbl);
		
		Component verticalGlue_3 = Box.createVerticalGlue();
		statsPanel.add(verticalGlue_3);
		
		Label currentUserLbl = new Label(uName);
		currentUserLbl.setAlignment(Label.CENTER);
		statsPanel.add(currentUserLbl);
		
		Component verticalGlue_5 = Box.createVerticalGlue();
		statsPanel.add(verticalGlue_5);
		
		Component rigidArea_2 = Box.createRigidArea(new Dimension(20, 20));
		bg.add(rigidArea_2);
		if(currentTurn==computerColor){
			AIMove();
		}
		hal = new Hal9000(allPieces,computerColor,userColor);
		gameFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		gameFrame.setExtendedState(JFrame.MAXIMIZED_BOTH);
		gameFrame.setVisible(true);
	}

	private void setPieces(){
		boolean colourTwoUsedFlag=false;
		for(int i=0;i<3;i++){ 
			if(i%2==0){
				for(int j=0;j<8;j++){
					if(colourTwoUsedFlag){
						allPieces[i][j]=new Man(i,j);
						allPieces[i][j].setImage(1);
					}
					else{
						allPieces[i][j]=new Man(i,j);
						allPieces[i][j].setImage(3);
					}
					colourTwoUsedFlag=!colourTwoUsedFlag;
				}

			}
			else{
				for(int j=0;j<8;j++){
					if(colourTwoUsedFlag){
						allPieces[i][j]=new Man(i,j);
						allPieces[i][j].setImage(3);
					}
					else{
						allPieces[i][j]=new Man(i,j);
						allPieces[i][j].setImage(1);
					}

					colourTwoUsedFlag=!colourTwoUsedFlag;
				}
			}
		}

		for(int i=3;i<5;i++){	
			if(i%2==0){
				for(int j=0;j<8;j++){
					if(colourTwoUsedFlag){
						allPieces[i][j]=new Man(i,j);
						allPieces[i][j].setImage(4);
					}
					else{
						allPieces[i][j]=new Man(i,j);
						allPieces[i][j].setImage(3);
					}
					colourTwoUsedFlag=!colourTwoUsedFlag;
				}

			}
			else{
				for(int j=0;j<8;j++){
					if(colourTwoUsedFlag){
						allPieces[i][j]=new Man(i,j);
						allPieces[i][j].setImage(3);
					}
					else{
						allPieces[i][j]=new Man(i,j);
						allPieces[i][j].setImage(4);
					}
					colourTwoUsedFlag=!colourTwoUsedFlag;
				}
			}
		}

		for(int i=5;i<8;i++){ 
			if(i%2==0){
				for(int j=0;j<8;j++){
					if(colourTwoUsedFlag){
						allPieces[i][j]=new Man(i,j);
						allPieces[i][j].setImage(2);
					}
					else{
						allPieces[i][j]=new Man(i,j);
						allPieces[i][j].setImage(3);
					}

					colourTwoUsedFlag=!colourTwoUsedFlag;
				}

			}
			else{
				for(int j=0;j<8;j++){
					if(colourTwoUsedFlag){
						allPieces[i][j]=new Man(i,j);
						allPieces[i][j].setImage(3);
					}
					else{
						allPieces[i][j]=new Man(i,j);
						allPieces[i][j].setImage(2);
					}
					colourTwoUsedFlag=!colourTwoUsedFlag;
				}
			}
		}

	}

	public static void firePieceSelected(int x,int y,int type,boolean isK){
		if(currentTurn==userColor){
			if(originalX!=-1 && originalY!=-1){
				newX=x;
				newY=y;

				switch(selectedType){


				case 1:
					allPieces[originalX][originalY].setImage(1);
					break;
				case 2:
					allPieces[originalX][originalY].setImage(2);
					break;
				}
				Validation v=new Validation(originalX,originalY,newX,newY,selectedType,isKing,allPieces);
				mainclause : if(v.checkIfValid()){
					ArrayList<int[]> allMov=Hal9000.findMoveable(userColor, allPieces);
					for(int i=0;i<allMov.size();i++){
						if(!allMov.isEmpty() && allMov.get(i)[4]==1){
							if(v.getEat()){
								break;
							}
							else{
								allMovesArea.setBackground(Color.RED);
								allMovesArea.append("You must eat an opponent's piece!\n");
								break mainclause;
							}
						}
					}
					numOfMoves++;
					movesLbl.setText(""+numOfMoves);
					allMovesArea.setBackground(Color.WHITE);
					allMovesArea.append("You played "+originalX+","+originalY+" to "+newX+","+newY+"\n");
					allPieces=GameActions.moveTo(originalX, originalY, newX, newY, allPieces);
					if(v.getEat()){
						allPieces=GameActions.removeAt((int)(originalX+newX)/2, (int)(originalY+newY)/2, allPieces);
					}
					if(Validation.becomesKing(newX,selectedType,isKing)){
						allPieces[newX][newY]=new King(newX,newY);
						allPieces[newX][newY].setImage(userColor);
					}
					redrawGrid();
					if(checkIfPlayerLoses(computerColor)){
						JOptionPane.showMessageDialog(gameFrame, "YOU WIN :( ");
						gameFrame.dispose();
						timeLbl.stop();
						//PostGameMenu pgm = new PostGameMenu(userID,true,numOfMoves,userTimeTaken);
					}
					else{
						SwingUtilities.invokeLater(new Runnable() {
						    public void run() {
								AIMove();
						    }
						});
				    	redrawGrid();

					}
					changeTurnLbl(1);
					currentTurn=computerColor;
				}
				else{
					allMovesArea.setBackground(Color.RED);
					allMovesArea.append("Invalid Move!\n");
				}
				originalX=-1;
				originalY=-1;
			}
			else if(originalX==-1 && originalY==-1  && type==userColor){
				isKing=isK;
				originalX=x;
				originalY=y;
				selectedType=type;

				switch(type){
				case 1:
					allPieces[x][y].setImage(5);
					break;
				case 2:
					allPieces[x][y].setImage(6);
					break;
				}
			}
		}
	}

	private static  void redrawGrid(){
		gamePanel.removeAll();
		for(int i=0;i<8;i++){
			for(int j=0;j<8;j++){
				gamePanel.add(allPieces[i][j]);
			}
		}
		gamePanel.revalidate();
		gamePanel.repaint();
	}

	private static void changeTurnLbl(int i){
		switch(i){
		case 1:
			turnLbl.setIcon(new ImageIcon("Resources/myTurn.png"));
			break;
		case 2:
			turnLbl.setIcon(new ImageIcon("Resources/yourTurn.png")); 
			break;
		}
		showMovesPanel.revalidate();
	}
	
	private static  void AIMove(){
		hal.updateBoard(allPieces);
		allPieces=hal.getNextMove();
		if(allPieces==null){
			JOptionPane.showMessageDialog(gameFrame, "YOU WIN :( ");
			gameFrame.dispose();
			timeLbl.stop();
			//PostGameMenu pgm = new PostGameMenu(userID,true,numOfMoves,userTimeTaken);
			return;
		}
		for(int i=0;i<8;i++){
			
			System.out.println("");
			for(int j=0;j<8;j++){
				/*
				if(allPieces[i][j].getType()==userColor){
					System.out.print(userColor+" ");
				}
				else if(allPieces[i][j].getType()==computerColor){
					System.out.print(computerColor+" ");
				}
				else{
					System.out.print("0 ");
				}
				*/
				allPieces[i][j].setNewPos(i, j);
			}
			redrawGrid();
			currentTurn=userColor;
			changeTurnLbl(2);
			if(checkIfPlayerLoses(userColor)){
				JOptionPane.showMessageDialog(gameFrame, "I WIN! ");
				gameFrame.dispose();
				timeLbl.stop();
				//PostGameMenu pgm = new PostGameMenu(userID,false,numOfMoves,userTimeTaken);
				return;
			}
		}
	}

	private static boolean checkIfPlayerLoses(int player){
		int pCount=0;
		for(int i=0;i<allPieces.length;i++){
			for(int j=0;j<allPieces[i].length;j++){
				if(allPieces[i][j].getType()==player){
					pCount++;
				}
			}
		}
		if(pCount==0){
			return true;
		}
		
		ArrayList<int[]> temp = Hal9000.findMoveable(player, allPieces);
		if(temp.isEmpty()){
			return true;
		}
		return false;
	}
	
	public static void setUserTime(long i){
		userTimeTaken=i;
	}
	}

	class Stopwatch extends JLabel implements ActionListener{
	private static long i=0;
	private Timer t;
	public Stopwatch() {
	    super("00:"+"00:"+"00");
	    i=0;
	    t = new Timer(1000, this);
	    t.start();
	  }
	
	public void actionPerformed(ActionEvent arg0) {
		if(i>=59){
			i++;
			setText(i/3600+":"+i%3600/60+":"+(i%3600)%60);
		}
		else{
			setText("00:"+"00:"+i++);
		}
		NewGame.setUserTime(i);
	}

	public void stop(){
		t.stop();
	}

	}



