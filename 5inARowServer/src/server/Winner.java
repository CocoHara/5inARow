package server;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Winner 
{
	private String theWinner;

	public String getTheWinner() 
	{
		return theWinner;
	}

	public void setTheWinner(String theWinner) 
	{
		this.theWinner = theWinner;
	}
}
