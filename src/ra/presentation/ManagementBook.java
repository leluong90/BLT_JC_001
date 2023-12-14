package ra.presentation;

import ra.bussinessipm.BookIpm;
import ra.bussinessipm.CategoryIpm;
import ra.entity.Book;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ManagementBook {
    public static List<Book> listBook = new ArrayList<>();
    public static void MenuBook(){
        listBook = BookIpm.readBookFromFile();
        Scanner scanner = new Scanner(System.in);
        boolean isExit = true ;
        do {
            try {
                System.out.println("===== QUẢN LÝ SÁCH =====\n" +
                        "1. Thêm mới sách\n" +
                        "2. Cập nhật thông tin sách\n" +
                        "3. Xóa sách\n" +
                        "4. Tìm kiếm sách \n" +
                        "5. Hiển thị danh sách sách theo nhóm thể loại\n" +
                        "6. Quay lại\n");
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
                        System.out.println("Exit");
                        isExit = false ;

                        break;
                    default:
                        System.out.println("Please , enter 1-6 :");
                        break;

            }

            } catch (NumberFormatException e) {
                System.err.println(e.getMessage());
            }

        }while (isExit);
    }
}
