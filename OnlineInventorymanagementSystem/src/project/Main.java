package project;
import java.sql.*;
import java.util.*;
public class Main {

public static void main(String[] args) {
	Scanner sc=new Scanner(System.in);
	boolean flag=true;
	while(flag)
	{
	System.out.print("Enter 1 to signUp/signIn as a admin,2 to signIn/signUp as a customer,3 to exit");
	String input=sc.next();
	if(input.equals("1"))
	{
		AdminSignupPage ad=new AdminSignupPage();
		ad.adminSignUpPage();
	}
	else if(input.equals("2"))
	{
		CustomerSignupPage ad=new CustomerSignupPage();
		ad.customerSignUpPage();
	}
	else if(input.equals("3"))
		flag=false;
	else
		System.out.println("Enter correct input");
	
	}
	System.out.print("Thank You for visiting!");
	
	}

}
//mvc 