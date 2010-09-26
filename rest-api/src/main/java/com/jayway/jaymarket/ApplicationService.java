package com.jayway.jaymarket;

import java.io.Closeable;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URISyntaxException;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.apache.commons.io.IOUtil;
import org.grlea.log.SimpleLogger;

import com.jayway.jaymarket.dto.Application;
import com.jayway.jaymarket.dto.ApplicationRepository;
import com.jayway.jaymarket.dto.Applications;
import com.jayway.jaymarket.dto.InMemoryApplicationRepository;
import com.sun.jersey.core.header.ContentDisposition;
import com.sun.jersey.multipart.FormDataBodyPart;
import com.sun.jersey.multipart.FormDataMultiPart;

/**
 */
@Path("/")
public class ApplicationService {
	public static SimpleLogger log = new SimpleLogger(ApplicationService.class);

	// TODO: Should probably be injected by someone who knows spring or so
	private ApplicationRepository repo = new InMemoryApplicationRepository();

	@GET
	@Path("/applications")
	@Produces({ MediaType.TEXT_XML, MediaType.APPLICATION_JSON })
	public Applications fetchThemAll() {
		return repo.getApplications();
	}

	@GET
	@Path("/applications/{appId}/apk")
	@Produces("application/vnd.android.package-archive")
	public Response getApk(@PathParam("appId") String appId) throws URISyntaxException {
		// TODO: How do I get the correct index?
		Application application = repo.getApplication(appId);
		InputStream apkIn = application.getApkFile();
		String contentDisposition = "attachment; filename="
				+ application.getApkFileName();
		return Response.ok(apkIn)
				.header("Content-Disposition", contentDisposition).build();
	}

	// TODO: check that file is an apk file
	// TODO: save filename, url and description in database
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

		OutputStream fileOut = null;
		InputStream fileIn = null;
		try {
			fileOut = new FileOutputStream(fileName);
			fileIn = file.getValueAs(InputStream.class);
			IOUtil.copy(fileIn, fileOut);
			log.debug("Successfully written application " + fileName);

		} catch (FileNotFoundException e) {
			log.error("Could not write to file " + fileName);
			log.errorException(e);
		} catch (IOException e) {
			log.error("Exception when writing to file " + fileName);
			log.errorException(e);
		} finally {
			close(fileOut);
			close(fileIn);
		}
		return "super";
	}

	private void close(Closeable closeable) {
		if (closeable != null) {
			try {
				closeable.close();
			} catch (IOException e) {/* What would you do here? */
			}
		}
	}
}
