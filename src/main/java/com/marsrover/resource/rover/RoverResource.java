package com.marsrover.resource.rover;

import com.marsrover.model.response.SpaceStationResponse;
import com.marsrover.model.rover.RoverDto;
import com.marsrover.model.space.Obstacle;
import com.marsrover.model.space.Space;
import com.marsrover.service.rover.RoverService;

import javax.inject.Inject;
import javax.ws.rs.*;
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
    Space space;


    @POST
    public Response changeValues(RoverDto roverDto) {

        if (!isInputValid(roverDto)) {
            return Response.status(Response.Status.BAD_REQUEST.getStatusCode()).entity("Values required: " +
                    "position : {x, y} , " +
                    "direction: [N, E, S, W]").build();
        }

        Boolean isRoverChanged = roverService.changeValues(roverDto, space);

        if (!isRoverChanged) {
            return Response.status(Response.Status.BAD_REQUEST.getStatusCode()).entity("Error position initialization. There is an obstacle!").build();
        }

        System.out.println(space.getSpaceView());
        SpaceStationResponse response = new SpaceStationResponse(space.getRover(), space.getObstacles());

        return Response.ok(response).build();
    }

    @PUT
    @Path("/move")
    public Response sendCommands(List<String> commands) {

        String responseMessage = null;
        Obstacle obstacle = roverService.moveRover(space, commands);

        if (obstacle != null) {
            responseMessage = "There is an obstacle in position: (" + obstacle.getPosition().getX() + "," + obstacle.getPosition().getY() + ")";
        }

        SpaceStationResponse response = new SpaceStationResponse(responseMessage, space.getRover(), space.getObstacles());
        System.out.println(space.getSpaceView());
        return Response.ok(response).build();

    }

    private boolean isInputValid(RoverDto roverDto) {
        return roverDto.getPosition() != null && roverDto.getPosition().getX() != null && roverDto.getPosition().getY() != null && roverDto.getDirection() != null;
    }
}
