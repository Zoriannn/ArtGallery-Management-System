package group_2;
import java.io.Console;
import java.io.IOException;
import java.util.*;
public class Main {

	public static void main(String[] args) {
		clearScreen();
		System.out.println("--------------------------------------------------------------");
		System.out.println("                WELCOME TO UX GALLERY PROGRAM                 ");
		System.out.println("--------------------------------------------------------------");
		System.out.println("1. CUSTOMER");
		System.out.println("2. ARTIST");
		System.out.println("3. ARTWORK");
		System.out.println("4. EXIT");
		System.out.print("\nPlease enter your choice : ");
		Scanner input = new Scanner(System.in);
		int choice = input.nextInt();
		
		switch(choice) {
			case 1:
				
				CustomerInformationMenu.main();
				clearScreen();
				break;
			case 2:
				clearScreen();
				ArtistInformationTest.main();
				break;
			case 3:
				clearScreen();
				ArtworkInformationMenu.main();
				break;
			case 4:
				clearScreen();
				break;
		}
		
	}
	public static void clearScreen() {
	    System.out.print("\033[H\033[2J");  
	    System.out.flush();  
	}
}
