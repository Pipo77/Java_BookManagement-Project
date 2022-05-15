import java.util.ArrayList;
import java.util.Scanner;

public class Main {

    public static void printMenu() {
        System.out.println("-----------------------------------");
        System.out.print("1. List all books\n" + "2. Add a new book\n" + "3. Edit book\n" + "4. Delete book\n"
                + "5. Search books by name\n" + "6. Sort books descending by price\n\n" + "0. Save & exit\n"
                + "-----------------------------------\n" + "Your option: ");
    }

    public static void main(String[] args) {
        BookManager bm = new BookManager();
        bm.loadFromFile();
        Scanner sc = new Scanner(System.in);
        int choice = -1;

        try {
            do {
                printMenu();
                choice = sc.nextInt();

                switch (choice) {
                    case 1:
                        bm.printBooks(bm.getBooks());
                        break;
                    case 2:
//    				get info of new book
                        System.out.print("Enter book id: ");
                        int newId = sc.nextInt();
                        sc.nextLine();
                        System.out.print("Enter book name: ");
                        String newName = sc.nextLine();
                        System.out.print("Enter book price: ");
                        double newPrice = sc.nextDouble();
//
                        Book newBook = new Book(newId, newName, newPrice);

//    				check result and print out message
                        if (bm.add(newBook)) {
                            System.out.println("Added successfully.");
                        } else {
                            System.out.println("Duplicated ID!");
                        }
                        break;
                    case 3:
                        System.out.print("Enter book id: ");
                        int editId = sc.nextInt();
                        Book editedBook = bm.getBookById(editId);

                        if (editedBook != null) {
//    					change book info
                            sc.nextLine();
                            System.out.print("Enter book name: ");
                            String editName = sc.nextLine();
                            System.out.print("Enter book price: ");
                            double editPrice = sc.nextDouble();

//    					set new info and print out message
                            editedBook.setName(editName);
                            editedBook.setPrice(editPrice);
                            System.out.println("Updated successfully!");
                        } else {
                            System.out.println("Invalid ID!");
                        }
                        break;
                    case 4:
                        System.out.print("Enter book id: ");
                        int delId = sc.nextInt();
                        Book delBook = bm.getBookById(delId);

                        if (delBook != null) {
                            bm.delete(delBook);
                            System.out.println("Delete successfully!");
                        } else {
                            System.out.println("Invalid ID!");
                        }
                        break;
                    case 5:
                        System.out.print("Enter keyword: ");
                        sc.nextLine();
                        String keyword = sc.nextLine();
                        ArrayList<Book> searchBooks = bm.searchByName(keyword.toLowerCase());
                        if (searchBooks.size() == 0) {
                            System.out.println("(empty)");
                        } else {
                            System.out.printf("%-5s %-45s %-10s\n", "ID", "Name", "Price");
                            for (Book book : searchBooks) {
                                System.out.printf("%5d %-45s %10.2f\n", book.id, book.name, book.price);
                            }
                        }

                        break;
                    case 6:
                        bm.sortDescByPrice();
                        break;
                    case 0:
                        System.out.println("Saving to file...\nBye!");
                        bm.saveToFile();
                        break;
                    default:
                        System.out.println("Invalid option!");
                        break;
                }
            } while (choice != 0);
        } catch (Exception e) {
            System.out.print("Invalid Input");
        }
    }
}
