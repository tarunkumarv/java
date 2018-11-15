import java.awt.Color;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.*;
import java.awt.Graphics.*;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JTable;
import javax.swing.*;
import javax.swing.JComboBox;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.*;
import java.sql.*;
 class JTableRow1 {
	 Connection con=null;
	  Statement st=null;
	  JFrame frame;
	  int inc=1;
	  DefaultTableModel model=new DefaultTableModel();
	  JTextField textId=new JTextField(20);
	JTextField textcount=new JTextField(20);
	JTextField texttot=new JTextField(20);
	String disc[]={"0","5","10","15","20","25"};        
    JComboBox<String> discb=new JComboBox<String>(disc);  
	
  JTableRow1()
  {
    frame=new JFrame();
	JTable table=new JTable(){
    DefaultTableCellRenderer renderRight = new DefaultTableCellRenderer();

    { // initializer block
        renderRight.setHorizontalAlignment(SwingConstants.RIGHT);
    }

   
    public TableCellRenderer getCellRenderer (int arg0, int arg1) {
        return renderRight;
    }
};
  
	
	
	
	         try{
			Class.forName("com.mysql.jdbc.Driver");
		//connecting to db II
		    con=DriverManager.getConnection("jdbc:mysql://localhost/practise","root","");
			st=con.createStatement();
            }
		   catch(Exception e)
		  {
		      System.out.println("connection error");
		  }
	JMenu file, edit,view;  
    JMenuItem i1, i2, i3, i4, i5;
	JMenuBar mb=new JMenuBar();
	file=new JMenu("file");
	edit=new JMenu("edit");
    view=new JMenu("view");
	file.setFont(new Font("Myraid",Font.BOLD,15));
	edit.setFont(new Font("Myraid",Font.BOLD,15));
	view.setFont(new Font("Myraid",Font.BOLD,15));
    mb.add(file);
    mb.add(edit);
    mb.add(view);
   	
	  
	Object[] columns={"s.no","id","name","price","count","cprice"};
	

JTableHeader head = table.getTableHeader();
head.setFont(new Font("Clarendon Serif", Font.BOLD, 18));
 

	
	model.setColumnIdentifiers(columns);
	//table.setBackground(Color.cyan);
	head.setBackground(Color.white);
	table.setModel(model);
	Color tb=new Color(0,255,255);
	Color fb=new Color(255,255,204);
	table.setBackground(tb);
	frame.setBackground(Color.blue);
	table.setForeground(Color.black);
	Font font=new Font(" ",1,22);
	table.setRowHeight(20);
	table.setFont(font);
    table.setLocation(2000,2000);
	JPanel panel=new JPanel();
	panel.add(table, BorderLayout.CENTER);
	panel.add(table.getTableHeader(), BorderLayout.NORTH);
	
	JLabel l1,l2;
	 l1 = new JLabel("Product ID :");
	l2=new JLabel("count :");
	JLabel l3=new JLabel("Discount :");
	JLabel header=new JLabel("CONSUMER PRODUCT SALES");
	header.setFont(new Font("ArialBlack", Font.BOLD, 32));
	header.setBounds(195,30,500,100);
	 
	JButton btntotal=new JButton("Total");
	 JButton btnAdd=new JButton("add");
     JButton btnprint=new JButton("print");	
	texttot.setBounds(685,260,100,25);                         
	 textId.setBounds(100,220,200,25);
	 textcount.setBounds(100,250,200,25);
	 btnAdd.setBounds(200,290,100,25);
	 btntotal.setBounds(680,230,100,25);                                  
	 l1.setBounds(10,220,200,25);
	 l2.setBounds(10,250,200,25);
	 l3.setBounds(350,225,130,25);
      	 
      mb.setBounds(10,25,1000,30);	 
	  discb.setBounds(350,260,150,25);                          
	  discb.setSelectedItem("0");
	  btnprint.setBounds(760,560,100,25);
	 JScrollPane pane=new JScrollPane(table);
	 pane.setBounds(10,10,880,200);
	  pane.setLocation(50,350);
	 
	 textcount.setText("0");
	 frame.setLayout(null);
	 frame.add(mb);
	 frame.add(pane);
	 frame.add(panel);
	 frame.add(textId);
	 frame.add(textcount);
	 frame.add(texttot);
     frame.add(header);	
	 frame.add(btnAdd);
	 frame.add(btntotal);
	 frame.add(l1);
	 frame.add(l2);
	 frame.add(discb);
	 frame.add(l3);
	 frame.add(btnprint);
	 frame.setSize((int)(Toolkit.getDefaultToolkit().getScreenSize().getWidth()), (int)(Toolkit.getDefaultToolkit().getScreenSize().getHeight()));
     frame.setMaximizedBounds(new Rectangle(0,0 , 500, 500)); 
	 frame.setLocationRelativeTo(null);
	 frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	 frame.setVisible(true);
	 
	 btnAdd.addActionListener(new ActionListener(){
	 public void actionPerformed(ActionEvent ae){
		 
	         actionlistener(ae);  
          
		}});
		 btntotal.addActionListener(new ActionListener(){
	 public void actionPerformed(ActionEvent ae){
		 
	         actionlistener1();  
          
		}});
		btnprint.addActionListener(new ActionListener(){
			 public void actionPerformed(ActionEvent ae){
				 
				        
			 java.awt.EventQueue.invokeLater(new Runnable(){
							
								public void run()
								  {
									  new JTableRow1();
								  }
							  });
				 frame.setVisible(false);
				
		}});
		 
		  
	 }
	 Object[] row=new Object[6];
	 void actionlistener(ActionEvent ae)
		{
		   int i=0;
           float a=0;		   
		   String b=null;
		   row[0]=inc++;
		   row[1]=textId.getText();
		   ResultSet rs=null;
		   try{
			 
		   String qry="select * from product where id="+row[1];
		   rs=st.executeQuery(qry);
		   
		   }
		    catch(Exception e)
		   {
			   System.out.println("Sql exception caught:"+e);
		   }
		   try{
			   while(rs.next()==false){
				   
				   JOptionPane optionPane = new JOptionPane();
                       optionPane.setMessage("ID NOT FOUND");
					JDialog  dialog = optionPane.createDialog(null, "Connect to a server");
				     dialog.setVisible(true);
                       
                         //if(dialog == JOptionPane.OK_OPTION) {} 
						 
                           
					   
						
                        }
				   
			   
					   
		   a=rs.getFloat(3);
		    b=rs.getString(2);
		   }
		  
			   
		      
		   
		   catch(Exception e)
		   {System.out.println("try 2"+e);
		   }
		    row[2]=b;
		   row[3]=a;
		 row[4]=textcount.getText();
		   String s=textcount.getText();
		   int j=Integer.parseInt(s);
		   if(Integer.parseInt(textcount.getText())==0)
		   {
	       
			   j=1;
			   row[5]=a*j;
		   }
		   else
		   row[5]=a*j;
		  
		   
		   model.addRow(row);
		}
		
			
		void actionlistener1()
		{
			float total=0;
		  int rows=model.getRowCount();
		  for(int i=0;i<rows;i++)
		  {
			  total=total+Float.parseFloat(model.getValueAt(i,5).toString());
			  
			  
        }
		String s = discb.getItemAt(discb.getSelectedIndex()).toString();
		int k=Integer.parseInt(s);
			  total=total-total*k/100;
			
		texttot.setText(Float.toString(total));
		
		}
		
		  public void paint(Graphics g) {
		g.setColor(Color.red);
        g.drawLine(20,20,20,120);
		
    }

		
	
		
	 public static void main(String args[])
	 {
		     
	   java.awt.EventQueue.invokeLater(new Runnable(){
								  public void run()
								  {
									  new JTableRow1();
								  }
							  });
							   
							  
	 }
	 }
 