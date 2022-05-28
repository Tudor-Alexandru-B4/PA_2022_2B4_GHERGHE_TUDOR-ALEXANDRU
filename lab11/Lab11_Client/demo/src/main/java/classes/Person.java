package classes;

public class Person {
    private long id;
    private String name;
    private boolean log;

    public Person(){}

    public Person(String name, Boolean log) {
        this.name = name;
        this.log = log;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isLog() {
        return log;
    }

    public void setLog(boolean log) {
        this.log = log;
    }
}
