import java.lang.*;
import java.sql.*;
import java.util.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Statusall extends JFrame implements ActionListener
{
	private JTable myTable,statustable;
	private JScrollPane tableScrollPane,statustables;
	
	private JLabel serial,detail;
	private JTextField serialf;
	private JTextArea detailf;
	private JButton insert,update,delete,cancel,search;
	private JPanel panel;
	public String no1="",no2="",no3="",no4="",m="",q="",maxno="";
	public int h;
	public static int track1=0,track2=0;
	
	public static String [][]row1=new String[1000][2];
	public static String []col1={"SerialNo","STORY"};
	
	public void searchdata()
	{
		no1 = String.valueOf(serialf.getText());
		no2 = String.valueOf(detailf.getText());
		
		try{
				Class.forName("oracle.jdbc.driver.OracleDriver");
				Connection con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","promi","1234");
				Statement stmt=con.createStatement();
				
				if(!no1.isEmpty())
				{
					ResultSet rs1=stmt.executeQuery("select * from status where serialno = '"+no1+"'");
					while(rs1.next())
					{
						no2 = rs1.getString("STATUSDETAIL");
					}
					detailf.setText(no2);
				}
				con.close();
			}catch(Exception p){ System.out.println(p);}
			JOptionPane.showMessageDialog(null,"Search Complete","Status",JOptionPane.PLAIN_MESSAGE);
	}
	
	public void deletedb()
	{
		track2=track1;
		track1--;
		no1 = String.valueOf(serialf.getText());
		
			try{
				Class.forName("oracle.jdbc.driver.OracleDriver");
				Connection con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","promi","1234");
				Statement stmt=con.createStatement();
				ResultSet rs=stmt.executeQuery("delete from status where serialno='"+no1+"'");
				con.close();
			}catch(Exception p){ System.out.println(p);}
			table2();
		JOptionPane.showMessageDialog(null,"Delete Complete","Status",JOptionPane.PLAIN_MESSAGE);
	}
	
	public void updatede()
	{
		no1 = String.valueOf(serialf.getText());
		no2 = String.valueOf(detailf.getText());
		try{
				Class.forName("oracle.jdbc.driver.OracleDriver");
				Connection con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","promi","1234");
				Statement stmt=con.createStatement();
				if(!no2.isEmpty())
				{
					ResultSet rs1=stmt.executeQuery("update status set STATUSDETAIL='"+no2+"' where serialno = '"+no1+"'");
				}
				con.close();
			}catch(Exception p){ System.out.println(p);}
			table2();
			JOptionPane.showMessageDialog(null,"Update Complete","Status",JOptionPane.PLAIN_MESSAGE);
	}
	
	public void insertion()
	{
		track1++;
		no1 = String.valueOf(serialf.getText());
		no2 = String.valueOf(detailf.getText());
		int h;
		if(no1.isEmpty())
		{
			no1="-";
		}
		else if(no2.isEmpty())
		{
			no2="-";
		}
		
			try{
				Class.forName("oracle.jdbc.driver.OracleDriver");
				Connection con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","promi","1234");
				Statement stmt=con.createStatement();
				ResultSet rs=stmt.executeQuery("select max(serialno) from status");
				while(rs.next())
				{
					maxno=rs.getString(1);
				}
				no1=String.valueOf(maxno);
				h=Integer.valueOf(no1);
				h++;
				no1=String.valueOf(h);
				ResultSet rs1=stmt.executeQuery("insert into status values ('"+no1+"','"+no2+"','0')");
				con.close();
			}catch(Exception p){ System.out.println(p);}
			table2();
		JOptionPane.showMessageDialog(null,"Insertion Complete","Status",JOptionPane.PLAIN_MESSAGE);
	}
	
	public void table2()
	{
		for(int x=0;x<track2;x++)
		{
			row1[x][0]=null;
			row1[x][1]=null;
		}
		int i=0,j=0;
		String m="",n="";
		try{
			Class.forName("oracle.jdbc.driver.OracleDriver");
			Connection con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","promi","1234");
			Statement stmt=con.createStatement();
			ResultSet rs1=stmt.executeQuery("select * from status order by serialno");
		while(rs1.next())
		{
			row1[i][0]=rs1.getString("SERIALNO");
			row1[i][1]=rs1.getString("STATUSDETAIL");
			i++;
			track1++;
		}
	
		con.close();
		}catch(Exception e){ System.out.println(e);} 
		
		statustable = new JTable(row1,col1);
		statustables = new JScrollPane(statustable);
		statustables.setBounds(640,50,600,1000);
		panel.add(statustables);
	}
	
	public Statusall()
	{
		super("STATUS");
		this.setSize(2000, 1000);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		panel = new JPanel();
		panel.setLayout(null);
		
		serial = new JLabel("Enter SerialNO* : ");
		serial.setBounds(100,50,150,30);
		panel.add(serial);
		
		serialf = new JTextField();
		serialf.setBounds(260,50,100,30);
		panel.add(serialf);
		
		detail = new JLabel("Enter Message: ");
		detail.setBounds(100,100,150,30);
		panel.add(detail);
		
		detailf = new JTextArea();
		detailf.setBounds(260,100,200,200);
		panel.add(detailf);
		
		insert = new JButton("INSERT");
		insert.setBounds(50,370,100,40);
		insert.addActionListener(this);
		panel.add(insert);
		
		update = new JButton("UPDATE");
		update.setBounds(155,370,100,40);
		update.addActionListener(this);
		panel.add(update);
		
		delete = new JButton("DELETE");
		delete.setBounds(260,370,100,40);
		delete.addActionListener(this);
		panel.add(delete);
		
		search = new JButton("SEARCH");
		search.setBounds(365,370,100,40);
		search.addActionListener(this);
		panel.add(search);
		
		cancel = new JButton("CANCEL");
		cancel.setBounds(470,370,100,40);
		cancel.addActionListener(this);
		panel.add(cancel);
		
		table2();
		
		this.add(panel);
	}
	
	public void actionPerformed(ActionEvent e)
	{
		if(e.getSource()==insert)
		{
			insertion();
		}
		else if(e.getSource()==update)
		{
			updatede();
		}
		else if(e.getSource()==delete)
		{
			deletedb();
		}
		else if(e.getSource()==search)
		{
			searchdata();
		}
		else if(e.getSource()==cancel)
		{
			System.exit(0);
		}
	}
	
}


