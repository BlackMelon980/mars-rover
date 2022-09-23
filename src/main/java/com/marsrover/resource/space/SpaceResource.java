package com.marsrover.resource.space;

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

        System.out.println(space.getSpaceView());
        SpaceStationResponse response = new SpaceStationResponse(space.getRover(), space.getObstacles());

        return Response.ok(response).build();
    }

    @GET
    public Response getSpace() {

        System.out.println(space.getSpaceView());
        SpaceStationResponse response = new SpaceStationResponse(space.getRover(), space.getObstacles());
        return Response.ok(response).build();

    }

    private boolean isInputValid(SpaceDto spaceDto) {

        return spaceDto.getWidth() != null && spaceDto.getHeight() != null && spaceDto.getObstaclesCount() != null;

    }
}