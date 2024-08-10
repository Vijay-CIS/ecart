package com.demo.ecart.exception;

import java.util.Map;

public class ValidationResponse {
	private Map<String, String> validationMsg;
	private String errorCode;
	private String errorMessage;

	public Map<String, String> getValidationMsg() {
		return validationMsg;
	}

	public void setValidationMsg(Map<String, String> validationMsg) {
		this.validationMsg = validationMsg;
	}

	public String getErrorCode() {
		return errorCode;
	}

	public void setErrorCode(String errorCode) {
		this.errorCode = errorCode;
	}

	public String getErrorMessage() {
		return errorMessage;
	}

	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}

	public ValidationResponse(Map<String, String> validationMsg, String errorCode, String errorMessage) {
		super();
		this.validationMsg = validationMsg;
		this.errorCode = errorCode;
		this.errorMessage = errorMessage;
	}

	public ValidationResponse() {
		super();
		// TODO Auto-generated constructor stub
	}

}
