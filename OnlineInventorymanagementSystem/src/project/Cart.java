package project;
import java.util.*;
import java.sql.*;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Scanner;
import java.time.LocalDateTime;
public class Cart {
	Connection conn=null;
	int id;
	public Cart(int id)
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
public void addCart() {
	Scanner sc=new Scanner(System.in);
	boolean flag=true;
	int tempPId=-1 ;
	String q1="";
	long tempQty=0;
	while(flag) {
	System.out.println("Enter  n to terminate (c) to continue:");
	String n=sc.next();
	if(n.equalsIgnoreCase("n"))
	{
		flag=false;
		break;
	}
	System.out.println("Enter the PId of product(choose from catelogue):");
	int pId1=sc.nextInt();
	setpId(pId1);
	System.out.println("Enter the quantity:");
	long pQty1=sc.nextLong();
	setQty(pQty1);
	Product ob=new Product();
	boolean available1=ob.checkAvailable(getpId());
	if(!available1)
	{
		System.out.println("you have entered the wrong PId .Enter the correct Input");
		continue;
	}
	boolean available2=ob.checkAvailableQty(getpId(),getQty());
	if(!available2)
	{
		System.out.println("Please enter the available quantity u are going beyond quantity");
		continue;
	}
	int c=0;
	String q2="select pId,qty from cart where pId='"+getpId()+"';";
	try(Statement stmt=conn.createStatement())
	{
		try(ResultSet rs=stmt.executeQuery(q2))
		{
			while(rs.next())
			{
				tempPId=rs.getInt(1);
				tempQty=rs.getLong(2);
				c++;
			}
		}
	}
	catch(Exception e)
	{
		e.printStackTrace();
	}
	if(c>0) {
		tempQty=tempQty+getQty();
		setQty(tempQty);
		q1="update cart set qty="+getQty()+" where pId='"+getpId()+"';";
				
	}
	else
		q1="Insert into cart values('"+getId()+"','"+getpId()+"','"+getQty()+"');";
		try(Statement stmt=conn.createStatement())
	{
		int rowsAffected=stmt.executeUpdate(q1);
		if(rowsAffected>0)
			System.out.println("Added to cart successfully");
		else
			System.out.println("couldn't add");
	}
	catch(Exception e)
	{
		e.printStackTrace();
	}
	}
}
public void removeFromCart() {
	Scanner sc=new Scanner(System.in);
	boolean flag=true;
	String tempPId="";
	String q1="";
	long tempQty=0;
	while(flag) {
	System.out.println("Enter  n to terminate:");
	String n=sc.next();
	if(n.equalsIgnoreCase("n"))
	{
		flag=false;
		break;
	}
	System.out.println("Enter the PId of product you want to remove:");
	int pId1=sc.nextInt();
	setpId(pId1);
	System.out.println("Do you want to remove all (a) or reduce product(r):");
	String req=sc.next();
	if(req.equalsIgnoreCase("a"))
		q1="delete from cart where pId='"+getpId()+"' and id='"+getId()+"';";
	else
	{
	System.out.println("Enter the quantity you want to decrement:");
	long pQty1=sc.nextLong();
	setQty(pQty1);
	System.out.println(getpId()+" "+getId());
	String q2="select qty from cart where pId='"+getpId()+"' and id='"+getId()+"';";
	try(Statement stmt=conn.createStatement())
	{
		try(ResultSet rs=stmt.executeQuery(q2))
		{
			while(rs.next())
			{
				tempQty=rs.getLong(1);
			}
		}
	}
	catch(Exception e)
	{
		e.printStackTrace();
	}
	if(tempQty!=0)
	{
		tempQty=tempQty-getQty();
		setQty(tempQty);
		if(tempQty>0)
			q1="update cart set qty="+getQty()+" where pId='"+getpId()+"' and Id='"+getId()+"';";
			else
				q1="delete from cart where pId='"+getpId()+"' and Id='"+getId()+";";
	}
	}
	try(Statement stmt=conn.createStatement())
	{
		int rowsAffected=stmt.executeUpdate(q1);
		if(rowsAffected>0)
			System.out.println("Deleted to cart successfully");
		else
			System.out.println("couldn't delete");
	}
	catch(Exception e)
	{
		e.printStackTrace();
	}
}
	
}
}

//create table cart(id int references customer(custid),pid int references product(pid),qty int); 
