package com.github.phillipkruger.jaxblib.example;

import com.github.phillipkruger.jaxblib.example.pojo.Membership;
import com.github.phillipkruger.jaxblib.example.util.BadJAXBUtil;
import com.github.phillipkruger.jaxblib.example.util.GoodJAXBUtil;
import com.github.phillipkruger.jaxblib.example.util.JAXBUtil;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;
import javax.ws.rs.GET;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import lombok.extern.java.Log;

//@ApplicationScoped
@Path("/")
@Log
public class SomeEndpoint {
    private Random random = new Random();
    
    @Inject
    private List<Membership> memberships;
    
    @GET
    @Produces("text/plain")
    @Path("/bad/{loop}")
    public Response doBad(@PathParam("loop") int loop){
        String response = "";
        for(int i=0;i<loop;i++){
            response = response + run(new BadJAXBUtil(),getRandomMembership());
            //log.info(response);
        }
        return Response.ok(response).build();
    }
    
    @GET
    @Produces("text/plain")
    @Path("/good/{loop}")
    public Response doGood(@PathParam("loop") int loop){
        String response = "";
        for(int i=0;i<loop;i++){
            response = response + run(new GoodJAXBUtil(),getRandomMembership());
            //log.info(response);
        }
        return Response.ok(response).build();
    }
    
    private String run(JAXBUtil util,Membership membership){
        StringWriter report = new StringWriter();
        
        report.append("\n========================================================================\n");
        report.append("Testing server with " + util.getName() + " util\n\n");
        
        // Marshal
        final long startmarshal = System.currentTimeMillis();
        String xml = util.marshal(membership);
        final long timemarshal = System.currentTimeMillis() - startmarshal;
        
        report.append("Marshal took: " + timemarshal + " ms\n");
        
        // Unmarshal
        final long startunmarshal = System.currentTimeMillis();
        util.unmarshal(xml);
        final long timeunmarshal = System.currentTimeMillis() - startunmarshal;
        report.append("Unmarshal took: " + timeunmarshal + " ms\n\n");
        
        report.append("========================================================================\n");
        
        return report.toString();
    }
    
    private Membership getRandomMembership(){
        int r = random.nextInt(memberships.size());
        return memberships.get(r);
    }
}
