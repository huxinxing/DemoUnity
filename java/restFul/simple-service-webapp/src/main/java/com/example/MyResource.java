package com.example;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.ml.hadoop.DeleteHadoopFS;

/**
 * Root resource (exposed at "myresource" path)
 */
@Path("myresource")
public class MyResource {

    /**
     * Method handling HTTP GET requests. The returned object will be sent
     * to the client as "text/plain" media type.
     *
     * @return String that will be returned as a text/plain response.
     */
    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String getIt() {
        return "Got it!";
    }
    
    
    @Path("huxinxing")
    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String getHuxinxing(){
    	return "huxinxing nihao";
    }
    
    
    @Path("/paramter/{username}")
    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String HelloUser(@PathParam("username") String username){
    	return "hello " + username;
    }
    
    @Path("/deteHadoopFile")
    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String deleteHadoopFile(){
    	DeleteHadoopFS dfs = new DeleteHadoopFS();
    	dfs.deleteHadoopfs();
    	return "nihao";
    }
    
    
}
