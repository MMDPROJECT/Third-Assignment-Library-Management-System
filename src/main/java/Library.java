import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class Library {

    private ArrayList<Book> books;
    private ArrayList<User> users;
    private ArrayList<Librarian> librarians;
    private HashMap<String, Integer> book_maps;

    //Constructor
    public Library() {

        this.books = new ArrayList<Book>();
        this.users = new ArrayList<User>();
        this.librarians = new ArrayList<Librarian>();
        this.book_maps = new HashMap<String, Integer>();

        //default librarian
        Librarian default_librarian = new Librarian("Mobin Nesari", "1381");
        this.librarians.add(default_librarian);
    }

    //getter methods
    public ArrayList<Book> getBooks() {
        return this.books;
    }

    public ArrayList<User> getUsers() {
        return this.users;
    }

    public ArrayList<Librarian> getLibrarians() {
        return this.librarians;
    }

    public HashMap<String, Integer> getBook_maps() {
        return this.book_maps;
    }

    public Book getSpecifiedBook(String book_name) {

        for (Book book : this.books) {

            if (book.getBook_name().equals(book_name)) {

                return book;
            }
        }
        return null;
    }

    public User getSpecifiedUser(String username) {

        for (User user : this.users) {

            if (user.getUsername().equals(username)) {
                return user;
            }
        }
        return null;
    }

    public Librarian getSpecifiedLibrarian(String username) {

        for (Librarian librarian : this.librarians) {

            if (librarian.getUsername().equals(username)) {
                return librarian;
            }
        }
        return null;
    }


    //book - related functions

    public void addBook(String book_name, String author, String publish_year, String ISBN) {
        //add a new book to library
        Book new_book = new Book(book_name, author, publish_year, ISBN);
        this.books.add(new_book);
        increaseBook(ISBN);
        System.out.println("Book has been successfully added to library");
    }

    public void removeBook(String book_name) {
        //remove a special book from library
        for (int i = 0; i < this.books.size(); i++) {

            if (books.get(i).getBook_name().equals(book_name)) {

                book_maps.remove(books.get(i).getISBN());
                books.remove(i);
            }
        }
        System.out.println("Book has been successfully removed");
    }

    public void searchBook() {
        //Shows a list of books or a message that says library is empty
        if (this.books.size() == 0) {

            System.out.println("Library is vacant of books\n");
        } else {

            System.out.println("List Of Books:\n");

            for (Book book : this.books) {

                System.out.println("Name Of The Book: " + book.getBook_name() + ", Author: " + book.getAuthor() + ", Amount: " + this.book_maps.get(book.getISBN()) + ", Year Of Publication: " + book.getPublish_year() + ", ISBN Of The Book: " + book.getISBN());

            }
        }
    }

    public void updateBook(String ISBN, String new_book_name, String new_author, String new_publish_year) {
        //Update information of a book
        for (Book book : this.books) {

            if (book.getISBN().equals(ISBN)) {

                book.setBook_name(new_book_name);
                book.setAuthor(new_author);
                book.setPublish_year(new_publish_year);
                System.out.println("Book has been successfully updated");
                return;
            }
        }
        System.out.println("This book doesn't really exist, so you cannot update it.");
    }

    public boolean doesBookExist(String book_name) {
        //Check if a book exist or not
        for (Book book : this.books) {

            if (book.getBook_name().equals(book_name) && book_maps.get(book.getISBN()) > 0) {
                return true;
            }
        }
        return false;
    }

    public void increaseBook(String ISBN) {
        //increase amount by one
        if (!book_maps.containsKey(ISBN)) {

            book_maps.put(ISBN, 1);
        } else {

            book_maps.replace(ISBN, book_maps.get(ISBN) + 1);
        }
        System.out.println("Amount has been successfully increased");
    }

    public void decreaseBook(String ISBN) {
        //decrease amount by one
        book_maps.replace(ISBN, book_maps.get(ISBN) - 1);
        System.out.println("Amount has been successfully decreased");
    }

    //user related functions

    public void addUser(String username, String password) {
        //adds a user to list of users
        User new_user = new User(username, password);
        this.users.add(new_user);

        System.out.println("User " + username + " has been successfully added\n");
    }

    public void removeUser(String username) {
        //removes a user from list of users
        this.users.removeIf(user -> user.getUsername().equals(username));
        System.out.println("User has been successfully removed");
    }

    public void searchUser() {
        //Shows a list of users or a message says that users list is empty
        if (this.users.size() == 0) {

            System.out.println("Users list is empty!");
        } else {

            System.out.println("A list of users:\n");
            for (User user : this.users) {

                System.out.println("Username: " + user.getUsername() + ", Password: " + user.getPassword());
            }
        }
    }

    public void updateUser(String old_username, String new_username, String new_password) {
        //updates information of a special user
        for (User user : this.users) {

            if (user.getUsername().equals(old_username)) {

                user.setUsername(new_username);
                user.setPassword(new_password);
                System.out.println("User has been successfully updated");
                return;
            }
        }
        System.out.println("This user doesn't really exist, so you cannot update it");
    }

    public boolean doesUserExist(String username) {
        //Checks if a user exists
        for (User user : this.users) {

            if (user.getUsername().equals(username)) {

                return true;
            }
        }
        return false;
    }

    public boolean isUserValid(String username, String password) {
        //checks if this user is valid or not
        for (User user : this.users) {

            if (user.getUsername().equals(username) && user.getPassword().equals(password)) {
                return true;
            }
        }
        return false;
    }

    //librarian related functions

    public void addLibrarian(String username, String password) {
        //adds a new librarian to the librarians list
        Librarian new_librarian = new Librarian(username, password);
        this.librarians.add(new_librarian);
        System.out.println("Librarian has been successfully added");
    }

    public void removeLibrarian(String username) {
        //removes a special librarian
        this.librarians.removeIf(librarian -> librarian.getUsername().equals(username));
        System.out.println("librarian has been successfully removed");
    }

    public void searchLibrarian() {
        //Shows a list of librarians or a message says that librarians list is empty!
        if (this.librarians.size() == 0) {
            System.out.println("Librarians list is vacant!");
        } else {

            System.out.println("A list of librarians:");
            for (Librarian librarian : this.librarians) {

                System.out.println("Username: " + librarian.getUsername() + ", Password: " + librarian.getPassword());
            }
        }
    }

    public void updateLibrarian(String old_username, String new_username, String new_password) {
        //Updates information for a special librarian
        for (Librarian librarian : this.librarians) {

            if (librarian.getUsername().equals(old_username)) {

                librarian.setUsername(new_username);
                librarian.setPassword(new_password);
            }
        }
        System.out.println("Librarian has been successfully updated");
    }

    public boolean doesLibrarianExist(String username) {
        //Checks if a special librarian exists
        for (Librarian librarian : this.librarians) {

            if (librarian.getUsername().equals(username)) {
                return true;
            }
        }
        return false;
    }

    public boolean isLibrarianValid(String username, String password) {
        //Checks if librarians is valid
        for (Librarian librarian : this.librarians) {

            if (librarian.getUsername().equals(username) && librarian.getPassword().equals(password)) {
                return true;
            }
        }
        return false;
    }
}
