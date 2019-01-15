package model;

public class Player
{
	private String pName;
	private String counter;
	private boolean myTurn = false;
	
	public String getpName() 
	{
		return pName;
	}
	
	public void setpName(String pName)
	{
		this.pName = pName;
	}
	
	public String getCounter() 
	{
		return counter;
	}
	
	public void setCounter(String counter)
	{
		this.counter = counter;
	}
	
	public boolean isMyTurn()
	{
		return myTurn;
	}
	
	public void setMyTurn(boolean myTurn) 
	{
		this.myTurn = myTurn;
	}
}
