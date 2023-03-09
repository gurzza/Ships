import java.util.Objects;
import java.util.Scanner;

public class BattleShips {
    public static int nRows = 10;
    public static int nColumn = 10;
    //public static String[][] grid = new String[nRows][nColumn];
    //public static String[][] gridShot = new String[nRows][nColumn];
    //public static String[][] compGrid = new String[nRows][nColumn];
    //public static String[][] compGridShot = new String[nRows][nColumn];
    public static int nShips = 5;
    //public static int nPlayerShips = nShips;
    //public static int nCompShips = nShips;
    public static boolean step = true;

    public static void main(String[] args){

        //createGrid();

        //deployPlayerShips();

        //deployComputerShips();

        //printGrid(grid);

        //grid[0][0] = "0";
        //printGrid(grid);
        //printGrid(compGrid);

//        while(nPlayerShips > 0 && nCompShips > 0){
//            Battle();
//        }

//        if(nPlayerShips == 0) {
//            System.out.println("CONGRATULATIONS PLAYER!");
//        } else {
//            System.out.println("PC HAS WON:(");
//        }
    }

    private static boolean makeShot(String[][] gridForCheck, String[][] gridForShot) {
        // bGrid == 1 => Player1
        Scanner scan = new Scanner(System.in);
        int x, y;
        boolean shotStatus = true; // true => got, false => away

        do{
            System.out.println("\nEnter coordinate X for the shot: ");
            x = scan.nextInt();
            System.out.println("Enter coordinate Y for the shot: ");
            y = scan.nextInt();

            if(x < 0 || y < 0 || x >= nColumn || y >= nRows){
                System.out.println("WRONG COORDINATE! TRY ONE MORE TIME!");
                continue;
            } else if (gridForShot[y][x] == "*" || gridForShot[y][x] == "d") {
                System.out.println("YOU HAVE ALREADY SHOTED TO THIS CELL (%d, %d)!".formatted(x, y));
                continue;
            } else {
                if (Objects.equals(gridForCheck[y][x], "s")) {
                    gridForShot[y][x] = "d";
                    System.out.println("KILL");
                    shotStatus = true;
                } else {
                    System.out.println("BESIDE");
                    gridForShot[y][x] = "*";
                    shotStatus = false;
                }
                break;
            }
        } while(true);

        return shotStatus;
    }
    public static void Battle(Player p1, Player p2) {
        // step => player1, !step => player2
        while (p1.shipsStillAlive > 0 && p2.shipsStillAlive > 0) {
            boolean shotStatus = true;

            while (shotStatus && p1.shipsStillAlive > 0 && p2.shipsStillAlive > 0) {
                if (step) {
                    System.out.println("\nPLAYER %s:".formatted(p1.getName()));
                    printGrid(p1.pShotGrid);
                    shotStatus = makeShot(p2.getPersonalGrid(), p1.pShotGrid);
                    p2.shipsStillAlive -= shotStatus ? 1 : 0;
                } else {
                    System.out.println("\nPLAYER %s:".formatted(p2.getName()));
                    printGrid(p2.pShotGrid);
                    shotStatus = makeShot(p1.getPersonalGrid(), p2.pShotGrid);
                    p1.shipsStillAlive -= shotStatus ? 1 : 0;
                }
            }
            step = !step;
        }

        if (p1.shipsStillAlive == 0) {
            System.out.print("%s LOST:(".formatted(p1.getName().toUpperCase()));
        } else {
            System.out.print("%s LOST:(".formatted(p2.getName().toUpperCase()));
        }
    }

//    private static void deployComputerShips() {
//        for (int i = 0; i < nRows; i++) {
//            for (int j = 0; j < nColumn; j++) {
//                compGrid[i][j] = grid[i][j];
//            }
//        }
//    }

    public static void printGrid(String[][] outGrid) {
        System.out.print('\t');
        for (int i = 0; i < nColumn; i++){
            System.out.print(String.valueOf(i) + '\t');
        }
        for (int i = 0; i < nRows; i++){
            System.out.print('\n' + String.valueOf(i) + "|\t");
            for (int j = 0; j < nColumn; j++){
                System.out.print(outGrid[i][j] + '\t');
            }
            System.out.print('|' + String.valueOf(i));
        }
        System.out.println();
        for (int i = 0; i < nColumn; i++){
            System.out.print('\t' + String.valueOf(i));
        }
    }



//    public static void createGrid() {
//
//        for(int i = 0; i < nRows; i++){
//            for(int j = 0; j < nColumn; j++){
//                grid[i][j] = " ";
//                gridShot[i][j] = " ";
//                compGridShot[i][j] = " ";
//            }
//        }
//
//        //first line
//        System.out.print('\t');
//        for (int i = 0; i < nColumn; i++){
//            System.out.print(String.valueOf(i) + '\t');
//        }
//        for (int i = 0; i < nRows; i++){
//            System.out.print('\n' + String.valueOf(i) + "|\t");
//            for (int j = 0; j < nColumn; j++){
//                System.out.print(" " + '\t');
//            }
//            System.out.print('|' + String.valueOf(i));
//        }
//        System.out.println();
//        for (int i = 0; i < nColumn; i++){
//            System.out.print('\t' + String.valueOf(i));
//        }
//    }
}