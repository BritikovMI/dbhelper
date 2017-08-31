package ru.rbt.dbhelper.rest;

import ru.rbt.dbhelper.jpa.Order;

import javax.ws.rs.*;

/**
 * Created by KryukovMV on 23.03.2017.
 */
@Path("/order")
@Consumes("application/json;charset=UTF-8")
@Produces("application/json;charset=UTF-8")
public interface OrderRest {

    @GET
    @Path("/echo")
    @Produces("text/plain;charset=UTF-8")
    String echo();

    @GET
    @Path("/name/{id}/{name}")
    Order findByAliasOrName(@PathParam("id") Long id,
                            @PathParam("name") String name);

}