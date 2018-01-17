 


import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

public class PostGameMenu {
	private JFrame pgm = new JFrame();
	private JTextField nameTF = new JTextField();
	private JTextField surnameTF = new JTextField();
	private JTextField usernameTF = new JTextField();
	private JTextField timeTF = new JTextField();
	private JTextField movesTF = new JTextField();


	public PostGameMenu(int uID,boolean ifW,int numOfMoves,long t){

		Background subFrame = new Background();
		pgm.getContentPane().add(subFrame, BorderLayout.CENTER);
		subFrame.setLayout(new CardLayout(0, 0));

		try {
			JLabel titleLabel=new JLabel();
			File sourceimage = new File("Resources/Title.png");
			Image image = ImageIO.read(sourceimage);
			titleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
			titleLabel.setIcon(new ImageIcon(image));
		} catch (IOException e) {
			JOptionPane.showMessageDialog(null,"Error Code 002.Please see Troubleshooting.","Error Occured", JOptionPane.ERROR_MESSAGE);
		}

		Background losePanel = new Background();
		subFrame.add(losePanel, "lose");
		losePanel.setLayout(new BoxLayout(losePanel, BoxLayout.PAGE_AXIS));

		Component verticalGlue_2 = Box.createVerticalGlue();
		losePanel.add(verticalGlue_2);

		JLabel looseL = new JLabel("You Lose! Try Again?");
		looseL.setHorizontalAlignment(SwingConstants.CENTER);
		looseL.setFont(new Font("Harlow Solid Italic", Font.PLAIN, 34));
		looseL.setAlignmentX(0.5f);
		losePanel.add(looseL);

		Component verticalGlue_4 = Box.createVerticalGlue();
		losePanel.add(verticalGlue_4);

		JPanel buttonPanel = new JPanel();
		buttonPanel.setOpaque(false);
		losePanel.add(buttonPanel);
		buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.X_AXIS));

		JButton tryAgainB = new JButton();
		tryAgainB.addActionListener(arg0 -> {
			pgm.dispose();
			String name = null;
			ArrayList<String[]> UsersTable;
			try {
				UsersTable = MainMenu.readUsers();
				for(int j=0;j<UsersTable.size();j++){
					if(uID==Integer.parseInt(UsersTable.get(j)[0])){
						name=UsersTable.get(j)[1];
					}
				}
				new NewGame(uID,name);
			} catch (IOException e) {			
				JOptionPane.showMessageDialog(null,"Error Code 001.Please see Troubleshooting.","Error Occured", JOptionPane.ERROR_MESSAGE);

			}
		});

		tryAgainB.setContentAreaFilled(false);
		tryAgainB.setBorderPainted(false);
		try{
			tryAgainB.setIcon(new ImageIcon("Resources/tryAgain.png"));
		} catch (Exception e) {			
			JOptionPane.showMessageDialog(null,"Error Code 028.Please see Troubleshooting.","Error Occured", JOptionPane.ERROR_MESSAGE);

		}
		buttonPanel.add(tryAgainB);

		JButton backB = new JButton();
		backB.setContentAreaFilled(false);
		backB.setBorderPainted(false);
		try{
			backB.setIcon(new ImageIcon("Resources/mainMenu.png"));
		} catch (Exception e) {			
			JOptionPane.showMessageDialog(null,"Error Code 029.Please see Troubleshooting.","Error Occured", JOptionPane.ERROR_MESSAGE);

		}
		backB.addActionListener(arg0 -> {
			pgm.dispose();
			new MainMenu();	
		});
		buttonPanel.add(backB);

		Component verticalGlue_8 = Box.createVerticalGlue();
		losePanel.add(verticalGlue_8);

		JPanel winPanel = new JPanel();
		subFrame.add(winPanel, "win");
		winPanel.setPreferredSize(new Dimension(200, 200));
		winPanel.setOpaque(false);
		winPanel.setLayout(new GridLayout(6, 2, 20, 0));

		JLabel nameLbl = new JLabel("Name");
		nameLbl.setHorizontalAlignment(SwingConstants.CENTER);
		nameLbl.setAlignmentX(Component.CENTER_ALIGNMENT);
		nameLbl.setFont(new Font("Harlow Solid Italic", Font.PLAIN, 34));
		winPanel.add(nameLbl);

		JPanel temppanel1 = new JPanel();
		temppanel1.setOpaque(false);
		winPanel.add(temppanel1);
		temppanel1.setLayout(new BoxLayout(temppanel1, BoxLayout.Y_AXIS));

		Component verticalGlue = Box.createVerticalGlue();
		temppanel1.add(verticalGlue);
		nameTF.setEditable(false);
		nameTF.setHorizontalAlignment(SwingConstants.CENTER);
		nameTF.setColumns(20);
		nameTF.setMaximumSize(new Dimension(200, 500));
		temppanel1.add(nameTF);


		Component verticalGlue_1 = Box.createVerticalGlue();
		temppanel1.add(verticalGlue_1);

		JLabel surnameLbl = new JLabel("Surname");
		surnameLbl.setHorizontalAlignment(SwingConstants.CENTER);
		surnameLbl.setAlignmentX(Component.CENTER_ALIGNMENT);
		surnameLbl.setFont(new Font("Harlow Solid Italic", Font.PLAIN, 34));
		winPanel.add(surnameLbl);

		JPanel temppanel2 = new JPanel();
		temppanel2.setOpaque(false);
		winPanel.add(temppanel2);
		temppanel2.setLayout(new BoxLayout(temppanel2, BoxLayout.Y_AXIS));

		Component verticalGlue2 = Box.createVerticalGlue();
		temppanel2.add(verticalGlue2);

		surnameTF.setEditable(false);
		surnameTF.setHorizontalAlignment(SwingConstants.CENTER);
		surnameTF.setColumns(20);
		surnameTF.setMaximumSize(new Dimension(200, 500));
		temppanel2.add(surnameTF);


		Component verticalGlue_3 = Box.createVerticalGlue();
		temppanel2.add(verticalGlue_3);


		JLabel usernameLbl = new JLabel("Username");
		usernameLbl.setHorizontalAlignment(SwingConstants.CENTER);
		usernameLbl.setAlignmentX(Component.CENTER_ALIGNMENT);
		usernameLbl.setFont(new Font("Harlow Solid Italic", Font.PLAIN, 34));
		winPanel.add(usernameLbl);

		JPanel temppanel3 = new JPanel();
		temppanel3.setOpaque(false);
		winPanel.add(temppanel3);
		temppanel3.setLayout(new BoxLayout(temppanel3, BoxLayout.Y_AXIS));

		Component verticalGlue4 = Box.createVerticalGlue();
		temppanel3.add(verticalGlue4);

		usernameTF.setEditable(false);
		usernameTF.setHorizontalAlignment(SwingConstants.CENTER);
		usernameTF.setColumns(20);
		usernameTF.setMaximumSize(new Dimension(200, 500));
		temppanel3.add(usernameTF);


		Component verticalGlue_5 = Box.createVerticalGlue();
		temppanel3.add(verticalGlue_5);

		JLabel timeLbl = new JLabel("Time taken");
		timeLbl.setHorizontalAlignment(SwingConstants.CENTER);
		timeLbl.setAlignmentX(Component.CENTER_ALIGNMENT);
		timeLbl.setFont(new Font("Harlow Solid Italic", Font.PLAIN, 34));
		winPanel.add(timeLbl);

		JPanel temppanel4 = new JPanel();
		temppanel4.setOpaque(false);
		winPanel.add(temppanel4);
		temppanel4.setLayout(new BoxLayout(temppanel4, BoxLayout.Y_AXIS));

		Component verticalGlue5 = Box.createVerticalGlue();
		temppanel4.add(verticalGlue5);

		timeTF.setEditable(false);
		timeTF.setHorizontalAlignment(SwingConstants.CENTER);
		timeTF.setColumns(20);
		timeTF.setMaximumSize(new Dimension(200, 500));
		temppanel4.add(timeTF);


		Component verticalGlue_6 = Box.createVerticalGlue();
		temppanel4.add(verticalGlue_6);

		JLabel movesLbl = new JLabel("Moves Played");
		movesLbl.setHorizontalAlignment(SwingConstants.CENTER);
		movesLbl.setAlignmentX(Component.CENTER_ALIGNMENT);
		movesLbl.setFont(new Font("Harlow Solid Italic", Font.PLAIN, 34));
		winPanel.add(movesLbl);

		JPanel temppanel5 = new JPanel();
		temppanel5.setOpaque(false);
		winPanel.add(temppanel5);
		temppanel5.setLayout(new BoxLayout(temppanel5, BoxLayout.Y_AXIS));

		Component verticalGlue6 = Box.createVerticalGlue();
		temppanel5.add(verticalGlue6);

		movesTF.setEditable(false);
		movesTF.setHorizontalAlignment(SwingConstants.CENTER);
		movesTF.setColumns(20);
		movesTF.setMaximumSize(new Dimension(200, 500));
		temppanel5.add(movesTF);


		Component verticalGlue_7 = Box.createVerticalGlue();
		temppanel5.add(verticalGlue_7);


		JButton backB2 = new JButton();
		try{
			backB2.setIcon(new ImageIcon("Resources/mainMenuS.png"));
		} catch (Exception e) {			
			JOptionPane.showMessageDialog(null,"Error Code 030.Please see Troubleshooting.","Error Occured", JOptionPane.ERROR_MESSAGE);

		}
		backB2.addActionListener(arg0 -> {
			int choice=JOptionPane.showConfirmDialog(null, "Are you sure you don't want to save your score?");
			if(choice==JOptionPane.YES_OPTION){
				pgm.dispose();
			}
		});
		backB2.setContentAreaFilled(false);
		backB2.setBorderPainted(false);
		winPanel.add(backB2);

		JButton btnSaveScore = new JButton();
		try{
			btnSaveScore.setIcon(new ImageIcon("Resources/saveScore.png"));
		} catch (Exception e) {			
			JOptionPane.showMessageDialog(null,"Error Code 031.Please see Troubleshooting.","Error Occured", JOptionPane.ERROR_MESSAGE);

		}
		btnSaveScore.addActionListener(arg0 -> {
			FileWriter fw;
			try {
				fw = new FileWriter("Resources/highscoresT.txt",true);

				fw.append(uID+","+movesTF.getText()+","+t+","+getPoints(t,Integer.parseInt(movesTF.getText()))+"\n");
				fw.close();
			} catch (IOException e) {
				JOptionPane.showMessageDialog(null,"Error Code 010.Please see Troubleshooting.","Error Occured", JOptionPane.ERROR_MESSAGE);
			}
			pgm.dispose();
		});
		btnSaveScore.setContentAreaFilled(false);
		btnSaveScore.setBorderPainted(false);
		winPanel.add(btnSaveScore);
		if(ifW){
			((CardLayout)subFrame.getLayout()).show(subFrame, "win");
			setWinContent(uID);
			timeTF.setText(t/60+" minutes and "+t%60+" seconds.");
			movesTF.setText(Integer.toString(numOfMoves));
		}
		else{
			((CardLayout)subFrame.getLayout()).show(subFrame, "lose");
		}

		pgm.getContentPane().add(subFrame);
		pgm.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		pgm.addWindowListener(new WindowAdapter() {

			@Override
			public void windowClosed(WindowEvent e) {
				new MainMenu();
			}
		});
		pgm.setExtendedState(JFrame.MAXIMIZED_BOTH);
		pgm.setVisible(true);
	}

	public void setWinContent(int id){
		try {
			ArrayList<String[]> UsersTable =MainMenu.readUsers();
			for(int j=0;j<UsersTable.size();j++){
				if(id==Integer.parseInt(UsersTable.get(j)[0])){
					nameTF.setText(UsersTable.get(j)[1]);
					surnameTF.setText(UsersTable.get(j)[2]);
					usernameTF.setText(UsersTable.get(j)[3]);
					break;
				}
			}			
		} catch (IOException e) {
			JOptionPane.showMessageDialog(null,"Error Code 001.Please see Troubleshooting.","Error Occured", JOptionPane.ERROR_MESSAGE);
		}
	}

	private double getPoints(long time, int moves){
		double pts=(1000/((time/20)*(moves/10)));
		return pts;
	}

}
