package bakingsoft;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
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

public class Transfer {
	public static void main(String[] args) {
		new TransferMoney();
	}
}


class TransferMoney extends JFrame{
	TransferMoney(){
		JFrame transferFrame = new JFrame();
		JPanel transferContainer = new JPanel();
		JTextField amount = new JTextField();
		JTextField accountNumber = new JTextField();
		JTextField toAccountNumber = new JTextField();
		JLabel transferText = new JLabel("Transfer Amount from your Account!!");
		JLabel amountText = new JLabel("Amount (in Rs.): ");
		JLabel fromAccNumberText = new JLabel("From (Account Number): ");
		JLabel toAccNumberText = new JLabel("To (Account Number): ");
		JButton transferBtn = new JButton("Transfer");
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
					transferFrame.dispose();
					mp.main(null);
					
				}
	    		
	    	});
	    	
	    	//account open button
		    	//deposit button
		    		deposit.addActionListener(new ActionListener() {
		    			@Override
		    			public void actionPerformed(ActionEvent e) {
		    				Deposit dep = new Deposit();
		    				transferFrame.dispose();
		    				dep.main(null);
		    			}
		    		});
		    	//deposit button
		    	
		    		//withdraw button
		    		withdraw.addActionListener(new ActionListener() {
		    			@Override
		    			public void actionPerformed(ActionEvent e) {
		    				Withdraw withdraw = new Withdraw();
		    				transferFrame.dispose();
		    				withdraw.main(null);
		    			}
		    		});
		    		//withdraw button
		    		
		    		 //exit button
		    	    exit.addActionListener(new ActionListener() {

		    			@Override
		    			public void actionPerformed(ActionEvent e) {
		    				transferFrame.dispose();
		    			}
		    	    });
		    	    //exit button
		    	  //Menu Items buttons

		
		
		//Labels
		transferText.setBounds(50, 10, 500, 40);
		transferText.setFont(new Font("Montserrat", Font.BOLD, 20));
		amountText.setBounds(30, 50, 100, 30);
		fromAccNumberText.setBounds(30, 90, 150, 30);
		toAccNumberText.setBounds(30, 130, 150, 30);
		//Labels
		
		
		//transfer button
		transferBtn.setBackground(new Color(0xFFB01E68));
		transferBtn.setBounds(190, 170, 170, 30);
		transferBtn.setForeground(Color.white);
		transferBtn.setFocusable(false);
		transferBtn.setFont(new Font("Montessrat", Font.BOLD, 16));
		transferBtn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				String amountInput = amount.getText();
				try {
					if(amount.getText().isEmpty() || toAccountNumber.getText().isEmpty()) {
						JOptionPane.showMessageDialog(transferFrame, "Please fill all field!!");
					}
					else {
						Statement stmt = null;
						Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/bankingsoft", "root", "");
//						if(conn != null) {
//							System.out.print("Connection Success");
//						}
						
						stmt = conn.createStatement();
						
//						String query = "SELECT acc_number FROM `user_account` WHERE acc_number='"+accNumber+"'";
						String query = "UPDATE user_account SET acc_balance = acc_balance+'"+amount.getText()+"' WHERE acc_number='"+ toAccountNumber.getText() +"'";
						String decQuery = "UPDATE user_account SET acc_balance = acc_balance-'"+amount.getText()+"' WHERE acc_number='"+ accountNumber.getText() +"'";
						Integer result = stmt.executeUpdate(query);
						Integer resultDec = stmt.executeUpdate(decQuery);
						 if(result != null) {
							 JOptionPane.showMessageDialog(transferFrame, "Transferred Successfully!!");
						 }
//						 else {
//							 JOptionPane.showMessageDialog(depositFrame, "Error while depositing");
//						 }
					}
					
					
				}catch(Exception err) {
					System.out.print(err.toString());
				}
//				JOptionPane.showMessageDialog(transferFrame, "Successfully Transferred "+ amountInput +" !!!");
			}
		});
		//transfer button
		
		
		//Textfield
			amount.setBounds(190, 50, 170, 30);
			accountNumber.setBounds(190, 90, 170, 30);
			toAccountNumber.setBounds(190, 130, 170, 30);
		//Textfield
		
		
		
		//transfer container
		transferContainer.setBackground(new Color(0xFFF49D1A));
		transferContainer.setBounds(0, 0, 500, 500);
		transferContainer.add(transferText);
		transferContainer.add(amount);
		transferContainer.add(amountText);
		transferContainer.add(fromAccNumberText);
		transferContainer.add(toAccNumberText);
		transferContainer.add(accountNumber);
		transferContainer.add(toAccountNumber);
		transferContainer.add(transferBtn);
		transferContainer.setLayout(null);
		//transfer container
		
		
		//transfer Frame
		transferFrame.setSize(500,500);
		transferFrame.setTitle("Banking Soft | Transfer money");
		transferFrame.add(transferContainer);
		transferFrame.setJMenuBar(mb);
		transferFrame.setLayout(null);
		transferFrame.setVisible(true);
		//transfer Frame
	}
}
