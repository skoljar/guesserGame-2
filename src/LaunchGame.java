import java.util.Scanner;

public class LaunchGame {
    public static void main(String[] args){

        int numberOfPlayers = 2;
        int numberOfWins = 3;

        Umpire umpire = new Umpire(numberOfPlayers, numberOfWins);
    }
}

class Guesser {
    private int guessNum;

    public Guesser() {
        Scanner scan = new Scanner(System.in);
        do {
            System.out.print("Guesser kindly guess the number from 0 to 9: ");
            this.guessNum = scan.nextInt();
        } while (guessNum >9 || guessNum <0);

        for(int clear = 0; clear < 100; clear++)
        {
            System.out.println() ;
        }
    }

    public int getGuessNumber() {
        return this.guessNum;
    }
}

class Player {
    private int pguessNum;

    public Player(int i) {
        Scanner scan = new Scanner(System.in);
        do {
            System.out.print("Player " + i + " kindly guess the number from 0 to 9: ");
            this.pguessNum = scan.nextInt();
        } while (pguessNum >9 || pguessNum <0);

        for(int clear = 0; clear < 100; clear++)
        {
            System.out.println() ;
        }
    }

    public int getPGuessNumber() {
        return this.pguessNum;
    }
}

class Umpire {
    private int numFromGuesser;
    private int [][] playersNumbers;
    private int numberOfWinGames;
    private int numberOfWins;

    public Umpire(int numberOfPlayers, int numberOfWins) {
        if(numberOfPlayers <=0) {
            System.out.println("Players cannot be less than 1");
            System.exit(0);
        }

        if(numberOfWins <=0) {
            System.out.println("Number of wins should be greater than 0");
            System.exit(0);
        }

        this.playersNumbers = new int[numberOfPlayers][2];
        this.numberOfWins = numberOfWins;

        while(numberOfWinGames<numberOfWins) {
            Guesser guesser = new Guesser();
            this.numFromGuesser = guesser.getGuessNumber();
            collectNumbersFromPlayers();
            compare();
        }
    }

    public void collectNumbersFromPlayers() {
        for(int i=0; i<playersNumbers.length; i++) {
            Player player = new Player(i+1);
            playersNumbers[i][0] = player.getPGuessNumber();
        }
    }

    public void compare() {
        boolean printFlag = false;
        for(int i = 0; i<playersNumbers.length; i++) {
            if (playersNumbers[i][0] == numFromGuesser && !printFlag) {
                System.out.print("Player " + (i + 1));
                printFlag = true;
                numberOfWinGames++;
                playersNumbers[i][1] ++;
            } else if (playersNumbers[i][0] == numFromGuesser && printFlag) {
                playersNumbers[i][1] ++;
                System.out.print(" & Player " + (i + 1));
            }
        }
        if(printFlag) {
            System.out.println(" guessed correctly");
        } else {
            System.out.println("Nobody guessed correctly");
        }
        System.out.println("The guessed number was " + numFromGuesser);
        System.out.println("========================");

        printScores();

        System.out.println("Number of guessed times: " + numberOfWinGames + " / " + numberOfWins);
        System.out.println("========================");
    }

    private void printScores() {
        for(int i = 0; i<playersNumbers.length; i++) {
            System.out.println("Player " + (i + 1) + " score: " + playersNumbers[i][1]);
        }
    }
}



