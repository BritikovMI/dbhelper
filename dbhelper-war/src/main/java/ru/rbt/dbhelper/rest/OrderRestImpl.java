package ru.rbt.dbhelper.rest;

import ru.rbt.dbhelper.other.DaoManager;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;
import java.util.List;

/**
 * Created by KryukovMV on 23.03.2017.
 */
@Stateless(name="OrderRestImpl")
@Path("/order")
@Produces({ "text/plain;charset=UTF-8" })
public class OrderRestImpl implements OrderRest {

    @Inject
    private DaoManager daoManager;

    @Override
    public Response findByNameAndId(String name, Long id) {
//        StringBuilder sb = new StringBuilder();
//        sb.append("Hello, the name is: " + name + " Your table \n\n");
//        List<String> result = daoManager.handleRequest(name, id);
//        for (String s : result) {
//            sb.append(s).append('\n');
//        }


        Response theBestResponseIveEverMade = Response.ok("new").build();
        return theBestResponseIveEverMade;
    }
}