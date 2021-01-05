package org.acme;

import java.io.InputStream;

public class FormData {

//    @FormParam("file")
//    @PartType(MediaType.APPLICATION_OCTET_STREAM)
    public InputStream data;

//    @FormParam("name")
//    @PartType(MediaType.TEXT_PLAIN)
    public String fileName;

//    @FormParam("mime_type")
//    @PartType(MediaType.TEXT_PLAIN)
    public String mime_type;

//    @FormParam("form_uuid")
//    @PartType(MediaType.TEXT_PLAIN)
    public String form_uuid;

}