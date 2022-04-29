

public class Book
        implements Boxable{

    // Instance Fields
    public String author;
    public String name;
    public  String ISBN;
    public double weight;


    // Book Constructor
    public Book(String author, String name, String ISBN, double weight)
    {
        this.author = author;
        this.name = name;
        this.ISBN = ISBN;
        this.weight = weight;
    }

    // Boxable Interface Method
    @Override
    public double weight() {
        return weight;
    }

    // Book toString() Method
    @Override
    public String toString() {
        return String.format("%s, %s, %s, %s lbs", name, author, ISBN, weight);
    }
}
