package bakingsoft;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;


public class Signup {
	public static void main(String[] agrs) {
		new SignupForm();
	}
	
}

class SignupForm extends JFrame{
	JFrame f = new JFrame();
	JTextField username = new JTextField(20);
	JPasswordField password = new JPasswordField(20);
	JPanel container = new JPanel();
	JButton loginBtn = new JButton();
	JButton registerBtn = new JButton();
	JLabel regText = new JLabel();
	JPanel imageContainer = new JPanel();
	ImageIcon signupImage = new ImageIcon("SignupFront.png");
	
	SignupForm(){
		
		
		
		imageContainer.setBounds(0, 0, 500, 550);
		imageContainer.add(new JLabel(signupImage));
		
		regText.setText("Create Your Account");
		regText.setBounds(100, 50, 500, 50);
		regText.setFont(new Font("Montserrat", Font.BOLD, 25));
		
		loginBtn.setText("Login");
		loginBtn.setForeground(Color.white);
		loginBtn.setBounds(190, 250, 100, 30);
		loginBtn.setFocusable(false);
		loginBtn.setFont(new Font("Montserrat", Font.BOLD, 15));
		loginBtn.setBackground(new Color(0xFFB01E68));
		loginBtn.addActionListener(new ActionListener() {
	         public void actionPerformed(ActionEvent e) {
	          f.dispose();
	        	Login lg = new Login();
	        	 lg.main(null);
	         }
	      });
//		loginBtn.setBorder(Border(10));
		
		registerBtn.setText("Register");
		registerBtn.setForeground(Color.white);
		registerBtn.setBounds(190, 210, 100, 30);
		registerBtn.setFocusable(false);
		registerBtn.setFont(new Font("Montserrat", Font.BOLD, 15));
		registerBtn.setBackground(new Color(0xFFB01E68));
		registerBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					PreparedStatement statement = null;
					Class.forName("com.mysql.cj.jdbc.Driver");
					Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/bankingsoft", "root","");
//					if(conn != null) {
//						System.out.print("Connection Success");
//					}
//					String userEmail = username.getText();
//					String userPassword = password.getText();
//					System.out.println(userEmail);
//					System.out.println(userPassword);
					if(username.getText().isEmpty() || password.getText().isEmpty()) {
						 JOptionPane.showMessageDialog(f, "Email or Password field is empty");
					}
					else {
						 String sql = "INSERT INTO user_registration(email, pass) VALUES (?,?)";
						 statement = conn.prepareStatement(sql);
						 statement.setString(1, username.getText());
						 statement.setString(2, password.getText());
						 statement.executeUpdate();
						 JOptionPane.showMessageDialog(f, "Record Inserted Successfully");
						 username.setText("");
						 password.setText("");
						 statement.close();
						 conn.close();
					}
					 
					
				}catch(Exception err) {
					System.out.print(err.toString());	
				}
				
			}
			
		});
		
		JLabel emailLabel = new JLabel("Email: ");
		emailLabel.setBounds(80,80,100,100);
		emailLabel.setFont(new Font("Montserrat", Font.BOLD, 15));
		
		
		JLabel passwordLabel = new JLabel("Password: ");
		passwordLabel.setBounds(80,140,100,100);
		passwordLabel.setFont(new Font("Montserrat", Font.BOLD, 15));
		
		container.add(regText);
		container.setBounds(500, 0, 1000, 550);
		container.setBackground(new Color(0xFFFFE15D));
		container.add(emailLabel);
		container.add(username);
		username.setBounds(190, 113, 200, 30);
		container.add(passwordLabel);
		container.add(password);
		password.setBounds(190, 170, 200, 30);
		container.add(loginBtn);
		container.add(registerBtn);
		container.setLayout(null);
		
		
		f.add(container);
		f.add(imageContainer);
		f.setTitle("SanBank | Register Account");
		f.setLayout(null);
		f.setSize(1000,550);
		f.setResizable(false);
		f.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		f.setVisible(true);
	}
}
