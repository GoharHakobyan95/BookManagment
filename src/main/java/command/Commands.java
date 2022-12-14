package command;

public interface Commands {
    int LOGOUT = 0;
    int ADD_BOOK = 1;
    int ADD_AUTHOR = 2;
    int PRINT_ALL_BOOKS = 3;
    int PRINT_BOOKS_BY_AUTHOR_NAME = 4;
    int PRINT_BOOKS_BY_GENRE = 5;
    int PRINT_BOOKS_BY_PRICE_RANGE = 6;
    int DELETE_AUTHOR_BY_INDEX = 7;
    int DOWNLOAD_BOOKS_EXCEL = 8;


    int EXIT = 0;
    int SIGN_IN = 1;
    int SIGN_UP = 2;

    static void printLoginCommands() {
        System.out.println("Please input " + EXIT + " for exit");
        System.out.println("Please input " + SIGN_IN + " for sign in");
        System.out.println("Please input " + SIGN_UP + " for register");
    }

    static void printAdminCommands() {
        System.out.println("Please input " + LOGOUT + " for logout ");
        System.out.println("Please input " + ADD_BOOK + " for add book ");
        System.out.println("Please input " + ADD_AUTHOR + " for add author");
        System.out.println("Please input " + PRINT_ALL_BOOKS + " for print all books ");
        System.out.println("Please input " + PRINT_BOOKS_BY_AUTHOR_NAME + " for print books by author name ");
        System.out.println("Please input " + PRINT_BOOKS_BY_GENRE + " for print books by genre ");
        System.out.println("Please input " + PRINT_BOOKS_BY_PRICE_RANGE + " for print books by price range ");
        System.out.println("Please input " + DELETE_AUTHOR_BY_INDEX + " for delete author ");
        System.out.println("Please input " + DOWNLOAD_BOOKS_EXCEL + " for download book's excel file ");
    }


}
