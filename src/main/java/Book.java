public class Book {

    private String book_name;
    private String author;
    private String publish_year;
    private String ISBN;

    //Constructor
    public Book(String book_name, String author, String publish_year, String ISBN) {

        this.book_name = book_name;
        this.author = author;
        this.publish_year = publish_year;
        this.ISBN = ISBN;
    }

    //getter methods
    public String getBook_name() {
        return book_name;
    }

    public String getAuthor() {
        return author;
    }

    public String getPublish_year() {
        return publish_year;
    }

    public String getISBN() {
        return ISBN;
    }

    //setter methods
    public void setBook_name(String book_name) {
        this.book_name = book_name;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public void setPublish_year(String publish_year) {
        this.publish_year = publish_year;
    }

}