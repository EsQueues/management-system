package Model;

public abstract class Literature {
    private int id;
    private String title;
    private int year;


    public Literature(String title, int year){
        this.title=title;
        this.year = year;
    }
    public String getTitle() {
        return title;
    }

    public int getYear() {
        return year;
    }



}
