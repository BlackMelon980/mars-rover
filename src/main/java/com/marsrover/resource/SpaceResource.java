package com.marsrover.resource;

import com.marsrover.model.response.SpaceStationResponse;
import com.marsrover.model.space.Space;
import com.marsrover.model.space.SpaceDto;
import com.marsrover.service.space.SpaceService;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/space")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class SpaceResource {

    @Inject
    SpaceService spaceService;

    @Inject
    Space space;

    @POST
    @Path("/init")
    public Response sendStartingPointAndPosition(SpaceDto spaceDto) {

        if (!isInputValid(spaceDto)) {
            return Response.status(Response.Status.BAD_REQUEST.getStatusCode()).entity("Values required: [ width, height, obstaclesCount ]").build();
        }

        spaceService.initSpaceWithObstacles(space, spaceDto);

        String spaceView = spaceService.getSpaceView(space);
        SpaceStationResponse response = new SpaceStationResponse("Space situation", space.getRover(), space.getObstacles(), spaceView);

        return Response.ok(response).build();
    }

    @GET
    public Response getSpace() {

        String spaceView = spaceService.getSpaceView(space);
        SpaceStationResponse response = new SpaceStationResponse("Space situation", space.getRover(), space.getObstacles(), spaceView);
        return Response.ok(response).build();

    }


    @PUT
    @Path("/clear")
    public Response clearSpace() {

        spaceService.clearSpace(space);
        String spaceView = spaceService.getSpaceView(space);
        SpaceStationResponse response = new SpaceStationResponse("Now the space is clean", space.getRover(), space.getObstacles(), spaceView);
        return Response.ok(response).build();

    }

    private boolean isInputValid(SpaceDto spaceDto) {

        return spaceDto.getWidth() != null && spaceDto.getHeight() != null && spaceDto.getObstaclesCount() != null;

    }
}