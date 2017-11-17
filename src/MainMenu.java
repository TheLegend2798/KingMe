 


import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Image;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
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
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableModel;

public class MainMenu {

	private JFrame mainMenuFrame;//The first frame holding all menu components
	private JLabel titleLabel;//Will contain the header image
	private Background bg;//Will be used to set the background image in the menu
	private JPanel changerPanel;//will be used to switch menus
	private JTable table;//Holds returning users table
	private Object[][] allUsers;//holds data about all users
	private JTextField usernameTF;//Username is entered by the user here

	public MainMenu(){
		mainMenuFrame =new JFrame("KingMe!");
		titleLabel = new JLabel();
		bg = new Background();
		changerPanel = new JPanel();
		changerPanel.setMaximumSize(new Dimension(600, 600));
		changerPanel.setOpaque(false);
		changerPanel.setLayout(new CardLayout(0, 20));

		try {
			ArrayList<String[]> UsersTable =readUsers();
			allUsers = new Object[UsersTable.size()][4];
			for(int a=0;a<allUsers.length;a++){
				for(int s=0;s<4;s++){
					allUsers[a][s]=UsersTable.get(a)[s];
				}
			}
		} catch (IOException e1) {
			JOptionPane.showMessageDialog(null,"Error Code 001.Please see Troubleshooting.","Error Occured", JOptionPane.ERROR_MESSAGE);

		}

		try {
			File sourceimage = new File("Resources/Title.png");
			Image image = ImageIO.read(sourceimage);
			titleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
			titleLabel.setIcon(new ImageIcon(image));
		} catch (IOException e) {
			JOptionPane.showMessageDialog(null,"Error Code 002.Please see Troubleshooting.","Error Occured", JOptionPane.ERROR_MESSAGE);
		}

		JButton backButton = new JButton();
		backButton.setAlignmentX(Component.CENTER_ALIGNMENT);
		backButton.setContentAreaFilled(false);
		backButton.setBorderPainted(false);
		try {
			backButton.setIcon(new ImageIcon("Resources/back.png"));
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null,"Error Code 005.Please see Troubleshooting.","Error Occured", JOptionPane.ERROR_MESSAGE);
		}
		backButton.addActionListener(arg0 -> ((CardLayout) changerPanel.getLayout()).show(changerPanel, "mainMenu"));

		setMainMenu();
		setSecondaryMenu();
		setNewUserMenu();
		try {
			setReturnMenu();
		} catch (IOException e) {
			JOptionPane.showMessageDialog(null,"Error Code 001.Please see Troubleshooting.","Error Occured", JOptionPane.ERROR_MESSAGE);
		}

		bg.setLayout(new BoxLayout(bg, BoxLayout.PAGE_AXIS));
		bg.add(titleLabel);
		Component rigidArea = Box.createRigidArea(new Dimension(20, 20));
		bg.add(rigidArea);
		bg.add(changerPanel);

		JPanel rulesPanel = new JPanel();
		rulesPanel.setOpaque(false);
		changerPanel.add(rulesPanel, "rules");
		rulesPanel.setLayout(new BoxLayout(rulesPanel, BoxLayout.X_AXIS));

		Component horizontalGlue = Box.createHorizontalGlue();
		rulesPanel.add(horizontalGlue);

		JLabel rulesOneLbl = new JLabel();
		try {
			rulesOneLbl.setIcon(new ImageIcon("Resources/rules1.png"));
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null,"Error Code 003.Please see Troubleshooting.","Error Occured", JOptionPane.ERROR_MESSAGE);
		}
		rulesPanel.add(rulesOneLbl);

		Component horizontalStrut = Box.createHorizontalStrut(20);
		rulesPanel.add(horizontalStrut);

		JLabel rulesTwoLbl = new JLabel();
		try {
			rulesTwoLbl.setIcon(new ImageIcon("Resources/rules2.png"));
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null,"Error Code 004.Please see Troubleshooting.","Error Occured", JOptionPane.ERROR_MESSAGE);
		}
		rulesPanel.add(rulesTwoLbl);

		Component horizontalGlue_1 = Box.createHorizontalGlue();
		rulesPanel.add(horizontalGlue_1);

		JPanel aboutPanel = new JPanel();
		aboutPanel.setOpaque(false);
		changerPanel.add(aboutPanel, "about");
		aboutPanel.setLayout(new BoxLayout(aboutPanel, BoxLayout.PAGE_AXIS));

		Component verticalGlue_2 = Box.createVerticalGlue();
		aboutPanel.add(verticalGlue_2);

		JTextArea textArea = new JTextArea();
		textArea.setOpaque(false);
		textArea.setFont(new Font("Harlow Solid Italic", Font.PLAIN, 25));
		textArea.setText("King Me! is a Java based game featuring \nartificial intelligence to play against the computer.\n"
				+ "Compiled using Java 8 and Java SE-1.8.\n"
				+ "Programmed using BlueJ version 3.1.6.\n"
				+ "Made By Enrico Zammit Lonardelli for \nMATSEC  Computing A-Level May 2016.");
		textArea.setEditable(false);
		aboutPanel.add(textArea);

		Component verticalGlue_3 = Box.createVerticalGlue();
		aboutPanel.add(verticalGlue_3);




		Component verticalGlue = Box.createVerticalGlue();
		bg.add(verticalGlue);
		bg.add(backButton);
		Component verticalGlue_1 = Box.createVerticalGlue();
		bg.add(verticalGlue_1);

		mainMenuFrame.getContentPane().add(bg);
		mainMenuFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		mainMenuFrame.setExtendedState(JFrame.MAXIMIZED_BOTH);
		mainMenuFrame.setVisible(true);
	}

	private void setHighscores() throws IOException{
		JPanel highscoresPanel = new JPanel();
		changerPanel.add(highscoresPanel, "highscore");
		highscoresPanel.setLayout(new BorderLayout(0, 0));

		JTable highscoreTable = new JTable();
		JScrollPane hsP = new JScrollPane();
		hsP.setViewportView(highscoreTable);

		ArrayList<String[]> dataScores =readHighScores();
		Object[][] dataT = new Object[dataScores.size()][6];
		int i=0;
		ArrayList<String[]> userList =readUsers();
		while(i<dataScores.size()){
			for(int j=0;j<userList.size();j++){
				if(Integer.parseInt(dataScores.get(i)[0])==Integer.parseInt(userList.get(j)[0])){
					dataT[i][0]=userList.get(j)[1];
					dataT[i][1]=userList.get(j)[2];
					dataT[i][2]=userList.get(j)[3];
					break;
				}
			}
			dataT[i][3]=dataScores.get(i)[1];
			dataT[i][4]=(Integer.parseInt(dataScores.get(i)[2]))/60+" m "+(Integer.parseInt(dataScores.get(i)[2]))%60+" s.";
			dataT[i][5]=dataScores.get(i)[3];
			i++;
		}
		dataT=sortArray(dataT);
		highscoreTable.setModel(new DefaultTableModel(
				dataT,
				new String[] {"Name", "Surname","Username","Moves","Time Taken","Points"}
				));		
		highscoresPanel.add(hsP, BorderLayout.CENTER);
		highscoreTable.setEnabled(false);

	}

	private Object[][] sortArray(Object[][] ar){
		int n = ar.length;

		for(int i=0; i < n; i++){
			for(int j=1; j < n-i; j++){
				Object temp[] = new Object[6];
				if(Double.parseDouble((String) ar[j-1][5]) < Double.parseDouble((String) ar[j][5])){
					for(int k=0;k<6;k++){
						temp[k] = ar[j-1][k];
					}
					for(int k=0;k<6;k++){
						ar[j-1][k] = ar[j][k];
					}
					for(int k=0;k<6;k++){
						ar[j][k] = temp[k];
					}
				}

			}
		}
		return ar;
	}

	private void setMainMenu(){

		JPanel buttonMMPanel = new JPanel();
		buttonMMPanel.setOpaque(false);
		buttonMMPanel.setLayout(new GridLayout(2, 5, 40, 40));
		changerPanel.add(buttonMMPanel, "mainMenu");

		Component horizontalGlue = Box.createHorizontalGlue();
		buttonMMPanel.add(horizontalGlue);

		JButton btnNewGame = new JButton();
		btnNewGame.addActionListener(e -> ((CardLayout) changerPanel.getLayout()).next(changerPanel));
		btnNewGame.setContentAreaFilled(false);
		btnNewGame.setBorderPainted(false);
		try{
			btnNewGame.setIcon(new ImageIcon("Resources/newgame.png"));
		}catch(Exception e){
			JOptionPane.showMessageDialog(null,"Error Code 006.Please see Troubleshooting.","Error Occured", JOptionPane.ERROR_MESSAGE);
		}
		buttonMMPanel.add(btnNewGame);

		JButton btnRules = new JButton();
		btnRules.addActionListener(e -> ((CardLayout) changerPanel.getLayout()).show(changerPanel, "rules"));
		btnRules.setContentAreaFilled(false);
		btnRules.setBorderPainted(false);
		try{
			btnRules.setIcon(new ImageIcon("Resources/rules.png"));
		}catch(Exception e){
			JOptionPane.showMessageDialog(null,"Error Code 007.Please see Troubleshooting.","Error Occured", JOptionPane.ERROR_MESSAGE);
		}
		buttonMMPanel.add(btnRules);

		Component horizontalGlue_2 = Box.createHorizontalGlue();
		buttonMMPanel.add(horizontalGlue_2);

		Component horizontalGlue_1 = Box.createHorizontalGlue();
		buttonMMPanel.add(horizontalGlue_1);

		JButton btnAbout = new JButton();
		btnAbout.setContentAreaFilled(false);
		btnAbout.setBorderPainted(false);
		try{
			btnAbout.setIcon(new ImageIcon("Resources/about.png"));
		}catch(Exception e){
			JOptionPane.showMessageDialog(null,"Error Code 008.Please see Troubleshooting.","Error Occured", JOptionPane.ERROR_MESSAGE);
		}
		btnAbout.addActionListener(e -> ((CardLayout) changerPanel.getLayout()).show(changerPanel, "about"));
		buttonMMPanel.add(btnAbout);

		JButton btnHighScores = new JButton();
		btnHighScores.setContentAreaFilled(false);
		btnHighScores.setBorderPainted(false);
		try{
			btnHighScores.setIcon(new ImageIcon("Resources/highscores.png"));
		}catch(Exception e){
			JOptionPane.showMessageDialog(null,"Error Code 009.Please see Troubleshooting.","Error Occured", JOptionPane.ERROR_MESSAGE);
		}
		btnHighScores.addActionListener(e -> {
			try {
				setHighscores();
			} catch (IOException a) {
				JOptionPane.showMessageDialog(null,"Error Code 010.Please see Troubleshooting.","Error Occured", JOptionPane.ERROR_MESSAGE);

			}
			((CardLayout) changerPanel.getLayout()).show(changerPanel, "highscore");
		});
		buttonMMPanel.add(btnHighScores);

		Component horizontalGlue_3 = Box.createHorizontalGlue();
		buttonMMPanel.add(horizontalGlue_3);
	}

	private void setSecondaryMenu(){
		JPanel buttonSMPanel = new JPanel();
		buttonSMPanel.setOpaque(false);
		buttonSMPanel.setPreferredSize(new Dimension(200, 300));
		buttonSMPanel.setLayout(new BoxLayout(buttonSMPanel, BoxLayout.X_AXIS));
		changerPanel.add(buttonSMPanel, "secondaryMenu");

		Component horizontalGlue_4 = Box.createHorizontalGlue();
		buttonSMPanel.add(horizontalGlue_4);

		JButton btnNewPlayer = new JButton();
		btnNewPlayer.addActionListener(e -> ((CardLayout) changerPanel.getLayout()).next(changerPanel));
		btnNewPlayer.setContentAreaFilled(false);
		btnNewPlayer.setBorderPainted(false);
		try{
			btnNewPlayer.setIcon(new ImageIcon("Resources/newplayer.png"));
		}catch(Exception e){
			JOptionPane.showMessageDialog(null,"Error Code 011.Please see Troubleshooting.","Error Occured", JOptionPane.ERROR_MESSAGE);
		}
		buttonSMPanel.add(btnNewPlayer);

		JButton btnReturningPlayer = new JButton();
		btnReturningPlayer.addActionListener(e -> {
			((CardLayout) changerPanel.getLayout()).show(changerPanel,"ReturningPlayer");
			try {
				updateTable();
			} catch (IOException e1) {
				JOptionPane.showMessageDialog(null,"Error Code 001.Please see Troubleshooting.","Error Occured", JOptionPane.ERROR_MESSAGE);
			}
		});
		btnReturningPlayer.setContentAreaFilled(false);
		btnReturningPlayer.setBorderPainted(false);
		try{
			btnReturningPlayer.setIcon(new ImageIcon("Resources/returningplayer.png"));
		}catch(Exception e){
			JOptionPane.showMessageDialog(null,"Error Code 012.Please see Troubleshooting.","Error Occured", JOptionPane.ERROR_MESSAGE);
		}
		buttonSMPanel.add(btnReturningPlayer);

		Component horizontalGlue_5 = Box.createHorizontalGlue();
		buttonSMPanel.add(horizontalGlue_5);
	}

	private void setNewUserMenu() {

		JPanel newUserPanel = new JPanel();
		newUserPanel.setPreferredSize(new Dimension(200, 200));
		newUserPanel.setOpaque(false);
		newUserPanel.setLayout(new GridLayout(4, 2, 50, 20));
		changerPanel.add(newUserPanel, "NewUser");

		JLabel nameLbl = new JLabel("Name");
		nameLbl.setHorizontalAlignment(SwingConstants.CENTER);
		nameLbl.setAlignmentX(Component.CENTER_ALIGNMENT);
		nameLbl.setFont(new Font("Harlow Solid Italic", Font.PLAIN, 34));
		newUserPanel.add(nameLbl);

		JPanel temppanel1 = new JPanel();
		temppanel1.setOpaque(false);
		newUserPanel.add(temppanel1);
		temppanel1.setLayout(new BoxLayout(temppanel1, BoxLayout.Y_AXIS));

		Component verticalGlue = Box.createVerticalGlue();
		temppanel1.add(verticalGlue);

		JTextField nameTF = new JTextField();
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
		newUserPanel.add(surnameLbl);

		JPanel temppanel2 = new JPanel();
		temppanel2.setOpaque(false);
		newUserPanel.add(temppanel2);
		temppanel2.setLayout(new BoxLayout(temppanel2, BoxLayout.Y_AXIS));

		Component verticalGlue2 = Box.createVerticalGlue();
		temppanel2.add(verticalGlue2);

		JTextField surnameTF = new JTextField();
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
		newUserPanel.add(usernameLbl);

		JPanel temppanel3 = new JPanel();
		temppanel3.setOpaque(false);
		newUserPanel.add(temppanel3);
		temppanel3.setLayout(new BoxLayout(temppanel3, BoxLayout.Y_AXIS));

		Component verticalGlue4 = Box.createVerticalGlue();
		temppanel3.add(verticalGlue4);

		usernameTF = new JTextField();
		usernameTF.setHorizontalAlignment(SwingConstants.CENTER);
		usernameTF.setColumns(20);
		usernameTF.setMaximumSize(new Dimension(200, 500));
		temppanel3.add(usernameTF);


		Component verticalGlue_5 = Box.createVerticalGlue();
		temppanel3.add(verticalGlue_5);

		Component rigidArea_2 = Box.createRigidArea(new Dimension(20, 20));
		newUserPanel.add(rigidArea_2);

		JButton saveBtn = new JButton();
		saveBtn.addActionListener(arg0 -> {
			if(!(nameTF.getText().isEmpty() || surnameTF.getText().isEmpty() || usernameTF.getText().isEmpty())){
				try {
					saveUser(Integer.toString(allUsers.length),nameTF.getText(),surnameTF.getText(),usernameTF.getText());
				} catch (IOException e) {
					JOptionPane.showMessageDialog(null,"Error Code 001.Please see Troubleshooting.","Error Occured", JOptionPane.ERROR_MESSAGE);
				}
				JOptionPane.showMessageDialog(null, "User saved succesfully. Good Luck!");
				mainMenuFrame.dispose();
				new NewGame(allUsers.length,usernameTF.getText());
			}
			else{
				JOptionPane.showMessageDialog(null, "Please fill in all fields");
			}
		});
		saveBtn.setContentAreaFilled(false);
		saveBtn.setBorderPainted(false);
		try{
			saveBtn.setIcon(new ImageIcon("Resources/save.png"));
		}catch(Exception e){
			JOptionPane.showMessageDialog(null,"Error Code 013.Please see Troubleshooting.","Error Occured", JOptionPane.ERROR_MESSAGE);
		}
		newUserPanel.add(saveBtn);		
	}

	private void setReturnMenu() throws IOException{
		JPanel returningPlayerPanel = new JPanel();
		returningPlayerPanel.setOpaque(false);
		changerPanel.add(returningPlayerPanel, "ReturningPlayer");

		JScrollPane scrollPane = new JScrollPane();
		returningPlayerPanel.add(scrollPane);
		ArrayList<String[]> UsersTable =readUsers();
		allUsers = new Object[UsersTable.size()][4];
		table = new JTable();
		table.setModel(new DefaultTableModel(
				allUsers,
				new String[] {"ID","Name", "Surname","Username"}
				));
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		table.getSelectionModel().addListSelectionListener(event -> {
			if (table.getSelectedRow() > -1) {
				int option=JOptionPane.showConfirmDialog(null,"Do you want to play as "+(String) table.getValueAt(table.getSelectedRow(),3));
				if(option==JOptionPane.YES_OPTION){
					mainMenuFrame.dispose();
					new NewGame(Integer.parseInt((String) table.getValueAt(table.getSelectedRow(),0)),(String) table.getValueAt(table.getSelectedRow(),3));
				}
			}
		});
		scrollPane.setViewportView(table);
		scrollPane.setOpaque(false);
	}

	private void saveUser(String id,String name,String surname,String uname) throws IOException{
		FileWriter fw = new FileWriter("Resources/Users.txt",true);
		fw.append(id+","+name+","+surname+","+uname+"\n");
		fw.close();
	}

	public static ArrayList<String[]> readUsers() throws IOException{
		ArrayList<String[]> UsersTable = new ArrayList<String[]>(1);
		BufferedReader in = new BufferedReader(new FileReader("Resources/Users.txt"));
		String original= in.readLine();
		int i=0;
		try{
			while(original!=null){
				String[] user = new String[4];
				String temp=original.substring(0, original.indexOf(','));
				int commaPos=original.indexOf(',');
				user[0]=temp;
				original=original.substring(commaPos+1);
				temp=original.substring(0, original.indexOf(','));
				commaPos=original.indexOf(',');
				user[1]=temp;
				original=original.substring(commaPos+1);
				temp=original.substring(0, original.indexOf(','));
				commaPos=original.indexOf(',');
				user[2]=temp;
				original=original.substring(commaPos+1);
				user[3]=original;
				UsersTable.add(i,user);
				i++;
				original=in.readLine();
			}
		}catch(Exception e){}
		in.close();
		return UsersTable;
	}

	private static ArrayList<String[]> readHighScores() throws IOException{
		ArrayList<String[]> dataTable = new ArrayList<String[]>(1);
		BufferedReader in = new BufferedReader(new FileReader("Resources/highscoresT.txt"));
		String original= in.readLine();
		int i=0;
		try{
			while(original!=null){
				dataTable.add(i,new String[4]);
				String temp=original.substring(0, original.indexOf(','));
				int commaPos=original.indexOf(',');
				dataTable.get(i)[0]=temp;
				original=original.substring(commaPos+1);
				temp=original.substring(0, original.indexOf(','));
				commaPos=original.indexOf(',');
				dataTable.get(i)[1]=temp;
				original=original.substring(commaPos+1);
				temp=original.substring(0, original.indexOf(','));
				commaPos=original.indexOf(',');
				dataTable.get(i)[2]=temp;
				original=original.substring(commaPos+1);
				dataTable.get(i)[3]=original;
				i++;
				original=in.readLine();
			}
		}catch(Exception e){}
		in.close();
		return dataTable;
	}

	private void updateTable() throws IOException{
		ArrayList<String[]> UsersTable =readUsers();
		allUsers = new Object[UsersTable.size()][4];
		for(int a=0;a<allUsers.length;a++){
			for(int s=0;s<4;s++){
				allUsers[a][s]=UsersTable.get(a)[s];
			}
		}
		((DefaultTableModel) table.getModel()).setDataVector(allUsers,new String[] {"ID","Name", "Surname","Username"});
		table.updateUI();
	}

	public static void main(String args[]){
		new MainMenu();
	}

}

