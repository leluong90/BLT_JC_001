package ra.bussinessipm;

import ra.entity.Book;
import ra.entity.Category;

import java.io.*;
import java.util.*;

import static ra.presentation.ManagementBook.listBook;
import static ra.presentation.ManagementCategory.listCategory;

public class CategoryIpm {
    public static final String TEXT_RED = "\u001B[31m";
    public static final String TEXT_BLACK = "\u001B[30m";
    public static final String TEXT_GREEN = "\u001B[32m";
    public static final String TEXT_BLUE = "\u001B[34m";
    public static final String TEXT_RESET = "\u001B[0m";
    public static final String TEXT_PURPLE = "\u001B[35m";
    public static final String TEXT_CYAN = "\u001B[36m";
    public static final String TEXT_YELLOW = "\u001B[33m";
    public static final String TEXT_WHITE = "\u001B[37m";
    public static void addCategory(Scanner scanner) {
        try {
            System.out.println("Enter number category :");
            int numberOfCategory = Integer.parseInt(scanner.nextLine());
            for (int i = 0; i < numberOfCategory; i++) {
                Category category = new Category();
                category.input(scanner);
                listCategory.add(category);
            }
        } catch (NumberFormatException e) {
            System.err.println("Enter interger ");
        }

    }

    public static void displayCategory() {
        Collections.sort(listCategory, Comparator.comparing(Category::getName));
        // In tiêu đề của bảng
        System.out.println("+--------+----------------------------------+--------+");
        System.out.println("|  ID    |              Name                | Status |");
        System.out.println("+--------+----------------------------------+--------+");
        // In dữ liệu từ danh sách thể loại
        for (Category category : listCategory) {
            System.out.printf("| %-6d | %-32s | %-6b |\n", category.getId() , category.getName(), category.getStatus());

        }
        System.out.println("+--------+----------------------------------+--------+");

    }

    public static void updateCategory(Scanner scanner) {
        try {
            boolean isExists = false;
            System.out.println("Enter id need update : ");
            int updateId = Integer.parseInt(scanner.nextLine());
            for (Category category : listCategory) {
                if (category.getId() == updateId) {
                    category.updateData(scanner);
                    isExists = true;
                    break;
                }
            }
            if (!isExists) {
                System.err.println("Id not exists");

            }

        } catch (NumberFormatException e) {
            System.err.println(e.getMessage());
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }

    }

    public static void deleteCategory(Scanner scanner) {
        try {
            boolean isExists = false;
            System.out.println("Enter category id need delete : ");
            int deleteCategoryId = Integer.parseInt(scanner.nextLine());
            // Kiểm tra xem thể loại có chứa sách hay không
            boolean existsCategoryId = listCategory.stream()
                    .anyMatch(category -> category.getId() == deleteCategoryId);
            boolean hasBooksCategory = listBook.stream()
                    .anyMatch(book -> book.getCategoryId() == deleteCategoryId);

            // Nếu thể loại không chứa sách, xóa thể loại đó
            if (!existsCategoryId){
                System.err.println("Category not exists");
            }else if (!hasBooksCategory && existsCategoryId ) {
                listCategory.removeIf(category -> category.getId() == deleteCategoryId);
                System.out.println("Category removed successfully.");
            } else {
                System.err.println("Category contains books. Cannot remove.");
            }


        } catch (NumberFormatException e) {
            System.err.println(e.getMessage());
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }


    }
//    public static boolean hasBook(int categoryIdToRemove){
//
////        for (Book book : listBook) {
////            if (book.getCategoryId() == categoryId){
////                return true;
////            }
////        }
////        return false ;
//    }

    public static void statisticsCategory() {

        for (Category category : listCategory) {

            long count = listBook.stream().filter(book -> book.getCategoryId() == category.getId()).count();
            System.out.printf("%s have %d book ", category.getName(), count);
            System.out.println();
        }
    }

    public static List<Category> readCategoryFromFile() {
        List<Category> listCategoryRead = null;
        File file = new File("categories.txt");
        FileInputStream fis = null;
        ObjectInputStream ois = null;
        try {
            fis = new FileInputStream(file);
            ois = new ObjectInputStream(fis);
            listCategoryRead = (List<Category>) ois.readObject();
        } catch (Exception ex) {
            listCategoryRead = new ArrayList<>();
        } finally {
            if (fis != null) {
                try {
                    fis.close();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
            if (ois != null) {
                try {
                    ois.close();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        }
        return listCategoryRead;
    }
    public static void writeCategorytoFile() {
        File file = new File("categories.txt");
        FileOutputStream fos = null;
        ObjectOutputStream oos = null;
        try {
            fos = new FileOutputStream(file);
            oos = new ObjectOutputStream(fos);
            oos.writeObject(listCategory);
            oos.flush();
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            if (fos != null) {
                try {
                    fos.close();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
            if (oos != null) {
                try {
                    oos.close();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }
}
