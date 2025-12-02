package models;

public class Book {

    private final int id;
    private final String title;
    private final Author author;
    private final int year;
    private boolean isAvailable;

    public Book(int id, String title, Author author, int year) {
        if (title == null || title.isBlank()) throw new IllegalArgumentException("Title cannot be null or blank");
        if (author == null) throw new IllegalArgumentException("Author cannot be null");
        if (year < 0) throw new IllegalArgumentException("Year cannot be negative");
        this.id = id;
        this.title = title;
        this.author = author;
        this.year = year;
        this.isAvailable = true;
    }

    public int getId(){
        return id;
    }
    public String getTitle(){
        return title;
    }
    public Author getAuthor(){
        return author;
    }
    public int getYear(){
        return year;
    }
    public boolean isAvailable(){
        return isAvailable;
    }
    public void setAvailable(){
        this.isAvailable = false;
    }

}
