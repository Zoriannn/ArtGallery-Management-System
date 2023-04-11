package group_2;

import java.io.BufferedWriter;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class ArtistInformation {
	// Instance variables
		private String artistID;
		private String artistName;
		private String specialty;
		private String status;
		private String priceRange;
		private static ArrayList<ArtistInformation> artistList = new ArrayList<>();
		
		// Accessor methods
		public String getArtistID() {
			return artistID;
		}
		
		public String getArtistName() {
			return artistName;
		}

		public String getSpecialty() {
			return specialty;
		}
		
		public String getStatus() {
			return status;
		}
		
		public String getPriceRange() {
			return priceRange;
		}
		
		public ArrayList<ArtistInformation> getList() {
			return artistList;
		}
		
		// Mutator methods
		public void setArtistID(String artistID) {
			this.artistID = artistID;
		}
		
		public void setArtistName(String artistName) {
			this.artistName = artistName;
		}
		
		public void setSpecialty(String specialty) {
			this.specialty = specialty;
		}
		
		public void setStatus(String status) {
			this.status = status;
		}
		
		public void setPriceRange(String priceRange) {
			this.priceRange = priceRange;
		}
		
		// Constructor
		public ArtistInformation(String artistID, String artistName, String specialty, String status, String priceRange) {	
			this.artistID = artistID;
			this.artistName = artistName;
			this.specialty = specialty;
			this.status = status;
			this.priceRange = priceRange;
		}
		
		public ArtistInformation() {
			
		}
		
		// Read artist data text file
		public void readArtistFile() {
			//read the file and 
			try {
				//Create a new file object
				FileReader fileReader = new FileReader ("artist.txt");
				BufferedReader bufferedReader = new BufferedReader(fileReader);
				
				//Read each line of the file and add it to the ArrayList
				String line;
		        while ((line = bufferedReader.readLine()) != null) {
	                String[] fields = line.split("\\|");
	                String artistID = fields[0];
	                String artistName = fields[1];
	                String specialty = fields[2];
	                String status = fields[3];
	                String priceRange = fields[4];
	                
	                ArtistInformation artistInfo = new ArtistInformation(artistID, artistName, specialty, status, priceRange);
	                artistList.add(artistInfo);
		        }
		        
		        bufferedReader.close();
		        fileReader.close();
			}
			catch(IOException e) {
				System.out.println("An error has occurred.");
				e.printStackTrace();
			}
		}
			
		// Write text file
		public void writeFile() {		
			 try {
		        // Create a new FileWriter object with the file path as parameter
		        FileWriter fileWriter = new FileWriter("artist.txt");
		
		        // Create a new BufferedWriter object to write data to the file
		        BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
		
		        // Write data to the file
	            for (ArtistInformation artistInformation : artistList) {
	            	String line = String.format("%s|%s|%s|%s|%s",
	            		    artistInformation.getArtistID(),
	            		    artistInformation.getArtistName(),
	            		    artistInformation.getSpecialty(),
	            		    artistInformation.getStatus(),
	            		    artistInformation.getPriceRange());
	            	bufferedWriter.write(line);
	            	bufferedWriter.newLine(); // add new line character
	            }
		
		        // Close the BufferedWriter
	            bufferedWriter.close();
	            fileWriter.close();
	        } catch (IOException e) {
	            e.printStackTrace();
	            }
		}

		// Add artist method
		public static void addArtist() {
			Scanner input = new Scanner(System.in);
			
			System.out.println("--------------------------------------------------------------------------------------");
			System.out.printf("%1$44s%n", "ADD ARTIST");
			System.out.println("--------------------------------------------------------------------------------------");
			
			String[] idList = new String[artistList.size()];
			for (int i = 0; i < artistList.size(); i++) {
			    idList[i] = artistList.get(i).getArtistID();
			}
			
			System.out.print("Enter artist ID			: ");
			String id = input.next();
			
			boolean idExists = true;
	        while (idExists) {
	            for (int j = 0; j < idList.length; j++) {
	                if (idList[j].equals(id)) {
	                    System.out.println("The ID already exists. Please use another ID.");
	                    System.out.print("Enter artist ID			: ");
	                    id = input.next();
	                    break; // Break out of the for loop so we don't keep checking the same ID
	                }
	                if (j == idList.length - 1) {
	                    idExists = false; // Exit the while loop because the ID doesn't exist in the array
	                }
	            }
	        }

			System.out.print("Enter artist name			: ");
			input.nextLine();
			String name = input.nextLine();
				
			System.out.print("Enter artist specialty			: ");
			String specialty = input.next();
			
			System.out.print("Enter artist status		: ");
			String status = input.next();

			System.out.print("Enter artist price range		: ");
			String priceRange = input.nextLine();
			
			
			while (true)
	        {
	        	System.out.println("\nPlease check your newly added artist data.");
	        	System.out.println("The artist ID				: " + id);
	        	System.out.println("The artist name			: " + name);
	        	System.out.println("The artist specialty			: " + specialty);
	        	System.out.println("The artist status	: " + status);
	        	System.out.println("The artist price range	: " + priceRange);
	        	
	            System.out.println("\nAre you sure you want to add?");
		        System.out.print("Yes(Y)     No(N)      : ");
		        char option = input.next().charAt(0);
		        if (option == 'Y' || option == 'y') 
		        {
	                ArtistInformation artistInformation = new ArtistInformation(id, name, specialty, status, priceRange);
	                artistList.add(artistInformation);
	               
	                artistInformation.writeFile();
		            System.out.println("\nThe artist " + id + " has been added successfully.");
		            break;
		        } 
		        else if (option == 'N' || option == 'n') 
		        {
		            System.out.println("\nThe add artist action is cancelled.");
		            break;
		        } 
		        else 
		        {
		            System.out.println("Wrong option. Only Y and N is allowed.");
		            
		        }	        
	        }
			System.out.println("Press any key to continue.....");
			input.nextLine();      
			input.nextLine();
			
			input.close();
		}

		// Delete artist method
		public static void deleteArtist() {
			Scanner input = new Scanner(System.in);
			
			System.out.println("--------------------------------------------------------------------------------------");
			System.out.printf("%1$50s%n", "DELETE ARTIST");
			System.out.println("--------------------------------------------------------------------------------------");
			
			String[] idList = new String[artistList.size()];
			for (int i = 0; i < artistList.size(); i++) 
			{
			    idList[i] = artistList.get(i).getArtistID();
			}
				
			System.out.print("Enter artist ID		: ");
			String id = input.next();
			
			boolean found = true;
			boolean idExists = true;
	        while (idExists) {
	            for (int j = 0; j < idList.length; j++) {
	                if (idList[j].equals(id)) {
	                	System.out.println("\nThe ID " + id + " is in the artist list.");
	                	System.out.println("The artist ID			: " + id );
	                	System.out.println("The artist name		: " + artistList.get(j).getArtistName());
	                	System.out.println("The artist specialty		: " + artistList.get(j).getSpecialty());
	                	System.out.println("The artist status		: " + artistList.get(j).getStatus());
	                	System.out.println("The artist price range	: " + artistList.get(j).getPriceRange()); 
	            
	                	System.out.println("\nAre you sure you want to delete?");
	            		System.out.print("Yes(Y)     No(N)      : ");
	            		char option = input.next().charAt(0);
	            		if(option == 'Y' || option == 'y' || option == 'N' || option == 'n'){
		            		while(found){ 			
		            			if (option == 'Y' || option == 'y'){
		            				artistList.remove(j);	            					            				

		                            ArtistInformation artistInformation = new ArtistInformation();
		                            artistInformation.writeFile();
		                            	
		            				System.out.println("\nThe artist information of " + id + " was deleted successfully.");
		            				idExists = false; 
		            				break;
		                		}
		                		else if (option == 'N' || option == 'n') {
		                			System.out.println("\nThe delete artist action is cancelled");
		                			idExists = false; 
		                			break;
		                		}
		            		}
	            		}
	            		else {
	            			System.out.println("Wrong option. Only Y and N is allowed.");
	            			System.out.print("Yes(Y)     No(N)      : ");
	            			option = input.next().charAt(0);
	            		}
	            		
	                    break; // Break out of the for loop so we don't keep checking the same ID
	                }
	                if (j == idList.length - 1) {
	                    System.out.println("The ID does not exist. Please use another ID.");
	                    System.out.print("Enter artist ID			: ");
	                    id = input.next();
	                }
	            }
	        }  
	        System.out.println("Press any key to continue.....");
			input.nextLine();
			input.nextLine();
			
			input.close();
		}

		public static void modifyArtist() {
			Scanner input = new Scanner (System.in);
			System.out.println("--------------------------------------------------------------------------------------");
			System.out.printf("%1$50s%n", "MODIFY ARTIST");
			System.out.println("--------------------------------------------------------------------------------------");
			
			System.out.print("Enter artist ID			: ");
			String id = input.next();
			
			boolean idExists = false;
			for (int i = 0; i < artistList.size(); i++) {
			    if (artistList.get(i).getArtistID().equals(id)) {
			        idExists = true;
			        System.out.println("\nThe ID " + id + " is in the artist list.");
			        System.out.println("The artist ID			: " + id );
	            	System.out.println("The artist name		: " + artistList.get(i).getArtistName());
	            	System.out.println("The artist specialty		: " + artistList.get(i).getSpecialty());
	            	System.out.println("The artist status		: " + artistList.get(i).getStatus());
	            	System.out.println("The artist price range	: " + artistList.get(i).getPriceRange());
	            	
			        // Prompt for new artist info to modify
	            	System.out.println("\nPlease enter new artist info.");
		            System.out.print("Enter new ID			: ");
		            String newID = input.next();
		            System.out.print("Enter new name			: ");
		            input.nextLine();
		            String newName = input.nextLine();
		            System.out.print("Enter new specialty		: ");
		            String newSpecialty = input.next();
		            
		            System.out.print("Enter new status	: ");
		            String newStatus = input.next();
		            System.out.print("Enter new price range	: ");
		            String newPriceRange = input.next();
		      
		            while (true) {
		            	System.out.println("\nPlease check your newly modified artist data.");
		            	System.out.println("The new ID			: " + newID);
		            	System.out.println("The new name			: " + newName);
		            	System.out.println("The new specialty			: " + newSpecialty);
		            	System.out.println("The new status	: " + newStatus);
		            	System.out.println("The new price range	: " + newPriceRange);
		            	
			            System.out.println("\nAre you sure you want to modify?");
				        System.out.print("Yes(Y)     No(N)      : ");
				        char option = input.next().charAt(0);
				        if (option == 'Y' || option == 'y') {
				        	artistList.get(i).setArtistID(newID);
				        	artistList.get(i).setArtistName(newName);
				        	artistList.get(i).setSpecialty(newSpecialty);
				        	artistList.get(i).setStatus(newStatus);
				        	artistList.get(i).setPriceRange(newPriceRange);
				            ArtistInformation artistInfomation = new ArtistInformation();
		                    artistInfomation.writeFile();   
				            System.out.println("\nThe artist data has been modified successfully.");
				            break;
				        } 
				        else if (option == 'N' || option == 'n') {
				            System.out.println("\nThe modify artist action is cancelled.");
				            break;
				        } 
				        else {
				            System.out.println("Wrong option. Only Y and N is allowed."); 
				        }
		            }
		            break;
			    }
			}
			if (!idExists) {
			    System.out.println("The ID does not exist. Please enter a valid ID.");
			    System.out.print("Enter artist ID			: ");
	            id = input.next();
			}
			System.out.println("Press any key to continue...");
			input.nextLine();
			input.nextLine();
			
			input.close();
		}

		public static void searchArtist() {
			Scanner input = new Scanner (System.in);
			System.out.println("--------------------------------------------------------------------------------------");
			System.out.printf("%1$50s%n", "SEARCH ARTIST");
			System.out.println("--------------------------------------------------------------------------------------");

			System.out.print("Enter artist ID			: ");
			String id = input.next();

			boolean idExists = true;
			boolean found = false;
			while (idExists) {
				for (int i = 0; i < artistList.size(); i++) {
				    if (artistList.get(i).getArtistID().equals(id)) {
				        
				        System.out.println("\nThe ID " + id + " is in the artist list.");
				        System.out.println("The artist ID			: " + id );
		            	System.out.println("The artist name		: " + artistList.get(i).getArtistName());
		            	System.out.println("The artist specialty		: " + artistList.get(i).getSpecialty());
		            	System.out.println("The artist status		: " + artistList.get(i).getStatus());
		            	System.out.println("The artist price range	: " + artistList.get(i).getPriceRange());
		            	//idExists = false;
		            	found = true;
		            	idExists = false;
		            	break;
				    }
				}
				if (!found) {
				    System.out.println("\nThe ID does not exist. Please enter a valid ID.");
				    System.out.print("Enter artist ID			: ");
		            id = input.next();
				}
			}
			System.out.println("Press any key to continue.....");
			input.nextLine();
			input.nextLine();
			
			input.close();
		}
}

