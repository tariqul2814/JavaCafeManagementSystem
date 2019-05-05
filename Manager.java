import java.lang.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.net.*;  
import java.io.*;

public class Manager extends JFrame implements ActionListener
{
	//1.Bill 2.Online Order. 3. Raw Materals
	JLabel title;
	JLabel bill, order;
	JButton bbill, b_order;
	JPanel panel;
	//LogIn l;
	
	public Manager()
	{
		super("Cafe Shania");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setSize(580, 500);
		
        //ImageIcon iraw_material = new ImageIcon("raw.png");
		ImageIcon ionline_order = new ImageIcon("online_order.png");
		ImageIcon ibill = new ImageIcon("bill.png");
		
		panel = new JPanel();
		panel.setLayout(null);
		panel.setBackground(Color.BLACK);
		
		title = new JLabel("Manager");
		title.setBounds(215,50,180,50);
		title.setForeground(Color.blue);
		title.setFont(new Font("Consolas",Font.ITALIC+Font.BOLD,30));
		panel.add(title);
		
		bbill = new JButton(ibill);
		bbill.setBounds(110,170,100,100);
		bbill.addActionListener(this);
		panel.add(bbill);
		
		bill = new JLabel("Bill");
		bill.setBounds(150,230,100,100);
		bill.setForeground(Color.WHITE);
		panel.add(bill);
		
		b_order = new JButton(ionline_order);
		b_order.setBounds(310,170,100,100);
		b_order.addActionListener(this);
		panel.add(b_order);
		
		order = new JLabel("Online Order");
		order.setBounds(320,230,100,100);
		order.setForeground(Color.WHITE);
		panel.add(order);
		
		/*br_material = new JButton(iraw_material);
		br_material.setBounds(230,350,100,100);
		br_material.addActionListener(this);
		panel.add(br_material);
		
		r_material = new JLabel("Raw Material");
		r_material.setBounds(240,420,100,100);
		r_material.setForeground(Color.WHITE);
		panel.add(r_material);*/
		
		this.add(panel);
		
		
	}
	public void actionPerformed(ActionEvent a)
	{
		if(a.getSource()==bbill)
		{
			Receite l = new Receite();
			l.setVisible(true);
		}
		else if(a.getSource()==b_order)
		{
			MyServer l = new MyServer();
		}
	}
	
	public static void main(String args[])
	{
		Manager l = new Manager();
		l.setVisible(true);
	}
	

}