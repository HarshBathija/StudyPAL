import java.io.*;
import java.util.Scanner;
public class Register {
	public Scanner sc;
	protected String name,username,password,passwordconfirm,contact;
	Register(Scanner sc)
	{
		this.sc=sc;
	}
	void getentry() throws IOException
	{
		System.out.print("Enter Name :");
		sc.nextLine();
		name=sc.nextLine();
		System.out.print("Enter Phone Number :");
		contact=sc.next();
		File file=new File("Data.txt");
		if(!file.exists())
			file.createNewFile();
		FileInputStream fin=new FileInputStream("Data.txt");
		BufferedReader br=new BufferedReader(new InputStreamReader(fin));
		String line=null;
		do
		{
			System.out.print("Enter Username :");
			username=sc.nextLine();
			int flag=1,i;
			String u;
			while((line=br.readLine())!=null)                           //till end of file is not reached. 
			{
				i=line.indexOf('*');
				u=line.substring(0,i);
				if(username.compareTo(u)==0)	  //p is extracted from file.
				{
					flag=0;
					break;
				}
			}
			if(flag==1)
				break;
			else
				System.out.println("Username already exists!!");
		}while(true);
		br.close();
		fin.close();
		do
		{
			System.out.print("Enter Password :");
			password=sc.next();
			System.out.print("Confirm password :");
			passwordconfirm=sc.next();
			if (password.compareTo(passwordconfirm)!=0)
				System.out.println("Password doesnt match!");
			else if(password.length()<8)
				System.out.println("Password is too small!");
			else
				break;
		}while(true);
		addentry();
	}
	void addentry()throws IOException//Writing to file
	{
		FileWriter fw=new FileWriter("Data.txt",true);
		fw.write(username+"*"+password+"~"+name+"\t"+contact+"\n");
		fw.close();
		System.out.println("Registered Successfully!!!!");
		System.out.println("\n\n\n\n\n\n\n\n\n\n");
	}
}
