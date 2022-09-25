package com.marsrover.resource.rover;

import com.marsrover.model.obstacle.Obstacle;
import com.marsrover.model.planet.Planet;
import com.marsrover.model.planet.PlanetInfoResponse;
import com.marsrover.model.rover.RoverDto;
import com.marsrover.service.rover.RoverService;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("/rover")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class RoverResource {

    @Inject
    RoverService roverService;

    @Inject
    Planet planet;


    @PUT
    public Response updateRover(RoverDto roverDto) {

        if (!isInputValid(roverDto)) {
            return Response.status(Response.Status.BAD_REQUEST.getStatusCode()).entity("Values required: " +
                    "position : {x, y} , " +
                    "direction: [N, E, S, W]").build();
        }

        Boolean isRoverChanged = roverService.updateRover(roverDto, planet);

        if (!isRoverChanged) {
            return Response.status(Response.Status.BAD_REQUEST.getStatusCode()).entity("Update Error: There is an obstacle!").build();
        }

        System.out.println(planet.getPlanetView());
        PlanetInfoResponse response = new PlanetInfoResponse(planet);

        return Response.ok(response).build();
    }

    @PUT
    @Path("/move")
    public Response sendCommands(List<String> commands) {

        String responseMessage = null;
        Obstacle obstacle = roverService.moveRover(planet, commands);

        if (obstacle != null) {
            responseMessage = "There is an obstacle in position: (" + obstacle.getPosition().getX() + "," + obstacle.getPosition().getY() + ")";
        }

        PlanetInfoResponse response = new PlanetInfoResponse(responseMessage, planet);
        System.out.println(planet.getPlanetView());
        return Response.ok(response).build();

    }

    private boolean isInputValid(RoverDto roverDto) {
        return roverDto.getPosition() != null && roverDto.getPosition().getX() != null && roverDto.getPosition().getY() != null && roverDto.getDirection() != null;
    }
}
