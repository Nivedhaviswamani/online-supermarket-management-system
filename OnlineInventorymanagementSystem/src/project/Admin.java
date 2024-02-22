package project;
import java.util.*;
public class Admin {
	String email;int id;
public Admin(int id)
{
	this.id=id;
}
public void adminDashBoard()
{
	Scanner sc=new Scanner(System.in);
	boolean flag=true;
	boolean f1=true;
	while(flag)
	{
	System.out.println("Enter 1 to view or update products,2 to view or update Employee details,3 to exit");
	String ip=sc.next();
	if(ip.equals("1"))
	{
		Product emp=new Product();

		while(f1)
			{
			System.out.println("Enter 1-add new product details,2-update product details,3-delete product details,4-search product,5-view product,6-exit");
			String n=sc.next();
			switch(n)
		{case "1":
			emp.addProduct();
			break;
		case "2":
			emp.updateProduct();
			break;
		case "3":
			emp.deleteProduct();
			break;
		case "4":
			emp.searchProduct();
			break;
		case "5":
			System.out.print("Enter 1 to view all products,2 to view available products,3 to view not available products");
			String r=sc.next();
			if(r.equalsIgnoreCase("1"))
			emp.viewProduct("all");
			else if(r.equalsIgnoreCase("2"))
				emp.viewProduct("available");
			if(r.equalsIgnoreCase("3"))
				emp.viewProduct("notavailable");
			break;
		case "6":
			f1=false;
			break;
			default:
				System.out.println("Enter correct input");
		}
		}
		
	}
	else if(ip.equals("2"))
	{
		Employee emp=new Employee();

		while(f1)
			{
			System.out.println("Enter 1-add new employee details,2-update employee details,3-delete employee details,4-search employee,5-view Employee,6-exit");
			String n=sc.next();
			switch(n)
		{case "1":
			emp.addEmployeeDetails();
			break;
		case "2":
			emp.updateEmployeeDetails();
			break;
		case "3":
			emp.deleteEmployeeDetails();
			break;
		case "4":
			emp.searchEmployeeDetails();
			break;
		case "5":
			emp.viewEmployeeDetails();
			break;
		case "6":
			f1=false;
			break;
			default:
				System.out.println("Enter correct input");
		}
		}
	}
	else if(ip.equals("3"))
	{
		flag=false;
	}
	}
	
}
}
//nivi@gmail.com,Nivedha@123