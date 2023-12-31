package ra.entity;

import ra.business.IEntity;

import java.io.Serializable;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static ra.presentation.ManagementCategory.listCategory;

public class Category implements IEntity, Serializable {
    private int id;
    private String name;
    private boolean status;

    public Category() {
    }

    public Category(int id, String name, boolean status) {
        this.id = id;
        this.name = name;
        this.status = status;
    }

    @Override
    public void input(Scanner scanner) {
        System.out.println("Enter id : ");
        this.id = checkId(scanner);
        System.out.println("Enter name : ");
        this.name = checkCategory(scanner);
        System.out.println("Enter status :");
        this.status = checkStatus(scanner);

    }

    @Override
    public void output() {
        this.id = getId();


    }

    public int checkId(Scanner scanner) {
        do {
            try {
                int checkId = Integer.parseInt(scanner.nextLine());
                if (checkId > 0) {
                    Boolean isUniqueId = true;

                    for (Category category : listCategory) {
                        if (category.getId() == checkId) {
                            isUniqueId = false;
                            break;
                        }
                    }
                    if (isUniqueId){
                        return checkId;
                    }else {
                        System.err.println("Exists , please enter again :");

                    }


                } else {
                    System.err.println("Please enter number !");
                }
            } catch (NumberFormatException e) {
                System.err.println("Please enter number !");
            }
        } while (true);

    }

    private static final String ACCOUNT_REGEX = "^[a-z]{6,30}$";

    public boolean validateName(String regex) {
        Pattern pattern = Pattern.compile(ACCOUNT_REGEX);
        Matcher matcher = pattern.matcher(regex);
        return matcher.matches();
    }

    //    note check 6 - 30 kí tự
    public String checkCategory(Scanner scanner) {
        do {
            try {
                String newNameCategory = (scanner.nextLine()).trim();
                boolean isExists = false;

                if (newNameCategory.length() >= 6 && newNameCategory.length() <= 30) {
                    for (Category category : listCategory) {
                        if (category.getName().equals(newNameCategory)) {
                            isExists = true;
                            break;
                        }
                    }
                    if (isExists) {
                        System.err.println("Exists , please enter category name");
                    } else {
                        return newNameCategory;
                    }
                }
                else {
                    System.err.println("Please enter name category 6-30 character");
                }
            } catch (Exception e) {
                System.err.println("Please enter new name category");
            }

        } while (true);

    }

    public boolean checkStatus(Scanner scanner) {
        do {
            try {

                String newStatus = scanner.nextLine();
                if (newStatus.equalsIgnoreCase("true") || newStatus.equalsIgnoreCase("false")) {
                    return Boolean.parseBoolean(newStatus);
                } else {
                    System.err.println("Enter true|false :");
                }
            } catch (Exception e) {
                System.err.println("Enter true|false :");
            }

        } while (true);

    }

    public void updateData(Scanner scanner) {
        boolean isExit = true;
        do {
            System.out.println("===== Update category =====");
            System.out.println("1. Update category id ");
            System.out.println("2. Update category name ");
            System.out.println("3. Update status ");
            System.out.println("4. Exit");
            System.out.println("Enter 1-3 :");
            int choice = Integer.parseInt(scanner.nextLine());
            switch (choice) {
                case 1:
                    System.out.println("Enter new category id : ");
                    this.id = checkId(scanner);
                    break;
                case 2:
                    System.out.println("Enter new category name : ");
                    this.name = checkCategory(scanner);
                    break;
                case 3:
                    System.out.println("Enter new status : ");
                    this.status = checkStatus(scanner);
                    break;
                case 4:
                    System.out.println("Exit");
                    isExit = false;
                    break;
                default:
                    System.err.println("Please enter 1-4 !");
                    break;

            }

        } while (isExit);
    }




    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean getStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }
}
