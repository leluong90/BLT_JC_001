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
                System.out.println(" ===== QUẢN LÝ THƯ VIỆN =====\n" +
                        "1. Quản lý Thể loại\n" +
                        "2. Quản lý Sách\n" +
                        "3. Thoát");
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
                        System.out.println("Exit");
                        System.exit(0);
                        break;
                    default:
                        break;
                }
            } catch (NumberFormatException e) {
                System.err.println(e.getMessage());
            }


        }while (true);

    }
}