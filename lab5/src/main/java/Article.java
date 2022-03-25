public class Article extends Item{
    private String date;
    private String author;
    private int pages;
    private String type;

    public Article(){
        type = "Article";
    }

    public Article(String id, String name, String location, String date, String author, int pages){
        super(id, name, location);
        this. date = date;
        this.author = author;
        this.pages = pages;
        this.type = "Article";
    }

    public void setAuthor(String author){
        this.author = author;
    }

    public String getAuthor(){
        return author;
    }

    public void setDate(String date){
        this.date = date;
    }

    public String getDate(){
        return date;
    }

    public void setPages(int pages){
        this.pages = pages;
    }

    public int getPages(){
        return pages;
    }

    public void setType(String type){
        this.type = type;
    }

    public String getType(){
        return type;
    }

    @Override
    public String toString() {
        return "\n  Article{" +
                "date='" + date + '\'' +
                ", author='" + author + '\'' +
                ", pages=" + pages +
                ", type='" + type + '\'' +
                ", id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", location='" + location + '\'' +
                '}';
    }
}
