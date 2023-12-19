package ra.bussinessipm;

import ra.entity.Book;
import ra.entity.Category;

import java.io.*;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

import static ra.presentation.ManagementBook.*;
import static ra.presentation.ManagementCategory.listCategory;


public class BookIpm {
    public static final String ANSI_GREEN = "\u001B[32m";

    public static void addBook(Scanner scanner) {
        try {
            System.out.println("Enter number book :");
            int numberOfBook = Integer.parseInt(scanner.nextLine());
            if (numberOfBook < 0) {
                System.err.println("Number category must be greater than 0");
            } else if (numberOfBook == 0) {
                System.out.println(ANSI_YELLOW + "You didn't chose number category\n");
            }else {
                for (int i = 0; i < numberOfBook; i++) {
                    Book book = new Book();
                    book.input(scanner);
                    listBook.add(book);
                    System.out.println(ANSI_GREEN + "Add book successfully !\n");
                }
            }

        } catch (NumberFormatException e) {
            System.err.println("Enter number !\n");
        }

    }

    public static void displayBook() {
        for (Book book : listBook) {
            book.output();

        }
    }

    public static void updateBook(Scanner scanner) {
        try {
            boolean isExists = false;
            System.out.println("Enter title need update :");
            String updateTitle = scanner.nextLine();
            for (Book book : listBook) {
                if (book.getTitle().equals(updateTitle)) {
                    book.updateBook(scanner);
                    System.out.println(ANSI_GREEN + "Update book successfully\n");
                    isExists = true;
                    break;
                }
            }
            if (!isExists) {
                System.err.println("Book not exists !");
            }

        } catch (Exception e) {
            System.err.println("Please , enter update id !");
        }

    }

    public static final String ANSI_YELLOW = "\u001B[33m";

    public static void deleteBook(Scanner scanner) {
        try {
            boolean isExists = false;
            System.out.println("Enter title need delete : ");
            String deleteTitle = scanner.nextLine();
            for (Book book : listBook) {
                if (book.getTitle().equals(deleteTitle)) {
                    isExists = true;
                    break;
                }
            }
            if (!isExists) {
                System.err.println("Not exists book !\n");
            } else {
                boolean isExit = true;
                do {
                    try {
                        System.out.println(ANSI_YELLOW + "Are you sure to delete book ? (Yes/No)");
                        String checkDeleteBook = (scanner.nextLine()).trim().toLowerCase();

                        switch (checkDeleteBook) {
                            case "yes":
                                for (Book book : listBook) {
                                    if (book.getTitle().equals(deleteTitle)) {
                                        listBook.remove(book);
                                        System.out.println(ANSI_GREEN + "Book removed successfully !\n");
                                        isExit = false;
                                        break;
                                    }
                                }

                                break;
                            case "no":
                                System.out.println(ANSI_YELLOW + "Book removed fail\n");
                                isExit = false;
                                break;
                            default:
                                System.err.println("Please , enter Yes/No !");
                                isExit = true;
                                break;
                        }
                    } catch (Exception e) {
                        System.err.println("Please , enter Yes/No !");
                    }

                } while (isExit);


            }


        } catch (NumberFormatException e) {
            System.err.println("Please , enter number !");
        }


    }

    public static void searchBook(Scanner scanner) {
        try {
            boolean isExists = false;
            System.out.println("Enter title need search :");
            String searchTitle = scanner.nextLine();
            for (Book book : listBook) {
                if (book.getTitle().equals(searchTitle)) {
                    book.output();
                    System.out.println();
                    isExists = true;
                    break;
                }
            }
            if (!isExists) {
                System.err.println("Not exists Title !");
            }

        } catch (Exception e) {
            System.err.println("Not exists Title !");
        }
    }

    public static void displayBookOfCategoryId() {
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

//                .forEach(System.out::println);
//        }

        for (Category category : listCategory) {
            System.out.printf(ANSI_WHITE + "%s have list book :\n", category.getName());
            System.out.println(ANSI_CYAN + "+------+----------------------+-----------------+-----------------+------+----------------------+-----------+");
            System.out.println(ANSI_CYAN + "|BookId|         Title        |      Author     |    Publisher    | Year |     Description      |Category Id|");
            System.out.println(ANSI_CYAN + "+------+----------------------+-----------------+-----------------+------+----------------------+-----------+");
            listBook.stream().filter(book -> book.getCategoryId() == category.getId()).forEach(System.out::println);
//            listBook.stream().sorted(Comparator.comparing(Book :: getTitle)).forEach(System.out::println);;
            System.out.println(ANSI_CYAN + "+------+----------------------+-----------------+-----------------+------+----------------------+-----------+");
            System.out.println();

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

