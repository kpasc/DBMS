import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Scanner;


public class Project
{
	// Project Class variables

	// The connection to the dbms
	private static Connection dbms;
	// The statement to be used to execute sql updates
	private Statement stmt;
	// Scanner to be used by the system
	Scanner scan = new Scanner(System.in);

	// Constructor for the project class - Entire program runs through here
	public Project()
	{
		// Load the information to the sql db and start the connection
		loadJdbc();
		// Create the DBMS by creating the tables to be used
	//	createDBMS();

		// Begin a while loop to prompt the user which query they would like to perform. Execute query and don't quit until user submits 20
		int choice = 0;
		while(choice != 20)
		{
			// Find query wanted by user
			choice = callQueries();
		}

		try
		{
			// Close the connection
			dbms.close();
		} catch (SQLException e)
		{
			e.printStackTrace();
		}
	}

	// Load JDBC - Opens the connection to the DB
	private void loadJdbc()
	{
		// Load JDBC Driver
		try
		{
			Class.forName("oracle.jdbc.OracleDriver");
		}
		catch(Exception e)
		{
			System.out.println("Unable to load the driver class!");
		}

		try
		{
			// Create an open connection to the oracle db
			dbms = DriverManager.getConnection("jdbc:oracle:thin:@//oracle.cs.ou.edu:1521/pdborcl.cs.ou.edu", "pasc6415", "WHji6Vv8");
			stmt = dbms.createStatement();
		}
		catch(SQLException e)
		{
			System.err.println("Couldn't get connection!");
		}
	}
	// Create DBMS method - Not Used
	private void createDBMS()
	{
		// Drop the tables
		dropTables();
		// Create the tables
		createTables();
	}
	// Drop Tables Method - Not Used
	private void dropTables()
	{
		// Create a string for each table to be dropped
		String customer, assembly, process, fitprocess, paintprocess, cutprocess, department, job, cutjob, paintjob, fitjob, account, assemblyaccount, departmentaccount, processaccount, transaction;

		customer = "drop table customer";
		assembly = "drop table assembly";
		fitprocess = "drop table paintprocess";
		paintprocess = "drop table paintprocess";
		cutprocess = "drop table cutprocess";
		process = "drop table process";
		department = "drop table department";
		cutjob = "drop table cutjob";
		paintjob = "drop table paintjob";
		fitjob = "drop table fitjob";
		job = "drop table job";
		assemblyaccount = "drop table assemblyaccount";
		departmentaccount = "drop table departmentaccount";
		processaccount = "drop table processaccount";
		account = "drop table account";
		transaction = "drop table transaction";

		try
		{
			// Execute dropping each table
			stmt.executeUpdate(customer);
			stmt.executeUpdate(assembly);
			stmt.executeUpdate(fitprocess);
			stmt.executeUpdate(paintprocess);
			stmt.executeUpdate(cutprocess);
			stmt.executeUpdate(process);
			stmt.executeUpdate(department);
			stmt.executeUpdate(cutjob);
			stmt.executeUpdate(paintjob);
			stmt.executeUpdate(fitjob);
			stmt.executeUpdate(job);
			stmt.executeUpdate(assemblyaccount);
			stmt.executeUpdate(departmentaccount);
			stmt.executeUpdate(processaccount);
			stmt.executeUpdate(account);
			stmt.executeUpdate(transaction);
		}
		catch(SQLException e)
		{
			System.err.println("SQLException:" + e.getMessage());
		}
	}
	// Create Tables Method - Not Used
	private void createTables()
	{
		// Create all of the tables that need to be created
		String customer, assembly, process, fitprocess, paintprocess, cutprocess, department, job, cutjob, paintjob, fitjob, account, assemblyaccount, departmentaccount, processaccount, transaction;

		transaction = "create table transaction" +
						"("+
						"no numeric(5,0),"+
						"sup_cost numeric(8,2),"+
						"primary key(no)"+
						")";

		customer = "create table customer" +
					"(" +
					"name varchar(30),"+
					"address varchar(30),"+
					"primary key(name)"+
					")";

		assembly = "create table assembly"+
					"("+
					"ID numeric(5,0),"+
					"date_ordered varchar(10),"+
					"details varchar(100),"+
					"primary key(ID)"+
					")";

		process = "create table process"+
					"("+
					"ID numeric(5,0),"+
					"data varchar(100),"+
					"primary key(ID)"+
					")";


		fitprocess = "create table fitprocess"+
						"("+
						"ID numeric(5,0) references process(ID),"+
						"fit_type varchar(50),"+
						"primary key(ID)"+
						")";


		paintprocess = "create table paintprocess"+
				"("+
				"ID numeric(5,0) references process(ID),"+
				"paint_type varchar(50),"+
				"paint_method varchar(50),"+
				"primary key(ID)"+
				")";


		cutprocess = "create table cutprocess"+
				"("+
				"ID numeric(5,0) references process(ID),"+
				"cut_type varchar(50),"+
				"machine_type varchar(50),"+
				"primary key(ID)"+
				")";


		department = "create table department"+
						"("+
						"no numeric(5,0),"+
						"data varchar(100),"+
						"primary key(no)"+
						")";


		job = "create table job"+
				"("+
				"no numeric(5,0),"+
				"date_commenced varchar(10),"+
				"date_completed varchar(10),"+
				"information varchar(100),"+
				"primary key(no)"+
				")";

		cutjob = "create table cutjob"+
					"("+
					"no numeric(5,0) references job(no),"+
					"machine_used varchar(50),"+
					"time_used varchar(20),"+
					"material_used varchar(20),"+
					"labor_time varchar(20),"+
					"primary key(no)"+
					")";

		paintjob = "create table paintjob"+
					"("+
					"no numeric(5,0) references job(no),"+
					"color varchar(10),"+
					"volume varchar(10),"+
					"labor_time varchar(20),"+
					"primary key(no)"+
					")";

		fitjob = "create table fitjob"+
					"("+
					"no numeric(5,0) references job(no),"+
					"labor_time varchar(20),"+
					"primary key(no)"+
					")";

		account = "create table account"+
					"("+
					"no numeric(5,0),"+
					"date_established varchar(10),"+
					"primary key(no)"+
					")";

		assemblyaccount = "create table assemblyaccount" +
							"("+
							"no numeric(5,0) references account(no),"+
							"record_costs numeric(8, 2),"+
							"primary key(no)"+
							")";

		departmentaccount = "create table departmentaccount"+
							"("+
							"no numeric(5,0) references account(no),"+
							"record_costs numeric(8,2),"+
							"primary key(no)"+
							")";

		processaccount = "create table processaccount"+
							"("+
							"number numeric(5,0) references account(no),"+
							"record_costs numeric(8,2),"+
							"primary key(no)"+
							")";
		try
		{
			// Execute the creation of all the tables
			int x = 0; System.out.println(x); x++;
			stmt.executeUpdate(transaction); System.out.println(x); x++;
			stmt.executeUpdate(customer); System.out.println(x); x++;
			stmt.executeUpdate(assembly); System.out.println(x); x++;
			stmt.executeUpdate(process); System.out.println(x); x++;
			stmt.executeUpdate(fitprocess); System.out.println(x); x++;
			stmt.executeUpdate(paintprocess); System.out.println(x); x++;
			stmt.executeUpdate(cutprocess); System.out.println(x); x++;
			stmt.executeUpdate(department); System.out.println(x); x++;
			stmt.executeUpdate(job); System.out.println(x); x++;
			stmt.executeUpdate(cutjob); System.out.println(x); x++;
			stmt.executeUpdate(paintjob); System.out.println(x); x++;
			stmt.executeUpdate(fitjob); System.out.println(x); x++;
			stmt.executeUpdate(account); System.out.println(x); x++;
			stmt.executeUpdate(assemblyaccount); System.out.println(x); x++;
			stmt.executeUpdate(departmentaccount); System.out.println(x); x++;
			stmt.executeUpdate(processaccount); System.out.println(x); x++;
			System.out.println("all good");
		}
		catch(SQLException e)
		{
			System.err.println("SQLException:" + e.getMessage());
		}
	}
	// Method that will prompt the user and call the proper queries
	private int  callQueries()
	{
		// Prompt the user which query they want to use
		System.out.println("");
		System.out.println("WELCOME TO THE JOB-SHOP ACCOUNTING DATABASE SYSTEM");
		System.out.println("");
		System.out.println("Input 1: to Enter a New Customer.");
		System.out.println("Input 2: to Enter a New Department.");
		System.out.println("Input 3: to Enter a New Assembly.");
		System.out.println("Input 4: to Enter a New Process ID and its Department.");
		System.out.println("Input 5: to Create a New Account.");
		System.out.println("Input 6: to Enter a New Job.");
		System.out.println("Input 7: to Enter Completion Date and Info of a Job.");
		System.out.println("Input 8: to Enter a Transaction number and it's Sup-cost.");
		System.out.println("Input 9: to Retrieve Cost Incurred on Assembly-ID.");
		System.out.println("Input 10: to Retrieve Labor Time Recorded on Assembly-ID.");
		System.out.println("Input 11: to Retrieve Labor Time within a Department for Jobs Completed in the Department given a Date.");
		System.out.println("Input 12: to Retrieve the Processes through which a given Assembly-ID has passed so far and Department Responsible for each Process.");
		System.out.println("Input 13: to Retrieve Jobs Completed given a Date in a given Department.");
		System.out.println("Input 14: to Retreive the Customers whose Assemblies are Painted RED using a given Painting Method.");
		System.out.println("Input 15: to Delete all Cut Jobs whose Job Number lies in a Range.");
		System.out.println("Input 16: to Change the Color of a given Paint Job.");
		System.out.println("Input 17: to Retrieve the Average Cost of all Accounts.");
		System.out.println("Input 18: Import");
		System.out.println("Input 19: Export");
		System.out.println("Input 20: Quit");

		// Take the users choice
		int choice = scan.nextInt();
		scan.nextLine();

		// If choice is less than 1 or greater than 20 it is incorrect. Ask them again
		if(!((choice >= 1) && (choice <=20)))
		{
			// Tell user that it was incorrect
			System.out.println("Invalid input. Please try again.");
			choice = 0;
		}

		else
		{
			// Take a valid choice and execute the query
			if(choice == 1)
				insertNewCustomer();	// insert a new customer
			else if(choice == 2)
				insertNewDepartment();	// insert a new department
			else if(choice == 3)
				insertNewAssembly();	// insert a new assembly
			else if(choice == 4)
				insertNewProcess();		// insert a new process
			else if(choice == 5)
				insertNewAccount();		// insert new account
			else if(choice == 6)
				insertNewJob();			// insert new job
			else if(choice == 7)
				completeJob();			// complete job
			else if(choice == 8)
				insertNewTransaction();	// insert new transaction
			else if(choice == 9)
				retrieveCostOfAssembly(); // retrieve the cost of an assembly
			else if(choice == 10)
				retrieveLaborTimeOfAssembly();		// retrieve labor time of an assembly
			else if(choice == 11)
				retrieveTotalLaborTimeOfDepartment();	// retrieve total labor time of a department
			else if(choice == 12)
				retrieveProcessAssemblyPassed();	// retrieve processes an assembly has passed
			else if(choice == 13)
				retrieveJobs();			// retrieve jobs
			else if(choice == 14)
				retrieveRedAssemblies(); // retrieve red assemblies
			else if(choice == 15)
				deleteCutJobs();		// delete cut jobs
			else if(choice == 16)
				changePaintJobColor();	// change paint job color
			else if(choice == 17)
				retrieveAverageCostOfAccounts();	// retrieve average cost of accounts
			else if(choice == 18)
				importCustomers();		// import customers
			else if(choice == 19)
				exportCustomers();		// export customers
			else if(choice == 20)
				System.out.println("See you next time!");
		}
		return choice;
	}
	// Query 1 - Insert New Customer - Completed
	private void insertNewCustomer()
	{
		// Get customer info from user
		System.out.println("Enter the new Customer's unique name");
		String name = scan.nextLine();
		System.out.println("Enter the new Customer's address");
		String address = scan.nextLine();

		String insert = "insert into customer values('"+name+"','"+address+"')";

		// Add customer to db
		try
		{
			stmt.executeUpdate(insert);
		} catch(SQLException e)
		{
			System.err.println("SQLException:" + e.getMessage());
		}
	}
	// Query 2 - Insert New Department - Completed
	private void insertNewDepartment()
	{
		// Ask user for department information
		System.out.println("Enter the Department ID");
		int id = scan.nextInt();
		scan.nextLine();
		System.out.println("Enter any Data associated with this Department.");
		String data = scan.nextLine();

		String insert = "insert into department values("+id+",'"+data+"')";

		// Insert department info into db
		try
		{
			stmt.executeUpdate(insert);
		} catch(SQLException e)
		{
			System.err.println("SQLException:" + e.getMessage());
		}
	}
	// Query 3 - Insert New Assembly - Completed
	private void insertNewAssembly()
	{
		System.out.println("Enter the Assembly ID");
		int id = scan.nextInt();
		scan.nextLine();
		System.out.println("Enter the date ordered. mmddyyyy");
		String date = scan.nextLine();
		System.out.println("Enter additional Assembly details");
		String details = scan.nextLine();
		System.out.println("Enter the customers ID that ordered the assembly");
		String customer = scan.nextLine();

		String insert = "insert into assembly values(" + id + ", to_date('" + date + "', 'mmddyyyy') ,'" + details + "','" + customer + "')";

		try
		{
			stmt.executeUpdate(insert);
		} catch(SQLException e)
		{
			System.err.println("SQLException:" + e.getMessage());
		}
	}
	// Query 4 - Insert New Process - Completed
	private void insertNewProcess()
	{
		System.out.println("Enter the type of process. 1 for Fit, 2 for Paint, 3 for Cut");
		int type = scan.nextInt();
		scan.nextLine();
		System.out.println("Enter new Process ID");
		int id = scan.nextInt();
		scan.nextLine();
		System.out.println("Enter Process data");
		String data = scan.nextLine();
		System.out.println("Enter the Assembly ID");
		int assid = scan.nextInt();
		scan.nextLine();
		System.out.println("Enter the Processes Department Number");
		int depid = scan.nextInt();
		scan.nextLine();

		String t;
		String entertype;
		String insertType;
		while(true)
		{
			if(type == 1)
			{
				t = "fitprocess";
				entertype = "fit";
				System.out.println("Enter the type of Fit Process");
				String fittype = scan.nextLine();

				insertType = "insert into " + t + " values(" + id + ",'" + fittype + "')";
				break;
			}
			else if(type == 2)
			{
				t = "paintprocess";
				entertype = "paint";
				System.out.println("Enter the paint type");
				String painttype = scan.nextLine();
				System.out.println("Enter the type of paint method");
				String paintmethod = scan.nextLine();

				insertType = "insert into " + t + " values(" + id + ",'" + painttype + "','" + paintmethod + "')";
				break;
			}
			else if(type == 3)
			{
				t = "cutprocess";
				entertype = "cut";
				System.out.println("Enter the cut type");
				String cuttype = scan.nextLine();
				System.out.println("Enter the machine type");
				String machinetype = scan.nextLine();

				insertType = "insert into " + t + " values(" + id + ",'" + cuttype + "','" + machinetype + "')";
				break;
			}

			else
			{
				System.out.println("Enter the type of process. 1 for Fit, 2 for Paint, 3 for Cut");
				type = scan.nextInt();
				scan.nextLine();
			}
		}

		String insert = "insert into process values(" + id + ",'" + data + "'," + assid + "," + depid + ",'" + entertype + "')";

		try
		{
			stmt.executeUpdate(insert);
			stmt.executeUpdate(insertType);
		} catch(SQLException e)
		{
			System.err.println("SQLException: " + e.getMessage());
		}
	}
	// Query 5 - Insert New Account - Completed
	private void insertNewAccount()
	{
		System.out.println("Enter the type of Account. 1 for Assembly, 2 for Department, 3 for Process");
		int type = scan.nextInt();
		scan.nextLine();
		System.out.println("Enter the account number");
		int number = scan.nextInt();
		scan.nextLine();
		System.out.println("Enter the date established. mmddyyyy");
		String date = scan.nextLine();

		String t;
		String insertAccount;
		while(true)
		{
			if(type == 1)
			{
				t = "assemblyaccount";
				System.out.println("Enter the Assembly ID that this account is for.");
				int assid = scan.nextInt();
				scan.nextLine();

				insertAccount = "insert into " + t + " values(" + number + "," + 0 + "," + assid + ")";
				break;
			}
			else if(type == 2)
			{
				t = "departmentaccount";
				System.out.println("Enter this Department number that this account is for.");
				int depid = scan.nextInt();
				scan.nextLine();

				insertAccount = "insert into " + t + " values(" + number + "," + 0 + "," + depid + ")";
				break;
			}
			else if(type == 3)
			{
				t = "processaccount";
				System.out.println("Enter this Process that this account is for.");
				int proid = scan.nextInt();
				scan.nextLine();

				insertAccount = "insert into " + t + " values(" + number + "," + 0 + "," + proid + ")";
				break;
			}

			else
			{
				System.out.println("Enter the type of Account. 1 for Assembly, 2 for Department, 3 for Process");
				type = scan.nextInt();
				scan.nextLine();
			}
		}

		String insert = "insert into account values(" + number + ", to_date('"+date+"','mmddyyyy'))";

		try
		{
			stmt.executeUpdate(insert);
			stmt.executeUpdate(insertAccount);
		} catch(SQLException e)
		{
			System.out.println("SQLException: " + e.getMessage());
		}
	}
	// Query 6 - Insert New Job - Completed
	private void insertNewJob()
	{
		System.out.println("Enter the type of Job. 1 for Fit Job, 2 for Paint Job, 3 for Cut Job.");
		int jobtype = scan.nextInt();
		scan.nextLine();
		System.out.println("Enter the job ID");
		int jobid = scan.nextInt();
		scan.nextLine();
		System.out.println("Enter the assembly ID");
		int assid = scan.nextInt();
		scan.nextLine();
		System.out.println("Enter the process ID");
		int proid = scan.nextInt();
		scan.nextLine();
		System.out.println("Enter the Date Commenced. mmddyyyy");
		String date = scan.nextLine();
		String info = "No info until completion";

		String t;
		String insertjob;
		while(true)
		{
			if(jobtype == 1)
			{
				t = "fitjob";
				System.out.println("Enter labor time.");
				float labortime = scan.nextFloat();
				scan.nextLine();

				insertjob = "insert into " + t + " values(" + jobid + "," + labortime + ")";
				break;
			}
			else if(jobtype == 2)
			{
				t = "paintjob";
				System.out.println("Enter the color");
				String color = scan.nextLine();
				System.out.println("Enter the volume");
				float volume = scan.nextFloat();
				scan.nextLine();
				System.out.println("Enter the labor time");
				float labortime = scan.nextFloat();
				scan.nextLine();

				insertjob = "insert into " + t + " values(" + jobid + ",'" + color + "'," + volume + "," + labortime + ")";
				break;
			}
			else if(jobtype == 3)
			{
				t = "cutjob";
				System.out.println("Enter the machine used");
				String machineused = scan.nextLine();
				System.out.println("Enter the time used");
				float timeused = scan.nextFloat();
				scan.nextLine();
				System.out.println("Enter the material used");
				String materialused = scan.nextLine();
				System.out.println("Enter the labor time");
				float labortime = scan.nextFloat();
				scan.nextLine();


				insertjob = "insert into " + t + " values(" + jobid + ",'" + machineused + "'," + timeused + ",'" + materialused + "'," + labortime + ")";
				break;
			}

			else
			{
				System.out.println("Enter the type of Job. 1 for Fit Job, 2 for Paint Job, 3 for Cut Job.");
				jobtype = scan.nextInt();
			}
		}

		String insert = "insert into job values(" + jobid + ", to_date('"+date+"','mmddyyyy'),"+null+",'" + info + "'," + proid + "," + assid + ")";

		try
		{
			stmt.executeUpdate(insert);
			stmt.executeUpdate(insertjob);
		} catch(SQLException e)
		{
			System.err.println("SQLException: " + e.getMessage());
		}
	}
	// Query 7 - Complete a Job - Completed
	private void completeJob()
	{
		System.out.println("Enter the ID of the job that has been completed");
		int jobid = scan.nextInt();
		scan.nextLine();
		System.out.println("Enter the date this job completed. mmddyyyy");
		String date = scan.nextLine();
		System.out.println("Enter information relevant to this job");
		String info = scan.nextLine();

		String update = "update job set date_completed = to_date('"+date+"','mmddyyyy') where job.no = " + jobid;
		String updateinfo = "update job set information = '" + info + "' where job.no = " + jobid;
		try
		{
			stmt.executeUpdate(update);
			stmt.executeUpdate(updateinfo);
		} catch(SQLException e)
		{
			System.err.println("SQLException: " + e.getMessage());
		}
	}
	// Query 8 - Insert a Transaction - Completed
	private void insertNewTransaction()
	{
		System.out.println("Enter the transaction number");
		int number = scan.nextInt();
		scan.nextLine();
		System.out.println("Enter the job number needed for this transaction.");
		int jobid = scan.nextInt();
		scan.nextLine();
		System.out.println("Enter the sup-cost for this transaction");
		float cost = scan.nextFloat();
		scan.nextLine();

		try
		{
			// Find the process linked to this job
			String proID = "select pro_no from job where job.no = "+jobid;
			ResultSet r = stmt.executeQuery(proID);
			r.next();
			int pro = r.getInt("pro_no");
			// Find the record_costs for the process account and update it
			String updatePro = "update processaccount set record_costs = record_costs+ "+cost+" where processaccount.no = "+pro;
			stmt.executeUpdate(updatePro);
			// Find the assembly linked to this job
			String assID = "select ass_id from job where job.no = "+jobid;
			ResultSet rs = stmt.executeQuery(assID);
			rs.next();
			int ass = rs.getInt("ass_id");
			// Find the record_costs for the assembly account and update it
			String updateAss = "update assemblyaccount set record_costs = record_costs+" +cost+" where assemblyaccount.no = "+ass;
			stmt.executeUpdate(updateAss);

			// Find the department linked to this job
			String depID = "select no from department where department.no in (select dep_no from process where process.id = '"+pro+"')";
			ResultSet rsss = stmt.executeQuery(depID);
			rsss.next();
			int dep = rsss.getInt("no");
			// Find the record_costs for the department account and update it
			String updateDep = "update departmentaccount set record_costs = record_costs+"+cost+" where departmentaccount.no = "+dep;
			stmt.executeUpdate(updateDep);





			// Finally, Insert the new transaction
			String insert = "insert into transaction values("+number+","+cost+","+jobid+")";
			stmt.executeUpdate(insert);

		} catch(SQLException e)
		{
			System.err.println("SQLException:"+e.getMessage());
		}

	}
	// Query 9 - Retrieve Cost of Assembly - Completed
	private void retrieveCostOfAssembly()
	{
		System.out.println("Enter the Assembly ID you want to retrieve the cost of.");
		int id = scan.nextInt();
		scan.nextLine();

		String retrieve = "select record_costs from assemblyaccount where assemblyaccount.no = "+id;

		try
		{
			ResultSet rs = stmt.executeQuery(retrieve);
			rs.next();
			String cost = rs.getString("record_costs");
			System.out.println("Cost for Assembly ID: "+id+" is: "+cost);
		} catch(SQLException e)
		{
			System.err.println("SQLException: " + e.getMessage());
		}
	}
	// Query 10 - Retrieve Labor Time of Assembly - Completed
	private void retrieveLaborTimeOfAssembly()
	{
		System.out.println("Enter the Assembly ID");
		int assid = scan.nextInt();
		scan.nextLine();

		try
		{
			// Retrieve the job numbers that are associated with this assembly
			String assJobs = "select no from job where job.pro_no in (select id from process where process.ass_id in "+ assid+")";

			// All the jobs
			ResultSet r = stmt.executeQuery(assJobs);
			ArrayList<Integer> allJobs = new ArrayList<Integer>();
			while(r.next())
			{
				allJobs.add(r.getInt("no"));
			}

			// All of the cut jobs
			String cut = "select no from cutjob";
			ResultSet s = stmt.executeQuery(cut);
			ArrayList<Integer> cutJobs = new ArrayList<Integer>();
			while(s.next())
			{
				cutJobs.add(s.getInt("no"));
			}

			// Loop to gather labor_cost of the cut jobs
			float cutTotal = 0;
			for(int i = 0; i < allJobs.size(); i++)
			{
				for(int j = 0; j < cutJobs.size(); j++)
				{
					if(allJobs.get(i) == cutJobs.get(j))
					{
						String cutLabor = "select labor_time from cutjob where cutjob.no = "+cutJobs.get(j);
						ResultSet rs = stmt.executeQuery(cutLabor); rs.next();
						cutTotal += rs.getFloat("labor_time");
					}
				}
			}

			// All of the cut jobs
			String paint = "select no from paintjob";
			ResultSet f = stmt.executeQuery(paint);
			ArrayList<Integer> paintJobs = new ArrayList<Integer>();
			while(f.next())
			{
				paintJobs.add(f.getInt("no"));
			}

			// Loop to gather labor_cost of the paint jobs
			float paintTotal = 0;
			for(int i = 0; i < allJobs.size(); i++)
			{
				for(int j = 0; j < paintJobs.size(); j++)
				{
					if(allJobs.get(i) == paintJobs.get(j))
					{
						String paintLabor = "select labor_time from paintjob where paintjob.no = "+paintJobs.get(j);
						ResultSet rs = stmt.executeQuery(paintLabor); rs.next();
						paintTotal += rs.getFloat("labor_time");
					}
				}
			}

			// All of the fit jobs
			String fit = "select no from fitjob";
			ResultSet z = stmt.executeQuery(fit);
			ArrayList<Integer> fitJobs = new ArrayList<Integer>();
			while(z.next())
			{
				fitJobs.add(z.getInt("no"));
			}

			// Loop to gather labor_cost of the fit jobs
			float fitTotal = 0;
			for(int i = 0; i < allJobs.size(); i++)
			{
				for(int j = 0; j < fitJobs.size(); j++)
				{
					if(allJobs.get(i) == fitJobs.get(j))
					{
						String fitLabor = "select labor_time from fitjob where fitjob.no = "+fitJobs.get(j);
						ResultSet rs = stmt.executeQuery(fitLabor); rs.next();
						fitTotal += rs.getFloat("labor_time");
					}
				}
			}

			float total = fitTotal + paintTotal + cutTotal;
			System.out.println("Cost is: " + total);
		} catch(SQLException e)
		{
			System.err.println("SQLException: "+ e.getMessage());
		}

	}
	// Query 11 - Retrieve Total Labor Time within a Department - Completed
	private void retrieveTotalLaborTimeOfDepartment()
	{
		System.out.println("Enter a department");
		String dep = scan.nextLine();
		System.out.println("Enter the date.");
		String date = scan.nextLine();

		try
		{
			// Retrieve job numbers associated with this department
			String depJobs = "select no from job where job.pro_no in (select id from process where process.dep_no in "+dep+") and job.date_completed = to_date('"+date+"','mmddyyyy')";
			ResultSet r = stmt.executeQuery(depJobs);
			ArrayList<Integer> allJobs = new ArrayList<Integer>();
			while(r.next())
			{
				allJobs.add(r.getInt("no"));
			}

			// All of the cut jobs
			String cut = "select no from cutjob";
			ResultSet s = stmt.executeQuery(cut);
			ArrayList<Integer> cutJobs = new ArrayList<Integer>();
			while(s.next())
			{
				cutJobs.add(s.getInt("no"));
			}

			// Loop to gather labor_cost of the cut jobs
			float cutTotal = 0;
			for(int i = 0; i < allJobs.size(); i++)
			{
				for(int j = 0; j < cutJobs.size(); j++)
				{
					if(allJobs.get(i) == cutJobs.get(j))
					{
						String cutLabor = "select labor_time from cutjob where cutjob.no = "+cutJobs.get(j);
						ResultSet rs = stmt.executeQuery(cutLabor); rs.next();
						cutTotal += rs.getFloat("labor_time");
					}
				}
			}

			// All of the cut jobs
			String paint = "select no from paintjob";
			ResultSet f = stmt.executeQuery(paint);
			ArrayList<Integer> paintJobs = new ArrayList<Integer>();
			while(f.next())
			{
				paintJobs.add(f.getInt("no"));
			}

			// Loop to gather labor_cost of the paint jobs
			float paintTotal = 0;
			for(int i = 0; i < allJobs.size(); i++)
			{
				for(int j = 0; j < paintJobs.size(); j++)
				{
					if(allJobs.get(i) == paintJobs.get(j))
					{
						String paintLabor = "select labor_time from paintjob where paintjob.no = "+paintJobs.get(j);
						ResultSet rs = stmt.executeQuery(paintLabor); rs.next();
						paintTotal += rs.getFloat("labor_time");
					}
				}
			}

			// All of the fit jobs
			String fit = "select no from fitjob";
			ResultSet z = stmt.executeQuery(fit);
			ArrayList<Integer> fitJobs = new ArrayList<Integer>();
			while(z.next())
			{
				fitJobs.add(z.getInt("no"));
			}

			// Loop to gather labor_cost of the fit jobs
			float fitTotal = 0;
			for(int i = 0; i < allJobs.size(); i++)
			{
				for(int j = 0; j < fitJobs.size(); j++)
				{
					if(allJobs.get(i) == fitJobs.get(j))
					{
						String fitLabor = "select labor_time from fitjob where fitjob.no = "+fitJobs.get(j);
						ResultSet rs = stmt.executeQuery(fitLabor); rs.next();
						fitTotal += rs.getFloat("labor_time");
					}
				}
			}

			float total = fitTotal + paintTotal + cutTotal;
			System.out.println("Total labor time: "+ total);


		} catch(SQLException e)
		{
			System.err.println("SQLException: "+ e.getMessage());
		}
	}
	// Query 12 - Retrieve Process Assembly Passed - Completed
	private void retrieveProcessAssemblyPassed()
	{
		System.out.println("Enter the assembly ID");
		int id = scan.nextInt();

		String retrieve = "select * from process where process.ass_id ="+id;

		try
		{
			ResultSet r = stmt.executeQuery(retrieve);
			while(r.next())
			{
				System.out.println("Process: "+ r.getInt("ID") + " Department: "+r.getInt("dep_no"));
			}
		} catch(SQLException e)
		{
			System.err.println("SQLException: "+ e.getMessage());
		}
	}
	// Query 13 - Retrieve Jobs - Completed
	private void retrieveJobs()
	{
		System.out.println("Enter the date. mmddyyyy");
		String date = scan.nextLine();
		System.out.println("Enter the department.");
		String dept = scan.nextLine();

		try
		{
			String retrieve = "select * from job where date_completed = to_date('"+date+"','mmddyyyy')";
			ResultSet r = stmt.executeQuery(retrieve);
			while(r.next())
			{
				System.out.println("Job: " + r.getInt("no") + " completed on: " +date+" in Department: "+dept);
				System.out.println("Additonal information about this job: ");
				System.out.println("Type Info: "+ r.getString("information") +" Assembly: "+r.getInt("ass_id"));
			}

		} catch(SQLException e)
		{
			System.err.println("SQLException: "+ e.getMessage());
		}
	}
	// Query 14 - Retrieve Customers Red Assemblies	- INCOMPLETE
	private void retrieveRedAssemblies()
	{
		// Get the painting method from the user to prompt a query
		System.out.println("Enter the painting method");
		String method = scan.nextLine();

		// Two queries. First to get customer names where red is the paint color
		// The second to get customer names where the paint method is the input
		String red = "select name from customer where customer.name in (select cust from assembly where assembly.id in (select ass_id from job where job.no in (select no from paintjob where paintjob.color = 'red')))";
		String cmethod = "select name from customer where customer.name in (select cust from assembly where assembly.id in (select ass_id from job where job.pro_no in (select id from process where process.id in (select id from paintprocess where paint_method = '"+method+"'))))";

		try
		{
			// Grab the names of the red customers
			ResultSet r = stmt.executeQuery(red);
			ArrayList<String> custRed = new ArrayList<String>();

			while(r.next())
			{
				custRed.add(r.getString("name"));
			}

			// Grab the names of the 'method' customers
			ResultSet s = stmt.executeQuery(cmethod);
			ArrayList<String> custMethod = new ArrayList<String>();
			while(s.next())
			{
				custMethod.add(s.getString("name"));
			}

			// Now create a union on custRed and custMethod and output
			for(int i = 0; i < custRed.size();i++)
			{
				for(int j = 0; j < custMethod.size();j++)
				{
					if(custRed.get(i).equals(custMethod.get(j)))
					{
						System.out.println("List of Customers ordered red paint with method: " + method);
						System.out.println("Customer: " + custMethod.get(j));
					}
				}
			}

		} catch(SQLException e)
		{
			System.err.println("SQLException: " + e.getMessage());
		}
	}
	// Query 15 - Delete Cut Jobs - Completed
	private void deleteCutJobs()
	{
		System.out.println("Enter the first number in the range. (exclusive)");
		int first = scan.nextInt();
		scan.nextLine();
		System.out.println("Enter the second number in the range. (exclusive)");
		int second = scan.nextInt();
		scan.nextLine();

		String find = "select no from cutjob where cutjob.no between "+ first +" and "+ second;

		try
		{
			ResultSet r = stmt.executeQuery(find);
			ArrayList<Integer> job = new ArrayList<Integer>();
			while(r.next())
			{
				job.add(r.getInt("no"));
			}

			for(int i = 0; i < job.size(); i++)
			{
				String deletecut = "delete from cutjob where cutjob.no = "+ job.get(i);
				String delete = "delete from job where job.no = " + job.get(i);
				stmt.executeUpdate(deletecut);
				stmt.executeUpdate(delete);
			}

		} catch(SQLException e)
		{
			System.err.println("SQLException:" + e.getMessage());
		}
	}
	// Query 16 - Change Paint Job Color - Completed
	private void changePaintJobColor()
	{
		System.out.println("Enter the Paint Job Number to be modified.");
		int number = scan.nextInt();
		scan.nextLine();
		System.out.println("Enter the color you would like to change this job to.");
		String color = scan.nextLine();

		String change = "update paintjob set color = '"+color+"' where paintjob.no = "+number;

		try
		{
			stmt.executeUpdate(change);
		} catch(SQLException e)
		{
			System.err.println("SQLException:" + e.getMessage());
		}
	}
	// Query 17 - Retrieve Average Cost of Accounts - Completed
	private void retrieveAverageCostOfAccounts()
	{
		String countAssembly = "select count(record_costs) from assemblyaccount";
		String countDepartment = "select count(record_costs) from departmentaccount";
		String countProcess = "select count(record_costs) from processaccount";

		String sumAssembly = "select sum(record_costs) from assemblyaccount";
		String sumDepartment = "select sum(record_costs) from departmentaccount";
		String sumProcess = "select sum(record_costs) from processaccount";

		try
		{
			// Grab the total number of rows for the denominator
			ResultSet r = stmt.executeQuery(countAssembly);
			r.next();
			int blockOne = r.getInt("count(record_costs)");
			r = stmt.executeQuery(countDepartment);
			r.next();
			int blockTwo = r.getInt("count(record_costs)");
			r = stmt.executeQuery(countProcess);
			r.next();
			int blockThree = r.getInt("count(record_costs)");

			// total count
			int count = blockOne + blockTwo + blockThree;

			// Grab the sum from each column set for the numerator
			r = stmt.executeQuery(sumAssembly);
			r.next();
			int blockSix = r.getInt("sum(record_costs)");
			r = stmt.executeQuery(sumDepartment);
			r.next();
			int blockFour = r.getInt("sum(record_costs)");
			r = stmt.executeQuery(sumProcess);
			r.next();
			int blockFive = r.getInt("sum(record_costs)");

			// total sum
			int sum = blockFour + blockFive + blockSix;

			// print the average
			System.out.println("Average of all accounts is: " + sum/count);

		} catch(SQLException e)
		{
			System.err.println("SQLException:" + e.getMessage());
		}
	}
	// Query 18 - Import Customers - Completed
	private void importCustomers()
	{
		System.out.println("Enter the file name.");
		String f = scan.nextLine();
		String line = null;

		try
		{
			FileReader fileReader = new FileReader(f);
			BufferedReader bufferedReader = new BufferedReader(fileReader);

			while((line = bufferedReader.readLine()) != null)
			{
				try
				{
					stmt.executeUpdate(line);
					System.out.println("Customer added");
				} catch(SQLException e)
				{
					System.err.println("SQLException:" + e.getMessage());
				}
			}

			bufferedReader.close();
		} catch(FileNotFoundException e)
		{
			e.printStackTrace();
		} catch(IOException e)
		{
			e.printStackTrace();
		}
	}
	// Query 19 - Export Customers - Completed
	private void exportCustomers()
	{
		System.out.println("Enter the file name you would like to export to.");
		String f = scan.nextLine();

		try
		{
			PrintWriter writer = new PrintWriter(f, "UTF-8");
			ArrayList<String> customers = new ArrayList<String>();
			ResultSet r = stmt.executeQuery("select * from customer");
			writer.write("Name"+ ", " + "Address" + "\n");
			while(r.next())
			{
				writer.write(r.getString("Name") + ", " + r.getString("Address") + "\n");
			}

			writer.close();
		} catch (FileNotFoundException e)
		{
			e.printStackTrace();
		} catch (UnsupportedEncodingException e)
		{
			e.printStackTrace();
		} catch (SQLException e)
		{
			System.err.println("SQLException: " + e.getMessage());
		}
	}
}
