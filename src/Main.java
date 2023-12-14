import ra.bussinessipm.CategoryIpm;
import ra.entity.Category;
import ra.presentation.ManagementBook;
import ra.presentation.ManagementCategory;

import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {



        Scanner scanner = new Scanner(System.in);
        do {
            try {
                System.out.println(" ===== Library Management =====\n" +
                        "1. Category Management\n" +
                        "2. Book Management\n" +
                        "3. Exit");
                System.out.println("Please , enter 1-3 :");
                int choice = Integer.parseInt(scanner.nextLine());
                switch (choice){
                    case 1:
                        ManagementCategory.MenuCategory();
                        break;
                    case 2 :
                        ManagementBook.MenuBook();
                        break;
                    case 3 :
                        System.out.println("3. Exit");
                        System.exit(0);
                        break;
                    default:
                        System.err.println("Please , enter 1 -3 :");
                        break;
                }
            } catch (NumberFormatException e) {
                System.err.println(e.getMessage());
            }


        }while (true);

    }
}