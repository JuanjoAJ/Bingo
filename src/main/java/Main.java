import controller.Bingo;

import javax.swing.*;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Bingo bingo=new Bingo();
        Scanner scanner=new Scanner(System.in);
        String name, id;
        int balance, num;
        int option;
        do{

            System.out.println("""
                     Welcome to BINGO |\u001B[32m Bienvenido a Bingo \u001B[0m
                        1. Create player |\u001B[32m Crear jugador \u001B[0m
                        2. Buy card |\u001B[32m Comprar cartón \u001B[0m
                        3. Play Bingo |\u001B[32m Iniciar bingo \u001B[0m
                        4. Exit |\u001B[32m Salir \u001B[0m
                    """);
            option= scanner.nextInt();
            switch (option){
                case 1:
                    System.out.println("Player name: |\u001B[32m Nombre de jugador:\u001B[0m");
                    name= scanner.next();
                    System.out.println("ID:");
                    id= scanner.next();
                    System.out.println("Initial balance: |\u001B[32m Saldo inicial:\u001B[0m");
                    balance= scanner.nextInt();
                    bingo.createPlayer(name, id, balance);
                    break;
                case 2:
                    System.out.println("Please enter your ID |\u001B[32m Por favor, introduce identificación\u001B[0m");
                    id=scanner.next();
                    System.out.println("How many cards do you want to buy? ||\u001B[32m  ¿Cuantos cartones deseas comprar?\u001B[0m");
                    num= scanner.nextInt();
                    bingo.addCard(id, num);
                    break;
                case 3:
                    JOptionPane.showMessageDialog(null, "Start the game! | \u001B[32m  Empieza el juego\u001B[0m");
                    bingo.game();
                    break;
                case 4:
                    System.out.println("Thank you for choosing to Bingo with us");
                    System.out.println("\u001B[32mGracias por elegir jugar al Bingo con nosotros\u001B[0m");
                    break;
                default:
                    System.out.println("Invalid choice \n\u001B[32mElección no valida\u001B[0m");
                    break;

            }
        }while (option!=4);





    }
}
