import javax.swing.ImageIcon;

public class Man extends Piece{

	public Man(int a, int b) {
		super(a, b);
	}

	public void setImage(int option){
		typeOfPiece=option;
		switch(option){
		case 1:
			setIcon(new ImageIcon("Resources/WB.png")); 
			isPiece=true;
			break;
		case 2:
			setIcon(new ImageIcon("Resources/BB.png"));
			isPiece=true;
			break;
		case 3:
			setIcon(new ImageIcon("Resources/whiteBg.png")); 
			break;
		case 4:
			setIcon(new ImageIcon("Resources/blackBg.png")); 
			break;
		case 5:
			setIcon(new ImageIcon("Resources/chosenWB.png")); 
			break;
		case 6:
			setIcon(new ImageIcon("Resources/chosenBB.png")); 
			break;
		}
	}



}
