package client;

import java.io.IOException;
import java.io.InputStream;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpEntity;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;

import model.Game;

public class ParseText 
{
	boolean inGame = false;
	boolean inRowCol = false;
	boolean inRow = false;
	boolean inColumn = false;
	boolean inCounter = false;
	boolean inWinner = false;
	boolean inTheWinner = false;
	boolean inWhosTurn = false;
	boolean inTurn = false;
	
	Game currentRowCol;
	List<Game> currentGameBoard;
	String turn;
	String winner;
	
	public List<Game> doParseGameBoard (String s)
	{
		try
		{
			XmlPullParserFactory factory = XmlPullParserFactory.newInstance();
			XmlPullParser pullParser = factory.newPullParser();
			pullParser.setInput(new StringReader(s));
			processDocument(pullParser);
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		
		return currentGameBoard;
	}
	
	public String doParseWhosTurn (String s)
	{
		try
		{
			XmlPullParserFactory factory = XmlPullParserFactory.newInstance();
			XmlPullParser pullParser = factory.newPullParser();
			pullParser.setInput(new StringReader(s));
			processDocument(pullParser);
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		
		return turn;
	}
	
	public String doParseWinner (String s)
	{
		try
		{
			XmlPullParserFactory factory = XmlPullParserFactory.newInstance();
			XmlPullParser pullParser = factory.newPullParser();
			pullParser.setInput(new StringReader(s));
			processDocument(pullParser);
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		
		return winner;
	}
	
	public void processDocument (XmlPullParser pullParser) throws XmlPullParserException, IOException
	{
		int eventType = pullParser.getEventType();
		
		do
		{
			if (eventType == XmlPullParser.START_DOCUMENT)
			{
				//System.out.println("Start Document");
			}
			else if (eventType == XmlPullParser.END_DOCUMENT)
			{
				//System.out.println("End Document");
			}
			else if (eventType == XmlPullParser.START_TAG)
			{
				processStartElement(pullParser);
			}
			else if (eventType == XmlPullParser.END_TAG)
			{
				processEndElement(pullParser);
			}
			else if (eventType == XmlPullParser.TEXT)
			{
				processText(pullParser);
			}
			
			eventType = pullParser.next();
		} while (eventType != XmlPullParser.END_DOCUMENT);
	}
	
	public void processStartElement (XmlPullParser event)
	{
		String name = event.getName();
		
		if (name.equals("games"))
		{
			inGame = true;
			currentGameBoard = new ArrayList<Game>();
		}
		else if (name.equals("rowcol"))
		{
			inRowCol = true;
			currentRowCol = new Game();
		}
		else if (name.equals("row"))
		{
			inRow = true;
		}
		else if (name.equals("column"))
		{
			inColumn = true;
		}
		else if (name.equals("counter"))
		{
			inCounter = true;
		}
		if (name.equals("winner"))
		{
			inWinner = true;
		}
		else if (name.equals("theWinner"))
		{
			inTheWinner = true;
		}
		if (name.equals("whosTurn"))
		{
			inWhosTurn = true;
		}
		else if (name.equals("turn"))
		{
			inTurn = true;
		}
	}
	
	public void processText (XmlPullParser event) throws XmlPullParserException
	{
		if (inRow)
		{
			String s = event.getText();
			currentRowCol.setRow(Integer.parseInt(s));
		}
		if (inColumn)
		{
			String s = event.getText();
			currentRowCol.setColumn(Integer.parseInt(s));
		}
		if (inCounter)
		{
			String s = event.getText();
			currentRowCol.setCounter(s);
		}
		if (inTheWinner)
		{
			winner = event.getText();
		}
		if (inTurn)
		{
			turn = event.getText();
		}
	}
	
	public void processEndElement (XmlPullParser event)
	{
		String name = event.getName();
		
		if (name.equals("games"))
		{
			inGame = false;
		}
		else if (name.equals("rowcol"))
		{
			inRowCol = false;
			currentGameBoard.add(currentRowCol);
		}
		else if (name.equals("row"))
		{
			inRow = false;
		}
		else if (name.equals("column"))
		{
			inColumn = false;
		}
		else if (name.equals("counter"))
		{
			inCounter = false;
		}
		if (name.equals("winner"))
		{
			inWinner = false;
		}
		else if (name.equals("theWinner"))
		{
			inTheWinner = false;
		}
		if (name.equals("whosTurn"))
		{
			inWhosTurn = false;
		}
		else if (name.equals("turn"))
		{
			inTurn = false;
		}
	}
	
	static String getASCIIContentFromEntity (HttpEntity entity) throws IllegalStateException, IOException
	{
		InputStream in = entity.getContent();
		StringBuffer out = new StringBuffer();
		int n = 1;
		
		while (n > 0)
		{
			byte[] b = new byte[4096];
			n = in.read(b);
			
			if (n > 0)
				out.append(new String(b, 0, n));
		}
		
		return out.toString();
	}
}
