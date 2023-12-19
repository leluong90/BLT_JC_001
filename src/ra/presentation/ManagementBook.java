package ra.presentation;

import ra.bussinessipm.BookIpm;
import ra.bussinessipm.CategoryIpm;
import ra.entity.Book;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ManagementBook {
    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_BLACK = "\u001B[30m";
    public static final String ANSI_RED = "\u001B[31m";
    public static final String ANSI_GREEN = "\u001B[32m";
    public static final String ANSI_YELLOW = "\u001B[33m";
    public static final String ANSI_BLUE = "\u001B[34m";
    public static final String ANSI_PURPLE = "\u001B[35m";
    public static final String ANSI_CYAN = "\u001B[36m";
    public static final String ANSI_WHITE = "\u001B[37m";
    public static List<Book> listBook = new ArrayList<>();
    public static void MenuBook(){
        listBook = BookIpm.readBookFromFile();
        Scanner scanner = new Scanner(System.in);
        boolean isExit = true ;
        do {
            try {
                System.out.println(ANSI_PURPLE+"===== Book Management =====\n" +
                        "1. Add book\n" +
                        "2. Update book\n" +
                        "3. Delete book\n" +
                        "4. Search book \n" +
                        "5. Displays a list of books by category\n" +
                        "6. Exit\n");
                System.out.println("Enter 1-6 :");
                int choice = Integer.parseInt(scanner.nextLine());
                switch (choice){
                    case 1:
                        BookIpm.addBook(scanner);
                        BookIpm.writeBookToFile();

                        break;
                    case 2 :
                        BookIpm.updateBook(scanner);
                        BookIpm.writeBookToFile();
                        break;
                    case 3 :
                        BookIpm.deleteBook(scanner);
                        BookIpm.writeBookToFile();
                        break;
                    case 4 :
                        BookIpm.searchBook(scanner);
                        break;
                    case 5 :
                        BookIpm.displayBookOfCategoryId();
                        break;
                    case 6:
                        System.out.println("Exit\n");
                        isExit = false ;

                        break;
                    default:
                        System.err.println("Please , enter 1-6 !");
                        break;

            }

            } catch (NumberFormatException e) {
                System.err.println("Please , enter 1-6 !");
            }

        }while (isExit);
    }
}
