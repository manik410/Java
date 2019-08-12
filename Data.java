package BloodBank;
import java.sql.Statement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.*;
import java.sql.ResultSet;
class Data
{
	Statement stmt;
	Connection conn;
	Scanner sc;
	int option=0,age=0,option1=0;
	String name,location,bloodgroup,date,status,query,result,username,password;
	double contact;
	int count=0;
	Data()
	{
		try
		{
			Class.forName("com.mysql.jdbc.Driver");
			conn=DriverManager.getConnection("jdbc:mysql://localhost:3306/bloodbank","root","");
			stmt=conn.createStatement();
		}
		catch(Exception e)
		{
			System.out.println("Connection Failed");
		}
	}
	void operations()
	{

		sc=new Scanner(System.in);
		System.out.println("------Welcome To World Blood Bank------");
		System.out.println("Enter 1 for Sign-Up");
		System.out.println("Enter 2 for Login");
		option=sc.nextInt();
		try
		{
			if(option==1)
			{
				System.out.println("Enter Name");
				name=sc.next();
				System.out.println("Enter Password");
				password=sc.next();
				System.out.println("Enter Contact");
				contact=sc.nextDouble();
				query="insert into signup values('"+name+"','"+password+"',"+contact+")";
				stmt.execute(query);
				System.out.println("----------Sign-Up Successfully----------");
				System.out.println("Enter 3 for Registering as a Donor");
				System.out.println("Enter 4 for Updating Donor Status");
				System.out.println("Enter 5 for Searching Donor");
				System.out.println("Enter 6 for Logout");
				option1=sc.nextInt();
				boolean b=true;
				while(b==true)
				{

				switch(option1)
				{
					case 3:
						registerDonor();
						break;
					case 4:
						updateDonor();
						break;
					case 5:
						searchDonor();
						break;
					default:
						b=false;
						System.out.println("Exiting");
						System.exit(0);
				}
				System.out.println("-----------------------------------");
				System.out.println("Enter 3 for Registering as a Donor");
				System.out.println("Enter 4 for Updating Donor Status");
				System.out.println("Enter 5 for Searching Donor");
				System.out.println("Enter 6 for Logout");
				option1=sc.nextInt();
				}
			}
			else
			{
					count=0;
					while(count<3)
					{
						System.out.println("Enter name");
						name=sc.next();
						System.out.println("Enter Password");
						password=sc.next();
						query="select Password from signup where Name='"+name+"'";
						ResultSet pointer=stmt.executeQuery(query);
						while(pointer.next())
						{
							result=pointer.getString(1);
						}
						if(result.equals(password))
						{
							break;
						}
						else
						{
							System.out.println("Invalid Name or Password.");
							count++;
							if(count==3)
							{
								System.out.println("Too many attempts try after some time");
								System.exit(0);
							}
						}
					}
					System.out.println("----------Login-in Successfully----------");
					System.out.println("Enter 3 for Registering as a Donor");
					System.out.println("Enter 4 for Updating Donor Status");
					System.out.println("Enter 5 for Searching Donor");
					System.out.println("Enter 6 for Logout");
					option1=sc.nextInt();
					boolean b=true;
					while(b==true)
					{

					switch(option1)
					{
						case 3:
							registerDonor();
							break;
						case 4:
							updateDonor();
							break;
						case 5:
							searchDonor();
							break;
						default:
							b=false;
							System.out.println("Exiting");
							System.exit(0);
					}
					System.out.println("-----------------------------------");
					System.out.println("Enter 3 for Registering as a Donor");
					System.out.println("Enter 4 for Updating Donor Status");
					System.out.println("Enter 5 for Searching Donor");
					System.out.println("Enter 6 for Logout");
					option1=sc.nextInt();
					}
			}
			
		}
		
		catch(Exception e)
		{
			System.out.println("Error "+e.getMessage());
		}
	}
	
	void updateDonor()
	{
		Scanner sc=new Scanner(System.in);
		try
		{
			System.out.println("Enter Username to update");
			username=sc.next();
			System.out.println("Enter Status");
			status=sc.next();
			query="update Donor set Available='"+status+"' where username='"+username+"'";
			stmt.execute(query);
			System.out.println("----------Updated Successfully----------");
		}
		catch(Exception e)
		{
			System.out.println("Error "+e.getMessage());
		}
	}
	void registerDonor()
	{
		Scanner sc=new Scanner(System.in);
		try
		{
			System.out.println("Enter User name");
			username=sc.next();
			System.out.println("Enter location");
			location=sc.next();
			System.out.println("Enter age");
			age=sc.nextInt();
			System.out.println("Enter Bloodgroup");
			bloodgroup=sc.next();
			System.out.println("Enter Date of Registration as yy:mm:dd");
			date=sc.next();
			System.out.println("Enter Status");
			status=sc.next();
			query="insert into Donor values('"+username+"','"+location+"',"+age+",'"+bloodgroup+"','"+date+"','"+status+"')";
			stmt.execute(query);
			System.out.println("Registered Successfully");
		}
		catch(Exception e)
		{
			System.out.println("Error "+e.getMessage());
		}
	}
	void searchDonor()
	{
		Scanner sc=new Scanner(System.in);
		try
		{
			System.out.println("Enter BloodGroup");
			bloodgroup=sc.next();
			System.out.println("Enter Location ");
			location=sc.next();
			query="select * from Donor where Blood_Group='"+bloodgroup+"' and Location='"+location+"' and Available='YES'";
			ResultSet pointer1=stmt.executeQuery(query);
			while(pointer1.next())
			{
				System.out.println("Username : "+pointer1.getString(1)+" Location :"+pointer1.getString(2)+" Age : "+pointer1.getInt(3)+" Blood_Group : "+pointer1.getString(4)+" Register_date : "+pointer1.getString(5)+" Status : "+pointer1.getString(6));
			}
		}
		catch(Exception e)
		{
			System.out.println("Error "+e.getMessage());
		}
	}
}
