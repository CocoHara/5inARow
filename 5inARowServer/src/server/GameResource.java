package server;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;

@Path("/game")
public class GameResource 
{
	@GET
	@Path("reset")
	@Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON, MediaType.TEXT_XML })
	public void resetGame()
	{
		GameDAO.instance.resetGame();
		//return GameDAO.instance.getGame();
	}
	
	@GET
	@Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON, MediaType.TEXT_XML })
	public List<Game> getGame()
	{
		return GameDAO.instance.getGame();
	}
	
	@POST
	@Produces(MediaType.TEXT_HTML)
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	public void postGameChange(@FormParam("column") int column, @FormParam("counter") String counter,
			@Context HttpServletResponse servletResponse) throws IOException
	{
		GameDAO.instance.editGameBoard(column, counter);
	}
	
	@GET
	@Path("/winner")
	@Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON, MediaType.TEXT_XML})
	public Winner whoWins()
	{
		return GameDAO.instance.getGameWinner();
	}
	
	@GET
	@Path("/whosturn")
	@Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON, MediaType.TEXT_XML})
	public Turns whosTurn()
	{
		return GameDAO.instance.getWhosTurn();
	}
}
