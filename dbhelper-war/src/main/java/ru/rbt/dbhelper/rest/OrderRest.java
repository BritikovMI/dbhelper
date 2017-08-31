package ru.rbt.dbhelper.rest;

import ru.rbt.dbhelper.jpa.Order;

import javax.ws.rs.*;
import java.util.List;

/**
 * Created by KryukovMV on 23.03.2017.
 */
@Path("/order")
@Consumes("application/json;charset=UTF-8")
@Produces("application/json;charset=UTF-8")
public interface OrderRest {
    @GET
    @Path("/findby/name/{name}/id/{id}")
    List<Order> findByNameAndId(@PathParam("name") String name,
                                @PathParam("id") Long id);

}