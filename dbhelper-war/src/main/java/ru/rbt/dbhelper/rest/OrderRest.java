package ru.rbt.dbhelper.rest;

import javax.ws.rs.*;
import javax.ws.rs.core.Response;

/**
 * Created by KryukovMV on 23.03.2017.
 */
@Path("/order")
@Produces("text/plain;charset=UTF-8")
public interface OrderRest {
    @GET
    @Path("/findby/")
    Response findByNameAndId(@QueryParam("name") String name,
                             @QueryParam("id") Long id);

}