package server;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public enum GameDAO 
{
	instance;
	
	private Map<Integer, Game> gameMap = new TreeMap<Integer, Game>();
	private Game playersPlay = new Game();
	
	private GameDAO()
	{
		int id = 1;
		
		for (int row=0; row<6; row++)
		{
			for (int col=0; col<9; col++)
			{			
				Game rowcol = new Game();
				rowcol.setRow(row);
				rowcol.setColumn(col);
				rowcol.setCounter(" ");
				
				gameMap.put(id, rowcol);
				id++;
			}
		}
		
		playersPlay.setRow(0);
		playersPlay.setColumn(0);
		playersPlay.setCounter(" ");
	}
	
	public void resetGame()
	{
		for (Game resetBoard : gameMap.values())
		{
			resetBoard.setCounter(" ");
		}
		
		playersPlay.setRow(0);
		playersPlay.setColumn(0);
		playersPlay.setCounter(" ");
	}
	
	public List<Game> getGame()
	{
		List<Game> game = new ArrayList<Game>();
		game.addAll(gameMap.values());
		return game;
	}
	
	public void editGameBoard(int column, String counter)
	{
		int id = 1;
		int previousId = 0;
		int previousRow = 0;
		//int row = 0;
		boolean changed = false;
		
		for (Game insertCounter : gameMap.values()) 
		{		
			if (insertCounter.getColumn() == column && !changed)
			{
				if (insertCounter.getRow() == 5 && " ".equals(insertCounter.getCounter()))
				{
					insertCounter.setCounter(counter);
					changed = true;
					playersPlay.setRow(insertCounter.getRow());
				}
				if (!" ".equals(insertCounter.getCounter()) && !changed)
				{
					Game newCounter = new Game();
					newCounter.setRow(previousRow);
					newCounter.setColumn(insertCounter.getColumn());
					newCounter.setCounter(counter);
					
					gameMap.replace(previousId, newCounter);
					playersPlay.setRow(previousRow);
					changed = true;
				}
				if (" ".equals(insertCounter.getCounter()) && !changed)
				{
					previousId = id;
					previousRow = insertCounter.getRow();
				}
			}
			id++;
		}
		
		playersPlay.setColumn(column);
		playersPlay.setCounter(counter);
	}
	
	public Turns getWhosTurn()
	{
		Turns whichPlayersTurn = new Turns();
		whichPlayersTurn.setTurn("No One Yet");
		
		if (playersPlay.getCounter().equals("X"))
		{
			whichPlayersTurn.setTurn("player2");
		}
		else
		{
			whichPlayersTurn.setTurn("player1");
		}
		
		return whichPlayersTurn;
	}
	
	public Winner getGameWinner()
	{
		Winner theWinner = new Winner();
		int count = 0;
		int y = playersPlay.getColumn();
		String board[][] = new String[6][9];
		
		for (Game gameBoard : gameMap.values())
		{
			board[gameBoard.getRow()][gameBoard.getColumn()] = gameBoard.getCounter();
		}
		
		//Horizontal
		while (y < 9 && board[playersPlay.getRow()][y].equals(playersPlay.getCounter()))
		{
			count++;
			y++;
		}
		y = playersPlay.getColumn() - 1;
		while (y >= 0 && board[playersPlay.getRow()][y].equals(playersPlay.getCounter()))
		{
			count++;
			y--;
		}
		if (count == 5)
		{
			theWinner.setTheWinner(playersPlay.getCounter());
			return theWinner;
		}
		
		//Vertical
		count = 0;
		int x = playersPlay.getRow();
		
		while (x < 6 && board[x][playersPlay.getColumn()].equals(playersPlay.getCounter()))
		{
			count++;
			x++;
		}
		if (count == 5)
		{
			theWinner.setTheWinner(playersPlay.getCounter());
			return theWinner;
		}
		
		//Diagonal
		count = 0;
		x = playersPlay.getRow();
		y = playersPlay.getColumn();
		
		while (x >= 0 && y < 9 && board[x][y].equals(playersPlay.getCounter()))
		{
			count++;
			x--;
			y++;
		}
		x = playersPlay.getRow() + 1;
		y = playersPlay.getColumn() - 1;
		while (x < 6 && y >= 0 && board[x][y].equals(playersPlay.getCounter()))
		{
			count ++;
			x++;
			y--;
		}
		if (count == 5)
		{
			theWinner.setTheWinner(playersPlay.getCounter());
			return theWinner;
		}
		
		//Reverse Diagonal
		count = 0;
		x = playersPlay.getRow();
		y = playersPlay.getColumn();
		
		while (x < 6 && y < 9 && board[x][y].equals(playersPlay.getCounter()))
		{
			count++;
			x++;
			y++;
		}
		x = playersPlay.getRow() - 1;
		y = playersPlay.getColumn() - 1;
		while (x >= 0 && y >= 0 && board[x][y].equals(playersPlay.getCounter()))
		{
			count ++;
			x--;
			y--;
		}
		if (count == 5)
		{
			theWinner.setTheWinner(playersPlay.getCounter());
			return theWinner;
		}
		
		theWinner.setTheWinner("No One Yet");
		return theWinner;
	}
}