import java.sql.SQLException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        DatabaseCommands commands = new DatabaseCommands();
        if (commands.connection != null) {
            Scanner scanner = new Scanner(System.in);
            System.out.println("The system is starting...");

            int method = 0;
            while (method >= 0 && method < 9) {
                switch (method) {
                    case 0:
                        System.out.println("Menu options:\n* Press 0 to see the menu.\n" +
                                "* Press 1 to find drink.\n" +
                                "* Press 2 to update the drink.\n" +
                                "* Press 3 to remove the drink.\n" +
                                "* Press 4 to find equipment.\n" +
                                "* Press 5 to add new shop.\n" +
                                "* Press 6 to see drinks in the shop.\n" +
                                "* Press 7 to do buy drink for a user.\n" +
                                "* Press 8 to show Users.\n" +
                                "* Press 9 to exit");
                        break;
                    case 1:
                        System.out.println("Input drink name");
                        String drink = scanner.nextLine();
                        commands.findDrink(drink);
                        break;
                    case 2:
                        try {
                            commands.showDrinks();
                            System.out.println("Input drink id");
                            int drinkId = Integer.parseInt(scanner.nextLine());
                            System.out.println("Input new drink name");
                            String newDrinkName = scanner.nextLine();
                            commands.updateDrink(drinkId, newDrinkName);
                        } catch (NumberFormatException ex) {
                            System.out.println("Provided id is not valid.");
                        }
                        break;
                    case 3:
                        try {
                            commands.showDrinks();
                            System.out.println("Input drink id");
                            int drinkId = Integer.parseInt(scanner.nextLine());
                            commands.removeDrink(drinkId);
                        } catch (NumberFormatException ex) {
                            System.out.println("Provided id is not valid.");
                        }
                        break;
                    case 4:
                        System.out.println("Input equipment name");
                        String equipment = scanner.nextLine();
                        commands.findEquipment(equipment);
                        break;
                    case 5:
                        System.out.println("Input country name");
                        String country = scanner.nextLine();
                        System.out.println("Input city");
                        String city = scanner.nextLine();
                        System.out.println("Input street");
                        String street = scanner.nextLine();
                        System.out.println("Input shop start time");
                        String startTime = scanner.nextLine();
                        System.out.println("Input shop end time");
                        String endTime = scanner.nextLine();
                        commands.addNewShop(country, city, street, startTime, endTime);
                        break;
                    case 6:
                        try {
                            commands.showShops();
                            System.out.println("Input shop id");
                            int shopId = Integer.parseInt(scanner.nextLine());
                            commands.showCoffeeTeaInTheShop(shopId);
                        } catch (NumberFormatException ex) {
                            System.out.println("Provided id is not valid.");
                        }
                        break;
                    case 7:
                        try {
                            commands.showShops();
                            System.out.println("Input shop id");
                            int shopId = Integer.parseInt(scanner.nextLine());
                            commands.showCoffeeTeaInTheShop(shopId);
                            System.out.println("Input drink id");
                            int drinkId = Integer.parseInt(scanner.nextLine());
                            System.out.println("Input amount");
                            int amount = Integer.parseInt(scanner.nextLine());
                            if (amount <= 0) {
                                System.out.println("Amount cannot be negative");
                                break;
                            }
                            commands.showUsers();
                            int userId = Integer.parseInt(scanner.nextLine());
                            commands.buyDrinkForAUser(amount, drinkId, userId, shopId);
                        } catch (NumberFormatException ex) {
                            System.out.println("Provided id is not valid.");
                        }
                        break;
                    case 8:
                        commands.showUsers();
                        break;
                    default:
                        break;
                }
                method = scanner.nextInt();
                scanner.nextLine();
            }
            scanner.close();

            try {
                commands.connection.close();
            } catch (SQLException exp) {
                System.out.println("Cannot close connection!");
                exp.printStackTrace();
            }
        }
    }
}
