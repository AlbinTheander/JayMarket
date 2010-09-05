
package com.jayway.jaymarket;

import com.jayway.jaymarket.dto.Application;
import com.jayway.jaymarket.dto.Applications;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import java.util.ArrayList;
import java.util.List;

/** Example resource class hosted at the URI path "/myresource"
 */
@Path("/")
public class ApplicationService {
    
    /** Method processing HTTP GET requests, producing "text/plain" MIME media
     * type.
     * @return String that will be send back as a response of type "text/plain".
     */
    @GET
    @Path("/applications")
    @Produces("text/xml")
    public Applications fetchThemAll() {
        List<Application> applications = new ArrayList<Application>();
        
        applications.add(new Application("Hello App"));
        applications.add(new Application("Nice to be here App"));
        applications.add(new Application("ByeBye  App"));

        return new Applications(applications);
    }
}
