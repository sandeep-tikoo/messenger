package org.arnav.javabrains.messenger;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

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
    @Produces(MediaType.TEXT_HTML)
    public String getIt() {
        return "<html>\r\n" + 
        		"<body>\r\n" + 
        		"    <h2>Jersey RESTful Web Application Response</h2>\r\n" + 
        		"    <p><a href=\"http://localhost:8080/messenger/\">Back to Home</a>\r\n" + 
        		"    <p>Visit <a href=\"http://jersey.java.net\">Project Jersey website</a>\r\n" + 
        		"    for more information on Jersey!\r\n" + 
        		"</body>\r\n" + 
        		"</html>";
    }
}