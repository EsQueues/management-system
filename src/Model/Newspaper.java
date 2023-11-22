package Model;

public class Newspaper extends Literature {
    private int id;

    public Newspaper(int id,String title, int year){
        super(title,year);
        this.id=id;
    }
    public Newspaper(String title, int year) {
        super(title, year);
    }

    @Override
    public String toString() {
        return "Newspaper{" +
                "title='" + getTitle() + '\'' +
                ", year=" + getYear() +
                '}';
    }

    public int getId() {
        return this.id;
    }
}
