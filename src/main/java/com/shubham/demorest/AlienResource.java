package com.shubham.demorest;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("aliens")
public class AlienResource {

//	@GET
//	@Produces(MediaType.APPLICATION_XML)
//	public Alien getAlien() {
//
//		Alien a = new Alien();
//		a.setName("Shubham Singh");
//		a.setPoints(91);
//		return a;
//	}

	AlienRepository alienRepository = new AlienRepository();

	@GET
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML }) // content-negotiation, can produce both, just
																			// define in postman the Accept
	public List<Alien> getAliens() {

		return alienRepository.getAliens();
	}

	@GET
	@Path("alien/{id}")
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML }) // content-negotiation, can produce both, just
																			// define in postman the Accept
	public Alien getAlien(@PathParam("id") int id) {
		return alienRepository.getAlien(id);
	}

	@POST
	@Path("alien")
	@Produces(MediaType.APPLICATION_XML)
	@Consumes({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML }) // content-negotiation, can consume both, will
																			// reject any other format
	public Alien createAlien(Alien a) {
		System.err.println("Create request -> " + a);
		return alienRepository.createAlien(a);
	}

	@PUT
	@Path("alien")
	@Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
	@Consumes({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
	public Alien updateAlien(Alien a) {
		System.out.println("Update request -> " + a);
		if (alienRepository.getAlien(a.getId()) == null) {
			return alienRepository.createAlien(a);
		} else {
			return alienRepository.updateAlien(a);
		}
	}

	@DELETE
	@Path("alien/{id}")
	public Alien deleteAlien(@PathParam("id") int id) {
		Alien a = alienRepository.getAlien(id);
		if (a != null)
			alienRepository.deleteAlien(id);
		return a;
	}

}
