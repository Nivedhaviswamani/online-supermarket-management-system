package project;
import java.util.*;
public class PaymentMethod {
	private long amount;
	String payMethod;
	public PaymentMethod(long amount)
	{
		this.amount=amount;
	}
	public void setPayMethod(String payMethod)
	{
		this.payMethod=payMethod;
	}
	public String getPayMethod()
	{
		return payMethod;
	}
	public void setAmount(long amount)
	{
		this.amount=amount;
	}
	public long getAmount()
	{
		return amount;
	}
public String pay()
{
	Scanner sc=new Scanner(System.in);
	boolean f=false;
	while(true) {
	System.out.println("1.Pay online,2.cash on delivery");
	String s=sc.next();
	if(s.equals("1"))
	{
		f=payOnline();
		String pay="onlinePayment";
		setPayMethod(pay);
	}
	else if(s.equals("2"))
	{
		f=cashOnDelivery();
		String pay="cashOnDelivery";
		setPayMethod(pay);
	}
	else
		System.out.println();
	if(f)
		break;
	}
	return getPayMethod();
}
public boolean payOnline()
{
	boolean flag=false;
	Scanner sc=new Scanner(System.in);
	System.out.println("you have to pay "+amount+":");
	System.out.println("1 -to pay using UPI,2 -to pay using debit card");
	String s=sc.next();
	if(s.equals("1"))
	{
		System.out.println("Enter your amount:");
		long amt=sc.nextLong();
		if(amt==getAmount()) {
			System.out.println("successfully paid");
			flag=true;}
		else
			System.out.println("Enter the correct amount");
		
	}
	else if(s.equals("2"))
	{
		System.out.println("Enter debit card No:");
		long card=sc.nextLong();
		System.out.println("Enter card holder name:");
		String name=sc.nextLine();
		System.out.println("Enter expiry date:");
		String date=sc.next();
		System.out.println("Enter ccv:");
		String ccv=sc.next();
		System.out.println("enter the amount :");
		long amt=sc.nextLong();
		if(amt==getAmount()) {
			System.out.println("successfully paid");
			flag=true;
		}
		else
			System.out.println("Enter the correct amount");
		
	}
	else
		System.out.println("Enter correct input");
	return flag;
	
}
public boolean cashOnDelivery()
{
	System.out.println("cash On delivery Accepted");
	return true;
}
}
