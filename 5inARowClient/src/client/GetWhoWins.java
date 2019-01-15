package client;

import java.net.URI;

import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;

public class GetWhoWins 
{
	public static String whoWins()
	{
		try
		{
			CloseableHttpResponse response = null;
			
			try
			{
				URI uri = new URIBuilder().setScheme("http")
						.setHost("localhost")
						.setPort(8080)
						.setPath("/5inARowServer/rest/game/winner")
						.build();
				
				HttpGet httpGet = new HttpGet(uri);
				httpGet.setHeader("Accept", "application/xml");
				
				CloseableHttpClient httpClient = HttpClients.createDefault();
				response = httpClient.execute(httpGet);
				
				HttpEntity entity = response.getEntity();
				String text = ParseText.getASCIIContentFromEntity(entity);
				
				String winner = new ParseText().doParseWinner(text);
				
				System.out.println();
				return winner;
			}
			finally
			{
				response.close();
			}
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		return null;
	}
}
