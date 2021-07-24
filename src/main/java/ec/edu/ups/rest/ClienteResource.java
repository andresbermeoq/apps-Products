package ec.edu.ups.rest;

import java.util.List;

import javax.ejb.EJB;
import javax.enterprise.inject.New;
import javax.json.bind.Jsonb;
import javax.json.bind.JsonbBuilder;
import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import ec.edu.ups.ejb.UserFacade;
import ec.edu.ups.entidad.Usuario;

@Path("/cliente/")
public class ClienteResource {
	
	@EJB
	private UserFacade userFacade;
	
	private Usuario usuario;
	
	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}
	
	@POST
	@Path("/login/{email}/{pass}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response loginCliente(@PathParam("email") String email, @PathParam("pass") String password) {
		Usuario usuario = new Usuario();
		usuario = userFacade.inicioUsuario(email, password);
		
		System.out.println(usuario);
		
		if (usuario != null) {
			if (usuario.getRole().equals("Cliente") && usuario.getEstado().equals("Activo")) {
				return Response.ok(usuario).build();
			} else {
				return Response.status(Response.Status.NO_CONTENT).build();
			}
		} else {
			return Response.status(Response.Status.NOT_FOUND).build();
		}
	}
	
	
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response getUsuarios() {
		Jsonb jsonb = JsonbBuilder.create();
		List<Usuario> usuarios = userFacade.findAll();
		System.out.println(usuarios.toString());
		return Response.ok(jsonb.toJson(usuarios))
				.header("Access-Control-Allow-Origin", "*")
				.header("Access-Control-Allow-Headers", "origin, content-type, accept, authorization")
				.header("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE").build();
	}

}
