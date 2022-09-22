package com.marsrover.resource;

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
    @Path("/create")
    public Response create(RoverDto roverDto) {

        if (!isInputValid(roverDto)) {
            return Response.status(Response.Status.BAD_REQUEST.getStatusCode()).entity("Values required: " +
                    "position : {x, y} , " +
                    "direction: [N, E, S, W]").build();
        }

        Boolean isInit = roverService.create(roverDto, space);

        if (!isInit) {
            return Response.status(Response.Status.BAD_REQUEST.getStatusCode()).entity("Error position initialization. There is an obstacle!").build();
        }

        String spaceView = space.getSpaceView();
        System.out.println(spaceView);
        SpaceStationResponse response = new SpaceStationResponse("Space situation", space.getRover(), space.getObstacles());

        return Response.ok(response).build();
    }

    @PUT
    @Path("/move")
    public Response sendCommands(List<String> commands) {

        String message = "Space situation";
        Obstacle obstacle = roverService.moveRover(space, commands);
        if (obstacle != null) {
            message = "There is an obstacle in position: (" + obstacle.getPosition().getX() + "," + obstacle.getPosition().getY() + ")";
        }

        String spaceView = space.getSpaceView();
        SpaceStationResponse response = new SpaceStationResponse(message, space.getRover(), space.getObstacles());
        System.out.println(spaceView);
        return Response.ok(response).build();

    }

    private boolean isInputValid(RoverDto roverDto) {
        return roverDto.getPosition() != null && roverDto.getPosition().getX() != null && roverDto.getPosition().getY() != null && roverDto.getDirection() != null;
    }
}
