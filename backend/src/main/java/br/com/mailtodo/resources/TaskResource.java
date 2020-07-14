package br.com.mailtodo.resources;

import java.net.URI;
import java.util.Optional;

import javax.ejb.EJB;
import javax.transaction.Transactional;
import javax.validation.Valid;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.core.UriInfo;

import org.eclipse.microprofile.openapi.annotations.parameters.RequestBody;

import br.com.mailtodo.dao.TaskDao;
import br.com.mailtodo.dto.TaskForm;
import br.com.mailtodo.entity.Task;

@Path("tasks")
public class TaskResource {

	@EJB
	private TaskDao dao;

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response index() {
		return Response.ok(dao.findAll()).build();
	}

	@POST
	@Transactional
	@Consumes(MediaType.APPLICATION_JSON)
	public Response create(@Valid @RequestBody TaskForm form, @Context UriInfo uriInfo) {
		System.out.println(form);
		System.out.println("TASK/POST");
		Task task = new Task();
		form.transferDataTo(task);
		dao.save(task);
		URI uri = uriInfo.getAbsolutePathBuilder().path(Integer.toString(task.getId())).build();
		return Response.created(uri).build();
	}

	@PUT
	@Path("/{id}")
	@Transactional
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response update(@PathParam("id") Integer id, @Valid @RequestBody TaskForm form) {
		Optional<Task> optional = dao.findById(id);
		if (optional.isPresent()) {
			Task task = optional.get();
			form.transferDataTo(task);
			dao.save(task);
			return Response.ok(task).build();
		}
		return Response.status(Status.NOT_FOUND).build();
	}

	@PUT
	@Path("/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response show(@PathParam("id") Integer id, @QueryParam("done") boolean done) {
		Optional<Task> optional = dao.findById(id);
		if (optional.isPresent()) {
			Task task = optional.get();
			task.setDone(done);
			return Response.ok(task).build();
		}
		return Response.status(Status.NOT_FOUND).build();
	}

	@DELETE
	@Path("/{id}")
	@Transactional
	public Response delete(@PathParam("id") Integer id) {
		System.out.println("delete");
		Optional<Task> optional = dao.findById(id);
		if (optional.isPresent()) {
			dao.delete(optional.get());
			return Response.noContent().build();
		}
		return Response.status(Status.NOT_FOUND).build();
	}

}
