package Model;

public class Book  extends Literature {
    private int id;

    public Book(int id,String title, int year,  String author, boolean available, String genre, int readerId) {
        super(title, year);
        this.id = id;
        this.author = author;
        this.available = available;
        this.genre = genre;
        this.readerId = readerId;
    }

    public int getId() {
        return id;
    }

    private final String author;
    private boolean available;

    @Override
    public String toString() {
        return "Book{" +
                "id=" + id +
                ", author='" + author + '\'' +
                ", available=" + available +
                ", genre='" + genre + '\'' +
                ", readerId=" + readerId +
                '}';
    }

    private String genre;
    private int readerId;

    public Book(String title, int year,  String author, boolean available, String genre) {
        super(title,year);
        this.author = author;
        this.available = available;
        this.genre = genre;
    }

    public int getReaderId() {
        return readerId;
    }
    public void setReaderId(int readerId){
        this.readerId=readerId;
    }
    public String getAuthor() {
        return author;
    }


    public boolean isAvailable() {
        return available;
    }

    public void setAvailable(boolean available) {
        this.available = available;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

}
