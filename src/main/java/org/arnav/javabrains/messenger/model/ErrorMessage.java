package org.arnav.javabrains.messenger.model;

import java.util.Date;
//import javax.ws.rs.core.Context;
//import javax.ws.rs.core.UriInfo;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class ErrorMessage {
	
	private String errorMessage;
	private int errorCode;
	private String documentation;
	private Date errorDateTime;
	private String locationWas;
//	private @Context("locationWas") String locationWas;
//	private @QueryParam("size") int size;

	//no arg constructor
	public ErrorMessage() {

	}
	
//	public ErrorMessage(String errorMessage, int errorCode, String documentation, @Context UriInfo uriInfo) {
	public ErrorMessage(String errorMessage, int errorCode, String documentation, String locationWas) {
		super();
		this.errorMessage = errorMessage;
		this.errorCode = errorCode;
		this.documentation = documentation;
		this.errorDateTime = new Date();
		this.locationWas = locationWas;
	}
		
	public String getErrorMessage() {
		return errorMessage;
	}
	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}
	public int getErrorCode() {
		return errorCode;
	}
	public void setErrorCode(int errorCode) {
		this.errorCode = errorCode;
	}
	public String getDocumentation() {
		return documentation;
	}
	public void setDocumentation(String documentation) {
		this.documentation = documentation;
	}
	public Date getErrorDateTime() {
		return errorDateTime;
	}
	public void setErrorDateTime(Date errorDateTime) {
		this.errorDateTime = errorDateTime;
	}
	public String getlocationWas() {
		return locationWas;
	}

	public void setlocationWas(String locationWas) {
		this.locationWas = locationWas;
	}

	
}
