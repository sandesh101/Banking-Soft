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

public class Withdraw {
	public static void main(String[] args) {
		new WithdrawMoney();
	}
}


class WithdrawMoney extends JFrame{
	WithdrawMoney(){
		JFrame withdrawFrame = new JFrame();
		JPanel withdrawContainer = new JPanel();
		JTextField amount = new JTextField();
		JTextField accountNumber = new JTextField();
		JLabel withdrawText = new JLabel("Withdraw Amount from your Account!!");
		JLabel amountText = new JLabel("Amount (in Rs.): ");
		JLabel accNumberText = new JLabel("Account Number: ");
		JButton withdrawBtn = new JButton("Withdraw");
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
					withdrawFrame.dispose();
					mp.main(null);
					
				}
	    		
	    	});
	    	
	    	//account open button
		    	//deposit button
		    		deposit.addActionListener(new ActionListener() {
		    			@Override
		    			public void actionPerformed(ActionEvent e) {
		    				Deposit dep = new Deposit();
		    				withdrawFrame.dispose();
		    				dep.main(null);
		    			}
		    		});
		    	//deposit button
		    	
		    		//withdraw button
		    		withdraw.addActionListener(new ActionListener() {
		    			@Override
		    			public void actionPerformed(ActionEvent e) {
		    				Withdraw withdraw = new Withdraw();
		    				withdrawFrame.dispose();
		    				withdraw.main(null);
		    			}
		    		});
		    		//withdraw button
		    		
		    		//transfer button
		    		transfer.addActionListener(new ActionListener() {
		    			@Override
		    			public void actionPerformed(ActionEvent e) {
		    				Transfer transfer = new Transfer();
		    				withdrawFrame.dispose();
		    				transfer.main(null);
		    			}
		    		});
		    		//transfer button
		    		
		    		 //exit button
		    	    exit.addActionListener(new ActionListener() {

		    			@Override
		    			public void actionPerformed(ActionEvent e) {
		    				withdrawFrame.dispose();
		    			}
		    	    });
		    	    //exit button
		    	  //Menu Items buttons

		    	   
		
		//Labels
		withdrawText.setBounds(50, 10, 500, 40);
		withdrawText.setFont(new Font("Montserrat", Font.BOLD, 20));
		amountText.setBounds(30, 50, 100, 30);
		accNumberText.setBounds(30, 90, 150, 30);
		//Labels
		
		
		//Withdraw button
		withdrawBtn.setBackground(new Color(0xFFB01E68));
		withdrawBtn.setBounds(150, 150, 150, 30);
		withdrawBtn.setForeground(Color.white);
		withdrawBtn.setFocusable(false);
		withdrawBtn.setFont(new Font("Montessrat", Font.BOLD, 16));
		withdrawBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					if(amount.getText().isEmpty() || accountNumber.getText().isEmpty()) {
						JOptionPane.showMessageDialog(withdrawFrame, "Please fill all field!!");
					}
					else {
						Statement stmt = null;
						Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/bankingsoft", "root", "");
//						if(conn != null) {
//							System.out.print("Connection Success");
//						}
						
						stmt = conn.createStatement();
						
//						String query = "SELECT acc_number FROM `user_account` WHERE acc_number='"+accNumber+"'";
						String query = "UPDATE user_account SET acc_balance = acc_balance-'"+amount.getText()+"' WHERE acc_number='"+ accountNumber.getText() +"'";
						
						Integer result = stmt.executeUpdate(query);
						 if(result != null) {
							 JOptionPane.showMessageDialog(withdrawFrame, "Withdrawn  Successfully!!", "Success", JOptionPane.INFORMATION_MESSAGE);
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
		//Withdraw button
		
		
		//Textfield
			amount.setBounds(150, 50, 150, 30);
			accountNumber.setBounds(150, 90, 150, 30);
		//Textfield
		
		
		
		//Withdraw container
		withdrawContainer.setBackground(new Color(0xFFF49D1A));
		withdrawContainer.setBounds(0, 0, 500, 500);
		withdrawContainer.add(withdrawText);
		withdrawContainer.add(amount);
		withdrawContainer.add(amountText);
		withdrawContainer.add(accNumberText);
		withdrawContainer.add(accountNumber);
		withdrawContainer.add(withdrawBtn);
		withdrawContainer.setLayout(null);
		//Withdraw container
		
		
		//Withdraw Frame
		withdrawFrame.setSize(500,500);
		withdrawFrame.setTitle("Banking Soft | Withdraw money");
		withdrawFrame.add(withdrawContainer);
		withdrawFrame.setJMenuBar(mb);
		withdrawFrame.setLayout(null);
		withdrawFrame.setVisible(true);
		//Withdraw Frame
	}
}
