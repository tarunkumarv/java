import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import javax.swing.*;

class login  implements ActionListener
{
  JTextField un,ps;
  JButton b;
  Statement st;
  Connection con;
  JFrame frame=new JFrame();
  JLabel l1,l2;
  
  
  login()
  {
    frame. setTitle("login page");
	frame.setFont(new Font("Verdana", Font.BOLD, 12)); 
	 frame.setSize((int)(Toolkit.getDefaultToolkit().getScreenSize().getWidth()), (int)(Toolkit.getDefaultToolkit().getScreenSize().getHeight()));
     frame.setMaximizedBounds(new Rectangle(0,0 , 500, 500)); 
	 frame.setLocationRelativeTo(null);
    frame.setLayout(null);
	frame.setVisible(true);
	
	l1=new JLabel("username:");
	l1.setBounds(500,311,200,25);
	frame.add(l1);
	un=new JTextField(10);
	un.setBounds(600,316,250,30);
    l2=new JLabel("pasword:");
	l2.setBounds(505,354,300,25);
	ps=new JTextField(10);
	ps.setBounds(600,358,250,30);
	b=new JButton("login");
	b.setBounds(750,410,105,25);
	b.setBackground(Color.blue);
	b.setForeground(Color.white);
	frame.add(un);
	frame.add(l2);
	frame.add(ps);
	frame.add(b);
	
     frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	b.addActionListener(this);


		
  }
  public void actionPerformed(ActionEvent a){
	  
	  String user=un.getText();
	  String pass=ps.getText();
	
	 
	 if(user.equals("root")&&pass.equals("null"))
	  {
         try{
			Class.forName("com.mysql.jdbc.Driver");
		//connecting to db II
		    con=DriverManager.getConnection("jdbc:mysql://localhost/practise",un.getText(),"");
			st=con.createStatement();
	
			new JTableRow1();
            }
		   catch(Exception e)
		  {
		      System.out.println("connection error");
	  }
	  }
	  else 
	  {  
		  if(!user.equals("root")){
		  un.setText("username error");
	        un.setForeground(Color.red);
		  }
		  
	      
		  if(!pass.equals("null")){
			  ps.setText("password error");
		        ps.setForeground(Color.red);
		  }
		  
	  }
		  
	  
  }
 
}
class log
{
	public static void main(String[] args)
	{
		new login();
	}
}

	