package ru.rbt.dbhelper.rest;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;

/**
 * Created by KryukovMV on 23.03.2017.
 */
@Path("/order")
@Produces("text/plain;charset=UTF-8")
public interface OrderRest {
    @GET
    @Path("/findby/name/{name}/id/{id}")
    Response findByNameAndId(@PathParam("name") String name,
                             @PathParam("id") Long id);

}