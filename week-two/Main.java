import java.util.ArrayList;


public class Main {


    // Instance Field "result" to pass into isResult method
    public static boolean result;

    public static void main(String[] args)
    {

        Main m = new Main(); // Instantiates Main

        Box box = new Box(10); // Instantiates Box

        // Stores the return value true or false from the add method in result
        result = box.add(new Book("To Kill a Mocking Bird", "Harper Lee", "ISBN-10" +
                "0446310786", 2.4));
        m.isResult(result); // tests if result is true or false

        result = box.add(new Book("Nineteen Eighty-Four", "George Orwell", "ISBN-10" +
                "9780451524935", 1.2));
        m.isResult(result);

        result = box.add(new Book("The Diary of Anne Frank", "Anne Frank", "ISBN-10" +
                "9780553296983", 1.2));
        m.isResult(result);

        result = box.add(new DVD("The Matrix", "Lana Wachowski", 1999));
        m.isResult(result);

        result = box.add(new DVD("The Godfather", "Francis Ford Coppola", 1972));
        m.isResult(result);

        result = box.add(new DVD("The Good, The Bad, and the Ugly", "Sergio Leone", 1966));
        m.isResult(result);


        System.out.println(box);   //calls toString that you have overridden in Box class.



        System.out.println();
        Box box2 = new Box(3); // Instantiates Box2

        result = box2.add(new DVD("Band of Brothers", "Steven Spielberg", 2001));
        m.isResult(result);

        result = box2.add(new Book("Don Quixote", "Miguel de Cervantes", "ISBN-10" +
                "100142437239", 1.26));
        m.isResult(result);

        result = box2.add(new Book("Moby Dick", "Herman Melville", "ISBN-101503280780", 1.4));
        m.isResult(result);

        result = box2.add(new Book("Colton Wade", "This is a test", "ISBN-58259823859", 0.5));
        m.isResult(result);

        System.out.println(box2); //calls toString that you have overridden in Box class.



        System.out.println();
        Box box3 = new Box(20); // Instantiates Box3

        result = box3.add(box);
        m.isResult(result);

        result = box3.add(box2);
        m.isResult(result);
        System.out.println(box3);

    }

    // If result is not valid, it will print a message in the console.
    public void isResult(boolean result)
    {
        if(!result){
            System.out.println("Item has not been added because it exceeds weight limit");
        }
    }
}
