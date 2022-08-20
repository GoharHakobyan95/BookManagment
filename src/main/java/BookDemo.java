package homework.books;

import homework.books.command.Commands;
import homework.books.exception.AuthorNotFoundException;
import homework.books.model.*;
import homework.books.storage.AuthorStorage;
import homework.books.storage.BookStorage;
import homework.books.storage.UserStorage;


import java.util.Scanner;

public class BookDemo implements Commands {
    private static Scanner scanner = new Scanner(System.in);
    private static BookStorage bookStorage = new BookStorage();
    private static AuthorStorage authorStorage = new AuthorStorage();
    private static UserStorage userStorage = new UserStorage();

    private static User currentUser = null;

    public static void main(String[] args) throws AuthorNotFoundException {
        User admin = new User("admin", "admin", "admin@mail.ru", "123456", Role.ADMIN);
        userStorage.add(admin);

        boolean run = true;
        while (run) {
            Commands.printLoginCommands();
            int command;
            try {
                command = Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                command = -1;
            }
            switch (command) {
                case EXIT:
                    run = false;
                    break;
                case SIGN_IN:
                    logIn();
                    break;
                case SIGN_UP:
                    register();
                    break;
                default:
                    System.out.println("Invalid command, please try again.");
                    break;
            }
        }
    }


    private static void logIn() throws AuthorNotFoundException {
        System.out.println("Please input email, password");
        String emailPasswordStr = scanner.nextLine();
        String[] emailPassword = emailPasswordStr.split(",");
        User user = userStorage.getUserByEmail(emailPassword[0]);
        if (user == null) {
            System.out.println("User does not exists!");
        } else {
            if (!user.getPassword().equals(emailPassword[1])) {
                System.out.println("Password is wrong!");
            } else {
                currentUser = user;
                if (user.getRole() == Role.ADMIN) {
                    adminLogin();
                } else if (user.getRole() == Role.USER) {
                    userLogin();
                }
            }
        }
    }

    private static void register() {
        System.out.println("Please input name, surname, email, password");
        String userDataStr = scanner.nextLine();
        String[] userData = userDataStr.split(",");
        if (userData.length < 4) {
            System.out.println("Please input correct data for user");
        } else {
            if (userStorage.getUserByEmail(userData[2]) == null) {
                User user = new User();
                user.setName(userData[0]);
                user.setSurname(userData[1]);
                user.setEmail(userData[2]);
                user.setPassword(userData[3]);
                user.setRole(Role.USER);
                userStorage.add(user);
                System.out.println("User registered!");
            } else {
                System.out.println("User with " + userData[2] + "already exists. ");
            }
        }
    }


    private static void adminLogin() throws AuthorNotFoundException {
        boolean run = true;
        while (run) {
            int command;
            Commands.printAdminCommands();
            try {
                command = Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                command = -1;
            }
            switch (command) {
                case LOGOUT:
                    currentUser = null;
                    run = false;
                    break;
                case ADD_BOOK:
                    addBook();
                    break;
                case ADD_AUTHOR:
                    addAuthor();
                    break;
                case PRINT_ALL_BOOKS:
                    bookStorage.print();
                    break;
                case PRINT_BOOKS_BY_AUTHOR_NAME:
                    printBooksByAuthorName();
                    break;
                case PRINT_BOOKS_BY_GENRE:
                    printBooksByGenre();
                    break;
                case PRINT_BOOKS_BY_PRICE_RANGE:
                    printBooksByPriceRange();
                    break;
                case DELETE_AUTHOR_BY_INDEX:
                    deleteAuthor();
                    break;
                default:
                    System.out.println("Invalid command, please try again. ");
                    break;
            }
        }
    }


    private static void userLogin() throws AuthorNotFoundException {
        System.out.println("Welcome, " + currentUser.getName());
        boolean run = true;
        while (run) {
            int command;
            Commands.printUserCommands();
            try {
                command = Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                command = -1;
            }
            switch (command) {
                case LOGOUT:
                    currentUser = null;
                    run = false;
                    break;
                case PRINT_ALL_BOOKS:
                    bookStorage.print();
                    break;
                case PRINT_BOOKS_BY_AUTHOR_NAME:
                    printBooksByAuthorName();
                    break;
                case PRINT_BOOKS_BY_GENRE:
                    printBooksByGenre();
                    break;
                case PRINT_BOOKS_BY_PRICE_RANGE:
                    printBooksByPriceRange();
                    break;
                default:
                    System.out.println("Invalid command, please try again. ");
                    break;
            }
        }
    }

    private static void addAuthor() {
        System.out.println("Please input Author name ");
        String name = scanner.nextLine();

        System.out.println("Please input Author surname. ");
        String surname = scanner.nextLine();

        System.out.println("Please input Author Email. ");
        String email = scanner.nextLine();

        System.out.println("Please input Author gender. ");
        Gender gender = gender();

        Author author = new Author(name, surname, email, gender, currentUser);
        author.setGender(gender);
        authorStorage.add(author);
        System.out.println("Thank you, Author was added !!! ");
    }

    private static Gender gender() {
        Gender gender;
        try {
            gender = Gender.valueOf(scanner.nextLine().toUpperCase());
        } catch (IllegalArgumentException e) {
            System.out.println("Invalid gender input");
            gender = gender();
        }
        return gender;
    }


    private static void addBook() throws AuthorNotFoundException {
        if (authorStorage.getSize() == 0) {
            addAuthor();
        } else {
            authorStorage.print();
            System.out.println("Please choose author index  for add book. ");
            Author author;
            try {
                int authorIndex = Integer.parseInt(scanner.nextLine());
                author = authorStorage.getAuthorByIndex(authorIndex);

                System.out.println("Please input Title of Book. ");
                String title = scanner.nextLine();
                System.out.println("Please input Price of Book. ");
                String priceStr = scanner.nextLine();
                System.out.println("Please input Count of Books. ");
                String countStr = scanner.nextLine();
                System.out.println("Please input Genre of Book. ");
                String genre = scanner.nextLine();
                double price = Double.parseDouble(priceStr);
                int count = Integer.parseInt(countStr);

                Book book = new Book(title, author, price, count, genre);
                bookStorage.add(book);
                System.out.println("Thank you, book was added !!! ");
            } catch (AuthorNotFoundException e) {
                System.out.println("Doesn't found. Add new author ! ");
                addAuthor();
                addBook();
            } catch (NumberFormatException e) {
                System.out.println("Please input valid number or index. ");
            }
        }
    }

    private static void deleteAuthor() {
        authorStorage.print();
        System.out.println("Please choose author index");
        int index = Integer.parseInt(scanner.nextLine());
        authorStorage.delete(index);
    }

    private static void printBooksByAuthorName() {
        System.out.println("Please input Author name of Book! ");
        String authorName = scanner.nextLine();
        authorStorage.booksByAuthorName(authorName);
    }


    private static void printBooksByGenre() {
        System.out.println("Please input the Genre of Book! ");
        String genre = scanner.nextLine();
        bookStorage.booksByGenre(genre);
    }

    private static void printBooksByPriceRange() {
        System.out.println("Please input price range for Books. ");
        String range = scanner.nextLine();
        String[] split = range.split(",");
        try {
            double min = Double.parseDouble(split[0]);
            double max = Double.parseDouble(split[1]);
            bookStorage.booksByPriceRange(min, max);
        } catch (NumberFormatException e) {
            System.out.println("Invalid input!  Number only! ");
        }
    }
}