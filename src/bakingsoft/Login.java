package bakingsoft;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import com.mysql.cj.protocol.Resultset;

//import javax.swing.border.Border;

public class Login {
	public static void main(String[] agrs) {
		new LoginForm();
		
	}
	
}

class LoginForm extends JFrame{
	JFrame loginForm = new JFrame();
	JTextField email = new JTextField(20);
	JPasswordField password = new JPasswordField(20);
	JPanel container = new JPanel();
	JButton loginBtn = new JButton();
	JButton registerBtn = new JButton();
	JLabel welText = new JLabel();
	JPanel imageContainer = new JPanel();
	ImageIcon loginImage = new ImageIcon("LoginFront.png");
	
	LoginForm(){
		
		
//		imageContainer.setBackground(Color.red);
		imageContainer.setBounds(0, 0, 500, 550);
		imageContainer.add(new JLabel(loginImage));
		
		
		welText.setText("WELCOME TO SANBANK");
		welText.setBounds(100, 50, 500, 50);
		welText.setFont(new Font("Montserrat", Font.BOLD, 25));
		
		loginBtn.setText("Login");
		loginBtn.setForeground(Color.white);
		loginBtn.setBounds(190, 250, 100, 30);
		loginBtn.setFocusable(false);
		loginBtn.setFont(new Font("Montserrat", Font.BOLD, 15));
		loginBtn.setBackground(new Color(0xFFB01E68));
		loginBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					Statement statement = null;
					Class.forName("com.mysql.cj.jdbc.Driver");
					Connection conn = DriverManager.getConnection("jdbc:mysql://localhost/bankingsoft", "root","");
//					if(conn != null) {
//						System.out.print("Connection Success");
//					}
					String userEmail = email.getText();
					String userPassword = password.getText();
//					System.out.println(userEmail);
//					System.out.println(userPassword);
					 statement =  conn.createStatement();
					 String sql = "SELECT * FROM `user_registration` WHERE email='"+userEmail+"' AND pass = '"+userPassword+"'";
					 ResultSet result = statement.executeQuery(sql);
					 if(result.next()) {
						 JOptionPane.showMessageDialog(loginForm, "Login Successful");
						 loginForm.dispose();
						 MainPage mp = new MainPage();
						 mp.main(null);
					 }
					 else {
						 JOptionPane.showMessageDialog(loginForm, "Login Failed");
					 }
					
				}catch(Exception err) {
					System.out.print(err.toString());	
				}
				
			}
			
		});
		
		registerBtn.setText("Register");
		registerBtn.setForeground(Color.white);
		registerBtn.setBounds(190, 300, 100, 30);
		registerBtn.setFocusable(false);
		registerBtn.setFont(new Font("Montserrat", Font.BOLD, 15));
		registerBtn.setBackground(new Color(0xFFB01E68));
		
		registerBtn.addActionListener(new ActionListener() {
	         public void actionPerformed(ActionEvent e) {
	        	 loginForm.dispose();
	        	 Signup sg = new Signup();
	        	 sg.main(null);
	         }
	      });
		
		JLabel emailLabel = new JLabel("Email: ");
		emailLabel.setBounds(80,110,100,100);
		emailLabel.setFont(new Font("Montserrat", Font.BOLD, 15));
		
		
		JLabel passwordLabel = new JLabel("Password: ");
		passwordLabel.setBounds(80,170,100,100);
		passwordLabel.setFont(new Font("Montserrat", Font.BOLD, 15));
		
		container.add(welText);
		container.setBounds(500, 0, 500, 550);
		container.setBackground(new Color(0xFFFFE15D));
		container.add(emailLabel);
		container.add(email);
		email.setBounds(190, 140, 200, 30);
		container.add(passwordLabel);
		container.add(password);
		password.setBounds(190, 205, 200, 30);
		container.add(loginBtn);
		container.add(registerBtn);
		container.setLayout(null);
		
		
		loginForm.add(container);
		loginForm.add(imageContainer);
		loginForm.setTitle("SanBank | Login into Your Account");
		loginForm.setLayout(null);
		loginForm.setSize(1000,550);
		loginForm.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		loginForm.setResizable(false);
		loginForm.setVisible(true);
	}
}
