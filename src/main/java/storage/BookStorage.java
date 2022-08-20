package homework.books.storage;

import homework.books.model.Book;

public class BookStorage {
    private Book[] array = new Book[10];
    private int size = 0;

    public void add(Book book) {
        if (size == array.length) {
            extend();
        }
        array[size++] = book;
    }

    private void extend() {
        Book[] temp = new Book[array.length + 10];
        System.arraycopy(array, 0, temp, 0, size);
        array = temp;
    }

    public void print() {
        if (size == 0) {
            System.out.println("There are no books ! ");
        }
        for (int i = 0; i < size; i++) {
            System.out.println(i + "." + array[i] + " ");
        }
    }

    public void booksByGenre(String genre) {
        for (int i = 0; i < size; i++) {
            if (array[i].getGenre().equals(genre)) {
                System.out.println(array[i]);
            }
        }
    }

    public void booksByPriceRange(double min, double max) {
        for (int i = 0; i < size; i++) {
            Book book = array[i];
            if (book.getPrice() >= min && book.getPrice() <= max) {
                System.out.println(book);
            }

        }
    }
}
