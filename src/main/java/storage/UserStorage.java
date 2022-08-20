package homework.books.storage;

import homework.books.model.User;

public class UserStorage {
    private User[] array = new User[10];
    private int size = 0;

    public void add(User user) {
        if (size == array.length) {
            extend();
        }
        array[size++] = user;
    }

    private void extend() {
        User[] temp = new User[array.length + 10];
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

    public User getUserByEmail(String email) {
        for (int i = 0; i < size; i++) {
            if (array[i].getEmail().equals(email)) {
                return array[i];
            }
        }
        return null;
    }
}

