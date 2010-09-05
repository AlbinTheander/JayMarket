
package com.jayway.jaymarket;

import com.jayway.jaymarket.dto.Application;
import com.jayway.jaymarket.dto.Applications;
import com.sun.jersey.core.header.ContentDisposition;
import com.sun.jersey.multipart.FormDataBodyPart;
import com.sun.jersey.multipart.FormDataMultiPart;
import org.grlea.log.SimpleLogger;

import javax.ws.rs.*;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 */
@Path("/")
public class ApplicationService {
    public static SimpleLogger log = new SimpleLogger(ApplicationService.class);

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

    //TODO: check that file is an apk file
    //TODO: save filename, url and description in database
    @POST
    @Path("/upload")
    @Consumes("multipart/form-data")
    public String upload(FormDataMultiPart formData) {
        FormDataBodyPart namePart = formData.getField("name");
        FormDataBodyPart descriptionPart = formData.getField("description");
        FormDataBodyPart file = formData.getField("file");

        ContentDisposition disposition = file.getContentDisposition();
        String fileName = disposition.getFileName();


        String title = namePart.getValueAs(String.class);
        String description = descriptionPart.getValueAs(String.class);
        
        BufferedInputStream bufferedInput = new BufferedInputStream(file.getValueAs(InputStream.class));
        BufferedOutputStream bufferedOut = null;

        try {
            bufferedOut = new BufferedOutputStream(new FileOutputStream(fileName),1024);
            byte[] data = new byte[1024];
            int counter;

            while ((counter = bufferedInput.read(data)) != -1) {
                bufferedOut.write(data,0,counter);
            }
            log.debug("Successfully written application "+fileName);

        } catch (FileNotFoundException e) {
            log.error("Could not write to file "+fileName);
            log.errorException(e);
        } catch (IOException e) {
            log.error("Exception when writing to file "+fileName);
            log.errorException(e);
        } finally {
            close(bufferedOut);
        }


        return "super";
    }

    private void close(Closeable closeable) {
        if(closeable != null) {
            try {
                closeable.close();
            } catch (IOException e) {/* What would you do here? */}
        }
    }
}
