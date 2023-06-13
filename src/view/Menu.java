package view;

/**
 *
 * @author Nguyễn Huy Phúc
 */
public class Menu {

    public static void printMenu() {
        System.out.println("      **********************************************************");
        System.out.println("      *          WELCOME TO OPTIONS WITH FLOWER SHOP           *");
        System.out.println("      **********************************************************");
        System.out.println("      *        1. Flower List                                  *");
        System.out.println("      *        2. Order List                                   *");
        System.out.println("      *        3. Save                                         *");
        System.out.println("      *        4. Load                                         *");
        System.out.println("      *        0. Quit                                         *");
        System.out.println("      **********************************************************");
        System.out.print("      **(Note: Your options from 0 - 4): ");
    }

    public static void exit() {
        System.out.println("       -------------------------------------------------------- ");
        System.out.println("      |                   Do you want to exit                  |");
        System.out.println("       -------------------------------------------------------- ");
        System.out.println("      |                   y = Yes                              |");
        System.out.println("      |                   n = No                               |");
        System.out.println("       -------------------------------------------------------- ");
        System.out.print("      **(Note: Your options 'y' or 'n' : ");
    }

    public static void checkSave() {
        System.out.println("       -------------------------------------------------------- ");
        System.out.println("      |             Do you want to save and exit               |");
        System.out.println("       -------------------------------------------------------- ");
        System.out.println("      |                   y = Yes                              |");
        System.out.println("      |                   n = No                               |");
        System.out.println("       -------------------------------------------------------- ");
        System.out.print("      **(Note: Your options 'y' or 'n' : ");
    }

    public static void FlowerList() {
        System.out.println("      ----------------------------------------------------------");
        System.out.println("      |                   Flower List                          |");
        System.out.println("       -------------------------------------------------------- ");
        System.out.println("      |        1. Add a flower                                 |");
        System.out.println("      |        2. Find a flower                                |");
        System.out.println("      |        3. Update a flower                              |");
        System.out.println("      |        4. Delete a flower                              |");
        System.out.println("      |        0. Quit                                         |");
        System.out.println("       -------------------------------------------------------- ");
        System.out.print("      **(Note: Your options from 0 - 4): ");
    }

    public static void orderList() {
        System.out.println("      ----------------------------------------------------------");
        System.out.println("      |                   Order List                           |");
        System.out.println("       -------------------------------------------------------- ");
        System.out.println("      |        1. Add a order                                  |");
        System.out.println("      |        2. Display order                                |");
        System.out.println("      |        3. Sort order                                   |");
        System.out.println("      |        0. Quit                                         |");
        System.out.println("       -------------------------------------------------------- ");
        System.out.print("      **(Note: Your options from 0 - 3): ");
    }

    public static void findFlower() {
        System.out.println("      ----------------------------------------------------------");
        System.out.println("      |                   Flower List                          |");
        System.out.println("       -------------------------------------------------------- ");
        System.out.println("      |        1. Find by name                                 |");
        System.out.println("      |        2. Find by id                                   |");
        System.out.println("      |        0. Quit                                         |");
        System.out.println("       -------------------------------------------------------- ");
        System.out.print("      **(Note: Your options from 0 - 2): ");
    }

    public static void addFlower() {
        System.out.println("       -------------------------------------------------------- ");
        System.out.println("      |                   Flower List                          |");
        System.out.println("       -------------------------------------------------------- ");
        System.out.println("      |        1. Add another flower                           |");
        System.out.println("      |        0. Quit                                         |");
        System.out.println("       -------------------------------------------------------- ");
        System.out.print("      **(Note: Your options from 0 - 1): ");
    }

    public static void addOrderDetail() {
        System.out.println("       -------------------------------------------------------- ");
        System.out.println("      |                   Order List                           |");
        System.out.println("       -------------------------------------------------------- ");
        System.out.println("      |        1. Add another order detail                     |");
        System.out.println("      |        0. Quit                                         |");
        System.out.println("       -------------------------------------------------------- ");
        System.out.print("      **(Note: Your options from 0 - 1): ");
    }

    public static void addOrder() {
        System.out.println("       -------------------------------------------------------- ");
        System.out.println("      |                   Order List                           |");
        System.out.println("       -------------------------------------------------------- ");
        System.out.println("      |        1. Add another order                            |");
        System.out.println("      |        0. Quit                                         |");
        System.out.println("       -------------------------------------------------------- ");
        System.out.print("      **(Note: Your options from 0 - 1): ");
    }

    public static void updateFlower() {
        System.out.println("       -------------------------------------------------------- ");
        System.out.println("      |                   Flower List                          |");
        System.out.println("       -------------------------------------------------------- ");
        System.out.println("      |        1. Update description                           |");
        System.out.println("      |        2. Update importDate                            |");
        System.out.println("      |        3. Update unitPrice                             |");
        System.out.println("      |        4. Update unitPrice                             |");
        System.out.println("      |        0. Quit                                         |");
        System.out.println("       --------------------------------------------------------");
        System.out.print("      **(Note: Your options from 0 - 4): ");
    }

    public static void check() {
        System.out.println("       -------------------------------------------------------- ");
        System.out.println("      |                   Are you sure                         |");
        System.out.println("       -------------------------------------------------------- ");
        System.out.println("      |                   y = Yes                              |");
        System.out.println("      |                   n = No                               |");
        System.out.println("       -------------------------------------------------------- ");
        System.out.print("      **(Note: Your options 'y' or 'n' : ");
    }
}
