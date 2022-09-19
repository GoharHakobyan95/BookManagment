package command;

public interface UserCommands {
    int LOGOUT = 0;
    int PRINT_ALL_BOOKS = 1;
    int PRINT_BOOKS_BY_AUTHOR_NAME = 2;
    int PRINT_BOOKS_BY_GENRE = 3;
    int PRINT_BOOKS_BY_PRICE_RANGE = 4;
    int DOWNLOAD_BOOKS_EXCEL = 5;

    static void printUserCommands() {
        System.out.println("Please input " + LOGOUT + " for logout ");
        System.out.println("Please input " + PRINT_ALL_BOOKS + " for print all books ");
        System.out.println("Please input " + PRINT_BOOKS_BY_AUTHOR_NAME + " for print books by author name ");
        System.out.println("Please input " + PRINT_BOOKS_BY_GENRE + " for print books by genre ");
        System.out.println("Please input " + PRINT_BOOKS_BY_PRICE_RANGE + " for print books by price range ");
        System.out.println("Please input " + DOWNLOAD_BOOKS_EXCEL + " for download book's excel file ");
    }
}
