public class Book extends Item{
    private String author;
    private int year;
    private String type;

    public Book(){
        this.type = "Book";
    }

    public Book(String id, String name, String location, int year, String author){
        super(id, name, location);
        this.year = year;
        this.author = author;
        this.type = "Book";
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "\n  Book{" +
                "author='" + author + '\'' +
                ", year=" + year +
                ", type='" + type + '\'' +
                ", id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", location='" + location + '\'' +
                '}';
    }
}
