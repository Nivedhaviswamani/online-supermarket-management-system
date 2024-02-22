package project;
import java.util.*;
import java.sql.*;
public class SignUpPage {
	Connection conn=null;
	String email,password,tableName="";
	int id;
	protected void setEmail(String email)
	{this.email=email;}
	protected String getEmail()
	{return email;}
	protected void setPassword(String password)
	{this.password=password;}
	protected String getPassword()
	{return password;}
	protected void setTableName(String tableName)
	{this.tableName=tableName;}
	protected String getTableName()
	{return tableName;}
	protected void setId(int id)
	{this.id=id;}
	protected int getId()
	{return id;}
public SignUpPage()
{
	Connections1 c1=new Connections1();
	conn=c1.connect();
}
protected int signIn()
{
	int id1=-1;
	Scanner sc=new Scanner(System.in);
	System.out.println("Enter Your email:");
	String tempEmail=sc.next();
	setEmail(tempEmail);
	System.out.println("Enter the password:");
	String tempPassword=sc.next();
	setPassword(tempPassword);
	String column;
	//System.out.println(getTableName());
	if(tableName.equalsIgnoreCase("admin"))
		column="id";
	else
		column="custid";
	String sql1="Select email,password from "+getTableName()+" where email='"+getEmail()+"';";
	try(Statement stmt=conn.createStatement())
	{
		try(ResultSet rs=stmt.executeQuery(sql1))
		{
			while(rs.next())
			{
				tempEmail=rs.getString(1);
				tempPassword=rs.getString(2);
			}
			Password p=new Password();
			boolean equal=p.checkPassword(password,tempPassword);
				if(equal)
                {
					 String s2="Select "+column+" from "+getTableName()+" where email='"+getEmail()+"';";
             		try(Statement st=conn.createStatement())
             		{
             			try(ResultSet rs1=st.executeQuery(s2))
             			{
             				while(rs1.next())
             				id1=rs1.getInt(1);
             			}
             		}
             		catch(Exception e)
             		{
             			e.printStackTrace();
             		}
               
                }
                else
                
                id1=-1;
                }
				
		
		catch(Exception e) {
		e.printStackTrace();}
	}
	catch(Exception e)
	{
		e.printStackTrace();
	}

	setId(id1);	
	return getId();
}


}

