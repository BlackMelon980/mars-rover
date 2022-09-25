package com.marsrover.resource.planet;

import com.marsrover.model.planet.Planet;
import com.marsrover.model.planet.PlanetDto;
import com.marsrover.model.planet.PlanetInfoResponse;
import com.marsrover.model.position.Position;
import com.marsrover.service.planet.PlanetService;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/planet")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class PlanetResource {

    @Inject
    PlanetService planetService;

    @Inject
    Planet planet;

    @POST
    @Path("/init")
    public Response sendStartingPointAndPosition(PlanetDto planetDto) {

        if (!isInputValid(planetDto)) {
            return Response.status(Response.Status.BAD_REQUEST.getStatusCode()).entity("Values required: [ width, height, obstaclesCount ]").build();
        }

        planetService.initPlanetWithObstacles(planet, planetDto);

        System.out.println(planet.getPlanetView());
        PlanetInfoResponse response = new PlanetInfoResponse(planet);

        return Response.ok(response).build();
    }

    @GET
    public Response getPlanet() {

        System.out.println(planet.getPlanetView());
        PlanetInfoResponse response = new PlanetInfoResponse(planet);
        return Response.ok(response).build();

    }

    @POST
    @Path("/obstacle")
    public Response createObstacle(Position position) {

        if (position.getX() == null || position.getY() == null) {
            return Response.status(Response.Status.BAD_REQUEST.getStatusCode()).entity("Values required: [ x, y ]").build();
        }
        Boolean obstacleWasAdded = planetService.addObstacle(planet, position);

        if (!obstacleWasAdded) {
            return Response.status(Response.Status.BAD_REQUEST.getStatusCode()).entity("Create obstacle error: position is not available.").build();
        }

        System.out.println(planet.getPlanetView());
        PlanetInfoResponse response = new PlanetInfoResponse(planet);

        return Response.ok(response).build();

    }

    @DELETE
    @Path("/obstacle")
    public Response deleteObstacle(Position position) {

        if (position.getX() == null || position.getY() == null) {
            return Response.status(Response.Status.BAD_REQUEST.getStatusCode()).entity("Values required: [ x, y ]").build();
        }
        Boolean obstacleWasDeleted = planetService.deleteObstacle(planet, position);

        if (!obstacleWasDeleted) {
            return Response.status(Response.Status.BAD_REQUEST.getStatusCode()).entity("Delete obstacle error: obstacle does not exist.").build();
        }

        System.out.println(planet.getPlanetView());
        PlanetInfoResponse response = new PlanetInfoResponse(planet);

        return Response.ok(response).build();

    }

    private boolean isInputValid(PlanetDto planetDto) {

        return planetDto.getWidth() != null && planetDto.getHeight() != null && planetDto.getObstaclesCount() != null;

    }
}