

public class DVD
        implements Boxable {

    // Instance fields
    public String name;
    public String director;
    public int year;
    public double weight;

    // Class DVD Constructor
    public DVD(String name, String director, int year)
    {
        this.name = name;
        this.director = director;
        this.year = year;
        this.weight = 0.2;
    }

    // DVD toString() Method
    @Override
    public String toString() {
        return String.format("%s, %s, %s, %s lbs", name, director, year, weight);
    }

    // Boxable Interface Method
    @Override
    public double weight() {
        return 0.2;
    }
}
