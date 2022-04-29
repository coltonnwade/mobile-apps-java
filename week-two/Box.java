
import java.util.ArrayList;

public class Box
        implements Boxable{

    // Instance Fields
    int maxCapacity;
    ArrayList<Boxable> Box = new ArrayList<>();

    // Box Constructor
    public Box(int maxCapacity)
    {
        this.maxCapacity = maxCapacity;
    }


    // addMethod
    public boolean add(Boxable b)
    {
        if ((b.weight() + weight()) <= maxCapacity)  // if object's weight PLUS the box weight is less than the maxCapacity it will add to the box
        {
            Box.add(b);
            return true;
        }
        // if object's weight exceeds max capacity it will return false
        else
        {
            return false;
        }
    }

    // Box toString() method
    @Override
    public String toString() {
        // Base String
        StringBuilder results = new StringBuilder(String.format("Box: %s items, total weight %.2f.", Box.size(), weight()));
        // Loops through ArrayList Box and adds each item to the end of results
        for (Boxable b : Box) results.append("\n\t").append(b.toString());
        return results.toString(); // returns results
    }

    // weight method
    @Override
    public double weight() {
        double weight = 0;
        // loops through the box and calculates the weight
        for (Boxable b : Box)
        {
            weight = weight + b.weight(); // weight is equal to weight plus each objects weight
        }
        return weight; // return weight
    }

}
