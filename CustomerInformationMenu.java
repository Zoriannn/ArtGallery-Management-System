package group_2;

import java.util.ArrayList;
import java.util.Scanner;

public class CustomerInformationMenu {

	public static void main() 
	{
		CustomerInformation customerInfomation = new CustomerInformation();	
		ArrayList <CustomerInformation> customerList = customerInfomation.getList();
		customerList.clear();
		customerInfomation.readCustomerFile(); 
		
		try {
			Scanner input = new Scanner(System.in);
			int choice;
			boolean loop = true;
	
			while(loop)
			{
				
				System.out.println("------------------------------------------------------------------------------------------");
				System.out.printf("%1$55s%n", "CUSTOMER INFORMATION");
				System.out.println("------------------------------------------------------------------------------------------");
	
				System.out.printf("%1$60s%n%n","List of the customer information");
				
				System.out.printf("%1$-5s %2$-20s %3$-15s %4$-20s %5$-20s%n", "ID", "NAME", "CONTACT", "ARTWORK PURCHASED", "ARTIST PREFERENCES");
				System.out.printf("%1$-5s %2$-20s %3$-15s %4$-20s %5$-20s%n", "----", "-----------------", "-----------", "-----------------", "------------------");
				
				for (CustomerInformation customerInformation : customerList)
				{
		            System.out.printf("%1$-5s %2$-20s %3$-15s %4$-20s %5$-20s%n",customerInformation.getCustomerId(), customerInformation.getCustomerName(),  
		            		customerInformation.getCustomerContact(), customerInformation.getCustomerArtworkPurchased(), customerInformation.getCustomerArtistPreferences());            
				}
				
				System.out.println("\n------------------------------------------------------------------------------------------");
				
				System.out.println("Do you want to : ");
				System.out.println("1. Add\n2. Delete\n3. Modify\n4. Search\n5. Exit\n");
				System.out.print("Enter option : ");
				
				choice = input.nextInt();
				input.nextLine();
				
				boolean loop2 = true;
				while(loop2)
				{
					if (choice == 1)
					{
						CustomerInformation.addCustomer();
						loop2 = false;
					}
					else if (choice == 2)
					{
						CustomerInformation.deleteCustomer(); 
						loop2 = false;
					}
					else if (choice == 3)
					{
						CustomerInformation.modifyCustomer();
						loop2 = false;
					}
					else if (choice == 4)
					{
						CustomerInformation.searchCustomer();
						loop2 = false;
					}
					else if (choice == 5)
					{
						loop2 = false;
						loop = false;
						Main.main(null);
					}
					else
					{
						System.out.println("Wrong option. Only 1 - 6 is allowed.");
						System.out.print("Please enter your choice : ");
						choice = input.nextInt();
	                    input.nextLine(); 
					}
				}				
			}
			input.close();
		}catch (Exception ex){
			ex.getMessage();
		}
		
	}
}
