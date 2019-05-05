import java.lang.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class LogIn extends JFrame implements ActionListener,KeyListener
{
	private JButton Admin,Manager;
	private JLabel label1,label2;
	private JPanel panel;
	public LogIn()
	{
		super("Cafe Shaina");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setSize(500, 500);
		
		ImageIcon adminImage = new ImageIcon("D:\\Java\\Final Project\\manager.png");
		ImageIcon manageImage = new ImageIcon("D:\\Java\\Final Project\\admin.png");
		
		panel = new JPanel();
		panel.setLayout(null);
		
		Admin = new JButton(adminImage);
		Admin.setBounds(100,150,100,100);
		Admin.addActionListener(this);
		panel.add(Admin);
		
		Manager = new JButton(manageImage);
		Manager.setBounds(280,150,100,100);
		Manager.addActionListener(this);
		panel.add(Manager);
		
		label1 = new JLabel("ADMIN");
		label1.setBounds(130,250,80,40);
		panel.add(label1);
		
		label2 = new JLabel("MANAGER");
		label2.setBounds(305,250,80,40);
		panel.add(label2);
		
		this.add(panel);
	}
	public void actionPerformed(ActionEvent ae)
	{
		if(ae.getSource()==Admin)
		{
			AdminPass a = new AdminPass();
			a.setVisible(true);
		}
		else if(ae.getSource()==Manager)
		{
			ManagerPass m = new ManagerPass();
			m.setVisible(true);
		}
	}
	public void keyPressed(KeyEvent e)
	{
		
	}
	public void keyReleased(KeyEvent e)
	{}
	public void keyTyped(KeyEvent e)
	{}
}