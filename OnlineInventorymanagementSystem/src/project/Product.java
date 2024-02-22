package project;
import java.sql.*;
import java.util.*;
public class Product {
	private String pName,pCategory,pBrand,tableName;
	private long price,qty;
	private int pId;
	Connection conn=null;
public Product()
{
	Connections1 c1=new Connections1();
	conn=c1.connect();
	tableName="product";
}
public int getpId() {
    return pId;
}

public void setpId(int pId) {
    this.pId = pId;
}

// Getter and Setter for pName
public String getpName() {
    return pName;
}

public void setpName(String pName) {
    this.pName = pName;
}

// Getter and Setter for category
public String getPCategory() {
    return pCategory;
}

public void setPCategory(String category) {
    this.pCategory = category;
}

// Getter and Setter for pBrand
public String getpBrand() {
    return pBrand;
}

public void setpBrand(String pBrand) {
    this.pBrand = pBrand;
}

// Getter and Setter for tableName
public String getTableName() {
    return tableName;
}

public void setTableName(String tableName) {
    this.tableName = tableName;
}
public long getPrice() {
    return price;
}

public void setPrice(long price) {
    this.price = price;
}

// Getter and Setter for qty
public long getQty() {
    return qty;
}

public void setQty(long qty) {
    this.qty = qty;
}
public void addProduct()
{
	Scanner sc=new Scanner(System.in);
	
    System.out.print("Product Name: ");
   setpName(sc.nextLine());

    System.out.print("Brand: ");
    setpBrand(sc.nextLine());
    
    System.out.print("Price: ");
    setPrice(sc.nextLong());

    System.out.print("Quantity: ");
    setQty(sc.nextLong());
 
    System.out.print("Category: ");
    sc.nextLine();
    setPCategory(sc.nextLine());
    System.out.println(getPCategory());
    
    try(Statement stmt=conn.createStatement())
    {
 	   String q1="Insert  into "+getTableName()+"(pName,pBrand,price,qty,pCategory) values('"+getpName()+"','"+getpBrand()+"','"+getPrice()+"','"+getQty()+"','"+getPCategory()+"');";
 	   int rowsAffected=stmt.executeUpdate(q1);
 	   if(rowsAffected>0)
 	   {
 		   System.out.println("Product Added ");
 		   
 	   }
 	   else
 		   System.out.println("sorry Couldn't add");
    }
    catch(Exception e)
    {
 	   e.printStackTrace();
    }
     
}
public void updateProduct()
{
	String column="";
	Scanner sc=new Scanner(System.in);
	boolean outFlag=true,inFlag=true,f=false;
	while(outFlag) {
	System.out.println("Enter the ProductId you want to update:(enter n to terminate)");
	String id=sc.next();
	//System.out.println(id);
	if(id.equalsIgnoreCase("n"))
	{
		outFlag=false;
		break;
	}
	int tempId=-1;
	setpId(Integer.valueOf(id));
	String q1="select pId from "+getTableName()+" where pId='"+getpId()+"';";
	try(Statement stmt=conn.createStatement())
	{
		try(ResultSet rs=stmt.executeQuery(q1))
		{
			while(rs.next())
			{
				tempId=rs.getInt(1);
			}
			if(getpId()==tempId)
			{
				f=true;
			}
		}
	}
	catch(Exception e)
	{
		e.printStackTrace();
	}
	//System.out.println(getpId()+" "+tempId);
	if(f)
	{
		while(inFlag)
		{
			System.out.print("Enter 1 to update pName,2 to update pBrand,3-price,4 to update qty,5 to update category,6 to quit");
			String n=sc.next();
			switch(n)
			{
			case "1":
				column="pName";
				break;
			case "2":
				column="pbrand";
				break;
			case "3":
				column="price";
				break;
			case "4":
				column="qty";
				break;
			case "5":
				column="pCategory";
				break;
			case "6":
				inFlag=false;
				break;	
				
			}
			if(!inFlag)
				break;
			if(column.equals("price") ||column.equals("qty") )
			{	long amt=sc.nextLong();
			q1="update "+getTableName()+" set "+column+"='"+amt+"' where pId='"+getpId()+"';";
			}
			else
			{
				System.out.println("Enter new "+column);
				sc.nextLine();
				String s=sc.nextLine();
				q1="update "+getTableName()+" set "+column+"='"+s+"' where pId='"+getpId()+"';";

			}
			
			
			try(Statement stmt=conn.createStatement())
			{
				int rowsAff=stmt.executeUpdate(q1);
				if(rowsAff>0)
					System.out.println(column+" updated successfully");
				else
					System.out.println("coudn't update");
			}
			catch(Exception e)
			{
				e.printStackTrace();
			}
		}
	}
	}
}
public void deleteProduct()
{
	Scanner sc=new Scanner(System.in);
	String q1="";
	boolean flag=true;
	while(flag) {
		System.out.println("1 delete by pId,2 delete by pname,3 delete by brand,4 delete by category,5 exit");
		String ip=sc.next();
		if(ip.equals("4"))
		{
			flag=false;
			break;
		}
		else if(ip.equals("1")) {
	System.out.println("Enter the pId you want to delete:");
	String id=sc.next();
	setpId(Integer.valueOf(id));
	q1="Delete from "+getTableName()+" where pId='"+getpId()+"';";
		}
		else if(ip.equals("2"))
		{
			System.out.println("Enter the product name in which  you want to delete all the products:");
			sc.nextLine();
			String dept=sc.nextLine();
			setpName(dept);
			q1="Delete from "+getTableName()+" where pName='"+getpName()+"';";
		}
		else if(ip.equals("3"))
		{
			System.out.println("Enter the brand name  you want to delete:");
			sc.nextLine();
			String id=sc.nextLine();
			setpBrand(id);
			q1="Delete from "+getTableName()+" where pBrand='"+getpBrand()+"';";
		}
		else if(ip.equals("4"))
		{
			System.out.println("Enter the category you want to delete:");
			sc.nextLine();
			String id=sc.next();
			setPCategory(id);
			q1="Delete from "+getTableName()+" where pCategory='"+getPCategory()+"';";
		}
		else if(ip.equals("5"))
			break;
		else
		{
			System.out.println("Enter correct input");
			continue;
		}
	try(Statement stmt=conn.createStatement())
	{
		int rowsAff=stmt.executeUpdate(q1);
		if(rowsAff>0)
			System.out.println("Deleted successfully");
		else
			System.out.println("coudn't delete");
	}
	catch(Exception e)
	{
		e.printStackTrace();
	}
	
}
}
public void viewProduct(String type)
{
	String q1="";
	if(type.equalsIgnoreCase("all"))
	q1="select * from "+getTableName()+";";
	else if(type.equalsIgnoreCase("available"))
		q1="select * from "+getTableName()+" where qty>0;";
	else if(type.equalsIgnoreCase("NotAvailable"))
		q1="select * from "+getTableName()+" where qty<=0;";
	//System.out.println("|  pId   |    pName     |         pBrand           |    price    |  qty   |");
	System.out.printf("%-20s%-20s%-20s%-20s%-20s%-20s|%n","------","------","------","------","------","------","------","------","------");
	System.out.printf("|%-20s%-20s%-20s%-20s%-20s%-20s|%n","pId","pName","pBrand","price","Quantity","category");
	System.out.printf("%-20s%-20s%-20s%-20s%-20s%-20s|%n","------","------","------","------","------","------","------","------","------");
	
	try(Statement stmt=conn.createStatement())
	{
		try(ResultSet rs=stmt.executeQuery(q1))
		{
			while(rs.next())
			{
				int temp1=rs.getInt(1);
				String temp2=rs.getString(2);
				String temp3=rs.getString(3);
				long temp4=rs.getLong(4);
				long temp5=rs.getLong(5);
				String temp6=rs.getString(6);
				System.out.printf("|%-20d%-20s%-20s%-20d%-20d%-20s\n", temp1, temp2, temp3, temp4, temp5, temp6);
			}
		}
	}
	catch(Exception e)
	{
		e.printStackTrace();
	}
}
public boolean checkAvailable(int pId1)
{
	String q1="select pId from "+getTableName()+" where qty>0 and pId='"+pId1+"';";
	int p=-1;
	try(Statement stmt=conn.createStatement())
	{
		try(ResultSet rs=stmt.executeQuery(q1))
		{
			while(rs.next())
			{
			p=rs.getInt(1);
			}
		}
	}
	catch(Exception e)
	{
		e.printStackTrace();
	}
	if(p==pId1)
	{
		return true;
	}
	else
		return false;
}

public void searchProduct()
{
	Scanner sc=new Scanner(System.in);
	String sql="";

	while(true) {
		System.out.println("1-search by product name,2-search by brand,3-search by cattegory,4-search by pId");
		String in=sc.next();
		sc.nextLine();
	if(in.equals("1")) {
		System.out.print("Enter name you want to search:");
		String t=sc.nextLine();
		sql="select * from "+getTableName()+" where pName like '"+t+"%';";
	}
	else if(in.equals("2")) {
		System.out.print("Enter brand you want to search:");
		String t=sc.nextLine();
		sql="select * from "+getTableName()+" where pBrand like '"+t+"%';";
	}
	else if(in.equals("3")) {
		System.out.print("Enter Category you want to search:");
		String t=sc.nextLine();
		sql="select * from "+getTableName()+" where pCategory like '"+t+"%';";
	}
	else if(in.equals("4")) {
		System.out.print("Enter Id you want to search:");
		int t=sc.nextInt();
		sql="select * from "+getTableName()+" where pId='"+t+"';";
	}
	else if(in.equals("5")) {
		break;
	}
	else {
		System.out.println("enter correct Input");
		continue;}
	System.out.println("|  pId   |    pName     |         pBrand           |    price    |  qty   | category");
	try(Statement stmt=conn.createStatement())
	{
		try(ResultSet rs=stmt.executeQuery(sql))
		{
			while(rs.next())
			{
				int temp1=rs.getInt(1);
				String temp2=rs.getString(2);
				String temp3=rs.getString(3);
				long temp4=rs.getLong(4);
				long temp5=rs.getLong(5);
				String temp6=rs.getString(6);
				System.out.println("|  "+temp1+"   |  "+temp2+"  |         "+temp3+"           |    "+temp4+"    |  "+temp5+"    | "+temp6+"|");
			}
		}
	}
	catch(Exception e)
	{
		e.printStackTrace();
	}
}
}
public boolean checkAvailableQty(int tempId,long tempQty)
{
	boolean flag=false;
	String sql="select qty from product where pId='"+tempId+"';";
	long temp1=-1;
	try(Statement stmt=conn.createStatement())
	{
		try(ResultSet rs=stmt.executeQuery(sql))
		{
			while(rs.next())
			{
				 temp1=rs.getLong(1);
				
			}
		}
	}
	catch(Exception e)
	{
		e.printStackTrace();
	}
	if(temp1>=tempQty)
		flag=true;
	return flag;
}
}
/*create table product(pId int Auto_Increment primary key,pName varchar(30) Not Null,pBrand varchar(30),price int(20) Not Null,qty int(20) default 0, pcategory varchar(30))Auto_increment=1;*/