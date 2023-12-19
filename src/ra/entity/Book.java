package ra.entity;

import ra.business.IEntity;

import java.io.Serializable;
import java.time.Year;
import java.util.Scanner;

import static ra.presentation.ManagementBook.ANSI_CYAN;
import static ra.presentation.ManagementBook.listBook;
import static ra.presentation.ManagementCategory.listCategory;

public class Book implements IEntity, Serializable {
    private String id;
    private String title;
    private String author;
    private String publisher;
    private int year;
    private String description;
    private int categoryId;

    public Book() {
    }

    public Book(String id, String title, String author, String publisher, int year, String description, int categoryId) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.publisher = publisher;
        this.year = year;
        this.description = description;
        this.categoryId = categoryId;
    }

    @Override
    public void input(Scanner scanner) {
        System.out.println("Enter book id :");
        this.id = checkId(scanner) ;
        System.out.println("Enter book title :");
        this.title = checkTitle(scanner);
        System.out.println("Enter author :");
        this.author = checkAuthor(scanner);
        System.out.println("Enter publisher :");
        this.publisher = checkPublisher(scanner);
        System.out.println("Enter year :");
        this.year = checkYear(scanner);
        System.out.println("Enter description :");
        this.description = checkDescription(scanner);
        System.out.println("Enter category id :");
        this.categoryId = checkCategoryId(scanner);
    }

    @Override
    public void output() {
        System.out.println(ANSI_CYAN+"+------+----------------------+-----------------+-----------------+------+----------------------+-----------+");
        System.out.println(ANSI_CYAN+"|BookId|         Title        |      Author     |      Publisher  | Year |     Description      |Category Id|");
        System.out.println(ANSI_CYAN+"+------+----------------------+-----------------+-----------------+------+----------------------+-----------+");
        System.out.printf(ANSI_CYAN+"| %-4s | %-20s | %-15s | %-15s | %-4d | %-20s | %-9d |\n",
                this.id, this.title, this.author, this.publisher, this.year, this.description, this.categoryId);
        System.out.println(ANSI_CYAN+"+------+----------------------+-----------------+-----------------+------+----------------------+-----------+");

    }

    public String checkId(Scanner scanner) {
        do {
            try {
                Boolean isExists = false;
                String checkId = scanner.nextLine();
                if (checkId.length() == 4 && checkId.startsWith("B")) {
                    for (Book book : listBook) {
                        if (book.getId().equals(checkId)) {
                            isExists = true;
                            break;
                        }
                    }
                    if (!isExists) {
                        return checkId;
                    } else {
                        System.err.println("Exists book id");
                    }

                } else {
                    System.err.println("Book id must have 4 character and first character is B ");
                }

            } catch (Exception e) {
                System.err.println("Book id must have 4 character and first character is B ");
            }
        } while (true);

    }

    public String checkTitle(Scanner scanner) {
        do {
            try {
                boolean isExists = false;
                String checkTitle = scanner.nextLine();
                for (Book book : listBook) {
                    if (book.getTitle().equals(checkTitle)) {
                        isExists = true;
                        break;
                    }
                }
                if (isExists) {
                    System.err.println("Exist title !");
                }else {
                    if (checkTitle.trim().length() >= 6 && checkTitle.trim().length() <= 50) {
                        return checkTitle ;
                    } else {
                        System.err.println("Title have 6-30 character !");
                    }

                }
            } catch (Exception e) {
                System.err.println(e.getMessage());
            }
        } while (true);
    }

    public String checkAuthor(Scanner scanner) {
        do {
            try {
                String checkAuthor = scanner.nextLine();
                if (checkAuthor.trim().isEmpty()) {
                    System.err.println("Not empty author");
                } else {
                    return checkAuthor;
                }
            } catch (Exception e) {
                System.err.println(e.getMessage());
            }

        } while (true);
    }

    public String checkPublisher(Scanner scanner) {
        do {
            try {
                String checkPublisher = scanner.nextLine();
                if (checkPublisher.trim().isEmpty()) {
                    System.err.println("Not empty publisher");
                } else {
                    return checkPublisher;
                }
            } catch (Exception e) {
                System.err.println(e.getMessage());
            }

        } while (true);
    }

    public int checkYear(Scanner scanner) {
        do {
            try {
                int checkYear = Integer.parseInt(scanner.nextLine());
                int currentYear = Year.now().getValue();
                if (checkYear >= 1970 && checkYear <= currentYear) {
                    return checkYear;
                } else {
                    System.err.println("Please enter 1970 - Current year");
                }
            } catch (NumberFormatException e) {
                System.err.println(e.getMessage());
            }

        } while (true);
    }

    public String checkDescription(Scanner scanner) {
        do {
            try {
                String checkDescription = scanner.nextLine();
                if (checkDescription.trim().isEmpty()) {
                    System.err.println("Not empty description");
                } else {
                    return checkDescription;
                }
            } catch (Exception e) {
                System.err.println(e.getMessage());
            }

        } while (true);
    }

    public int checkCategoryId(Scanner scanner) {
        do {
            try {
                Boolean isExists = false;
                int checkCategoryId = Integer.parseInt(scanner.nextLine());
                for (Category category : listCategory) {
                    if (category.getId() == checkCategoryId) {
                        isExists = true;
                        break;
                    }
                }
                if (isExists) {
                    return checkCategoryId;
                } else {
                    System.err.println("Category id is not exist");

                }

            } catch (NumberFormatException e) {
                System.err.println("Invalid input. Please enter a valid number:");
            } catch (Exception e) {
                System.err.println(e.getMessage());
            }


        } while (true);
    }

    public void updateBook(Scanner scanner) {
        boolean isExit = true;
        do {
            System.out.println("===== Update book =====");
            System.out.println("1. Update book id ");
            System.out.println("2. Update book title ");
            System.out.println("3. Update author ");
            System.out.println("4. Update publisher ");
            System.out.println("5. Update year ");
            System.out.println("6. Update description ");
            System.out.println("7. Update category id ");
            System.out.println("8. Exit");
            System.out.println("Enter 1-8");
            int choice = Integer.parseInt(scanner.nextLine());
            switch (choice) {
                case 1:
                    System.out.println("Enter new book id : ");
                    this.id = checkId(scanner);
                    break;
                case 2:
                    System.out.println("Enter new title : ");
                    this.title = checkTitle(scanner);
                    break;
                case 3:
                    System.out.println("Enter new author :");
                    this.author = checkAuthor(scanner);
                    break;
                case 4:
                    System.out.println("Enter new publisher :");
                    this.publisher = checkPublisher(scanner);
                    break;
                case 5:
                    System.out.println("Enter new year :");
                    this.year = checkYear(scanner);
                    break;
                case 6:
                    System.out.println("Enter new description :");
                    this.description = checkDescription(scanner);
                    break;
                case 7:
                    System.out.println("Enter new category id :");
                    this.categoryId = checkCategoryId(scanner);
                    break;
                case 8:
                    System.out.println("Exit");
                    isExit = false;
                    break;
                default:

                    System.err.println("Please enter 1-8 !");


                    break;

            }

        } while (isExit);
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;


    }

    public void setTitle(String title) {

        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {

        this.publisher = publisher;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
    }

    @Override
    public String toString() {
        return String.format("| %-4s | %-20s | %-15s | %-15s | %-4d | %-20s | %-9d |"
                , this.id, this.title, this.author,   this.publisher,this.year ,  this.description,this.categoryId);


    }
}
