package ra.presentation;

import ra.bussinessipm.CategoryIpm;
import ra.entity.Category;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ManagementCategory {
    public static final String ANSI_PURPLE = "\u001B[35m";
    public static List<Category> listCategory = new ArrayList<>();
    public static void MenuCategory(){

        listCategory=CategoryIpm.readCategoryFromFile();
        Scanner scanner = new Scanner(System.in);
        boolean isExit = true ;


        do {
            try {
                System.out.println(ANSI_PURPLE+"===== Category Management =====\n" +
                        "1. Add category\n" +
                        "2. Display category by name A – Z\n" +
                        "3. Genre statistics and number of books in category\n" +
                        "4. Update category\n" +
                        "5. Delete category\n" +
                        "6. Exit\n");
                System.out.println("Please , enter number 1-6 :");
                int choice = Integer.parseInt(scanner.nextLine());
                switch (choice){
                    case 1:
                        CategoryIpm.addCategory(scanner);
                        CategoryIpm.writeCategorytoFile();

                        break;
                    case 2 :
                        CategoryIpm.displayCategory();
                        break;
                    case 3 :
                        CategoryIpm.statisticsCategory();
                        break;
                    case 4 :
                        CategoryIpm.updateCategory(scanner);
                        CategoryIpm.writeCategorytoFile();
                        break;
                    case 5 :
                        CategoryIpm.deleteCategory(scanner);
                        CategoryIpm.writeCategorytoFile();


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
