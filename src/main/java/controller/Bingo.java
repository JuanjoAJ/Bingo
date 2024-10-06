package controller;

import model.*;

import java.util.ArrayList;

public class Bingo {

    private ArrayList<Player> players;

    public Bingo() {
        this.players = new ArrayList<>();
    }


    //Player method

    public void createPlayer(String name, String id, int balance) {

        if (getPlayer(id) == null) {
            players.add(new Player(name, id, balance));
            System.out.println("Player created correctly |\u001B[32m Jugador creado correctamente\u001B[0m");
            System.out.println();

        } else System.out.println("Player is already registered |\u001B[31m El jugador ya está registrado\u001B[0m \n");


    }

    public Player getPlayer(String id) {
        for (Player item : players) {
            if (id.equals(item.getId())) return item;
        }
        return null;
    }

    //Card method

    public void addCard(String id, int num) {
        try {
            if (getPlayer(id) == null)
                throw new RuntimeException("Player not registered |\u001B[32m El jugador no está registrado\u001B[0m");
            Card card;
            for (int i = 0; i < num; i++) {
                if (getPlayer(id).getBalance() <= 0)
                    throw new RuntimeException("There is no balance on the account |\u001B[32m Ya no queda saldo en la cuenta\u001B[0m");
                card =new Card();
                getPlayer(id).addBingoCard(card);
                getPlayer(id).setBalance(getPlayer(id).getBalance()-1);
                System.out.println("Registered card with " + card.getId() + " id |\u001B[32m Registrado cartón con la identificación " + card.getId() +"\u001B[0m");
                System.out.println("Your card: | \u001B[32m Tu cartón:  \u001B[0m");
                card.printCard();
            }
            System.out.println("Cards registered correctly |\u001B[32m Cartones registrados correctamente\u001B[0m");
        } catch (RuntimeException e) {
            System.err.println(e.getMessage());
        }

    }

    // Bingo method

    public void game(){
    int[] randomNumber=randomNumber();
    int i=0;
    boolean isBingo=false;
    boolean linea=false;
    do {
        try {
            System.out.println("🎆"+randomNumber[i]+"🎆");
            System.out.println("Please, check your card | \u001B[32m Por favor, compruebe su cartón \u001B[0m");
            Thread.sleep(5000);
            for (Player item:players) {
                if (!linea) linea=item.checkLine(randomNumber[i]);
                if(!isBingo) isBingo=item.checkBingo(randomNumber[i]);
            }
        i++;
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }while (!isBingo);
    }


    private int[] randomNumber(){
        int num;
        int[] randomNumber=new int[89];
        for (int i = 0; i < randomNumber.length; i++) {
            do{
            num=(int) (Math.random()*90)+1;
            }while (repeat(num, randomNumber, i));
            randomNumber[i]=num;
        }
        return randomNumber;
    }
    private boolean repeat(int num, int[] number, int currentIndex){
        for (int i = 0; i < currentIndex; i++) {
            if(number[i]==num) return true;
        }
        return false;
    }




}
