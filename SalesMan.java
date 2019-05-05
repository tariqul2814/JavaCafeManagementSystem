import java.lang.*;
import java.sql.*;
import java.util.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class SalesMan extends JFrame implements ActionListener
{
	private JTable myTable;
	private JScrollPane tableScrollPane;
	
	private JLabel name,dob,joindb,leftdb,id,salary;
	private JTextField namef,dobf,joindbf,leftdbf,idf,salaryf;
	private JButton insert,update,delete,cancel,search;
	private JPanel panel;
	public String no1="",no2="",no3="",no4="",no5="",no6="",m="",q="",maxno="";
	public int h;
	
	public static int track1=0,track2=0;
	public static String [][]row = new String[1000][6];
	public static String []col = {"ID", "NAME", "DATEOFBIRTH","JOINDATE","LEFTDATE","SALARY"};
	
	public void searchdata()
	{
		no1 = String.valueOf(idf.getText());
		no2 = String.valueOf(namef.getText());
		no3 = String.valueOf(dobf.getText());
		no4 = String.valueOf(joindbf.getText());
		no5 = String.valueOf(leftdbf.getText());
		no6 = String.valueOf(salaryf.getText());
		
		try{
				Class.forName("oracle.jdbc.driver.OracleDriver");
				Connection con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","promi","1234");
				Statement stmt=con.createStatement();
				if(!no1.isEmpty())
				{
					ResultSet rs1=stmt.executeQuery("select * from salesman where id = '"+no1+"'");
					while(rs1.next())
					{
						no2 = rs1.getString("name");
						no3 = rs1.getString("dateofbirth");
						no4 = rs1.getString("joindate");
						no5 = rs1.getString("leftdate");
						no6 = rs1.getString("salary");
					}
					String d="";
					idf.setText(no1);
					namef.setText(no2);
					salaryf.setText(no6);
					for(int i=0;i<10;i++)
					{
						d+=no3.charAt(i);
					}
					dobf.setText(d);
					d="";
					for(int i=0;i<10;i++)
					{
						d+=no4.charAt(i);
					}
					joindbf.setText(d);
					if(!no5.isEmpty())
					{
						d="";
						for(int i=0;i<10;i++)
						{
							d+=no5.charAt(i);
						}
						leftdbf.setText(d);
					}
				}
				else if(!no2.isEmpty())
				{
					ResultSet rs1=stmt.executeQuery("select * from salesman where name = '"+no2+"'");
					while(rs1.next())
					{
						no1 = rs1.getString("id");
						no3 = rs1.getString("dateofbirth");
						no4 = rs1.getString("joindate");
						no5 = rs1.getString("leftdate");
						no6 = rs1.getString("salary");
					}
					String d="";
					idf.setText(no1);
					namef.setText(no2);
					salaryf.setText(no6);
					for(int i=0;i<10;i++)
					{
						d+=no3.charAt(i);
					}
					dobf.setText(d);
					d="";
					for(int i=0;i<10;i++)
					{
						d+=no4.charAt(i);
					}
					joindbf.setText(d);
					if(!no5.isEmpty())
					{
						d="";
						for(int i=0;i<10;i++)
						{
							d+=no5.charAt(i);
						}
						leftdbf.setText(d);
					}
				}
				con.close();
			}catch(Exception p){ System.out.println(p);}
			JOptionPane.showMessageDialog(null,"Search Complete","Status",JOptionPane.PLAIN_MESSAGE);
	}
	
	public void deletedb()
	{
		track2=track1;
		track1--;
		no1 = String.valueOf(idf.getText());
		
			try{
				Class.forName("oracle.jdbc.driver.OracleDriver");
				Connection con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","promi","1234");
				Statement stmt=con.createStatement();
				ResultSet rs=stmt.executeQuery("delete from salesman where id='"+no1+"'");
				con.close();
			}catch(Exception p){ System.out.println(p);}
			table();
		JOptionPane.showMessageDialog(null,"Delete Complete","Status",JOptionPane.PLAIN_MESSAGE);
	}
	
	public void updatede()
	{
		no1 = String.valueOf(idf.getText());
		no2 = String.valueOf(namef.getText());
		no3 = String.valueOf(dobf.getText());
		no4 = String.valueOf(joindbf.getText());
		no5 = String.valueOf(leftdbf.getText());
		no6 = String.valueOf(salaryf.getText());
		try{
				Class.forName("oracle.jdbc.driver.OracleDriver");
				Connection con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","promi","1234");
				Statement stmt=con.createStatement();
				if(!no2.isEmpty())
				{
					ResultSet rs1=stmt.executeQuery("update salesman set name='"+no2+"' where id = '"+no1+"'");
				}
				if(!no3.isEmpty())
				{
					ResultSet rs2=stmt.executeQuery("update salesman set dateofbirth='"+no3+"' where id = '"+no1+"'");
				}
				if(!no4.isEmpty())
				{
					ResultSet rs3=stmt.executeQuery("update Salesman set joindate='"+no4+"' where id = '"+no1+"'");
				}
				if(!no5.isEmpty())
				{
					ResultSet rs4=stmt.executeQuery("update Salesman set leftdate='"+no5+"' where id = '"+no1+"'");
				}
				if(!no6.isEmpty())
				{
					ResultSet rs5=stmt.executeQuery("update Salesman set salary='"+no6+"' where id = '"+no1+"'");
				}
				con.close();
			}catch(Exception p){ System.out.println(p);}
			table();
			JOptionPane.showMessageDialog(null,"Update Complete","Status",JOptionPane.PLAIN_MESSAGE);
	}
	
	public void insertion()
	{
		track1++;
		no1 = String.valueOf(idf.getText());
		no2 = String.valueOf(namef.getText());
		no3 = String.valueOf(dobf.getText());
		no4 = String.valueOf(joindbf.getText());
		no5 = String.valueOf(leftdbf.getText());
		no6 = String.valueOf(salaryf.getText());
		int h;
		h=Integer.valueOf(no6);
		if(no1.isEmpty())
		{
			no1="-";
		}
		else if(no2.isEmpty())
		{
			no2="-";
		}
		else if(no3.isEmpty())
		{
			no3="-";
		}
		else if(no4.isEmpty())
		{
			no4="-";
		}
		else if(no5.isEmpty())
		{
			no5="01-Jan-61";
		}
		else if(no6.isEmpty())
		{
			h=0;
		}
		else if(!no5.isEmpty())
		{
			h=Integer.valueOf(no6);
		}
		//q = "insert into salesman values (";
		
			try{
				Class.forName("oracle.jdbc.driver.OracleDriver");
				Connection con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","promi","1234");
				Statement stmt=con.createStatement();
				ResultSet rs=stmt.executeQuery("select max(id) from salesman");
				while(rs.next())
				{
					maxno=rs.getString(1);
				}
				no1=String.valueOf(maxno);
				h=Integer.valueOf(no1);
				h++;
				no1=String.valueOf(h);
				ResultSet rs1=stmt.executeQuery("insert into salesman values ('"+no1+"','"+no2+"','"+no3+"','"+no4+"','"+no5+"','"+no6+"')");
				con.close();
			}catch(Exception p){ System.out.println(p);}
			table();
		JOptionPane.showMessageDialog(null,"Insertion Complete","Status",JOptionPane.PLAIN_MESSAGE);
	}
	
	public void table()
	{
		for(int x=0;x<track2;x++)
		{
			row[x][0]=null;
			row[x][1]=null;
			row[x][2]=null;
			row[x][3]=null;
			row[x][4]=null;
			row[x][5]=null;
		}
		int i=0,j=0;
		String m="",n="";
		try{
			Class.forName("oracle.jdbc.driver.OracleDriver");
			Connection con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","promi","1234");
			Statement stmt=con.createStatement();
			ResultSet rs=stmt.executeQuery("select * from salesman order by id");
		while(rs.next())
		{
			row[i][0]=rs.getString("id");
			row[i][1]=rs.getString("name");
			row[i][2]=rs.getString("dateofbirth");
			row[i][3]=rs.getString("joindate");
			row[i][4]=rs.getString("leftdate");
			row[i][5]=rs.getString("salary");
			i++;
		}
	
		con.close();
		}catch(Exception e){ System.out.println(e);} 
		
		myTable = new JTable(row,col);
		tableScrollPane = new JScrollPane(myTable);
		tableScrollPane.setBounds(500,50,600,200);
		panel.add(tableScrollPane);
	}
	
	public SalesMan()
	{
		super("SalesMan");
		this.setSize(2000, 1000);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		panel = new JPanel();
		panel.setLayout(null);
		
		id = new JLabel("Enter id* : ");
		id.setBounds(100,50,150,30);
		panel.add(id);
		
		idf = new JTextField();
		idf.setBounds(260,50,100,30);
		panel.add(idf);
		
		name = new JLabel("Enter Name: ");
		name.setBounds(100,100,150,30);
		panel.add(name);
		
		namef = new JTextField();
		namef.setBounds(260,100,100,30);
		panel.add(namef);
		
		dob = new JLabel("Enter DateOfBirth: ");
		dob.setBounds(100,150,150,30);
		panel.add(dob);
		
		dobf = new JTextField();
		dobf.setBounds(260,150,100,30);
		panel.add(dobf);
		
		joindb = new JLabel("Enter Join Date: ");
		joindb.setBounds(100,200,150,30);
		panel.add(joindb);
		
		joindbf = new JTextField();
		joindbf.setBounds(260,200,100,30);
		panel.add(joindbf);
		
		leftdb = new JLabel("Enter Left Date: ");
		leftdb.setBounds(100,250,150,30);
		panel.add(leftdb);
		
		leftdbf = new JTextField();
		leftdbf.setBounds(260,250,100,30);
		panel.add(leftdbf);
		
		salary = new JLabel("Enter Salary: ");
		salary.setBounds(100,300,150,30);
		panel.add(salary);
		
		salaryf = new JTextField();
		salaryf.setBounds(260,300,100,30);
		panel.add(salaryf);
		
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
		
		table();
		
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


