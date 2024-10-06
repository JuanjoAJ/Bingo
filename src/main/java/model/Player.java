package model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter

public class Player {
    private String name;
    private String id;
    private int balance;
    private ArrayList<Card> bingoCard;

    public Player(String name,String id, int balance) {
        this.name = name;
        this.id = id;
        this.balance = balance;
        bingoCard=new ArrayList<Card>();
    }

    public void addBingoCard(Card card) {
        bingoCard.add(card);
    }

   public boolean checkLine(int num){
        for (Card item:bingoCard){
            item.markNumber(num);
        if(item.isLineComplete()){
            System.out.printf("Line complete, congrats %s. You call line with your card %d \n", name, item.getId());
           JOptionPane.showMessageDialog(null, String.format("Line complete, congrats %s. You call line with your card %d", name, item.getId()));
        return true;
            }
        }
        return false;
    }

    public boolean checkBingo(int num) {
        for (Card item : bingoCard) {
            item.markNumber(num);
            if (item.isBingoComplete()) {
                System.out.printf("Bingo! Congrats %s. You called bingo with your card %d \n", name, item.getId());
                JOptionPane.showMessageDialog(null, String.format("Bingo! Congrats %s. You called bingo with your card %d", name, item.getId()));
                return true;
            }
        }
        return false;
    }
}





