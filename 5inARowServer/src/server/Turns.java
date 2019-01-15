package server;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "whosTurn")

public class Turns 
{
	private String turn;

	public String getTurn() 
	{
		return turn;
	}

	public void setTurn(String turn) 
	{
		this.turn = turn;
	}
}
