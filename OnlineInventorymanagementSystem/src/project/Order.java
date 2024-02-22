package project;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.time.LocalDateTime;
import java.util.Scanner;

public class Order {
	Connection conn=null;
	int id;
	public Order(int id)
	{
		this.id=id;
		Connections1 c=new Connections1();
		conn=c.connect();
	}
	private String email;
	int pId;
	private long price,qty;
	protected void setEmail(String email)
	{
		this.email=email;
	}
	protected String getEmail()
	{
		return email;
	}
	protected void setpId(int pId)
	{
		this.pId=pId;
	}
	protected int getpId()
	{
		return pId;
	}
	protected void setQty(long qty)
	{
		this.qty=qty;
	}
	protected long getQty()
	{
		return qty;
	}
	protected void setId(int id)
	{this.id=id;}
	protected int getId()
	{return id;}
	protected void setPrice(long price)
	{this.price=price;}
	protected long getPrice()
	{return price;}
public void placeOrder() {
	Scanner sc=new Scanner(System.in);
	String tempPId="";
	long tempQty=0,tempAvailableQty=0;
	long tempPrice=0,p=0,sum=0;
	System.out.println("Are u sure u want to place order that is available in your cart(y/n):");
	String s=sc.next();
	if(s.equalsIgnoreCase("y"))
	{
		String q2="select c.pId,c.qty,p.price,p.qty from cart c inner join product p on c.pId=p.pId where c.id='"+getId()+"';";
		try(Statement stmt=conn.createStatement())
		{
			try(ResultSet rs=stmt.executeQuery(q2))
			{
				while(rs.next())
				{
					tempPId=rs.getString(1);
					tempQty=rs.getInt(2);
					tempPrice=rs.getLong(3);
					tempAvailableQty=rs.getLong(4);
					long reduce=tempAvailableQty-tempQty;
					p=tempQty*tempPrice;
					sum+=p;
					System.out.println(sum);
					setPrice(sum);
					String q1="update  product set qty="+reduce+" where pId='"+tempPId+"';";
					try(Statement stmt1=conn.createStatement())
				{
					int rowsAffected=stmt1.executeUpdate(q1);
					if(rowsAffected>0)
						System.out.println(tempPId+" added successfuly");
					else
						System.out.println("couldn't add");
				}
				catch(Exception e)
				{
					e.printStackTrace();
				}
				}
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		
	
	 LocalDateTime currentDateTime = LocalDateTime.now();
	 PaymentMethod p1=new PaymentMethod(sum);
	 String pay1="";
	  pay1=p1.pay();
	  //System.out.println(pay1);
	 String paymentStatus="";
	 if(pay1.equals("online"))
		 paymentStatus="paid";
	 else
		 paymentStatus="pending";
	String q3="Insert into order_status(ordered_date,custid,total_purchased_amount,order_status,payment_method,payment_status) values('"+currentDateTime+"','"+getId()+"','"+getPrice()+"','ready to deliver','"+pay1+"','"+paymentStatus+"');";
	try(Statement stmt3=conn.createStatement())
	{
		int rowsAffected=stmt3.executeUpdate(q3);
		if(rowsAffected>0)
			System.out.println(tempPId+" added successfuly");
		else
			System.out.println("couldn't add");
	}
	catch(Exception e)
	{
		e.printStackTrace();
	}
}
}
public void cancelOrder()
{
	String tempPId="";
	long tempQty=0,tempAvailableQty=0;
	long tempPrice=0,p=0,add=0;
	String status="";
	int reduce=0;String q3="";
	Scanner sc=new Scanner(System.in);
	String sql="select order_status from order_status where custid='"+getId()+"';";
	try(Statement stmt=conn.createStatement())
	{
		try(ResultSet rs=stmt.executeQuery(sql))
		{
			while(rs.next())
			{
				status=rs.getString(1);
			}
		}
	}
	catch(Exception e)
	{
		e.printStackTrace();
	}
	if(status.equals("ready to deliver"))
	{
		System.out.println("Enter 1 -to cancel all,2-to cancel particular products,3-to exit");
		String s=sc.next();
		if(s.equals("1"))
		{
			String q2="select c.pId,c.qty,p.price,p.qty from cart c inner join product p on c.pId=p.pId where c.id='"+getId()+"';";
			try(Statement stmt=conn.createStatement())
			{
				try(ResultSet rs=stmt.executeQuery(q2))
				{
					while(rs.next())
					{
						tempPId=rs.getString(1);
						tempQty=rs.getInt(2);
						tempPrice=rs.getLong(3);
						tempAvailableQty=rs.getLong(4);
						add=tempAvailableQty+tempQty;
						p=tempQty*tempPrice;
						String q1="update  product set qty="+add+" where pId='"+tempPId+"';";
						 q3="Delete from cart where id='"+getId()+"' and pId='"+tempPId+"';";
						String q5="Delete from order_status where id='"+getId()+"';";
						try(Statement stmt1=conn.createStatement())
					{
						int rowsAffected=stmt1.executeUpdate(q1);
						int rowsAffected1=stmt1.executeUpdate(q3);
						int rowsAffected2=stmt1.executeUpdate(q5);
						if(rowsAffected>0 && rowsAffected1>0 && rowsAffected2>0)
							System.out.println("canceled successfully");
						else
							System.out.println("couldn't cancel");
					}
					catch(Exception e)
					{
						e.printStackTrace();
					}
					}
				}
			}
			catch(Exception e)
			{
				e.printStackTrace();
			}
			
		}
		else if(s.equals("2"))
		{
			while(true) {
				System.out.println("enter (n) to terminate (y) to continue:");
				String ss=sc.next();
				if(ss.equalsIgnoreCase("n"))
					break;
				System.out.println("Enter pId you want to cancel:");
				int pid=sc.nextInt();
				System.out.println("Enter the quntity of product you want to cancel:");
				int q=sc.nextInt();
				String q2="select c.pId,c.qty,p.price,p.qty from cart c inner join product p on c.pId=p.pId where c.id='"+getId()+"' and c.pId='"+pid+"';";
			try(Statement stmt=conn.createStatement())
			{
				try(ResultSet rs=stmt.executeQuery(q2))
				{
					while(rs.next())
					{
						tempPId=rs.getString(1);
						tempQty=rs.getInt(2);
						tempPrice=rs.getLong(3);
						tempAvailableQty=rs.getLong(4);
						add=tempAvailableQty+tempQty;
						p=tempQty*tempPrice;
						 reduce+=p;
						String q1="update  product set qty="+add+" where pId='"+tempPId+"';";
						if(tempQty-q==0)
						q3="Delete from cart where id='"+getId()+"' and pId='"+tempPId+"';";
						else if(tempQty>q)
							q3="update  cart set qty="+(tempQty-q)+" where pId='"+tempPId+"' and id="+getId()+"';";
						try(Statement stmt1=conn.createStatement())
					{
						int rowsAffected=stmt1.executeUpdate(q1);
						int rowsAffected1=stmt1.executeUpdate(q3);
						if(rowsAffected>0 && rowsAffected1>0)
							System.out.println("canceled successfully");
						else
							System.out.println("couldn't cancel");
					}
					catch(Exception e)
					{
						e.printStackTrace();
					}
					}
				}
			}
			catch(Exception e)
			{
				e.printStackTrace();
			}
			}
			String q4="update  order_status set total_purchased_Amount=total_purchased_Amount-"+reduce+" where custid='"+getId()+"';";
			try(Statement stmt1=conn.createStatement())
		{
			int rowsAffected=stmt1.executeUpdate(q4);
			if(rowsAffected>0)
				System.out.println("updated successfully");
			else
				System.out.println("couldn't cancel");
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		}
		
		else
			System.out.println("Enter correct input");
	}
	else
		System.out.println("Sorry there is no product of yours that has to be deleivered");
}
}
/*create table order_status(ordered_date datetime,deleivered_date datetime,custid int references customer(custid),sum int(20) not null,order_status varchar(30),payment_method varchar(30),payment_status varchar(30)); */
