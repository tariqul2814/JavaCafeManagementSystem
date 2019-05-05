import java.lang.*;
import javax.swing.*;
import java.awt.*;
import java.sql.*;
import java.awt.event.*;

public class ManagerPass extends JFrame implements ActionListener
{
	private JPanel panel;
	private JButton Ok,Exit,Forgetpass;
	private String adminpass;
	private JLabel label,label1;
	private JPasswordField pass;
	
	public ManagerPass()
	{
		super("Manager Account");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setSize(500, 500);
		
		panel = new JPanel();
		panel.setLayout(null);
		
		label1 = new JLabel("MANAGER");
		label1.setBounds(180,30,200,200);
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
		Ok.setBounds(120,240,100,40);
		Ok.addActionListener(this);
		panel.add(Ok);
		
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
				ResultSet rs=stmt.executeQuery("select pass from password where id = 'manager'");
		
				while(rs.next()) 
				{
					m=rs.getString("pass");
				}				
				
				if(fn.equals(m))
				{
					JOptionPane.showMessageDialog(null,"Successfully Login","Status",JOptionPane.PLAIN_MESSAGE);
					Manager l = new Manager();
					l.setVisible(true);
				}
				
				else
				{
					JOptionPane.showMessageDialog(null,"Invalid UserName or Password: Check It","Status",JOptionPane.PLAIN_MESSAGE);
				}
			
				con.close();
			}catch(Exception p){ System.out.println(p);} 
			
		}
		else if(e.getSource()==Exit)
		{
			System.exit(0);
		}
	}
}