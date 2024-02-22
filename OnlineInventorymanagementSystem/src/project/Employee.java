package project;
import java.util.*;
import java.sql.*;
public class Employee {
String empName,empAddress,empPosition,DOB,department,employmentStatus,tableName;
char gender;
long empSalary;
int empId;
Connection conn;
public Employee() {
	Connections1 c1=new Connections1();
	conn=c1.connect();
}
public int getEmpId() {
    return empId;
}

public void setEmpId(int empId) {
    this.empId = empId;
}

public String getEmpName() {
    return empName;
}

public void setEmpName(String empName) {
    this.empName = empName;
}

public String getEmpAddress() {
    return empAddress;
}

public void setEmpAddress(String empAddress) {
    this.empAddress = empAddress;
}

public String getEmpPosition() {
    return empPosition;
}

public void setEmpPosition(String empPosition) {
    this.empPosition = empPosition;
}

public String getDOB() {
    return DOB;
}

public void setDOB(String DOB) {
    this.DOB = DOB;
}

public String getDepartment() {
    return department;
}

public void setDepartment(String department) {
    this.department = department;
}

public String getEmploymentStatus() {
    return employmentStatus;
}

public void setEmploymentStatus(String employmentStatus) {
    this.employmentStatus = employmentStatus;
}

public char getGender() {
    return gender;
}

public void setGender(char gender) {
    this.gender = gender;
}

public long getEmpSalary() {
    return empSalary;
}

public void setEmpSalary(long empSalary) {
    this.empSalary = empSalary;
}
public String getTableName() {
    return tableName;
}

public void setTableName(String tableName) {
    this.tableName = tableName;
}

protected void addEmployeeDetails()
{
	Scanner sc=new Scanner(System.in);
    System.out.print("Enter Employee Name: ");
    String empName1 = sc.nextLine();
    setEmpName(empName1);
    System.out.print("Enter Employee Address: ");
    String empAddress1 = sc.nextLine();
    setEmpAddress(empAddress1);
    System.out.print("Enter Employee Position: ");
    String empPosition1 = sc.nextLine();
    setEmpPosition(empPosition1);
    System.out.print("Enter Employee Date of Birth(yy/mm/dd): ");
    String DOB1 = sc.nextLine();
    setDOB(DOB1);
    System.out.print("Enter Employee Department: ");
    String department1 = sc.nextLine();
    setDepartment(department1);
	    System.out.print("Enter Employment Status: ");
    String employmentStatus1 = sc.nextLine();
    setEmploymentStatus(employmentStatus1);
    System.out.print("Enter Employee Gender (M/F): ");
    char gender1 = sc.nextLine().charAt(0);
    setGender(gender1);
    System.out.print("Enter Employee Salary: ");
    long empSalary1 = sc.nextLong();
    setEmpSalary(empSalary1);
   try(Statement stmt=conn.createStatement())
   {
	   String q1="Insert  into employee(empName, empAddress, empPosition, DOB, department, employmentStatus, gender, empSalary) values('"+getEmpName()+"','"+getEmpAddress()+"','"+getEmpPosition()+"','"+getDOB()+"','"+getDepartment()+"','"+getEmploymentStatus()+"','"+getGender()+"','"+getEmpSalary()+"');";
	   int rowsAffected=stmt.executeUpdate(q1);
	   if(rowsAffected>0)
	   {
		   System.out.println("Employee Added ");
		   
	   }
	   else
		   System.out.println("sorry Couldn't add");
   }
   catch(Exception e)
   {
	   e.printStackTrace();
   }
    
}
public void updateEmployeeDetails()
{
	String column="";
	Scanner sc=new Scanner(System.in);
	boolean outFlag=true,inFlag=true,f=false;
	while(outFlag) {
	System.out.print("Enter the empId you want to update:(enter n to terminate)");
	String id=sc.next();
	if(id.equalsIgnoreCase("n"))
	{
		outFlag=false;
		break;
	}
	int tempId=-1;
	setEmpId(Integer.valueOf(id));
	String q1="select empId from employee where empId='"+getEmpId()+"';";
	try(Statement stmt=conn.createStatement())
	{
		try(ResultSet rs=stmt.executeQuery(q1))
		{
			while(rs.next())
			{
				tempId=rs.getInt(1);
			}
			if(getEmpId()==tempId)
			{
				f=true;
			}
		}
	}
	catch(Exception e)
	{
		e.printStackTrace();
	}
	if(f)
	{
		while(inFlag)
		{
			System.out.print("Enter 1 to update name,2 to update adress,3-position,4-dob,5,department,6-employement status,7-gender,8-salary,9-quit");
			String n=sc.next();
			switch(n)
			{
			case "1":
				column="empName";
				break;
			case "2":
				column="empAddress";
				break;
			case "3":
				column="empPosition";
				break;
			case "4":
				column="dob";
				break;
			case "5":
				column="department";
				break;
			case "6":
				column="employmentstatus";
				break;
			case "7":
				column="gender";
				break;
			case "8":
				column="empSalary";
				break;
			case "9":
				inFlag=false;
				break;	
				
			}
			if(!inFlag)
				break;
			if(column.equals("empSalary"))
			{	long amt=sc.nextLong();
			q1="update employee set "+column+"='"+amt+"' where empId='"+getEmpId()+"';";
			}
			
			else
			{
				System.out.println("Enter new "+column);
				sc.nextLine();
				String s=sc.nextLine();
				 q1="update employee set "+column+"='"+s+"' where empId='"+getEmpId()+"';";
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
public void deleteEmployeeDetails()
{
	Scanner sc=new Scanner(System.in);
	String q1="";
	boolean flag=true;
	while(flag) {
		System.out.println("1 delete by empId,2 delete all in department,3 delete by employement_status,4 exit");
		String ip=sc.next();
		if(ip.equals("4"))
		{
			flag=false;
			break;
		}
		else if(ip.equals("1")) {
	System.out.println("Enter the empId you want to delete:");
	int id=sc.nextInt();
	setEmpId(id);
	q1="Delete from employee where empId='"+getEmpId()+"';";
		}
		else if(ip.equals("2"))
		{
			System.out.println("Enter the department in which  you want to delete all the employess:");
			String dept=sc.next();
			setDepartment(dept);
			q1="Delete from employee where Department='"+getDepartment()+"';";
		}
		else if(ip.equals("3"))
		{
			System.out.println("Enter the employement_staus  you want to delete:");
			String id=sc.next();
			setEmploymentStatus(id);
			q1="Delete from employee where employmentstatus='"+getEmploymentStatus()+"';";
		}
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
public void viewEmployeeDetails()
{
	String q1="select * from employee;";     
	System.out.printf("%-20s%-20s%-20s%-20s%-20s%-20s%-20s%-20s%-20s|%n","------","------","------","------","------","------","------","------","------");
	System.out.printf("|%-20s%-20s%-20s%-20s%-20s%-20s%-20s%-20s%-20s|%n","empId","name","Address","position","DOB","department","employment status","gender","salary");
	System.out.printf("%-20s%-20s%-20s%-20s%-20s%-20s%-20s%-20s%-20s%n","------","------","------","------","------","------","------","------","------");
	
	try(Statement stmt=conn.createStatement())
	{
		try(ResultSet rs=stmt.executeQuery(q1))
		{
			while(rs.next())
			{
				int temp1=rs.getInt(1);
				String temp2=rs.getString(2);
				String temp3=rs.getString(3);
				String temp4=rs.getString(4);
				java.sql.Date temp5=rs.getDate(5);
				String temp6=rs.getString(6);
				String temp7=rs.getString(7);
				String temp8=rs.getString(8);
				String temp9=rs.getString(9);
				System.out.printf("|%-20d%-20s%-20s%-20s%-20s%-20s%-20s%-20s%-20d|\n", temp1, temp2, temp3, temp4, temp5, temp6, temp7,temp8,temp9);
			}
		}
	}
	catch(Exception e)
	{
		e.printStackTrace();
	}
}
public void searchEmployeeDetails()
{
	String column="";
	boolean flag=true;
	Scanner sc=new Scanner(System.in);
	while(flag) {
	System.out.println("Enter 1 to search by name,2 to search by empId,3 to search by department,4 to search by employment_status,5 to exit:");
	String s=sc.next();
	switch(s)
	{
	case "1":
		column="empName";
		break;
	case "2":
		column="empId";
		break;
	case "3":
		column="department";
		break;
	case "4":
		column="employmentStatus";
		break;
	case "5":
		flag=false;
		break;
		default:
			System.out.println("Enter the correct input");
		
	}
	if(!flag)
		continue;
	String q1="";
	System.out.println("Enter the "+column+" you want search:");
	if(!column.equals("empId")) {
		sc.nextLine();
	String search=sc.nextLine();
	 q1="select * from employee where "+column+" like '"+search+"%';";
	}
	else
	{int search=sc.nextInt();
	 q1="select * from employee where "+column+"='"+search+"';";
	}
	System.out.printf("%-20s%-20s%-20s%-20s%-20s%-20s%-20s%-20s%-20s|%n","------","------","------","------","------","------","------","------","------");
	System.out.printf("|%-20s%-20s%-20s%-20s%-20s%-20s%-20s%-20s%-20s|%n","empId","name","Address","position","DOB","department","employment status","gender","salary");
	System.out.printf("%-20s%-20s%-20s%-20s%-20s%-20s%-20s%-20s%-20s%n","------","------","------","------","------","------","------","------","------");
	//System.out.println("|  empId 	  |    name     |         Address           |    position    |  DOB    |  department    |      employment status     |    gender    | salary    |");
	try(Statement stmt=conn.createStatement())
	{
		try(ResultSet rs=stmt.executeQuery(q1))
		{
			while(rs.next())
			{
				//ArrayList<Employee> a=new ArrayList<>();
				//Employee e=new Employee();
				int temp1=rs.getInt(1);
				String temp2=rs.getString(2);
				String temp3=rs.getString(3);
				String temp4=rs.getString(4);
				java.sql.Date temp5=rs.getDate(5);
				String temp6=rs.getString(6);
				String temp7=rs.getString(7);
				String temp8=rs.getString(8);
				long temp9=rs.getLong(9);
				System.out.printf("|%-20d%-20s%-20s%-20s%-20s%-20s%-20s%-20s%-20d|\n", temp1, temp2, temp3, temp4, temp5, temp6, temp7,temp8,temp9);

				//System.out.println(temp1+"   |  "+temp2+"  |         "+temp3+"           |    "+temp4+"    |  "+temp5+"    |  "+temp6+"    |      "+temp7+"     |    "+temp8+"    | "+temp9+  "    |");
				//e.name=temp1;e.
				//a.add(e);
			}
		}
	}
	catch(Exception e)
	{
		e.printStackTrace();
	}
	
}
	
}
}
/*CREATE TABLE Employee (
    empId INT AUTO_INCREMENT PRIMARY KEY,
    empName VARCHAR(255),
    empAddress VARCHAR(255),
    empPosition VARCHAR(255),
    DOB DATE,
    department VARCHAR(255),
    employmentStatus VARCHAR(50),
    gender CHAR(1),
    empSalary int(20)
);*/