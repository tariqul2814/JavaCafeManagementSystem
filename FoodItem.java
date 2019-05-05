import java.lang.*;
import java.sql.*;
import java.util.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class FoodItem extends JFrame implements ActionListener
{
	private JTable myTable;
	private JScrollPane tableScrollPane;
	
	private JLabel serial,name,price,quantity;
	private JTextField serialf,namef,pricef,quantityf;
	private JButton insert,update,delete,cancel,search;
	private JPanel panel;
	public String no1="",no2="",no3="",no4="",m="",q="",maxno="";
	public int h;
	public static int track1=0,track2=0;
	public static String [][]row = new String[1000][4];
	public static String []col = {"SERIALNO", "FOOD NAME", "PRICE","QUANTITYSELL"};
	
	public void searchdata()
	{
		no1 = String.valueOf(serialf.getText());
		no2 = String.valueOf(namef.getText());
		no3 = String.valueOf(pricef.getText());
		no4 = String.valueOf(quantityf.getText());
		
		try{
				Class.forName("oracle.jdbc.driver.OracleDriver");
				Connection con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","promi","1234");
				Statement stmt=con.createStatement();
				
				if(!no2.isEmpty())
				{
					ResultSet rs2=stmt.executeQuery("select * from foodprice where food_name = '"+no2+"'");
					while(rs2.next())
					{
						no1 = rs2.getString("SERIALNO");
						no3 = rs2.getString("PRICE");
						no4 = rs2.getString("QUANTITYSELL");
					}
					serialf.setText(no1);
					pricef.setText(no3);
					quantityf.setText(no4);
				}
				else if(!no1.isEmpty())
				{
					ResultSet rs1=stmt.executeQuery("select * from foodprice where serialno = '"+no1+"'");
					while(rs1.next())
					{
						no2 = rs1.getString("FOOD_NAME");
						no3 = rs1.getString("PRICE");
						no4 = rs1.getString("QUANTITYSELL");
					}
					namef.setText(no2);
					pricef.setText(no3);
					quantityf.setText(no4);
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
				ResultSet rs=stmt.executeQuery("delete from FoodPrice where serialno='"+no1+"'");
				con.close();
			}catch(Exception p){ System.out.println(p);}
			table();
		JOptionPane.showMessageDialog(null,"Delete Complete","Status",JOptionPane.PLAIN_MESSAGE);
	}
	
	public void updatede()
	{
		no1 = String.valueOf(serialf.getText());
		no2 = String.valueOf(namef.getText());
		no3 = String.valueOf(pricef.getText());
		no4 = String.valueOf(quantityf.getText());
		try{
				Class.forName("oracle.jdbc.driver.OracleDriver");
				Connection con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","promi","1234");
				Statement stmt=con.createStatement();
				if(!no2.isEmpty())
				{
					ResultSet rs1=stmt.executeQuery("update FoodPrice set FOOD_NAME='"+no2+"' where serialno = '"+no1+"'");
				}
				if(!no3.isEmpty())
				{
					ResultSet rs2=stmt.executeQuery("update FoodPrice set PRICE='"+no3+"' where serialno = '"+no1+"'");
				}
				if(!no4.isEmpty())
				{
					ResultSet rs3=stmt.executeQuery("update FoodPrice set QUANTITYSELL='"+no4+"' where serialno = '"+no1+"'");
				}
				con.close();
			}catch(Exception p){ System.out.println(p);}
			table();
			JOptionPane.showMessageDialog(null,"Update Complete","Status",JOptionPane.PLAIN_MESSAGE);
	}
	
	public void insertion()
	{
		track1++;
		no1 = String.valueOf(serialf.getText());
		no2 = String.valueOf(namef.getText());
		no3 = String.valueOf(pricef.getText());
		int h;
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
		
			try{
				Class.forName("oracle.jdbc.driver.OracleDriver");
				Connection con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","promi","1234");
				Statement stmt=con.createStatement();
				ResultSet rs=stmt.executeQuery("select max(serialno) from FoodPrice");
				while(rs.next())
				{
					maxno=rs.getString(1);
				}
				no1=String.valueOf(maxno);
				h=Integer.valueOf(no1);
				h++;
				no1=String.valueOf(h);
				no4="0";
				ResultSet rs1=stmt.executeQuery("insert into FoodPrice values ('"+no1+"','"+no2+"','"+no3+"','"+no4+"')");
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
		}
		
		int i=0,j=0;
		String m="",n="";
		try{
			Class.forName("oracle.jdbc.driver.OracleDriver");
			Connection con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","promi","1234");
			Statement stmt=con.createStatement();
			ResultSet rs=stmt.executeQuery("select * from FoodPrice order by SerialNo");
		while(rs.next())
		{
			row[i][0]=rs.getString("SERIALNO");
			row[i][1]=rs.getString("FOOD_NAME");
			row[i][2]=rs.getString("PRICE");
			row[i][3]=rs.getString("QUANTITYSELL");
			i++;
		}
	
		con.close();
		}catch(Exception e){ System.out.println(e);} 
		
		myTable = new JTable(row,col);
		tableScrollPane = new JScrollPane(myTable);
		tableScrollPane.setBounds(500,50,600,200);
		panel.add(tableScrollPane);
	}
	
	public FoodItem()
	{
		super("Food Price");
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
		
		name = new JLabel("Enter Name: ");
		name.setBounds(100,100,150,30);
		panel.add(name);
		
		namef = new JTextField();
		namef.setBounds(260,100,100,30);
		panel.add(namef);
		
		price = new JLabel("Here Price: ");
		price.setBounds(100,150,150,30);
		panel.add(price);
		
		pricef = new JTextField();
		pricef.setBounds(260,150,100,30);
		panel.add(pricef);
		
		quantity = new JLabel("Enter Quantity: ");
		quantity.setBounds(100,200,150,30);
		panel.add(quantity);
		
		quantityf = new JTextField();
		quantityf.setBounds(260,200,100,30);
		panel.add(quantityf);
		
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


