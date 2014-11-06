/**
 * ScoreTable
 * Joshua Hunter
 * Section 103
 */
import java.util.Scanner;

public class ScoreTable {
  public static void main(String[] args) {
    Scanner input = new Scanner(System.in);
    int function;
    int[][] scores = {
        // game
        //0, 1, 2, 3, 4, 5, 6
        {20,27,16,23,20,27,18}, // player 0
        { 8,18,14,17, 9,12, 0}, // player 1
        {38,19,25,22,19,25,31}, // player 2
        {17, 8,11,21,15, 0, 9}, // player 3
        { 2, 1, 3, 0,10, 2, 4}  // player 4
    };
    // loop until user chooses to exit
    do {
      // check for valid input
      do {
        System.out.print("Make a selection (0 for help): ");
        function = input.nextInt();
      } while (function < 0 || function > 5);
      // get the desired statistic
      select(scores, function);
    } while (function != 5);
  }

  /** Handles user input. */
  public static void select(int[][] scores, int f) {
    Scanner input = new Scanner(System.in);
    if (f == 0) {
      // print the key
      System.out.println("(1) Average Points per Game");
      System.out.println("(2) Single Game Score");
      System.out.println("(3) Average Game Score");
      System.out.println("(4) Top Scoring Player");
      System.out.println("(5) Exit");
    } else if (f == 1) {
      // average points per game
      int player;

      do {
        System.out.print("Select a player (0-" + (scores.length - 1) + "): ");
        player = input.nextInt();
      } while (player < 0 || player > scores.length - 1);

      System.out.print("Average PPG for player " + player + " is ");
      System.out.println(averagePPG(scores, player));
    } else if (f == 2) {
      // single game score
      int game;

      do {
        System.out.print("Select a game (0-" + (scores[0].length - 1) + "): ");
        game = input.nextInt();
      } while (game < 0 || game > scores[0].length - 1);

      System.out.println("In game " + game + ", the team scored "
          + singleGameScore(scores, game) + " points");
    } else if (f == 3) {
      // average game score
      System.out.println("The average points per game is "
          + averageGameScore(scores));
    } else if (f == 4) {
      // single game, top scoring player
      int game;

      do {
        System.out.print("Select a game (0-" + (scores[0].length - 1) + "): ");
        game = input.nextInt();
      } while (game < 0 || game > scores[0].length - 1);

      System.out.println("The top scorer in game " + game + " was player "
          + singleGameTopScoringPlayer(scores, game));
    } else {
      // exit
      System.out.println("Bye!");
    }
  }

  /** Average points per game. */
  public static double averagePPG(int[][] scores, int player) {
    double points = 0;

    for (int i = 0; i < scores[player].length; i++) {
      points += scores[player][i];
    }
    return (points / scores[player].length);
  }

  public static int singleGameScore(int[][] scores, int game) {
    int score = 0;

    for (int i = 0; i < scores.length; i++) {
      score += scores[i][game];
    }
    return score;
  }

  public static double averageGameScore(int[][] scores) {
    int points = 0;

    for (int i = 0; i < scores.length; i++) {
      for (int j = 0; j < scores[i].length; j++) {
        points += scores[i][j];
      }
    }
    return (points / scores[0].length);
  }

  public static int singleGameTopScoringPlayer(int[][] scores, int game) {
    int highScore = 0;
    int player    = 0;

    for (int i = 0; i < scores.length; i++) {
      if (scores[i][game] > highScore) {
        highScore = scores[i][game];
        player = i;
      }
    }
    return player;
  }
}
