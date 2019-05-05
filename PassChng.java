import java.lang.*;
import java.sql.*;
import java.util.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class PassChng extends JFrame implements ActionListener
{
	private JTable myTable;
	private JScrollPane tableScrollPane;
	
	private JPanel panel;
	private JButton Change,Show,Cancel,Create,Delete;
	private JLabel User,Pass;
	private JTextField UserT,PassT;
	
	public static int track1=0,track2=0;
	public static String [][]row = new String[1000][2];
	public static String []col = {"ID", "PASS"};
	
	public void changepass()
	{
		try{
				Class.forName("oracle.jdbc.driver.OracleDriver");
				Connection con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","promi","1234");
				Statement stmt=con.createStatement();
				String u="";
				String p="";
				u=UserT.getText();
				p=PassT.getText();
				
				ResultSet rs1=stmt.executeQuery("update password set pass='"+p+"' where id = '"+u+"'");
				
				con.close();
			}catch(Exception p){ System.out.println(p);}
			table();
			JOptionPane.showMessageDialog(null,"Update Complete","Status",JOptionPane.PLAIN_MESSAGE);
	}
	
	public void createacc()
	{
		track1++;
		try{
				Class.forName("oracle.jdbc.driver.OracleDriver");
				Connection con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","promi","1234");
				Statement stmt=con.createStatement();
				String u="";
				String p="";
				u=UserT.getText();
				p=PassT.getText();
				//insert into password values('lubna','5555');
				
				ResultSet rs1=stmt.executeQuery("insert into password values('"+u+"','"+p+"')");
				
				con.close();
			}catch(Exception p){ System.out.println(p);}
			table();
			JOptionPane.showMessageDialog(null,"Update Complete","Status",JOptionPane.PLAIN_MESSAGE);
	}
	
	public void deleteacc()
	{
		track2=track1;
		track1--;
		
		//no1 = String.valueOf(idf.getText());
		String u="";
		u=UserT.getText();
			try{
				Class.forName("oracle.jdbc.driver.OracleDriver");
				Connection con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","promi","1234");
				Statement stmt=con.createStatement();
				ResultSet rs=stmt.executeQuery("delete from password where id='"+u+"'");
				con.close();
			}catch(Exception p){ System.out.println(p);}
			table();
		JOptionPane.showMessageDialog(null,"Delete Complete","Status",JOptionPane.PLAIN_MESSAGE);
	}
	
	public void showdb()
	{
		String no1="";
		String no2="";
		
		no1 = String.valueOf(UserT.getText());
		no2 = String.valueOf(PassT.getText());
		
		try{
				Class.forName("oracle.jdbc.driver.OracleDriver");
				Connection con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","promi","1234");
				Statement stmt=con.createStatement();
				ResultSet rs1=stmt.executeQuery("select * from password where id = '"+no1+"'");
				while(rs1.next())
				{
					no1 = rs1.getString("id");
					no2 = rs1.getString("pass");
				}
				UserT.setText(no1);
				PassT.setText(no2);
				
				con.close();
			}catch(Exception p){ System.out.println(p);}
			JOptionPane.showMessageDialog(null,"Search Complete","Status",JOptionPane.PLAIN_MESSAGE);
	}
	
	public void table()
	{
		for(int x=0;x<track2;x++)
		{
			row[x][0]=null;
			row[x][1]=null;
		}
		int i=0,j=0;
		try{
			Class.forName("oracle.jdbc.driver.OracleDriver");
			Connection con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","promi","1234");
			Statement stmt=con.createStatement();
			ResultSet rs=stmt.executeQuery("select * from password order by id");
			while(rs.next())
			{
				row[i][0]=rs.getString("id");
				row[i][1]=rs.getString("pass");
				i++;
			}
	
		con.close();
		}catch(Exception e){ System.out.println(e);} 
		
		myTable = new JTable(row,col);
		tableScrollPane = new JScrollPane(myTable);
		tableScrollPane.setBounds(400,50,250,200);
		panel.add(tableScrollPane);
		
	}
	
	public PassChng()
	{
		super("Password Detail");
		this.setSize(800, 400);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		panel = new JPanel();
		panel.setLayout(null);
		
		User = new JLabel("User Name* : ");
		User.setBounds(100,50,150,30);
		panel.add(User);
		
		UserT = new JTextField();
		UserT.setBounds(260,50,100,30);
		panel.add(UserT);
		
		Pass = new JLabel("Password: ");
		Pass.setBounds(100,100,150,30);
		panel.add(Pass);
		
		PassT = new JTextField();
		PassT.setBounds(260,100,100,30);
		panel.add(PassT);
		
		Change = new JButton("Change");
		Change.setBounds(80,180,80,40);
		Change.addActionListener(this);
		panel.add(Change);
		
		Show = new JButton("Show");
		Show.setBounds(180,180,80,40);
		Show.addActionListener(this);
		panel.add(Show);
		
		Create = new JButton("Create");
		Create.setBounds(280,180,80,40);
		Create.addActionListener(this);
		panel.add(Create);
		
		Delete = new JButton("Delete");
		Delete.setBounds(140,250,80,40);
		Delete.addActionListener(this);
		panel.add(Delete);
		
		Cancel = new JButton("Cancel");
		Cancel.setBounds(240,250,80,40);
		Cancel.addActionListener(this);
		panel.add(Cancel);
		
		table();
		
		this.add(panel);

	}
	
	public void actionPerformed(ActionEvent e)
	{
		if(e.getSource()==Change)
		{
			changepass();
		}
		else if(e.getSource()==Show)
		{
			showdb();
		}
		else if(e.getSource()==Create)
		{
			createacc();
		}
		else if(e.getSource()==Delete)
		{
			deleteacc();
		}
		else if(e.getSource()==Cancel)
		{
			System.exit(0);
		}
	}
	
}