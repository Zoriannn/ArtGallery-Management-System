package group_2;

import java.util.Scanner;
import java.io.FileNotFoundException;
import java.io.File;

public class ArtworkInformationMenu {
	public static void main() {
        Scanner scanner = new Scanner(System.in);
        ArtworkManager artworkManager = new ArtworkManager("inventory.txt");

        System.out.println("--------------------------------------------------------------------------------------------------------------------------------------------------------------------");
        System.out.printf("%80s\n", "ARTWORK INFORMATION");
        System.out.println("--------------------------------------------------------------------------------------------------------------------------------------------------------------------");
        System.out.println(String.format("%-10s%-30s%-30s%-20s%-20s%-20s%-20s%s", "ID", "Name", "Artist", "DatePurchase", "DateSold", "PurchasePrice", "SellingPrice", "Status"));
        
        for (Artwork artwork : artworkManager.listArtwork()) {
            System.out.println(String.format("%-10s%-30s%-30s%-20s%-20s%-20s%-20s%s",
                    artwork.getId(),
                    artwork.getTitle(),
                    artwork.getArtist(),
                    artwork.getDatePurchased(),
                    artwork.getDateSold(),
                    artwork.getPurchasePrice(),
                    artwork.getSellingPrice(),
                    artwork.getStatus()));
        }
        
        boolean exit = false;
        while (!exit) {
            System.out.println("\nDo you want to:");
            System.out.println("1. Find");
            System.out.println("2. Add");
            System.out.println("3. Modify");
            System.out.println("4. Delete");
            System.out.println("5. Exit");
            System.out.print("\nPlease enter your choice : ");

            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    System.out.print("\nEnter the ID of the artwork:");
                    String id = scanner.next();
                    scanner.nextLine();
                    Artwork artwork = artworkManager.findArtwork(id);
                    if (artwork != null) {
                        System.out.println(artwork.toText());
                    } else {
                        System.out.println("Artwork not found.");
                    }
                    break;
                case 2:
                    String newId;
                    while (true) {
                        System.out.print("\nEnter the ID of the artwork (4 digits):");
                        newId = scanner.next();
                        scanner.nextLine();
                        if (newId.length() != 4 || !newId.matches("\\d+")) {
                            System.out.println("Please enter a valid 4-digit number.");
                        } else if (artworkManager.findArtworkById(newId) != null) {
                            System.out.println("An artwork with that ID already exists.");
                        } else {
                            break;
                        }
                    }

                    System.out.print("Enter the title of the artwork:");
                    String title = scanner.nextLine();
                    
                    System.out.print("Enter the artist of the artwork:");
                    String artist = scanner.nextLine();
                    boolean artistFound = false;
                    try (Scanner artistFileScanner = new Scanner(new File("artist.txt"))) {
                        while (artistFileScanner.hasNextLine()) {
                            String line = artistFileScanner.nextLine();
                            String[] parts = line.split("\\|");
                            if (parts.length >= 2 && parts[1].equalsIgnoreCase(artist)) {
                                artistFound = true;
                                break;
                            }
                        }
                    } catch (FileNotFoundException e) {
                        System.out.println("Error: artist.txt file not found");
                    }

                    if (!artistFound) {
                        System.out.println("The artist is not found in the artist.txt file. Please add the artist first.");
                        // Return to the main menu
                        Main.main(null);
                        return;
                    }


                    System.out.print("Enter the date purchased (yyyy-mm-dd) of the artwork:");
                   String datePurchased = scanner.nextLine();
                    if (!datePurchased.matches("\\d{4}-\\d{2}-\\d{2}")) {
                        System.out.println("Invalid date format. Please enter the date in the format yyyy-mm-dd.");
                        scanner.nextLine(); 
                    }
                    System.out.print("Enter the date sold (yyyy-mm-dd) of the artwork (press enter if not sold yet):");
                    String dateSold = scanner.nextLine();

                    if (!dateSold.isEmpty() && !dateSold.matches("\\d{4}-\\d{2}-\\d{2}")) {
                        System.out.println("Invalid date format. Please enter the date in the format yyyy-mm-dd or press enter if not sold yet.");
                        scanner.nextLine(); // clear the buffer
                    }

                    System.out.print("Enter the purchase price of the artwork:");
                    double purchasePrice;
                    while (true) {
                        String purchasePriceStr = scanner.nextLine();
                        if (purchasePriceStr.matches("\\d+")) {
                            purchasePrice = Double.parseDouble(purchasePriceStr);
                            break;
                        }
                        System.out.println("Invalid input. Please enter only digits.");
                    }

                    System.out.print("Enter the selling price of the artwork (press enter if not sold yet):");
                    double sellingPrice = 0.0;
                    while (true) {
                        String sellingPriceStr = scanner.nextLine();
                        if (sellingPriceStr.isEmpty()) {
                            break;
                        }
                        if (sellingPriceStr.matches("\\d+")) {
                            sellingPrice = Double.parseDouble(sellingPriceStr);
                            break;
                        }
                        System.out.println("Invalid input. Please enter only digits or press enter if not sold yet.");
                    }


                    System.out.print("Enter the status of the artwork (sold/available):");
                    String status;
                    while (true) {
                        status = scanner.nextLine();
                        if (status.equals("Sold") || status.equals("Available")) {
                            break;
                        }
                        System.out.println("Invalid input. Please enter either 'sold' or 'available'.");
                    }
                    Artwork newArtwork = new Artwork(newId, title, artist, datePurchased, dateSold, purchasePrice, sellingPrice, status);
                    artworkManager.addArtwork(newArtwork);
                    System.out.println("Artwork added successfully.");
                    break;

                case 3:
                    System.out.print("\nEnter the ID of the artwork:");
                    String modifyId = scanner.next();
                    scanner.nextLine();
                    Artwork artwork1 = artworkManager.findArtwork(modifyId);
                    if (artwork1 != null) {
                        System.out.println("\nArtwork information:");
                        System.out.println("ID: " + artwork1.getId());
                        System.out.println("Name: " + artwork1.getTitle());
                        System.out.println("Artist: " + artwork1.getArtist());
                        System.out.println("Purchased Date: " + artwork1.getDatePurchased());
                        System.out.println("Sold Date: " + artwork1.getDateSold());
                        System.out.println("Purchased Price: " + artwork1.getPurchasePrice());
                        System.out.println("Sold Price: " + artwork1.getSellingPrice());
                        System.out.println("Status: " + artwork1.getStatus());

                        String newTitle = "";
                        while (newTitle.equals("")) {
                            System.out.print("\nEnter the new title of the artwork:");
                            newTitle = scanner.nextLine();
                        }
                        artwork1.setTitle(newTitle);

                        System.out.print("\nEnter the new artist of the artwork:");
                        String newArtist = scanner.nextLine();

                        boolean artistFound1 = false;
                        try (Scanner artistFileScanner = new Scanner(new File("artist.txt"))) {
                            while (artistFileScanner.hasNextLine()) {
                                String line = artistFileScanner.nextLine();
                                String[] parts = line.split("\\|");
                                if (parts.length >= 2 && parts[1].equalsIgnoreCase(newArtist)) {
                                    artistFound1 = true;
                                    break;
                                }
                            }
                        } catch (FileNotFoundException e) {
                            System.out.println("Error: artist.txt file not found");
                        }

                        if (!artistFound1) {
                            System.out.println("The artist is not found in the artist.txt file. Please add the artist first.");
                            // Return to the main menu
                            Main.main(null);
                            return;
                        }

                        artwork1.setArtist(newArtist);


                        String newPurchasedDate = "";
                        while (true) {
                            System.out.print("\nEnter the new purchased date of the artwork (yyyy-MM-dd): ");
                            newPurchasedDate = scanner.nextLine();
                            if (newPurchasedDate.equals("N/A") || newPurchasedDate.matches("\\d{4}-\\d{2}-\\d{2}")) {
                                break;
                            } else {
                                System.out.println("Error: Invalid input format. Please enter a valid date in the format yyyy-MM-dd or N/A.");
                            }
                        }
                        artwork1.setDatePurchased(newPurchasedDate);

                        String newSoldDate = "";
                        while (true) {
                            System.out.print("\nEnter the new sold date of the artwork (yyyy-MM-dd or N/A, press Enter to skip): ");
                            newSoldDate = scanner.nextLine();
                            if (newSoldDate.equals("") || newSoldDate.equals("N/A") || newSoldDate.matches("\\d{4}-\\d{2}-\\d{2}")) {
                                break;
                            } else {
                                System.out.println("Error: Invalid input format. Please enter a valid date in the format yyyy-MM-dd or N/A, or press Enter to skip.");
                            }
                        }
                        artwork1.setDateSold(newSoldDate);


                        double newPurchasePrice = 0.0;
                        while (newPurchasePrice == 0.0) {
                            System.out.print("\nEnter the new purchase price of the artwork:");
                            try {
                                newPurchasePrice = Double.parseDouble(scanner.nextLine());
                            } catch (NumberFormatException e) {
                                System.out.println("Invalid input. Please enter a valid number.");
                            }
                        }
                        artwork1.setPurchasePrice(newPurchasePrice);

                        double newSellingPrice = -1.0;
                        while (newSellingPrice < 0) {
                            System.out.print("\nEnter the new selling price of the artwork (Press enter to skip):");
                            String input = scanner.nextLine();
                            if (input.equals("")) {
                                newSellingPrice = 0.0;
                            } else {
                                try {
                                    newSellingPrice = Double.parseDouble(input);
                                    if (newSellingPrice < 0) {
                                        System.out.println("Error: Selling price cannot be negative.");
                                    }
                                } catch (NumberFormatException e) {
                                    System.out.println("Invalid input. Please enter a valid number.");
                                }
                            }
                        }
                        artwork1.setSellingPrice(newSellingPrice);


                        String newStatus = "";
                        while (!newStatus.equalsIgnoreCase("Sold") && !newStatus.equalsIgnoreCase("Available")) {
                            System.out.print("\nEnter the new status of the artwork (Sold or Available):");
                            newStatus = scanner.nextLine();
                            if (!newStatus.equalsIgnoreCase("Sold") && !newStatus.equalsIgnoreCase("Available")) {
                                System.out.println("Error: Invalid input. Please enter either Sold or Available.");
                            }
                        }
                        artwork1.setStatus(newStatus);

                        artworkManager.modifyArtwork(artwork1);
                        System.out.println("Artwork modified successfully.");
                    } else {
                        System.out.println("Artwork not found.");
                    }
                    break;

                case 4:
                    System.out.print("\nEnter the ID of the artwork to delete:");
                    String deleteId = scanner.next();
                    scanner.nextLine();
                    if (artworkManager.deleteArtwork(deleteId)) {
                        System.out.println("Artwork deleted successfully.");
                    } else {
                        System.out.println("Artwork not found.");
                    }
                    break;
                case 5:
                    System.out.println("\nExiting the artwork program...");
                    Main.main(null);
                    break;
                default:
                    System.out.println("wrong option. Only 1-6 is allowed");
                    }
            }
        }
}