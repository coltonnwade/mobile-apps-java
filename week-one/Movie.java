
package com.company;

// Movie Inherits Product class
public class Movie extends Product{

    // Movie's Instance Fields
    final String director;
    final String IBMd;

    // Movie Constructor
    Movie(String name, Double price, int quantity, String director, String IBMd) {
        super(name, price, quantity);
        this.director = director;
        this.IBMd = IBMd;
    }


    // Overrides Products toString()
    @Override
    public String toString()
    {
        // Overrides Product's toString()'s to add new Instance Fields for Movie.
        return String.format("\nName: %s \nPrice: %s \nQuantity: %s \nDirector: %s \nIMBd: %s\n", name, price, quantity, director, IBMd);
    }
}
