 


import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JLabel;

public abstract class Piece extends JLabel implements MouseListener,MoveablePiece{

	private int x,y;
	protected boolean isPiece=false;
	protected int typeOfPiece;
	protected boolean isKing=false;

	public Piece(int a, int b){
		x=a;
		y=b;
		addMouseListener(this);
	}

	public boolean isPiece(){
		return isPiece;
	}

	public int getType(){
		return typeOfPiece;
	}

	public abstract void setImage(int option);

	public void setNewPos(int newX,int newY){
		x=newX;
		y=newY;
	}

	@Override
	public void mouseClicked(MouseEvent e) {
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

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		NewGame.firePieceSelected(x,y,typeOfPiece,isKing);
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub

	}

}
