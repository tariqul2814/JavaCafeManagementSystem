import java.lang.*;
import java.sql.*;
import java.util.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Properties;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class AdminPass extends JFrame implements ActionListener
{
	private JPanel panel;
	private JButton Ok,Exit,Forgetpass;
	private String adminpass;
	private JLabel label,label1;
	private JPasswordField pass;
	
	public AdminPass()
	{
		super("Admin Account");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setSize(500, 500);
		
		panel = new JPanel();
		panel.setLayout(null);
		
		label1 = new JLabel("ADMIN");
		label1.setBounds(200,30,200,200);
		label1.setFont(new Font("Consolas",Font.BOLD,40));
		panel.add(label1);
		
		label = new JLabel("Password: ");
		label.setBounds(60,170,100,30);
		label.setFont(new Font("Consolas",Font.ITALIC+Font.BOLD,15));
		panel.add(label);
		
		pass = new JPasswordField();
		pass.setBounds(160, 170, 200, 30);
		pass.setEchoChar('*');
		panel.add(pass);
		
		Ok = new JButton("OK");
		Ok.setBounds(50,240,70,40);
		Ok.addActionListener(this);
		panel.add(Ok);
		
		Forgetpass = new JButton("Forget Password");
		Forgetpass.setBounds(140,240,135,40);
		Forgetpass.addActionListener(this);
		panel.add(Forgetpass);
		
		Exit = new JButton("Cancel");
		Exit.setBounds(290,240,100,40);
		Exit.addActionListener(this);
		panel.add(Exit);
		
		this.add(panel);
		
	}
	public void actionPerformed(ActionEvent e)
	{
		if(e.getSource()==Ok)
		{
			String m="",n="";
			try{
				Class.forName("oracle.jdbc.driver.OracleDriver");
				Connection con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","promi","1234");
				Statement stmt=con.createStatement();
				String fn = new String(pass.getPassword());
				ResultSet rs=stmt.executeQuery("select pass from password where id = 'admin'");
		
				while(rs.next()) 
				{
					m=rs.getString("pass");
				}				
				
				if(fn.equals(m))
				{
					JOptionPane.showMessageDialog(null,"Successfully Login","Status",JOptionPane.PLAIN_MESSAGE);
					Adminlogin l = new Adminlogin();
					l.setVisible(true);
				}
				
				else
				{
					JOptionPane.showMessageDialog(null,"Invalid UserName or Password: Check It","Status",JOptionPane.PLAIN_MESSAGE);
				}
			
				con.close();
			}catch(Exception p){ System.out.println(p);} 
			
		}
		else if(e.getSource()==Forgetpass)
		{
			Random dice = new Random();
			String k="";
			final String username = "promilubnamunia@gmail.com"; // enter your mail id
			final String password = "01683105317";// enter ur password

			Properties props = new Properties();
			props.put("mail.smtp.auth", "true");
			props.put("mail.smtp.starttls.enable", "true");
			props.put("mail.smtp.host", "smtp.gmail.com");
			props.put("mail.smtp.port", "587");

			Session session = Session.getInstance(props,
			new javax.mail.Authenticator() {
				protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(username, password);
			}
		  });

			try {

				Message message = new MimeMessage(session);
				message.setFrom(new InternetAddress("tariqul2814@gmail.com")); // same email id
				message.setRecipients(Message.RecipientType.TO,
				InternetAddress.parse("tariqul2814@gmail.com"));// whome u have to send mails that person id
				message.setSubject("Reset Password");
				int ran = 20000+dice.nextInt(50000);
				k=String.valueOf(ran);
				//System.out.println(k);
				message.setText("Your Reset Password Code: "+k);

				Transport.send(message);

				//System.out.println("Done");

			} catch (MessagingException xe) {
				throw new RuntimeException(xe);
			}
			
			String fn = JOptionPane.showInputDialog("Enter Number");
			if(fn.equals(k))
			{
				String sn = JOptionPane.showInputDialog("Enter Password Number");
				String m="",n="";
				try{
				Class.forName("oracle.jdbc.driver.OracleDriver");
				Connection con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","promi","1234");
				Statement stmt=con.createStatement();
				ResultSet rs=stmt.executeQuery("UPDATE password SET pass = '"+sn+"' where id = 'admin'");			
				
				JOptionPane.showMessageDialog(null,"SuccessFully Change Your Password","Status",JOptionPane.PLAIN_MESSAGE);
			
			
				con.close();
			}catch(Exception p){ System.out.println(p);} 
			}
			else
			{
				JOptionPane.showMessageDialog(null,"Wrong Number","Status",JOptionPane.PLAIN_MESSAGE);
			}
	
			
			
		}
		else if(e.getSource()==Exit)
		{
			System.exit(0);
		}
	}
}