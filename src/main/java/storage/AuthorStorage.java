package homework.books.storage;

import homework.books.exception.AuthorNotFoundException;
import homework.books.model.Author;

public class AuthorStorage {
    private Author[] array = new Author[10];
    private int size = 0;

    public void add(Author author) {
        if (size == array.length) {
            extend();
        }
        array[size++] = author;
    }

    private void extend() {
        Author[] temp = new Author[array.length + 10];
        System.arraycopy(array, 0, temp, 0, size);
        array = temp;
    }

    public void print() {
        for (int i = 0; i < size; i++) {
            System.out.println(i + "." + array[i] + " ");
        }
    }

    public int getSize() {
        return size;
    }


    public void booksByAuthorName(String author) {
        for (int i = 0; i < size; i++) {
            if (array[i].getName().equals(author)) {
                System.out.println(array[i]);
            } else {
                System.out.println("Please input correct name");
            }
        }
    }

    public void delete(int index) {
        if (index >= 0 && index < size) {
            for (int i = index; i < size; i++) {
                array[index] = array[index + 1];
            }
            size--;
            System.out.println("Author deleted");
        } else {
            System.out.println("Index out of bound");
        }
    }

    public Author getAuthorByIndex(int authorIndex) throws AuthorNotFoundException {
        if (authorIndex < 0 || authorIndex > size) {
            throw new AuthorNotFoundException();
        } else {
            if (array[authorIndex] == null) {
                throw new AuthorNotFoundException();
            }
            return array[authorIndex];
        }
    }
}



