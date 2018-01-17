 



import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

public class Man extends Piece{

	public Man(int a, int b) {
		super(a, b);
	}

	public void setImage(int option){
		typeOfPiece=option;
		switch(option){
		case 1:
			try{
				setIcon(new ImageIcon("Resources/WB.png")); 
			}catch(Exception e){
				JOptionPane.showMessageDialog(null,"Error Code 020.Please see Troubleshooting.","Error Occured", JOptionPane.ERROR_MESSAGE);
			}
			isPiece=true;
			break;
		case 2:
			try{
				setIcon(new ImageIcon("Resources/BB.png"));
			}catch(Exception e){
				JOptionPane.showMessageDialog(null,"Error Code 021.Please see Troubleshooting.","Error Occured", JOptionPane.ERROR_MESSAGE);
			}
			isPiece=true;
			break;
		case 3:
			try{
				setIcon(new ImageIcon("Resources/whiteBg.png"));
			}catch(Exception e){
				JOptionPane.showMessageDialog(null,"Error Code 022.Please see Troubleshooting.","Error Occured", JOptionPane.ERROR_MESSAGE);
			}
			isPiece=false;
			break;
		case 4:
			try{
				setIcon(new ImageIcon("Resources/blackBg.png")); 
			}catch(Exception e){
				JOptionPane.showMessageDialog(null,"Error Code 023.Please see Troubleshooting.","Error Occured", JOptionPane.ERROR_MESSAGE);
			}
			isPiece=false;
			break;
		case 5:
			try{
				setIcon(new ImageIcon("Resources/chosenWB.png")); 
			}catch(Exception e){
				JOptionPane.showMessageDialog(null,"Error Code 024.Please see Troubleshooting.","Error Occured", JOptionPane.ERROR_MESSAGE);
			}
			break;
		case 6:
			try{
				setIcon(new ImageIcon("Resources/chosenBB.png")); 
			}catch(Exception e){
				JOptionPane.showMessageDialog(null,"Error Code 025.Please see Troubleshooting.","Error Occured", JOptionPane.ERROR_MESSAGE);
			}
			break;
		}
	}



}
