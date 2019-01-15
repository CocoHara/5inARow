package client;

import java.util.Scanner;

import model.Player;

public class MainGame 
{
	public static void main(String[] args) 
	{
		Player player1 = new Player();
		Player player2 = new Player();
		
		String name = null;
		String input = null;
		int playerInput = 0;
		Scanner scanner = new Scanner(System.in);
		
		mainMenu();
		System.out.print("Welcome ('s': Start, 'q': Quit): ");
		input = scanner.nextLine();
		
		System.out.print("\nPlayer 1 enter your name: ");
		name = scanner.nextLine();
		player1.setpName(name);
		player1.setCounter("X");
		
		System.out.print("\nIs a Player 2 joining (y/n): ");
		input = scanner.nextLine();
		
		if (input.equals("y"))
		{
			System.out.print("\nPlayer 2 enter your name: ");
			name = scanner.nextLine();
			player2.setpName(name);
			player2.setCounter("O");
		}
		else
		{
			System.exit(0);
		}
		
		ResetGame.resetGameBoard();
		
		do
		{
			GetGame.showGameBoard();
			
			String whosTurn = GetWhosTurn.whosTurn();
			if (whosTurn.equals("player1"))
			{
				player2.setMyTurn(false);
				player1.setMyTurn(true);
			}
			else
			{
				player2.setMyTurn(true);
				player1.setMyTurn(false);
			}
			
			if (player1.isMyTurn())
			{
				do
				{
					System.out.print(player1.getpName() + " its your turn, choose a column(0-8): ");
					input = scanner.nextLine();
					
					playerInput = Integer.parseInt(input);
					if (playerInput < 9 && playerInput >=0)
					{
						try 
						{
							PostTurn.postTurn(input, player1.getCounter());
						} 
						catch (Exception e) 
						{
							e.printStackTrace();
							break;
						}
					}
					else
						System.out.println("Invalid input! Try Again!!");
				} while (!(playerInput < 9 && playerInput >=0));
			}
			else
			{
				do
				{
					System.out.print(player2.getpName() + " its your turn, choose a column(0-8): ");
					input = scanner.nextLine();
					
					playerInput = Integer.parseInt(input);
					if (playerInput < 9 && playerInput >=0)
					{
						try 
						{
							PostTurn.postTurn(input, player2.getCounter());
						} 
						catch (Exception e) 
						{
							e.printStackTrace();
							break;
						}
					}
					else
						System.out.println("Invalid input! Try Again!!");
				} while (!(playerInput < 9 && playerInput >=0));
			}
			
			String winner = GetWhoWins.whoWins();
			if (player1.getCounter().equals(winner))
			{
				System.out.println(player1.getpName() + " WINS!!!!");
				GetGame.showGameBoard();
				System.exit(0);
			}
			else if (player2.getCounter().equals(winner))
			{
				System.out.println(player2.getpName() + " WINS!!!!");
				GetGame.showGameBoard();
				System.exit(0);
			}
			else if (input.equals("q"))
			{
				if (player1.isMyTurn())
					System.out.println(player1.getpName() + " Wins, " + player2.getpName() + " quit");
				else
					System.out.println(player2.getpName() + " Wins, " + player1.getpName() + " quit");
			}
			else
				System.out.println(winner + " wins, The match rages on");
		}while(!input.equals("q"));
		scanner.close();
	}
	
	public static void mainMenu()
	{
		System.out.println(" ------------------ ");
		System.out.println("|    5 In A Row    |");
		System.out.println(" ------------------ ");
		System.out.println();
		System.out.println(" -------     ------ ");
		System.out.println("| Start |   | Quit | ");
		System.out.println(" -------     ------ ");
		System.out.println();
	}
}
