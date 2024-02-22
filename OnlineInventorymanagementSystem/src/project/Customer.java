package project;
import java.util.*;
public class Customer {
	protected String email;
	int id;
public Customer(int id)
{
	this.id=id;
}
protected void setId(int id)
{this.id=id;}
protected int getId()
{return id;}
public void setEmail(String email)
{
	this.email=email;
}
public String getEmail()
{
	return email;
}
protected void customerDashBoard()
{
	Scanner sc=new Scanner(System.in);
	boolean flag=true;
	Cart c=new Cart(getId());
	Order o=new Order(getId());
	while(flag) {
	System.out.println("Enter 0 to view product catelogue,1 to add to cart,2 to remove from cart,3 to got to cart and place order,4 to cancel order,5 to exit");
	String ip=sc.next();
	if(ip.equals("0"))
	{
		Product p1=new Product();
		p1.viewProduct("available");
	}
	else if(ip.equals("1"))
	{
		c.addCart();
	}
	else if(ip.equals("2"))
	{
		c.removeFromCart();
	}
	else if(ip.equals("3"))
	{
		o.placeOrder();
	}
	else if(ip.equals("4"))
	{
		o.cancelOrder();
	}
	else if(ip.equals("5"))
	{
		flag=false;
	}
	else 
		System.out.println("Enter the correct input");
	
	}
}
}
