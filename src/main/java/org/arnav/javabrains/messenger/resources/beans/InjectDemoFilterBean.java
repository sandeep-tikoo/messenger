package org.arnav.javabrains.messenger.resources.beans;

import javax.ws.rs.CookieParam;
import javax.ws.rs.HeaderParam;
import javax.ws.rs.MatrixParam;

public class InjectDemoFilterBean {

	private @MatrixParam("name") String matrixName; //Matrix particular query/matrix Param
	private @HeaderParam("XTP-SessionId") String SessionId; // Accessing particular header
	private @CookieParam("JSESSIONID") String JSESSIONID; //accessing particular cookie
	
	public String getMatrixName() {
		return matrixName;
	}
	public void setMatrixName(String matrixName) {
		this.matrixName = matrixName;
	}
	public String getSessionId() {
		return SessionId;
	}
	public void setSessionId(String sessionId) {
		SessionId = sessionId;
	}
	public String getJSESSIONID() {
		return JSESSIONID;
	}
	public void setJSESSIONID(String jSESSIONID) {
		JSESSIONID = jSESSIONID;
	}
	
}
