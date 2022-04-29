

package com.company;
import java.util.ArrayList;

// Store Class
public class Store {

    // Instance Fields
    public static ArrayList<Product> Products = new ArrayList<Product>();

    // Main
    public static void main(String[] args)
    {

        // Instantiate Ebook's
        Ebook mobyDick = new Ebook("Moby Dick", 1.99, 999, "Herman Melville", 1503280780);
        Ebook hpSorcererStone = new Ebook("Harry Potter and the Sorcerer's Stone", 9.99, 999, "J.K. Rowling", 9780590353427L);
        Ebook hpChamberOfSecrets = new Ebook("Harry Potter and the Chamber of Secrets", 9.99, 999, "J.K. Rowling", 9780439064873L);


        // Instantiate Movie's
        Movie theMatrix = new Movie("The Matrix", 9.99, 33, "Lana Wachowski", "tt0133093");
        Movie smNoWayHome = new Movie("Spider-Man: No Way Home", 16.99, 27,"Jon Watts", "tt10872600");
        Movie gbAfterlife = new Movie("Ghostbusters: Afterlife", 19.99, 40, "Jason Reitman", "tt4513678");
        Movie theMatrixReloaded = new Movie("The Matrix Reloaded", 9.99, 38,"Lana Wachowski", "tt0234215");


        // Adds all instantiated objects to Products list.
        addProducts(mobyDick);
        addProducts(theMatrix);
        addProducts(smNoWayHome);
        addProducts(hpSorcererStone);
        addProducts(theMatrixReloaded);
        addProducts(gbAfterlife);
        addProducts(hpChamberOfSecrets);


        // Calls searchProductName
        searchProductName("Harry");

    }

    //addProduct Method
    public static void addProducts(Product newProduct) // Takes in newProduct
    {

        Products.add(newProduct); // Adds newProduct to ArrayList Products
        System.out.println(newProduct.name + " Has been added to list!"); // Prints a message in console that says the product has been added to the list.
    }

    //searchProductName Method
    public static void searchProductName(String searchFor) // Take's in searchFor String
    {
        // For Products in Products ArrayList
        for (Product p : Products){
            if(p.getName().contains(searchFor)) // if Product p's name contains the searchFor String
            {
                System.out.println(p); // print's product to console
            }
        }
    }

}
