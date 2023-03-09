import java.util.Scanner;

public class Player extends BattleShips{

    private String [][] personalGrid = new String[nRows][nColumn];
    String [][] pShotGrid = new String[nRows][nColumn];
    private String playerName;
    int shipsStillAlive = nShips; // number of ships that are still alive
    static int nPlayers = 0;

    //////////////////////////////////////////////////////
    //////////////////////////////////////////////////////


    public Player(String playerName){
        this.playerName = playerName;

        for (int i = 0; i < nRows; i++){
            for (int j = 0; j < nColumn; j++){
                personalGrid[i][j] = " ";
                pShotGrid[i][j] = " ";
            }
        }
        this.deployPlayerShips();
    }
    public Player() {
       this("Player_" + String.valueOf(nPlayers));
       nPlayers++;
    }

    public String getName(){
        return this.playerName;
    }

    public String[][] getPersonalGrid(){
        return this.personalGrid;
    }

    public void deployPlayerShips() {
        Scanner scan = new Scanner(System.in);
        int x, y;
        String name = this.getName();
        // nShips with size 1
        for(int i = 0; i < nShips; ){
            System.out.printf("\nPLAYER: %s. Enter coordinate X for the " + i + " ship: ", name);
            x = scan.nextInt();
            System.out.print("Enter coordinate Y for the " + i + " ship: ");
            y = scan.nextInt();

            if (x < 0 || y < 0 || x >= nColumn || y >= nRows){
                System.out.print("WRONG COORDINATE! TRY ONE MORE TIME");
                continue;
            } else if (this.personalGrid[y][x] != " "){
                System.out.print("THIS CELL (%d, %d) IS NOT EMPTY! TRY ONE MORE TIME".formatted(x, y));
                continue;
            } else {
                this.personalGrid[y][x] = "s";
                i++;
            }
        }
    }




    public static void main(String[] args){
        Scanner scan = new Scanner(System.in);

        System.out.print("Enter the name of the first player: ");
        String name1 = scan.next();
        Player p = new Player(name1);
        //System.out.print(p.playerName);

        System.out.print("Enter the name of the second player: ");
        String name2 = scan.next();
        Player a = new Player(name2);
        //System.out.print(nPlayers);

        Battle(p, a);
    }

}
