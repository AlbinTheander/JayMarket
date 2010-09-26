package com.jayway.jaymarket.storage;

import com.jayway.jaymarket.repository.MongoApplicationRepository;
import com.sun.jersey.core.header.ContentDisposition;
import com.sun.jersey.multipart.FormDataBodyPart;
import com.sun.jersey.multipart.FormDataMultiPart;
import org.apache.commons.io.IOUtils;
import org.grlea.log.SimpleLogger;

import java.io.*;

/**
 * Created by IntelliJ IDEA.
 * User: karinhofbauer
 * Date: Sep 26, 2010
 * Time: 10:43:09 PM
 */
public class ApplicationStorage {
    public static SimpleLogger log = new SimpleLogger(ApplicationStorage.class);

    public boolean storeApplication(FormDataMultiPart formData){
        FormDataBodyPart namePart = formData.getField("name");
		FormDataBodyPart descriptionPart = formData.getField("description");
		FormDataBodyPart file = formData.getField("file");

        ContentDisposition disposition = file.getContentDisposition();
		String fileName = disposition.getFileName();

		String title = namePart.getValueAs(String.class);
		String description = descriptionPart.getValueAs(String.class);

        MongoApplicationRepository repository = new MongoApplicationRepository();
        if(writeFile(file, fileName)){
            return repository.storeApplication(title, description, fileName);
        }

        return false;

    }

    private boolean writeFile(FormDataBodyPart file, String fileName){

        
        OutputStream fileOut = null;
		InputStream fileIn = null;
		try {
			fileOut = new FileOutputStream(fileName);
			fileIn = file.getValueAs(InputStream.class);
			IOUtils.copy(fileIn, fileOut);
			log.debug("Successfully written application " + fileName);

		} catch (FileNotFoundException e) {
			log.error("Could not write to file " + fileName);
			log.errorException(e);
            return false;
		} catch (IOException e) {
			log.error("Exception when writing to file " + fileName);
			log.errorException(e);
            return false;
		} finally {
            IOUtils.closeQuietly(fileOut);
			IOUtils.closeQuietly(fileIn);
		}
        return true;
    }
}
