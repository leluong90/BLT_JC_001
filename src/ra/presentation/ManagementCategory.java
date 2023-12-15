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
                System.out.println("===== QUẢN LÝ THỂ LOẠI =====\n" +
                        "1. Thêm mới thể loại\n" +
                        "2. Hiển thị danh sách theo tên A – Z\n" +
                        "3. Thống kê thể loại và số sách có trong mỗi thể loại\n" +
                        "4. Cập nhật thể loại\n" +
                        "5. Xóa thể loại\n" +
                        "6. Quay lại");
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
                        System.out.println("Enter 1-6");
                        break;
                }

            } catch (NumberFormatException e) {
                System.err.println(e.getMessage());
            }


        }while (isExit);
    }
}
