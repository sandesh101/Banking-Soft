package bakingsoft;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class Deposit {
	public static void main(String[] args) {
		new MoneyDeposit();
	}
}


class MoneyDeposit extends JFrame{
	MoneyDeposit(){
		JFrame depositFrame = new JFrame();
		JPanel depositPanel = new JPanel();
		JLabel depositText = new JLabel();
		JLabel depositAmountText = new JLabel("Deposit Amount: ");
		JLabel accNumberText = new JLabel("Account Number: ");
		JTextField depositAmount = new JTextField();
		JTextField accNumber = new JTextField();
		JButton depositBtn = new JButton("Deposit");
		JMenu menu = new JMenu("File");
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
	    	
	    	//account open button
	    	accOpen.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent e) {
					MainPage mp = new MainPage();
					depositFrame.dispose();
					mp.main(null);
					
				}
	    		
	    	});
	    	
	    	//account open button
	    
	    
	    	//deposit button
	    		deposit.addActionListener(new ActionListener() {
	    			@Override
	    			public void actionPerformed(ActionEvent e) {
	    				Deposit dep = new Deposit();
	    				depositFrame.dispose();
	    				dep.main(null);
	    			}
	    		});
	    	//deposit button
	    	
	    		//withdraw button
	    		withdraw.addActionListener(new ActionListener() {
	    			@Override
	    			public void actionPerformed(ActionEvent e) {
	    				Withdraw withdraw = new Withdraw();
	    				depositFrame.dispose();
	    				withdraw.main(null);
	    			}
	    		});
	    		//transfer button
	    		//transfer button
	    		transfer.addActionListener(new ActionListener() {
	    			@Override
	    			public void actionPerformed(ActionEvent e) {
	    				Transfer transfer = new Transfer();
	    				depositFrame.dispose();
	    				transfer.main(null);
	    			}
	    		});
	    		//transfer button
	    
	    
	    
	    
	    //exit button
	    exit.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				depositFrame.dispose();
			}
	    });
	    //exit button
	  //Menu Items buttons
		
		
		//Deposit Text
		depositText.setText("Deposit Into Your Account");
		depositText.setFont(new Font("Montserrat", Font.BOLD, 20));
		depositText.setBounds(100, 10, 500, 30);
		//Deposit Text
		
		//Text Field
		depositAmountText.setBounds(10, 50, 150, 30);
		depositAmountText.setFont(new Font("Montserrat", Font.BOLD, 13));
		accNumberText.setBounds(10, 100, 150, 30);
		accNumberText.setFont(new Font("Montserrat", Font.BOLD, 13));
		depositAmount.setBounds(150, 50, 200, 30);
		accNumber.setBounds(150, 100, 200, 30);
		//Text Field
		
		
		
		//Button
		depositBtn.setBounds(150, 150, 200, 30);
		depositBtn.setBackground(new Color(0xFFB01E68));
		depositBtn.setFont(new Font("Montserrat", Font.BOLD, 20));
		depositBtn.setForeground(Color.white);
		depositBtn.setFocusable(false);
		depositBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					if(depositAmount.getText().isEmpty() || accNumber.getText().isEmpty()) {
						JOptionPane.showMessageDialog(depositFrame, "Please fill all field!!");
					}
					else {
						Statement stmt = null;
						Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/bankingsoft", "root", "");
//						if(conn != null) {
//							System.out.print("Connection Success");
//						}
						
						stmt = conn.createStatement();
						
//						String query = "SELECT acc_number FROM `user_account` WHERE acc_number='"+accNumber+"'";
						String query = "UPDATE user_account SET acc_balance = acc_balance+'"+depositAmount.getText()+"' WHERE acc_number='"+ accNumber.getText() +"'";
						
						Integer result = stmt.executeUpdate(query);
						 if(result != null) {
							 JOptionPane.showMessageDialog(depositFrame, "Deposited Successfully!!");
						 }
//						 else {
//							 JOptionPane.showMessageDialog(depositFrame, "Error while depositing");
//						 }
					}
					
					
				}catch(Exception err) {
					System.out.print(err.toString());
				}
				
			}
		});
		//Button
		
		
		
		//Deposit Panel
		depositPanel.setBackground(new Color(0xFFF49D1A));
		depositPanel.setBounds(0, 0, 500, 500);
		depositPanel.add(depositText);
		depositPanel.add(depositAmountText);
		depositPanel.add(accNumberText);
		depositPanel.add(depositAmount);
		depositPanel.add(accNumber);
		depositPanel.add(depositBtn);
		depositPanel.setLayout(null);
		//Deposit Panel
		
		
		
		
		
		//Deposit Frame
		depositFrame.setTitle("Baking Soft | Deposit");
		depositFrame.add(depositPanel);
		depositFrame.setJMenuBar(mb);
		depositFrame.setSize(500,500);
		depositFrame.setLayout(null);
		depositFrame.setResizable(false);
		depositFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		depositFrame.setVisible(true);
		//Deposit Frame
	}
}