package com.marsrover.resource;

import com.marsrover.model.response.SpaceStationResponse;
import com.marsrover.model.rover.RoverDto;
import com.marsrover.model.space.Space;
import com.marsrover.service.rover.RoverService;
import com.marsrover.service.space.SpaceService;

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
    SpaceService spaceService;

    @Inject
    Space space;


    @POST
    @Path("/create")
    public Response create(RoverDto roverDto) {

        if (!isInputValid(roverDto)) {
            return Response.status(Response.Status.BAD_REQUEST.getStatusCode()).entity("Values required: " +
                    "position : {x, y} , " +
                    "direction").build();
        }

        Boolean isInit = roverService.create(roverDto, space);

        if (!isInit) {
            return Response.status(Response.Status.BAD_REQUEST.getStatusCode()).entity("Error position initialization. There is an obstacle!").build();
        }

        String spaceView = spaceService.getSpaceView(space);
        SpaceStationResponse response = new SpaceStationResponse("Space situation", space.getRover(), space.getObstacles(), spaceView);

        return Response.ok(response).build();
    }

    @PUT
    @Path("/move")
    public Response getCommands(List<String> commands) {

        roverService.moveRover(space, commands);
        String spaceView = spaceService.getSpaceView(space);
        SpaceStationResponse response = new SpaceStationResponse("Space situation", space.getRover(), space.getObstacles(), spaceView);

        return Response.ok(response).build();
    }

    private boolean isInputValid(RoverDto roverDto) {
        return roverDto.getPosition() != null && roverDto.getPosition().getX() != null && roverDto.getPosition().getY() != null && roverDto.getDirection() != null;
    }
}
