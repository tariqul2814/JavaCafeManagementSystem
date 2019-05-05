import java.lang.*;
import javax.swing.JOptionPane;
import java.sql.*;
import java.util.Scanner;
import javax.swing.JPanel;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.data.general.DefaultPieDataset;
import org.jfree.data.general.PieDataset;
import org.jfree.ui.ApplicationFrame;
import org.jfree.ui.RefineryUtilities;
 
public class PieChart_AWT extends ApplicationFrame {
	
   static int rec;
   static String arrdb[];
   static double arrdb1[];
   
   public PieChart_AWT( String title ) {
      super( title ); 
      setContentPane(createDemoPanel( ));
   }
	
	public static void table()
	{
	String m="",n="";
	try{
			Class.forName("oracle.jdbc.driver.OracleDriver");
			Connection con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","promi","1234");
			Statement stmt=con.createStatement();
			ResultSet rs=stmt.executeQuery("select count(*) from FoodPrice");
		while(rs.next())  
			m=rs.getString(1);
		int x = Integer.valueOf(m);
		rec = x;
			int i=0;
			rs=stmt.executeQuery("select * from FoodPrice");
			arrdb = new String [rec];
			arrdb1 = new double [rec];
			while(rs.next())
			{
				arrdb[i]=rs.getString("food_name");
				double a = Double.parseDouble(rs.getString("QUANTITYSELL"));
				arrdb1[i]=a;
				i++;
			}
		con.close();
	}catch(Exception e){ System.out.println(e);} 
	}

   private static PieDataset createDataset( ) {
	   table();
      DefaultPieDataset dataset = new DefaultPieDataset( );
      for(int i=0;i<rec;i++)
	{
	  dataset.setValue( arrdb[i] , new Double( arrdb1[i] ) ); 
	 }		  
      return dataset;         
   }
   
   private static JFreeChart createChart( PieDataset dataset ) {
      JFreeChart chart = ChartFactory.createPieChart(      
         "Food Item Review",   // chart title 
         dataset,          // data    
         true,             // include legend   
         true, 
         false);

      return chart;
   }
   
   public static JPanel createDemoPanel( ) {
      JFreeChart chart = createChart(createDataset( ) );  
      return new ChartPanel( chart ); 
   }
   
}