package models;

public class Author {

    private final int id;
    private final String name;

    public Author(int id, String name) {
        if (name == null || name.isBlank()) throw new IllegalArgumentException("Name cannot be null or blank");
        this.id = id;
        this.name = name;
    }


    public int getId(){
        return id;
    }
    public String getName(){
        return name;
    }
}
