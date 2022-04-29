
package com.company;

// Ebook Inherits Product class
public class Ebook extends Product{

    // Ebook's Instance Fields
    final String author;
    final long isbn;

    // Ebook Constructor
    Ebook(String name, Double price, int quantity, String author, long isbn) {
        super(name, price, quantity);
        this.author = author;
        this.isbn = isbn;
    }

    // Overrides Products Buy()
    @Override
    public void Buy()
    {
        quantity = getQuantity(); // I originally left this empty, but I didn't like doing that, so I set it to the getQuantity() method, so it doesn't decrease.
    }

    // Overrides Products toString()
    @Override
    public String toString()
    {
        // Overrides Product's toString()'s to add new Instance Fields for Ebook.
        return String.format("\nName: %s \nPrice: %s \nQuantity: %s \nAuthor: %s \nISBN: %s\n", name, price, quantity, author, isbn);
    }

    
}
