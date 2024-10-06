package model;

import lombok.Getter;

import java.util.Arrays;
import java.util.Random;


@Getter
public class Card {

    private static int counterID;
    private int id;
    private final int price;
    private int[][] number;

    public Card() {
        id=++counterID;
        price=1;
        randomizer();
        sort();
        blankSpace();

    }

    //Method
    private void randomizer(){
        number=new int[3][9];
        int auxNum;
        Random random=new Random();

        for (int i = 0; i < number.length; i++) {
            for (int j = 0; j < number[i].length; j++) {
                do{
                    if (j==0) auxNum= random.nextInt(1,10);
                    else if (j==8) auxNum= random.nextInt(80, 91);
                    else auxNum= random.nextInt(10*j, (10*j)+10);
                }while (repeat(number, auxNum));
                number[i][j]=auxNum;
            }
        }
    }
    private void sort(){
        int[] aux=new int[3];
        for (int i = 0; i < number[0].length; i++) {
            for (int j = 0; j < number.length; j++) {
                aux[j]=number[j][i];
            }
            Arrays.sort(aux);
            for (int j = 0; j < number.length; j++) {
                number[j][i]=aux[j];
            }
        }
    }
    private void blankSpace(){

        Random random=new Random();
        int[] allocated = {1, 1, 1, 1, 1, 1, 1, 1, 1};

        int randomPlace;

        for (int i = 0; i < 3; i++) {
            do {
                randomPlace = random.nextInt(0, allocated.length);
            } while (allocated[randomPlace] == 2);
            allocated[randomPlace] = 2;
        }

        int[] numBlank = new int[3];
        boolean[] availableSpot;
        int smaller, randRow, ocuPlace;
        boolean equals;


        for (int j = 0; j < number[0].length; j++) {


            availableSpot = new boolean[]{true, true, true};

            equals = true;
            for (int i = 0; i < numBlank.length - 1 && equals; i++) {
                if (numBlank[i] != numBlank[i + 1]) {
                    equals = false;
                }
            }


            if (!equals) {

                // Busco el smaller hueco
                smaller = numBlank[0];
                for (int i = 1; i < numBlank.length; i++) {
                    if (numBlank[i] < smaller) {
                        smaller = numBlank[i];
                    }
                }

                for (int i = 0; i < availableSpot.length; i++) {
                    if (numBlank[i] != smaller) {
                        availableSpot[i] = false;
                    }
                }

            }


            do {

                randRow = random.nextInt(0, number.length );
            } while (!availableSpot[randRow] || number[randRow][j] == -1);


            number[randRow][j] = -1;


            numBlank[randRow]++;

            if (allocated[j] == 2) {

                ocuPlace = 0;
                for (int i = 0; i < number.length; i++) {
                    if (number[i][j] == -1) {
                        ocuPlace++;
                    }
                }
                if (ocuPlace < allocated[j]) {
                    j--;
                }
            }
        }

    }
    private boolean repeat(int[][] number, int num){
        for (int[] firstLoop:number) {
            for (int secondLoop:firstLoop) {
                if(num==secondLoop) return true;
            }
        }
        return false;
    }


    //Line
    public void markNumber(int number) {
        for (int i = 0; i < this.number.length; i++) {
            for (int j = 0; j < this.number[i].length; j++) {
                if (this.number[i][j] == number) {
                    this.number[i][j] = -1;
                }
            }
        }
    }
    public boolean isLineComplete() {
        for (int[] row : number) {
           if (isLineComplete(row)) return true;
        }
        return false;
    }

    public boolean isBingoComplete(){
        for (int[]row: number){
            if (!isLineComplete(row)) return false;
        }
        return true;
    }

    private boolean isLineComplete(int[] row){
        for(int num:row){
            if(num != -1) return false;
        }
        return true;
    }
    public void printCard(){
        for (int i = 0; i < number.length; i++) {
            for (int j = 0; j < number[i].length; j++) {
                if (number[i][j] == -1){
                    System.out.print("\t");
                }else {
                    System.out.print(number[i][j] + " \t");
                }
            }
            System.out.println();
        }


    }

}
