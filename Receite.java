import java.lang.*;
import java.io.*;
import java.util.*;
import java.text.SimpleDateFormat;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;

public class Receite extends JFrame implements ActionListener,MouseListener
{
	private JLabel Customer_Name,Customer_Phone,Item_Name,Unit_price,Quantity,TotalAmount,Balance,AmountPaid,Header,Payment,Receive,Refund;
	private JButton Received,Bill,AddNewItem,UpdateItem,DeleteItem,Total;
	private JPanel panel;
	private JRadioButton r1,r2;
	private JTextField Customer_Namef,Customer_Phonef,Order_Datef,Order_Timef,Order_Reff,Item_Namef,Unit_pricef,Quantityf,Refundf,TotalAmountf,Balancef,Receivef,AmountPaidf,Paymentf;
	private ButtonGroup bg1;
	private String checkstatus="",month="",year="",day="",f="";
	private DefaultTableModel model;
	private JScrollPane pane;
	public static final String date = "dd-MM-yyyy";
	private JTable table;
	public static int track=0,totala=0,refundm=0;
	
	public void receiteprint()
	{
		Scanner sc = new Scanner(System.in);
		InputStreamReader isr = new InputStreamReader(System.in);
		BufferedReader bfr = new BufferedReader(isr);
		
		String s="",temp=""; 
		char choice = 'y';
		try{
		File file = new File("MoneyReceite.txt");
		  
		file.createNewFile();
		
		FileWriter writer = new FileWriter(file); 
		
		String q = "",q1="",q2="",q3="";
		q="---------------------------------------------------------------------------------------------------------------------------";
		temp=temp+q+"\r"+"\n";
		q="---------------------------------------------------------------------------------------------------------------------------";
		temp=temp+q+"\r"+"\n";
		temp= temp+"\t"+"\t"+"\t"+"\t"+"\t"+"\t"+"\t"+"Cafe Shania"+"\r"+"\n";
		q="Customer Name: ";
		temp=temp+q;
		q=Customer_Namef.getText();
		temp=temp+q+"\r"+"\n";
		temp=temp+"\r"+"\n";
		q="Contact No: ";
		temp=temp+q;
		q=Customer_Phonef.getText();
		temp=temp+q+"\r"+"\n";
		temp=temp+"\r"+"\n";
		q ="       	ITEM NAME            QUANTITY               PRICE                   ";
		temp=temp+q+"\r"+"\n";
		temp=temp+"\r"+"\n";
		temp=temp+"\r"+"\n";
		q1="       	";
		q2="            ";
		q3="               ";
		for(int i=0;i<track;i++)
		{
			int stringcount=0;
			String h = (model.getValueAt(i,0).toString());
			stringcount=h.length();
			if(stringcount<9)
			{
				for(int xx=stringcount;xx<=9;xx++)
				{
					h+=" ";
				}
			}
			temp=temp+q1+h+q2;
			String h2 = (model.getValueAt(i,1).toString());
			stringcount=h2.length();
			if(stringcount<9)
			{
				for(int xx=stringcount;xx<=7;xx++)
				{
					h+=" ";
				}
			}
			temp=temp+h2+q3;
			String h3 = (model.getValueAt(i,2).toString());
			temp=temp+h3+"\r"+"\n";
			temp=temp+"\r"+"\n";
		}
		temp=temp+"\r"+"\n";
		temp=temp+"\r"+"\n";
		temp=temp+"\r"+"\n";
		q="        TOTAL: "+TotalAmountf.getText();
		temp=temp+q+"\r"+"\n";
		q="        RECEIVE:"+Receivef.getText();
		temp=temp+q+"\r"+"\n";
		q="        Refund:"+Refundf.getText();
		temp=temp+q+"\r"+"\n";
		writer.write(temp); 
		writer.flush();
		writer.close();}catch(Exception e){};
	}
	
	public void calculation()
	{
		totala=0;
		refundm=0;
		String q ="";
		for(int i=0;i<track;i++)
		{
			q = (model.getValueAt(i,2).toString());
			int sum = Integer.valueOf(q);
			totala+=sum;
		}
		q = String.valueOf(totala);
		TotalAmountf.setText(q);
	}
	
	public void foodrating()
	{
		String m="";
		try{
			Class.forName("oracle.jdbc.driver.OracleDriver");
			Connection con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","promi","1234");
			Statement stmt=con.createStatement();
			for(int i=0;i<track;i++)
			{
				String h = (model.getValueAt(i,0).toString());
				ResultSet rs=stmt.executeQuery("select quantitysell from foodprice where food_name='"+h+"'");
				String qwe = (model.getValueAt(i,1).toString());
				int counter2 = Integer.valueOf(qwe);
				while(rs.next())  
				m=rs.getString("quantitysell");
				int xqq = Integer.valueOf(m);
				xqq+=counter2;
				
				String hq = String.valueOf(xqq);
				ResultSet rs1=stmt.executeQuery("update foodprice set quantitysell='"+hq+"' where food_name='"+h+"'");
			}
		}catch(Exception e){ System.out.println(e);} 
	}
	
	public void receivecheck()
	{
		foodrating();
		receiteprint();
		String q = "";
		q = String.valueOf(Receivef.getText());
		refundm = Integer.valueOf(q);
		refundm-=totala;
		q = String.valueOf(refundm);
		Refundf.setText(q);
		receiteprint();
		int ff;
		String m="",m1="",m2="";
		try{
			Class.forName("oracle.jdbc.driver.OracleDriver");
			Connection con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","promi","1234");
			Statement stmt=con.createStatement();
			ResultSet rs=stmt.executeQuery("select max(TRANSACTION) from finance_info");
		while(rs.next())  
			m=rs.getString(1);
			ff=Integer.valueOf(m);
			ff++;
			m = String.valueOf(ff);
			m1 = String.valueOf(totala);
			m2 = checkstatus;
			Calendar calendar = Calendar.getInstance();
			SimpleDateFormat dateFormat = new SimpleDateFormat(date);
			String h = "";
			h = dateFormat.format(calendar.getTime());
			String h1="",h2="";
			h1 += h.charAt(3);
			h1 += h.charAt(4);
			int month = Integer.valueOf(h1);
			if(month ==1)
				h1="JAN";
			else if(month ==2)
				h1="FEB";
			else if(month ==3)
				h1="MAR";
			else if(month ==5)
				h1="APR";
			else if(month ==6)
				h1="MAY";
			else if(month ==7)
				h1="JUNE";
			else if(month ==8)
				h1="JUL";
			else if(month ==9)
				h1="AUG";
			else if(month ==10)
				h1="SEP";
			else if(month ==11)
				h1="OCT";
			else if(month ==12)
				h1="NOV";
			else if(month ==13)
				h1="DEC";
		
		for(int i=0;i<10;i++)
		{
			if(i==6 || i==7)
				continue;
			if(i==2 || i==5)
			{
				h2+="-";
			}
			else if(i==3)
			{
				i=4;
				h2+=h1;
			}
			else
			{
				char s;
				s=h.charAt(i);
				h2+=s;
			}
		}
			rs = stmt.executeQuery("insert into finance_info values ('"+m+"','"+h2+"','"+m1+"','"+m2+"')");
		}catch(Exception e){ System.out.println(e);} 
	}
	
	public void clearev()
	{
		Customer_Namef.setText("");
		Customer_Phonef.setText("");
		Item_Namef.setText("");
		Unit_pricef.setText("");
		TotalAmountf.setText("");
		Receivef.setText("");
		Refundf.setText("");
		
		for(int i=track-1;i>=0;i--)
		{
			model.removeRow(i);
		}
		track=0;
		refundm=0;
		totala=0;
	}
	
	public Receite()
	{
		super("Money Calculator");
		this.setSize(2000,1000);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		panel = new JPanel();
		panel.setLayout(null);
		
		table = new JTable();
		
		Object []col = {"ITEM NAME","QUANTITY","TOTAL"};
		
		model = new DefaultTableModel();
		model.setColumnIdentifiers(col);
		
		table.setModel(model);
		
		table.setBackground(Color.LIGHT_GRAY);
		table.setForeground(Color.black);
		Font font = new Font("",1,22);
		table.setFont(font);
		table.setRowHeight(30);
		table.addMouseListener(this);
		
		pane = new JScrollPane(table);
        pane.setBounds(540, 130, 810, 400);
		panel.add(pane);
		
		Header = new JLabel("Order System");
		Header.setBounds(600,10,200,100);
		Header.setFont(new Font("Consolas",Font.BOLD,25));
		Header.setOpaque(true);
		panel.add(Header);
		
		Customer_Name = new JLabel("Customer Name: ");
		Customer_Name.setBounds(50,130,180,40);
		Customer_Name.setFont(new Font("Consolas",Font.BOLD,20));
		Customer_Name.setOpaque(true);
		panel.add(Customer_Name);
		
		Customer_Namef = new JTextField();
		Customer_Namef.setBounds(230,130,180,40);
		panel.add(Customer_Namef);
		
		Customer_Phone = new JLabel("Customer Phone: ");
		Customer_Phone.setBounds(50,200,180,40);
		Customer_Phone.setFont(new Font("Consolas",Font.BOLD,20));
		Customer_Phone.setOpaque(true);
		panel.add(Customer_Phone);
		
		Customer_Phonef = new JTextField();
		Customer_Phonef.setBounds(230,200,180,40);
		panel.add(Customer_Phonef);
		
		Item_Name = new JLabel("Item Name: ");
		Item_Name.setBounds(50,280,180,40);
		Item_Name.setFont(new Font("Consolas",Font.BOLD,20));
		Item_Name.setOpaque(true);
		panel.add(Item_Name);
		
		Item_Namef = new JTextField();
		Item_Namef.setBounds(230,280,300,40);
		panel.add(Item_Namef);
		
		Unit_price = new JLabel("Unit Quantity: ");
		Unit_price.setBounds(50,350,180,40);
		Unit_price.setFont(new Font("Consolas",Font.BOLD,20));
		Unit_price.setOpaque(true);
		panel.add(Unit_price);
		
		Unit_pricef = new JTextField();
		Unit_pricef.setBounds(230,350,180,40);
		panel.add(Unit_pricef);
		
		Payment = new JLabel("Payment Process:");
		Payment.setBounds(50,420,200,40);
		Payment.setFont(new Font("Consolas",Font.BOLD,20));
		Payment.setOpaque(true);
		panel.add(Payment);
		
		r1 = new JRadioButton("CREDIT CARD");
		r1.setBounds(250,428,130,30);
		r1.addActionListener(this);
		r1.setFont(new Font("Consolas",Font.BOLD,15));
		r1.setOpaque(true);
		panel.add(r1);
		
		r2 = new JRadioButton("CASH");
		r2.setBounds(385,428,130,30);
		r2.addActionListener(this);
		r2.setFont(new Font("Consolas",Font.BOLD,15));
		r2.setOpaque(true);
		panel.add(r2);
		
		AddNewItem = new JButton("ADD");
		AddNewItem.setBounds(50,500,80,40);
		AddNewItem.addActionListener(this);
		panel.add(AddNewItem);
		
		UpdateItem = new JButton("UPDATE");
		UpdateItem.setBounds(140,500,80,40);
		UpdateItem.addActionListener(this);
		panel.add(UpdateItem);
		
		DeleteItem = new JButton("REMOVE");
		DeleteItem.setBounds(230,500,100,40);
		DeleteItem.addActionListener(this);
		panel.add(DeleteItem);
		
		Total = new JButton("TOTAL");
		Total.setBounds(50,560,80,40);
		Total.addActionListener(this);
		panel.add(Total);
		
		Bill = new JButton("CLEAR");
		Bill.setBounds(140,560,80,40);
		Bill.addActionListener(this);
		panel.add(Bill);
		
		Received = new JButton("RECEIVED");
		Received.setBounds(230,560,100,40);
		Received.addActionListener(this);
		panel.add(Received);
		
		TotalAmount = new JLabel("Total: ");
		TotalAmount.setBounds(560,540,100,40);
		TotalAmount.setFont(new Font("Consolas",Font.BOLD,20));
		TotalAmount.setOpaque(true);
		panel.add(TotalAmount);
		
		TotalAmountf = new JTextField();
		TotalAmountf.setBounds(660,540,100,40);
		panel.add(TotalAmountf);
		
		Receive = new JLabel("Receive: ");
		Receive.setBounds(560,590,100,40);
		Receive.setFont(new Font("Consolas",Font.BOLD,20));
		Receive.setOpaque(true);
		panel.add(Receive);
		
		Receivef = new JTextField();
		Receivef.setBounds(660,590,100,40);
		panel.add(Receivef);
		
		Refund = new JLabel("Refund: ");
		Refund.setBounds(560,640,100,40);
		Refund.setFont(new Font("Consolas",Font.BOLD,20));
		Refund.setOpaque(true);
		panel.add(Refund);
		
		Refundf = new JTextField();
		Refundf.setBounds(660,640,100,40);
		panel.add(Refundf);
		
		bg1 = new ButtonGroup();
		bg1.add(r1);
		bg1.add(r2);
		
		this.add(panel);
	}
	public String tableprice(String a)
	{
		int i=0,j=0;
		String m="",n="";
		try{
			Class.forName("oracle.jdbc.driver.OracleDriver");
			Connection con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","promi","1234");
			Statement stmt=con.createStatement();
			ResultSet rs=stmt.executeQuery("select * from foodprice where food_name='"+a+"'");
		while(rs.next())
		{
			m=rs.getString("price");
		}
	
		con.close();
		}catch(Exception e){ System.out.println(e);}
		return m;
	}
	
	public void actionPerformed(ActionEvent ae)
	{
		if(ae.getSource()==AddNewItem)
		{
			track++;
			Object []row = new Object[3];
			String mm=Item_Namef.getText();
			int x,y;
			row[0]=Item_Namef.getText();
			row[1]=Unit_pricef.getText();
			x=Integer.valueOf(Unit_pricef.getText());
			y=Integer.valueOf(tableprice(mm));
			row[2]=String.valueOf(x*y);
			model.addRow(row);
		}
		
		else if(ae.getSource()==UpdateItem)
		{
			int i = table.getSelectedRow();
			String mm = "";
			if(i>=0)
			{
				int x,y;
				model.setValueAt(Item_Namef.getText(),i,0);
				model.setValueAt(Unit_pricef.getText(),i,1);
				mm = Item_Namef.getText();
				x=Integer.valueOf(Unit_pricef.getText());
				y=Integer.valueOf(tableprice(mm));
				model.setValueAt(String.valueOf(x*y),i,2);
			}
			else 
			{
				System.out.println("Update Error");
			}
			
		}
		
		else if(ae.getSource()==DeleteItem)
		{
			track--;
			int i = table.getSelectedRow();
                if(i >= 0){
                
                    model.removeRow(i);
                }
                else{
                    System.out.println("Delete Error");
                }
		}	
		else if(ae.getSource()==Total)
		{
			calculation();
		}
		
		else if(ae.getSource()==Received)
		{
			receivecheck();
		}
		else if(ae.getSource()==Bill)
		{
			clearev();
		}
		
		if(ae.getSource()==r1)
		{
			checkstatus = "CREDIT CARD";
		}
		else if(ae.getSource()==r2)
		{
			checkstatus = "CASH";
		}
	}
	
	public void mouseClicked(MouseEvent e){ 
			int i = table.getSelectedRow();
            
            Item_Namef.setText(model.getValueAt(i, 0).toString());
            Unit_pricef.setText(model.getValueAt(i, 1).toString());
            
	}
	public void mouseEntered(MouseEvent e){} 
	public void mouseExited(MouseEvent e){}  
	public void mousePressed(MouseEvent e){}  
	public void mouseReleased(MouseEvent e){}  
	
	
}