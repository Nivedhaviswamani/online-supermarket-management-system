package project;

import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Scanner;

public class CustomerSignupPage extends SignUpPage{
	protected String custName,address;
	long phno;
	int custId;
public CustomerSignupPage()
{
setTableName("customer");	
}
public String getCustName() {
    return custName;
}

public void setCustName(String custName) {
    this.custName = custName;
}


// Getter and Setter for address
public String getAddress() {
    return address;
}

public void setAddress(String address) {
    this.address = address;
}

// Getter and Setter for phno
public long getPhno() {
    return phno;
}

public void setPhno(long phno) {
    this.phno = phno;
}
public int getCustId() {
    return custId;
}

public void setCustId(int custId) {
    this.custId = custId;
}
public void customerSignUpPage()
{
	Scanner sc=new Scanner(System.in);
	
		System.out.println("Enter 1 to signUp,2 to signIn,3.to exit");
		String n=sc.next();
		if(n.equals("1"))
		{
			int id1=signUp();
			setCustId(id1);
			if(id1>0) {
				Customer ad=new Customer(getCustId());
				ad.customerDashBoard();
			}
		}
		else if(n.equals("2"))
		{
			SignUpPage ob=new CustomerSignupPage();
			ob.setTableName("customer");
			int id1=ob.signIn();
			setCustId(id1);
			if(id1>0)
			{
				Customer ad=new Customer(getCustId());
				ad.customerDashBoard();
			}
			else
				System.out.println("Couldn't sign in");
		}
		
	
	
}


protected int signUp()
{
	Scanner sc=new Scanner(System.in);
	RegexValidation r=new RegexValidation();
	int id1=-1;
	String pass1="";
	System.out.print("Enter Customer Name: ");
    String custName1 = sc.nextLine();
    setCustName(custName1);
    System.out.print("Enter Phone Number: ");
    long phno1 = sc.nextLong();
    setPhno(phno1);
    System.out.print("Enter Address: ");
    sc.nextLine();
    String address1 = sc.nextLine();
    setAddress(address1);
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
		while(true) {
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
	String s1="Select email from customer where email='"+getEmail()+"';";
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
	
	
 String sql="Insert into customer(custname,phno,address,email,password) values('"+getCustName()+"','"+getPhno()+"','"+getAddress()+"','"+getEmail()+"','"+getPassword()+"');";
        try (Statement statement = conn.createStatement()) {
            	 int rowsAffected = statement.executeUpdate(sql);

                 if (rowsAffected > 0) {
                	 String s2="Select custid from customer where email='"+getEmail()+"';";
             		try(Statement st=conn.createStatement())
             		{
             			try(ResultSet rs=st.executeQuery(s2))
             			{
             				while(rs.next())
             				id1=rs.getInt(1);
             			}
             		}
             		catch(Exception e)
             		{
             			e.printStackTrace();
             		}
                	 System.out.println("Registered successfully");
                 } else {
                     id1=-1;
                 }
        } 
    catch (Exception e) {
        e.printStackTrace();
    }
		}
		setCustId(id1);
	return getCustId();
}
}
/*create table customer(custName varchar(30) Not null,phno int(12),Address varchar(50),email VARCHAR(30) PRIMARY KEY CHECK (email REGEXP '^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$'),
password VARCHAR(30) CHECK (password REGEXP '^(?=.*[A-Za-z])(?=.*\\d).{8,}$')
);*/