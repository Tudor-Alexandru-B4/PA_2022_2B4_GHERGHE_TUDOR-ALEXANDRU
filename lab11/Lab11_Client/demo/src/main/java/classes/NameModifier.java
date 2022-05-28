package classes;

public class NameModifier {
    private long id;
    private String newName;

    public NameModifier(){};

    public NameModifier(long id, String newName){
        this.id = id;
        this.newName = newName;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getNewName() {
        return newName;
    }

    public void setNewName(String newName) {
        this.newName = newName;
    }
}
