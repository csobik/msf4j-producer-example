package com.mallgroup.producer.resources;

import com.mallgroup.producer.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.Collections;
import java.util.Map;

@Component
@Path("/products")
@Produces({ MediaType.APPLICATION_JSON })
public class ProductResource {

    @Autowired
    private ProductService productService;

    @GET
    @Path("/")
    public Response all() {
        Map map = Collections.singletonMap("products", productService.findAll());
        return Response.ok()
                .entity(map)
                .build();
    }

    @GET
    @Path("/{id}")
    public Response product(@PathParam("id") int id) {
        return Response.ok()
                .entity(productService.find(id))
                .build();
    }

}
