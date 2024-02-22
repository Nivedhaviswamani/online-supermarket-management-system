package project;
import java.sql.Statement;
import java.util.*;
import java.sql.*;
public class AdminSignupPage extends SignUpPage {
	private String enterPass="nivi";
public void adminSignUpPage()
{
	boolean flag=false;;
	Scanner sc=new Scanner(System.in);
	System.out.println("Enter the admin common password:");
	String s=sc.next();
	if(s.equals(enterPass))
		flag=true;
	if(flag)
	{
		System.out.println("Enter 1 to signUp,2 to signIn,3.to exit");
		String n=sc.next();
		if(n.equals("1"))
		{
			int id=signUp();
			setId(id);
			
			if((id>0)) {
				Admin ad=new Admin(getId());
				ad.adminDashBoard();
			}
		}
		else if(n.equals("2"))
		{
			SignUpPage ob=new AdminSignupPage();
			ob.setTableName("admin");
			int id=ob.signIn();
			setId(id);
			//System.out.println(id);
			if(id!=-1 && id!=0) 
			{
				Admin ad=new Admin(getId());
				ad.adminDashBoard();
			}
		}
		else if(n.equals("3"))
			flag=false;
		else
			System.out.println("Wrong input");
	}
	else
		System.out.print("Wrong password");
	
}
public int signUp()
{
	Scanner sc=new Scanner(System.in);
	int id=-1;
	String pass1;
	RegexValidation r=new RegexValidation();
	boolean emailCheck=true,passCheck=true;
	while(true) {
	System.out.print("Enter your email id:");
	String email1=sc.next();
	setEmail(email1);
	if(r.isValidEmail(getEmail()))
		break;
	else
		System.out.println("your email is not valid.please enter correct format of email id.");
	}
	Boolean flag=true;
	while(flag) {
		while(true)
		{
	System.out.print("Enter a password:");
	 pass1=sc.next();
	if(r.isValidPassword(pass1))
		break;
	else
		System.out.println("you passsword should have atleast 1 digit,1 special character,1 upper case and 1 lower case letters and 8 characters .Please reEnter");
		}
	System.out.println("Re enter the password");
	String pass2=sc.next();
	if(pass1.equals(pass2)) {
		Password p=new Password();
		pass1=p.hashPassword(pass1);
	setPassword(pass1);
	flag=false;
	}else
	{
		System.out.println("password doesn't match.please refill it");
	}
	}
	String s1="Select email from admin where email='"+getEmail()+"';";
	String temp1="";
	boolean f=true;
	try(Statement st=conn.createStatement())
	{
		try(ResultSet rs=st.executeQuery(s1))
		{
			while(rs.next())
			temp1=rs.getString(1);
		}
	}
	catch(Exception e)
	{
		e.printStackTrace();
	}
	if(temp1.equals(getEmail()))
	{	f=false;
		System.out.println("You Already have an account in this id:"+getEmail());
	}
		if(f) {	
	String sql="Insert into admin(email,password) values('"+email+"','"+password+"');";
 
        try (Statement statement = conn.createStatement()) {
            	 int rowsAffected = statement.executeUpdate(sql);

                 if (rowsAffected > 0) {
                	 String s2="Select id from admin where email='"+getEmail()+"';";
                		try(Statement st=conn.createStatement())
                		{
                			try(ResultSet rs=st.executeQuery(s2))
                			{
                				while(rs.next())
                				id=rs.getInt(1);
                			}
                		}
                		catch(Exception e)
                		{
                			e.printStackTrace();
                		}
                	 System.out.println("Successfully Registered");
                 } else {
                     id=-1;
                 }
        } 
    catch (Exception e) {
        e.printStackTrace();
    }
		}
	return id;
	
}
}
/*
CREATE TABLE admin (id int auto_increment PRIMARY KEY,
		 email VARCHAR(30) CHECK (email REGEXP '^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$'),
		password VARCHAR(30) CHECK (password REGEXP '^(?=.*[A-Za-z])(?=.*\\d).{8,}$')
		)auto_increment=1;
*/