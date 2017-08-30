package ru.rbt.dbhelper.rest;

import ru.rbt.dbhelper.jpa.Order;

import javax.ws.rs.*;
import java.util.List;

import static ru.rbt.dbhelper.rest.RestApiContextNames.API_ORDER;
import static ru.rbt.dbhelper.rest.RestApiMediaType.JSON_UTF8;
import static ru.rbt.dbhelper.rest.RestApiMediaType.TEXT_UTF8;
import static ru.rbt.dbhelper.rest.RestApiPathNames.PATH_ECHO;
import static ru.rbt.dbhelper.rest.RestApiPathNames.PATH_SEARCH;

/**
 * Created by KryukovMV on 23.03.2017.
 */
@Path(API_ORDER)
@Consumes(JSON_UTF8)
@Produces(JSON_UTF8)
public interface OrderRest {

    @GET
    @Path(PATH_ECHO)
    @Produces(TEXT_UTF8)
    String echo();

    @GET
    @Path("/name/{id}/{name}")
    Order findByAliasOrName(@PathParam("id") Long id,
                              @PathParam("name") String name);

}
