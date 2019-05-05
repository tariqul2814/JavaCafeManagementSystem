import java.lang.*;
import java.sql.*;
import java.util.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.JOptionPane;

public class FinancialRecord extends JFrame implements ActionListener
{
	private JButton Total,Cancel;
	private JLabel Sum;
	private JTextField Sumf;
	private JPanel panel;
	private JTable myTable;
	private JScrollPane tableScrollPane;
	
	public void SumDB()
	{
		String m="";
		try{
			Class.forName("oracle.jdbc.driver.OracleDriver");
			Connection con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","promi","1234");
			Statement stmt=con.createStatement();
			ResultSet rs=stmt.executeQuery("select sum(money) from finance_info");
		while(rs.next())  
			m=rs.getString(1);
			Sumf.setText(m);
		}catch(Exception e){ System.out.println(e);} 
	}
	
	public void table()
	{
		int i=0,j=0;
		
		String [][]row = new String[1000][3];
		String []col = {"TRANSACTION", "DAY", "MONEY"};
		String m="",n="";
		try{
			Class.forName("oracle.jdbc.driver.OracleDriver");
			Connection con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","promi","1234");
			Statement stmt=con.createStatement();
			ResultSet rs=stmt.executeQuery("select * from finance_info");
		while(rs.next())
		{
			row[i][0]=rs.getString("TRANSACTION");
			row[i][1]=rs.getString("DAY");
			row[i][2]=rs.getString("MONEY");
			i++;
		}
	
		con.close();
		}catch(Exception e){ System.out.println(e);} 
		
		myTable = new JTable(row,col);
		tableScrollPane = new JScrollPane(myTable);
		tableScrollPane.setBounds(500,50,600,200);
		panel.add(tableScrollPane);
	}
	
	public FinancialRecord ()
	{
		super("Finance Info");
		this.setSize(2000, 1000);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		panel = new JPanel();
		panel.setLayout(null);
		
		Sum = new JLabel("Total : ");
		Sum.setBounds(100,50,150,30);
		panel.add(Sum);
		
		Sumf = new JTextField();
		Sumf.setBounds(260,50,100,30);
		panel.add(Sumf);
		
		Total = new JButton("Total");
		Total.setBounds(100,100,100,40);
		Total.addActionListener(this);
		panel.add(Total);
		
		Cancel = new JButton("CANCEL");
		Cancel.setBounds(220,100,100,40);
		Cancel.addActionListener(this);
		panel.add(Cancel);
		
		table();
		
		this.add(panel);
	}
	public void actionPerformed(ActionEvent e)
	{
		if(e.getSource()==Total)
		{
			SumDB();
		}
	}
}
