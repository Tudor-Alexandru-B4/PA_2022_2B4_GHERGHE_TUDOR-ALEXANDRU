package classes;

public class Friend {

    private Friendship id;

    public Friend(){}

    public Friend(Friendship id){
        this.id = id;
    }

    public Friendship getId() {
        return id;
    }

    public void setId(Friendship id) {
        this.id = id;
    }
}
