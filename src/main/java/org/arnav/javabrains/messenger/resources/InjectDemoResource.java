package org.arnav.javabrains.messenger.resources;

import javax.ws.rs.BeanParam;
import javax.ws.rs.Consumes;
import javax.ws.rs.CookieParam;
import javax.ws.rs.GET;
import javax.ws.rs.HeaderParam;
import javax.ws.rs.MatrixParam;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.UriInfo;

import org.arnav.javabrains.messenger.resources.beans.InjectDemoFilterBean;

@Path("/injectdemo")
@Consumes(MediaType.TEXT_PLAIN)
@Produces(MediaType.APPLICATION_JSON)
public class InjectDemoResource {

	//If you know what are the header values and Parms
	@GET
	@Path("/annotations")
	public String getParamsUsingAnotations(@MatrixParam("name") String matrixName, //Matrix Param
											@HeaderParam("XTP-SessionId") String SessionId, // Accessing Headers
											@CookieParam("JSESSIONID") String JSESSIONID)	{ //Accesing Cookies
//											@FormParam("CustName") String Custname)	{ // Accessing HTML form fields submitted to rest API :)
		String str =  "Matrix Param Entered was: " + matrixName + " , " 
				+ "XTP-SessionId: " + SessionId + " , " 
				+ "Cookie JSESSIONID: " + JSESSIONID;
		return str;
	}
	
	//If you don't know what are the header values and Parms
	@GET
	@Path("/context")
	//@Context allows us to annotote to UriInfo Type, which is a class and has many methods. 
	public String getParamsUsingContext(@Context UriInfo uriInfo,
										@Context HttpHeaders headers)	{
		String path = uriInfo.getAbsolutePath().toString();
//		String urib = uriInfo.getBaseUri().toString();
//		String urir = uriInfo.getRequestUri().toString();
		String urip = uriInfo.getPath().toString();
		String cookie = headers.getCookies().toString();
		String header = headers.getRequestHeaders().toString();
//		return "Path is: " + path + " , " + "Cookies are: " + cookie + " , " + "Urir is: " + urip;
		return "Headers are:"  + header;
	}
	
	@GET
	@Path("/beanParams")
	public InjectDemoFilterBean getParamsUsingBeanParams(@BeanParam InjectDemoFilterBean dmoFltrBean)	{
//		String Str = dmoFltrBean.getJSESSIONID() + " " + dmoFltrBean.getMatrixName() + " " + dmoFltrBean.getSessionId();
//		return "Captured params using @BeanParam: " + Str;
		return dmoFltrBean;
		//Above I was first returning string bu forming string by concatenating 
		//fields of InjectDemoFilterBean class
		//Later i decided to return Object instead of the string so that i could do Object to JSON conversion
	}
}
