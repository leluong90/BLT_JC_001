package ra.bussinessipm;

import ra.entity.Book;
import ra.entity.Category;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

import static ra.presentation.ManagementBook.listBook;
import static ra.presentation.ManagementCategory.listCategory;


public class BookIpm {
    public static void addBook(Scanner scanner) {
        try {
            System.out.println("Enter number book :");
            int numberOfBook = Integer.parseInt(scanner.nextLine());
            for (int i = 0; i < numberOfBook; i++) {
                Book book = new Book();
                book.input(scanner);
                listBook.add(book);
            }
        } catch (NumberFormatException e) {
            System.err.println("Enter number ");
        }

    }

    public static void displayBook() {
        for (Book book : listBook) {
            book.output();

        }
    }

    public static void updateBook(Scanner scanner) {
//        nhập id khác trong list chưa hiện ra gì
        try {
            boolean isExists = false ;
            System.out.println("Enter id need update :");
            String updateId = scanner.nextLine();
            for (Book book : listBook) {
                if (book.getId().equals(updateId)) {
                    book.updateBook(scanner);
                    isExists = true ;
                    break;
                }

            }
            if (!isExists){
                System.err.println("Book not exists");
            }

        } catch (NumberFormatException e) {
            System.err.println(e.getMessage());
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }

    }

    public static void deleteBook(Scanner scanner) {
        try {
            boolean isExists = false ;
            System.out.println("Enter book id need delete : ");
            String deleteBookid = scanner.nextLine();
            for (Book book : listBook) {
                if (book.getId().equals(deleteBookid)) {
                    listBook.remove(book);
                    System.out.println("Delete successfully");
                    isExists = true ;
                    break;
                }
            }
            if (!isExists){
                System.out.println("Book id is not exists");

            }

        } catch (NumberFormatException e) {
            System.err.println(e.getMessage());
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }


    }
    public static void searchBook(Scanner scanner){
        try {
            boolean isExists = false ;
            System.out.println("Enter book id need search :");
            String searchBookId = scanner.nextLine();
            for (Book book : listBook) {
                if (book.getId().equals(searchBookId)){
                    book.output();
                    System.out.println();
                    isExists = true ;
                    break;
                }
            }
            if (!isExists){
                System.err.println("Book id not exists");
            }

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
    public static void displayBookOfCategoryId(){
//        for (Category category : listCategory) {
//            System.out.printf("%s has list of books:\n", category.getName());
//
//            // Tìm danh sách sách thuộc về từng thể loại
//            List<Book> booksInCategory = listBook.stream()
//                    .filter(book -> book.getCategoryId() == category.getId())
//                    .collect(Collectors.toList());
//
//            // Hiển thị thông tin sách trong từng thể loại
//            for (Book book : booksInCategory) {
//                System.out.println(book);
//            }
//        }
            for (Category category : listCategory) {
                System.out.printf("%s have list book :\n" , category.getName());
                listBook.stream().filter(book -> book.getCategoryId() == category.getId()).forEach(System.out::println);
            }


    }
    public static List<Book> readBookFromFile() {
        List<Book> listBookRead = null;
        File file = new File("books.txt");
        FileInputStream fis = null;
        ObjectInputStream ois = null;
        try {
            fis = new FileInputStream(file);
            ois = new ObjectInputStream(fis);
            listBookRead = (List<Book>) ois.readObject();
        } catch (Exception ex) {
            listBookRead = new ArrayList<>();
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
        return listBookRead;
    }
    public static void writeBookToFile() {
        File file = new File("books.txt");
        FileOutputStream fos = null;
        ObjectOutputStream oos = null;
        try {
            fos = new FileOutputStream(file);
            oos = new ObjectOutputStream(fos);
            oos.writeObject(listBook);
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

