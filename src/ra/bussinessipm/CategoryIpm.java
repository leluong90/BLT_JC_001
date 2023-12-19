package ra.bussinessipm;

import ra.entity.Book;
import ra.entity.Category;

import java.io.*;
import java.util.*;

import static ra.presentation.ManagementBook.*;
import static ra.presentation.ManagementCategory.listCategory;

public class CategoryIpm {
    public static final String ANSI_GREEN = "\u001B[32m";

    public static void addCategory(Scanner scanner) {
        try {
            System.out.println("Enter number category :");
            int numberOfCategory = Integer.parseInt(scanner.nextLine());
            if (numberOfCategory < 0) {
                System.err.println("Number category must be greater than 0\n");
            } else if (numberOfCategory == 0) {
                System.out.println(ANSI_YELLOW + "You didn't chose number category\n");

            } else {
                for (int i = 0; i < numberOfCategory; i++) {
                    Category category = new Category();
                    category.input(scanner);
                    listCategory.add(category);
                    System.out.println(ANSI_GREEN + "Add category successfully !\n");
                }
            }
        } catch (NumberFormatException e) {
            System.err.println("Enter number !");
        }

    }

    public static void displayCategory() {
        Collections.sort(listCategory, Comparator.comparing(Category::getName));
        // In tiêu đề của bảng
        System.out.println(ANSI_CYAN + "+--------+----------------------------------+--------+");
        System.out.println(ANSI_CYAN + "|  ID    |              Name                | Status |");
        System.out.println(ANSI_CYAN + "+--------+----------------------------------+--------+");
        // In dữ liệu từ danh sách thể loại
        for (Category category : listCategory) {
            System.out.printf(ANSI_CYAN + "| %-6d | %-32s | %-6b |\n", category.getId(), category.getName(), category.getStatus());

        }
        System.out.println(ANSI_CYAN + "+--------+----------------------------------+--------+");

    }

    public static void updateCategory(Scanner scanner) {
        CategoryIpm.displayCategory();

        try {
            boolean isExists = false;
            System.out.println(ANSI_PURPLE + "Enter name need update : ");
            String updateName = scanner.nextLine();
            for (Category category : listCategory) {
                if (category.getName().equals(updateName)) {
                    category.updateData(scanner);
                    System.out.println(ANSI_GREEN + "Update category successfully !\n");
                    isExists = true;
                    break;
                }
            }
            if (!isExists) {
                System.err.println("Not exists name !");

            }

        } catch (NumberFormatException e) {
            System.err.println("Please , enter name !");
        }

    }

    public static final String ANSI_YELLOW = "\u001B[33m";

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
            if (!existsCategoryId) {
                System.err.println("Not exists Category !\n");
            } else if (!hasBooksCategory && existsCategoryId) {
                boolean isExit = true;
                do {
                    try {
                        System.out.println(ANSI_YELLOW + "Are you sure want to delete ?(Yes/No)");
                        String checkDelete = scanner.nextLine().trim().toLowerCase();
                        switch (checkDelete) {
                            case "yes":
                                listCategory.removeIf(category -> category.getId() == deleteCategoryId);
                                System.out.println(ANSI_GREEN + "Category removed successfully !\n");
                                isExit = false;
                                break;

                            case "no":
                                System.out.println(ANSI_YELLOW + "Category removed fail\n");
                                isExit = false;
                                break;
                            default:
                                System.err.println("Please , enter Yes/No\n");
                                isExit = true;
                                break;
                        }
                    } catch (Exception e) {
                        System.err.println("Please , enter Yes/No !\n");
                    }
                } while (isExit);
            } else {
                System.err.println("Category contains books. Cannot remove !\n");
            }


        } catch (NumberFormatException e) {
            System.err.println("Please , enter number !\n");
        }


    }

    public static void statisticsCategory() {

        for (Category category : listCategory) {

            long count = listBook.stream().filter(book -> book.getCategoryId() == category.getId()).count();


            if (count == 0) {
                System.out.printf(ANSI_WHITE + "%s have : %d book \n", category.getName(), count);
                System.out.println();
            } else {
                System.out.printf(ANSI_WHITE + "%s have : %d book  \n", category.getName(), count);
                System.out.println(ANSI_CYAN + "+------+----------------------+-----------------+-----------------+------+----------------------+-----------+");
                System.out.println(ANSI_CYAN + "|BookId|         Title        |      Author     |    Publisher    | Year |     Description      |Category Id|");
                System.out.println(ANSI_CYAN + "+------+----------------------+-----------------+-----------------+------+----------------------+-----------+");
                listBook.stream().filter(book -> book.getCategoryId() == category.getId()).forEach(System.out::println);
                System.out.println(ANSI_CYAN + "+------+----------------------+-----------------+-----------------+------+----------------------+-----------+");
                System.out.println();
            }
        }
    }
    public static List<Category> readCategoryFromFile () {
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

    public static void writeCategorytoFile () {
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
