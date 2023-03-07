import java.util.ArrayList;

public class User {
    private ArrayList<Book> rented_books;
    private String username;
    private String password;

    //constructor
    public User(String username, String password) {

        this.rented_books = new ArrayList<Book>();
        this.username = username;
        this.password = password;
    }

    //getter methods
    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public ArrayList<Book> getRented_books() {
        return rented_books;
    }

    //setter methods
    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    //user - related methods
    public void rentBook(Book rented_book) {
        //add book to the rented_books
        this.rented_books.add(rented_book);
        System.out.println("Book has been successfully rented");
    }

    public void returnBook(Book returned_book) {
        //find and remove book from rented_books
        this.rented_books.removeIf(book -> book.getBook_name().equals(returned_book.getBook_name()));
        System.out.println("Book has been successfully returned");
    }

    public boolean hasRentedBook(String book_name) {

        for (Book book : this.rented_books) {

            if (book.getBook_name().equals(book_name)) {
                return true;
            }
        }
        return false;
    }

    public void showBorrowedBooks() {

        if (rented_books.size() == 0) {

            System.out.println("User " + this.username + " has not rented any books yet!");
        } else {

            System.out.println("User " + this.username + " has rented this list of books");
            for (Book book : this.rented_books) {

                System.out.print(book.getBook_name() + "  ");
            }
        }
        System.out.println("\n");
    }
}