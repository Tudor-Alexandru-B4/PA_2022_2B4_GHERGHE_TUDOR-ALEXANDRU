package classes;

public class Friendship {

    private long id1;
    private long id2;

    public Friendship(){};

    public Friendship(long id1, long id2){
        this.id1 = id1;
        this.id2 = id2;
    }

    public long getId1() {
        return id1;
    }

    public void setId1(long id1) {
        this.id1 = id1;
    }

    public long getId2() {
        return id2;
    }

    public void setId2(long id2) {
        this.id2 = id2;
    }
}
