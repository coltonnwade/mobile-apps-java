
package com.company;

// Abstract Product Class
abstract class Product {


    public static void main(String[] args)
    {
        // Instantiate Ebook
        Ebook mobyDick = new Ebook("Moby Dick", 1.99, 999, "Herman Melville", 1503280780);

        // Instantiate Movie
        Movie theMatrix = new Movie("The Matrix", 9.99, 33, "Lana Wachowski", "tt0133093");


        System.out.println("Example of toString method:");
        System.out.println(mobyDick); // Prints Moby Dick's toString.

        System.out.println("Example of toString method:");
        System.out.println(theMatrix); // Prints The Matrix's toString.

        System.out.println("Buy Method Invoked for Moby Dick.");
        mobyDick.Buy(); // Call's the Buy method for Moby Dick
        System.out.println(mobyDick); // Prints Moby Dick's toString.

        System.out.println("Buy Method Invoked for The Matrix.");
        theMatrix.Buy(); // Call's the Buy Method for Moby Dick
        System.out.println(theMatrix); // Prints The Matrix's toString.

        //mobyDick.setQuantity(100);
        //mobyDick.Buy();
        //System.out.println(mobyDick);
    }

    // Instance Fields
    protected String name;
    protected int quantity;
    protected Double price;

    // Setters
    public void setName(String name) {
        this.name = name;
    }
    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
    public void setPrice(Double price) {
        this.price = price;
    }

    // Getters
    public String getName() {
        return name;
    }
    public int getQuantity() {
        return quantity;
    }
    public Double getPrice(){
        return price;
    }


    // Product Class Constructor
    Product (String name, Double price, int quantity)
    {
        this.name = name;
        this.price = price;
        this.quantity = quantity;
    }


    // Buy() Method
    public void Buy()
    {
        quantity = quantity - 1; // Decreases the current quantity by 1 everytime this method is invoked
    }

    // Product toString() Method
    @Override
    public String toString() {
        return String.format("\nName: %s \nPrice: %s \nQuantity: %s ", name, price, quantity); // formatted toString for product.
    }
}


