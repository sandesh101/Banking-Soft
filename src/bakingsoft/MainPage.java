package bakingsoft;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.JTextField;

import java.lang.Math;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;   

public class MainPage {
	public static void main(String[] args) {
		new Home();
	}
}


class Home extends JFrame{
	int accNumber = 0;
	int accBalance = 0;
	Home(){
		JFrame homeFrame = new JFrame();
		JPanel mainContainer = new JPanel();
		JLabel homeText = new JLabel();
		JLabel cusNameText = new JLabel("Customer Name: ");
		JLabel cusAddressText = new JLabel("Address: ");
		JLabel cusContactText = new JLabel("Contact Number: ");
		JLabel accTypeText = new JLabel("Account Type: ");
		JLabel openingBalanceText = new JLabel("Opening Balance: ");
		JTextField cusName = new JTextField();
		JTextField cusAddress = new JTextField();
		JTextField cusContact = new JTextField(20);
		JTextField openingBalance = new JTextField();
		JSeparator lineSeperator = new JSeparator();
		String accType[]={"Saving", "Current"};        
	    JComboBox<Object> accSelect =new JComboBox<Object>(accType);    
	    JButton saveBtn = new JButton();
	    JButton genAccNumber = new JButton();
	    JPanel accNumberContainer = new JPanel();
	    JMenu menu = new JMenu("File");
//	    JMenu menuOpen = new JMenu("Open");
	    JMenuBar mb = new JMenuBar();
	    JMenuItem accOpen = new JMenuItem("Account Open");
	    JMenuItem deposit = new JMenuItem("Deposit");
	    JMenuItem withdraw = new JMenuItem("Withdraw");
	    JMenuItem transfer = new JMenuItem("Transfer");
	    JMenuItem statement = new JMenuItem("Statement");
	    JMenuItem exit = new JMenuItem("Exit");
	    
	    
	    //Menu Items
	    menu.add(accOpen);
	    menu.add(deposit);
	    menu.add(withdraw);
	    menu.add(transfer);
	    menu.add(statement);
	    menu.add(exit);
	    mb.add(menu);
	    
	  //Menu Items
	    
	    
	   //Menu Items buttons
	    	//deposit button
	    		deposit.addActionListener(new ActionListener() {
	    			@Override
	    			public void actionPerformed(ActionEvent e) {
	    				Deposit dep = new Deposit();
	    				homeFrame.dispose();
	    				dep.main(accType);
	    			}
	    		});
	    	//deposit button
	    	
	    		//withdraw button
	    		withdraw.addActionListener(new ActionListener() {
	    			@Override
	    			public void actionPerformed(ActionEvent e) {
	    				Withdraw withdraw = new Withdraw();
	    				homeFrame.dispose();
	    				withdraw.main(accType);
	    			}
	    		});
	    		//withdraw button
	    		//transfer button
	    		transfer.addActionListener(new ActionListener() {
	    			@Override
	    			public void actionPerformed(ActionEvent e) {
	    				Transfer transfer = new Transfer();
	    				homeFrame.dispose();
	    				transfer.main(null);
	    			}
	    		});
	    		//transfer button
	    		
	    		//Statement button
	    		statement.addActionListener(new ActionListener() {
	    			@Override
	    			public void actionPerformed(ActionEvent e) {
	    				StatementPage statement = new StatementPage();
	    				homeFrame.dispose();
	    				statement.main(null);
	    			}
	    		});
	    		//Statement button
	    		
	    
	    
	    //exit button
	    exit.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				homeFrame.dispose();
			}
	    });
	    //exit button
	  //Menu Items buttons
	    
		
		//Account Open Text
		homeText.setText("Account Opening Form");
		homeText.setFont(new Font("Montserrat", Font.BOLD, 20));
		homeText.setBounds(100,10,500,50);
		//Account Open Text
		
		
		//Save Button
		saveBtn.setText("Save");
		saveBtn.setFont(new Font("Montserrat", Font.BOLD, 20));
		saveBtn.setFocusable(false);
		saveBtn.setBackground(new Color(0xFFB01E68));
		saveBtn.setForeground(Color.WHITE);
		saveBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				
				try {
					PreparedStatement statement = null;
					Class.forName("com.mysql.cj.jdbc.Driver");
					Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/bankingsoft", "root","");
//					if(conn != null) {
//						System.out.print("Connection Success");
//					}
					
					if(cusName.getText().isEmpty() || cusAddress.getText().isEmpty() || cusContact.getText().isEmpty()) {
						 JOptionPane.showMessageDialog(homeFrame, "Please Fill All Data Properly");
					}
					else if(genAccNumber.isEnabled()){
							 JOptionPane.showMessageDialog(homeFrame, "Please Generate Your Account Number");
					}
					
					else {
//					long contactNumber = Integer.parseInt(cusContact.getText());
					String selectedType = (String) accSelect.getSelectedItem();
					String sql = "INSERT INTO user_account VALUES (?,?,?,?,?,?,?)";
					statement = conn.prepareStatement(sql);
					statement.setInt(1, accNumber);
					statement.setString(2, cusName.getText());
					statement.setString(3, cusContact.getText());
					statement.setString(4, openingBalance.getText());
					statement.setString(5, selectedType);
					statement.setString(6, cusAddress.getText());
					statement.setInt(7, accBalance);
					statement.executeUpdate();
					JOptionPane.showMessageDialog(homeFrame, "Record Inserted Successfully");
					 statement.close();
					 conn.close();
					}
				}catch(Exception err) {
					System.out.print(err.toString());
				}
				
			}
		});
		//Save Button
		
		
		//Account number generate button
		genAccNumber.setText("Account Number");
		genAccNumber.setFont(new Font("Montserrat", Font.BOLD, 20));
		genAccNumber.setFocusable(false);
		genAccNumber.setBackground(new Color(0xFFB01E68));
		genAccNumber.setForeground(Color.WHITE);
		genAccNumber.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				JLabel accNumberLabel = new JLabel("Your Account Number: ");
				JLabel accNumberText = new JLabel();
//				JPanel accNumberContainer = new JPanel();
				accNumberContainer.setBounds(200, 310, 200, 30);
				accNumberContainer.setBackground(new Color(0xFFFFE15D));
				long min = 100000000;  
				long max = 999999999;  
				accNumber = (int)(Math.random()*(max-min+1)+min);  
				accNumberText.setText(String.valueOf(accNumber));
				mainContainer.add(accNumberLabel).setBounds(10, 310, 190, 30);
				accNumberLabel.setFont(new Font("Montserrat", Font.BOLD, 15));
				accNumberContainer.add(accNumberText);
				accNumberText.setFont(new Font("Montserrat", Font.BOLD, 15));
//				mainContainer.remove(genAccNumber);
				if(genAccNumber.isEnabled()) {
					genAccNumber.setEnabled(false);
				}
				
			}
			
		});
		//Account number generate button
		
		
		
		//Text Fields
		cusName.setBounds(120, 60, 200, 30);
		cusNameText.setBounds(5, 60, 200, 30);
		cusNameText.setFont(new Font("Montserrat", Font.BOLD, 12));
		cusAddress.setBounds(120, 100, 200, 30);
		cusAddressText.setBounds(5, 100, 200, 30);
		cusAddressText.setFont(new Font("Montserrat", Font.BOLD, 12));
		cusContact.setBounds(120, 140, 200, 30);
		cusContactText.setBounds(5, 140, 200, 30);
		cusContactText.setFont(new Font("Montserrat", Font.BOLD, 12));
		openingBalance.setBounds(120, 190, 200, 30);
		openingBalanceText.setBounds(5, 190, 200, 30);
		openingBalanceText.setFont(new Font("Montserrat", Font.BOLD, 12));
		accTypeText.setBounds(5, 230, 200, 30);
		accSelect.setBounds(120, 230, 200, 30);
		accTypeText.setFont(new Font("Montserrat", Font.BOLD, 12));
		saveBtn.setBounds(50, 270, 100, 30);
		genAccNumber.setBounds(170, 270, 270, 30);
		//Text Fields
		
		
		
		//Main Container
		mainContainer.setBounds(0,0,500,500);
		mainContainer.setBackground(new Color(0xFFF49D1A));
		mainContainer.setLayout(null);
		mainContainer.add(homeText);
		mainContainer.add(cusName);
		mainContainer.add(cusAddress);
		mainContainer.add(cusContact);
		mainContainer.add(cusNameText);
		mainContainer.add(cusAddressText);
		mainContainer.add(cusContactText);
		mainContainer.add(accSelect);
		mainContainer.add(accTypeText);
		mainContainer.add(lineSeperator).setBounds(20, 180, 440, 10);
		lineSeperator.setBackground(new Color(0xFF022C43));
		mainContainer.add(openingBalance);
		mainContainer.add(openingBalanceText);
		mainContainer.add(saveBtn);
		mainContainer.add(genAccNumber);
		mainContainer.add(accNumberContainer);
		//Main Container
		
		
		
		
		//Main Frame
		homeFrame.setSize(500,500);
		homeFrame.setTitle("Banking Soft | Open Account");
		homeFrame.add(mainContainer);
		homeFrame.setJMenuBar(mb); 
		homeFrame.setLayout(null);
		homeFrame.setResizable(false);
		homeFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		homeFrame.setVisible(true);
		//Main Frame
	}
}