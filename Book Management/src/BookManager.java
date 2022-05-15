import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.*;

public class BookManager {
    // attribute books
    ArrayList <Book> books ;


    public BookManager() {
        this.books = new ArrayList<>();
    }

    public ArrayList<Book> getBooks() {
        return this.books;
    }

    /**
     * update this.books by reading books from file books.txt
     */
    public void loadFromFile() {
        System.out.println("Loading books...");

        try {
//			initialize variable
            int id;
            String name;
            double price;
            Book book;

//			TODO: change link to books.txt file then delete this comment
            Scanner in = new Scanner(new File("src/projectPR1/books.txt"));

//        	read file
            while(in.hasNextLine()) {
                String sentence = in.nextLine();

//        		get substring
                id = Integer.parseInt(sentence.substring(1, 5).trim());
                name = sentence.substring(6, 50).trim();
                price = Double.parseDouble(sentence.substring(51).trim());

//        		add book to list
                book = new Book(id,name,price);
                books.add(book);
            }
        } catch(FileNotFoundException e) {
            System.out.println(e);
        }
    }

    /**
     * print books (one/line) in required format
     */
    public void printBooks(ArrayList<Book> books) {
        if(books.size() != 0 ) {
            System.out.printf("%-5s %-45s %-10s\n","ID","Name","Price");
            for(Book book : books) {
                System.out.println(book.toString());
            }
        } else {
            System.out.println("(empty)");
        }
    }

    /**
     * if book.id is not duplicated, add book to this.books
     * return whether added or not
     */
    public boolean add(Book book) {
        int newId = book.id;

        for(Book b : books) {
            if(newId == b.id) {
                return false;
            }
        }
//    	else
        books.add(book);
        return true;
    }

    /**
     * return book specified by id, null if not found
     */
    public Book getBookById(int id) {
        if(id > 0) {
            for(Book book : books) {
                if(id == book.id) {
                    return book;
                }
            }

        }
        return null;
    }

    /**
     * delete book from this.books
     */
    public void delete(Book book) {
        books.remove(book);
    }

    /**
     * update this.books to be sorted by price from high -> low
     */
    public void sortDescByPrice() {
//    	if there're some books
        if(books.size() != 0){
            books.sort((b1,b2) -> Double.compare(b1.price, b2.price) );
            Collections.reverse(books);

//        	Print out result
            System.out.println("After sorting: ");
            System.out.printf("%-5s %-45s %-10s\n","ID","Name","Price");
            for(Book b : books) {
                System.out.printf("%5d %-45s %10.2f\n",b.id,b.name,b.price);
            }
        } else {
            System.out.println("(empty)");
        }
    }

    /**
     * return all books having name contains keyword (case in-sensitive)
     */
    public ArrayList<Book> searchByName(String keyword) {
        ArrayList<Book> matches = new ArrayList<>();

        String bookName;
        for(Book book : books) {
            bookName = book.name.toLowerCase();
            if(bookName.contains(keyword)) {
                matches.add(book);
            }
        }

        return matches;
    }

    /**
     * write this.books to file books.txt in required format
     */
    public void saveToFile() {
        try {
            //TODO: change link to file and delete comment
            PrintWriter writer = new PrintWriter(new File("src/projectPR1/books.txt"));
            for(Book b : books) {
                writer.write(b.toString() + "\n");
            }
            writer.flush();
            writer.close();

        } catch (FileNotFoundException e) {
            System.out.println(e);
        }
    }
}
