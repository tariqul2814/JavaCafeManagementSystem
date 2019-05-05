import java.lang.*;
import java.util.*;
import javax.swing.*;
import java.awt.geom.*;
import javax.swing.border.*;
import java.awt.*;
import java.awt.event.*;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.data.general.DefaultPieDataset;
import org.jfree.data.general.PieDataset;
import org.jfree.ui.ApplicationFrame;
import org.jfree.ui.RefineryUtilities;

public class Adminlogin extends JFrame implements ActionListener
{
	private JButton bfinance, bsalesman, bpass, bfooditem, bfoodpiechart,exit,status;
	private JLabel lfinance, lsalesman, lpass, lfooditem, lfoodpiechart,lexit,lstatus;
	//private RoundButton rb;
	private JPanel panel;
	
	public Adminlogin()
	{
		super("Cafe Shania");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setSize(600, 800);
		
		ImageIcon ifinance = new ImageIcon("finance.png");
		ImageIcon isalesman = new ImageIcon("salesman.png");
		ImageIcon ipass = new ImageIcon("pass.png");
		ImageIcon ifood = new ImageIcon("food.png");
		ImageIcon ipie = new ImageIcon("pie.png");
		ImageIcon icustomerlist = new ImageIcon("customerlist.png");
		//ImageIcon iback = new ImageIcon("back.png");
		ImageIcon iexit = new ImageIcon("exit.png");
		
		panel = new JPanel();
		panel.setLayout(null);
		panel.setBackground(Color.BLACK);
		
		bfinance = new JButton(ifinance);
		bfinance.setBounds(100,150,100,100);
		bfinance.addActionListener(this);
		panel.add(bfinance);
		
		lfinance = new JLabel("Finance");
		lfinance.setBounds(120,210,100,100);
		lfinance.setForeground(Color.WHITE);
		panel.add(lfinance);
		
		bsalesman = new JButton(isalesman);
		bsalesman.setBounds(250,150,100,100);
		//bsalesman.setBorder(new RoundedBorder(10));
		bsalesman.addActionListener(this);
		panel.add(bsalesman);
		
		lsalesman = new JLabel("Salesman");
		lsalesman.setBounds(270,210,100,100);
		lsalesman.setForeground(Color.WHITE);
		panel.add(lsalesman);
		
		bpass = new JButton(ipass);
		bpass.setBounds(400,150,100,100);
		bpass.addActionListener(this);
		panel.add(bpass);
		
		lpass = new JLabel("Password");
		lpass.setBounds(410,210,100,100);
		lpass.setForeground(Color.WHITE);
		panel.add(lpass);
		
		bfooditem = new JButton(ifood);
		bfooditem.setBounds(100,320,100,100);
		bfooditem.addActionListener(this);
		panel.add(bfooditem);
		
		lfooditem = new JLabel("Food Item");
		lfooditem.setBounds(120,380,100,100);
		lfooditem.setForeground(Color.WHITE);
		panel.add(lfooditem);
		
		bfoodpiechart = new JButton(ipie);
		bfoodpiechart.setBounds(250,320,100,100);
		bfoodpiechart.addActionListener(this);
		panel.add(bfoodpiechart);
		
		lfoodpiechart = new JLabel("Food Pie Chart");
		lfoodpiechart.setBounds(265,380,100,100);
		lfoodpiechart.setForeground(Color.WHITE);
		panel.add(lfoodpiechart);
		
		status = new JButton("Status");
		status.setBounds(400,320,100,100);
		status.addActionListener(this);
		panel.add(status);
		
		lstatus = new JLabel("Status");
		lstatus.setBounds(435,380,100,100);
		lstatus.setForeground(Color.WHITE);
		panel.add(lstatus);
		
		exit = new JButton(iexit);
		exit.setBounds(100,490,100,100);
		exit.addActionListener(this);
		panel.add(exit);
		
		lexit = new JLabel("Exit");
		lexit.setBounds(120,550,100,100);
		lexit.setBackground(Color.BLACK);
		panel.add(lexit);
		
		//lback = new JLabel("Back");
		//lback.setBounds(170,550,100,100);
		//lback.setForeground(Color.WHITE);
		//panel.add(lback);

		
		this.add(panel);
	}
	
	public void actionPerformed(ActionEvent ae)
	{
		if(ae.getSource()==bfinance)
		{
			FinancialRecord l = new FinancialRecord();
			l.setVisible(true);
		}
		else if(ae.getSource()==bsalesman)
		{
			SalesMan l = new SalesMan();
			l.setVisible(true);
		}
		else if(ae.getSource()==bpass)
		{
			PassChng l = new PassChng();
			l.setVisible(true);
		}
		else if(ae.getSource()==bfooditem)
		{
			FoodItem l= new FoodItem();
			l.setVisible(true);
		}
		else if(ae.getSource()==bfoodpiechart)
		{
			PieChart_AWT demo = new PieChart_AWT( "Food Sales" );  
			demo.setSize( 560 , 367 );    
			RefineryUtilities.centerFrameOnScreen( demo );    
			demo.setVisible( true );
		}
		
		else if(ae.getSource()==status)
		{
			Statusall l = new Statusall();
			l.setVisible(true);
		}

		else if(ae.getSource()==exit)
		{
			System.exit(0);
		}
	}
}
  