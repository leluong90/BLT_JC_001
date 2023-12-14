package ra.presentation;

import ra.bussinessipm.CategoryIpm;
import ra.entity.Category;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ManagementCategory {
    public static List<Category> listCategory = new ArrayList<>();
    public static void MenuCategory(){
        listCategory=CategoryIpm.readCategoryFromFile();
        Scanner scanner = new Scanner(System.in);
        boolean isExit = true ;


        do {
            try {
                System.out.println("===== Category Management =====\n" +
                        "1. Add Category\n" +
                        "2. Display list by name A – Z\n" +
                        "3. Genre statistics and number of books in each category\n" +
                        "4. Update Category\n" +
                        "5. Delete Category\n" +
                        "6. Exit");
                System.out.println("Please , enter 1-6 :");
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
                        System.out.println("Exit");
                        isExit = false ;

                        break;
                    default:
                        System.err.println("Enter 1-6");
                        break;
                }

            } catch (NumberFormatException e) {
                System.err.println(e.getMessage());
            }


        }while (isExit);
    }
}
