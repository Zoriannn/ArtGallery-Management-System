package group_2;

import java.util.ArrayList;
import java.util.Scanner;

public class ArtistInformationTest {
	public static void main() {
		ArtistInformation artistInformation = new ArtistInformation();
		ArrayList<ArtistInformation> artistList = artistInformation.getList();
		artistInformation.readArtistFile();
		
		Scanner input = new Scanner(System.in);
		
		try {
			int opt;
			boolean loop = true;
			
			while (loop) {
				System.out.println("------------------------------------------------------------------------------------------");
				System.out.printf("%1$49s%n", "ARTIST INFORMATION");
				System.out.println("------------------------------------------------------------------------------------------");
				System.out.printf("%1$-5s %2$-20s %3$-15s %4$-20s %5$-20s%n", "ID", "NAME", "SPECIALTY", "STATUS", "PRICE RANGE");
				System.out.printf("%1$-5s %2$-20s %3$-15s %4$-20s %5$-20s%n", "----", "-----------------", "-----------", "-----------------", "------------------");

				for (ArtistInformation artistInfo : artistList) {
		            System.out.printf("%1$-5s %2$-20s %3$-15s %4$-20s %5$-20s%n", artistInfo.getArtistID(), artistInfo.getArtistName(),  
		            		artistInfo.getSpecialty(), artistInfo.getStatus(), artistInfo.getPriceRange());
		        }
				System.out.println("\n------------------------------------------------------------------------------------------");
				
				System.out.println("Do you want to: ");
				System.out.print("\n1. Add\n2. Delete\n3. Modify\n4. Search\n5. Exit\nEnter option: ");
				
				opt = input.nextInt();
				
				boolean loop2 = true;
				while(loop2) {
					if(opt == 1) {
						ArtistInformation.addArtist();
						loop2 = false;
					}
					else if(opt == 2) {
						ArtistInformation.deleteArtist();
						loop2 = false;
					}
					else if(opt == 3) {
						ArtistInformation.modifyArtist();
						loop2 = false;
					}
					else if(opt == 4) {
						ArtistInformation.searchArtist();
						loop2 = false;
					}
					else if(opt == 5) {
						System.out.println("Exiting to the Main Menu...");
						loop2 = false; 
						loop = false;
						Main.main(null);
					}
					else {
						System.out.println("Invalid option. Only 1 - 6 is allowed.");
						System.out.println("Please enter an option: ");
						opt = input.nextInt();
						input.nextLine(); 
					}
				}
			}
			input.close();
		} catch (Exception ex) {
			ex.getMessage();
		}
	}
}

