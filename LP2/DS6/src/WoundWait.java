
//Java program to implement Wound-Wait algorithm based on Timestamps.


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.Date;
import java.util.Random;
import java.util.Scanner;

public class WoundWait {

public static void main(String[] args) throws Exception {
		
    System.out.println("+++++ Wound-Wait Algorithm +++++");
		boolean b;	
		Random rn = new Random();
		int randomInt = rn.nextInt(2);
		if(randomInt==0)
		{		
			Date date1 = new Date();
			Timestamp ts1 = new Timestamp(date1.getTime());
			System.out.println("Transaction-1 started with timestamp: "+ts1);		
			Date date2 = new Date();
			Timestamp ts2 = new Timestamp(date2.getTime());
			System.out.println("Transaction-2 started with timestamp: "+ts2);
			
			Scanner sc = new Scanner(System.in);
			System.out.println("Enter resourse number required by Transaction-1");
			String res1 = sc.nextLine();
			System.out.println("Enter resourse number required by Transaction-2");
			String res2 = sc.nextLine();
			if(res1.equals(res2))
			{
				long endTimeMillis = System.currentTimeMillis() + 10000;
				System.out.println("Transaction-1 wounded Transaction-2 for time "+endTimeMillis+" in milli seconds");
				
				if (System.currentTimeMillis() < endTimeMillis)
				{
					System.out.println("Transaction-2 executing\n");

					b = performTrans("Create table Emp2(EID int primary key,Name char(30),Address char(30))");
					if(b)
						System.out.println("Transaction-2 Completed");
					else
						System.out.println("Transaction-2 Failed");
					
					System.out.println("Transaction-1 executing\n");
					b = performTrans("Create table Emp1(EID int primary key,Name char(30),Address char(30))");
					if(b)
						System.out.println("Transaction-1 Completed");
					else
						System.out.println("Transaction-1 Failed");			
				}
				else
				{
					System.out.println("Transaction-1 terminated Transaction-2\nTransaction-1 executing\n");
					b = performTrans("Create table Emp1(EID int primary key,Name char(30),Address char(30))");
					if(b)
						System.out.println("Transaction-1 Completed");
					else
						System.out.println("Transaction-1 Failed");
					System.out.println("Transaction-2 reborn with timestamp: "+ts2);
					System.out.println("Transaction-2 executing\n");
					b = performTrans("Create table Emp2(EID int primary key,Name char(30),Address char(30))");
					if(b)
						System.out.println("Transaction-2 Completed");
					else
						System.out.println("Transaction-2 Failed");
				}
			}
			else
			{
				System.out.println("Transaction-1 is waiting\nTransaction-2 is executing\n");
				b = performTrans("Create table Emp2(EID int primary key,Name char(30),Address char(30))");
				if(b)
					System.out.println("Transaction-2 Completed");
				else
					System.out.println("Transaction-2 Failed");
				System.out.println("Transaction-1 executing\n");
				b = performTrans("Create table Emp1(EID int primary key,Name char(30),Address char(30))");
				if(b)
					System.out.println("Transaction-1 Completed");
				else
					System.out.println("Transaction-1 Failed");                     
			}
		}
		else
		{
			
			Date date2 = new Date();
			Timestamp ts2 = new Timestamp(date2.getTime());
			System.out.println("Transaction-2 started with timestamp: "+ts2);
			
			Date date1 = new Date();
			Timestamp ts1 = new Timestamp(date1.getTime());;
			System.out.println("Transaction-1 started with timestamp: "+ts1);
			System.out.println("Transaction-1 is died with timestamp: "+ts1);
			System.out.println("Transaction-2 is executing\n");
			b = performTrans("Create table Emp2(EID int primary key,Name char(30),Address char(30))");
			if(b)
				System.out.println("Transaction-2 Completed");
			else
				System.out.println("Transaction-2 Failed");
			System.out.println("Transaction-1 reborn with timestamp: "+ts1);
			System.out.println("Transaction-1 executing\n");
			b = performTrans("Create table Emp1(EID int primary key,Name char(30),Address char(30))");
			if(b)
				System.out.println("Transaction-1 Completed");
			else
				System.out.println("Transaction-1 Failed");
		}
}

static boolean performTrans(String querry)throws Exception
	{
		
		Connection myCon = DriverManager.getConnection("jdbc:mysql://localhost/abc1", "root", "123");

		System.out.println("Database connection is created");
		//Create statement     
		Statement myStm = myCon.createStatement();
		//Execute querry
		int numRowsChanged = myStm.executeUpdate(querry);
		myCon.close();
		if(numRowsChanged==0)
		{
			System.out.println("Table created Successfully!!!");
			return true;
		}
		else
		{
		   System.out.println("Table does not created!!!");
		   return false;
		}
	} 
}





