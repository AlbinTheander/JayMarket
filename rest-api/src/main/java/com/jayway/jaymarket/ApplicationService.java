package com.jayway.jaymarket;

import java.io.InputStream;
import java.net.URISyntaxException;

import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

import com.jayway.jaymarket.adapter.ApplicationModelToDTOAdapter;
import com.jayway.jaymarket.dto.ApplicationsDTO;
import com.jayway.jaymarket.repository.ApplicationRepository;
import com.jayway.jaymarket.repository.InMemoryApplicationRepository;
import com.jayway.jaymarket.storage.ApplicationStorage;

import org.grlea.log.SimpleLogger;

import com.jayway.jaymarket.model.Application;
import com.jayway.jaymarket.model.Applications;
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
	public ApplicationsDTO fetchThemAll(@Context UriInfo info) {
		Applications applicatons = repo.getApplications();
        return ApplicationModelToDTOAdapter.convertApplications(applicatons, info.getBaseUri().toString());
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
        ApplicationStorage storage = new ApplicationStorage();
		return Boolean.toString(storage.storeApplication(formData));
	}
}
