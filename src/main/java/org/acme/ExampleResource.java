package org.acme;

import org.jboss.logging.Logger;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

@Path("/hello")
public class ExampleResource {

    static Logger logger = Logger.getLogger(ExampleResource.class);

    @Inject
    AzureService azureService;

    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String hello() throws FileNotFoundException {

        FormData formData = new FormData();
        formData.data = new FileInputStream("dog.jpg");
        formData.fileName = "dogimage";

        try {
            performDocumentUploading2Azure(formData);
        }catch (Exception e){
            logger.errorv(e, "While UPLOAD DOCUMENT FILE TO AZURE: [{0}]", formData.fileName);
            return "Not working";
        }
        logger.info("Done performDocumentUploading fileName : " + formData.fileName);

        return "Hello RESTEasy";
    }

    private void performDocumentUploading2Azure(FormData formData) throws Exception {
        logger.info("Trying to store disposal operation to azure confirmation document with the uuid [" + formData.form_uuid +"] .");
        azureService.uploadObject(formData, formData.form_uuid);
    }
}