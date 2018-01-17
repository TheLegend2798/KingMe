 


import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

public class King extends Piece {

	public King(int a, int b) {
		super(a, b);
		isKing=true;
	}

	public void setImage(int option) {
		typeOfPiece=option;
		switch(option){
		case 1:
			try{
				setIcon(new ImageIcon("Resources/KWB.png")); 
			}catch(Exception e){
				JOptionPane.showMessageDialog(null,"Error Code 014.Please see Troubleshooting.","Error Occured", JOptionPane.ERROR_MESSAGE);
			}
			isPiece=true;
			isKing=true;
			break;
		case 2:
			try{
				setIcon(new ImageIcon("Resources/KBB.png"));
			}catch(Exception e){
				JOptionPane.showMessageDialog(null,"Error Code 015.Please see Troubleshooting.","Error Occured", JOptionPane.ERROR_MESSAGE);
			}
			isPiece=true;
			isKing=true;
			break;
		case 3:
			try{
				setIcon(new ImageIcon("Resources/whiteBg.png"));
			}catch(Exception e){
				JOptionPane.showMessageDialog(null,"Error Code 016.Please see Troubleshooting.","Error Occured", JOptionPane.ERROR_MESSAGE);
			}
			isPiece=false;
			break;
		case 4:
			try{
				setIcon(new ImageIcon("Resources/blackBg.png")); 
			}catch(Exception e){
				JOptionPane.showMessageDialog(null,"Error Code 017.Please see Troubleshooting.","Error Occured", JOptionPane.ERROR_MESSAGE);
			}
			isPiece=false;
			break;
		case 5:
			try{
				setIcon(new ImageIcon("Resources/chosenKWB.png")); 
			}catch(Exception e){
				JOptionPane.showMessageDialog(null,"Error Code 018.Please see Troubleshooting.","Error Occured", JOptionPane.ERROR_MESSAGE);
			}
			break;
		case 6:
			try{
				setIcon(new ImageIcon("Resources/chosenKBB.png"));
			}catch(Exception e){
				JOptionPane.showMessageDialog(null,"Error Code 019.Please see Troubleshooting.","Error Occured", JOptionPane.ERROR_MESSAGE);
			}
			break;
		}
	}


}
