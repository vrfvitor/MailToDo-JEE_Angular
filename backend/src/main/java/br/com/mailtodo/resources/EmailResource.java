package br.com.mailtodo.resources;

import javax.ejb.EJB;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import br.com.mailtodo.mail.EmailSenderService;

@Path("/email")
public class EmailResource {

	@EJB
	private EmailSenderService ess;
	
	@POST
	@Consumes(MediaType.TEXT_PLAIN)
	public Response setNewEmail(String recipient) {
		ess.setRecipient(recipient);
		return Response.ok().build();
	}
	
}

