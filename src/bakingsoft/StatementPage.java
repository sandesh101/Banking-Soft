package bakingsoft;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
//import java.sql.Statement;
import java.sql.ResultSetMetaData;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class StatementPage {

	public static void main(String[] args) {
		new Statement();
	}

}


class Statement extends JFrame{
	boolean isBtnClicked = false;
	 Statement() {
		JFrame sFrame = new JFrame();
		JPanel sPanel = new JPanel();
		JTextField accNumber = new JTextField();
		JLabel accText = new JLabel();
		JButton sButton = new JButton();
		JLabel statementText = new JLabel();
		
		
		//Label
		accText.setText("Your Account Number: ");
		accText.setFont(new Font("Montsserat",Font.BOLD, 16));
		accText.setBounds(10, 10, 200, 50);
		//Label
		
		
		//Text Field
		accNumber.setBounds(210,20,200,30);
		//Text Filed
		
		
		
		//Button
		sButton.setText("Statement");
		sButton.setBounds(210, 80, 150, 30);
		sButton.setFocusable(false);
		sButton.setBackground(new Color(0xFFB01E68));
		sButton.setFont(new Font("Montserrat", Font.BOLD, 14));
		sButton.setForeground(Color.white);
		sButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					
					if(accNumber.getText().isEmpty()) {
						JOptionPane.showMessageDialog(sFrame, "Please enter your account number", "Error", JOptionPane.ERROR_MESSAGE);
					}
					else {
					java.sql.Statement st = null;
					Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/bankingsoft", "root", "");
					st = conn.createStatement();
					
					isBtnClicked = true;
					int balance = 0;
					
					String query = "SELECT `acc_balance` FROM `user_account` WHERE acc_number='"+accNumber.getText()+"'";

					ResultSet result = ((java.sql.Statement) st).executeQuery(query);
					if(result.next()) {
						//statementText
						String accBalance = result.getString("acc_balance");
						balance = Integer.parseInt(accBalance);
						if(isBtnClicked) {
						JOptionPane.showMessageDialog(sFrame, "Your balance is: Rs."+ balance, "Statement", JOptionPane.INFORMATION_MESSAGE,new ImageIcon("Moneys.png"));
						accNumber.setText("");
						}
						//statementText	
					}
					
					}
				}
				catch(Exception err) {
					System.out.print(err);
				}
				
				
			}
		});
		//Button
		
		
		
		//Panel
		sPanel.setLayout(null);
		sPanel.add(accText);
		sPanel.add(accNumber);
		sPanel.add(sButton);
		sPanel.add(statementText);
		sPanel.setBounds(0, 0, 500, 500);
		sPanel.setBackground(new Color(0xFFF49D1A));
		//Panel
		
		//Frame
		sFrame.setTitle("Banking Soft | Statement");
		sFrame.setSize(500,500);
		sFrame.add(sPanel);
		sFrame.setLayout(null);
		sFrame.setVisible(true);
		//Frame
	}
}
