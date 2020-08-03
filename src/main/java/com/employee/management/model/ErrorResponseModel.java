package com.employee.management.model;

public class ErrorResponseModel {

	private String errorMesaage;
	private String errorCode;

	public ErrorResponseModel() {
    }
	
    public ErrorResponseModel(String errorMesaage, String errorCode) {
		this.errorMesaage = errorMesaage;
		this.errorCode = errorCode;
	}

	public String getErrorMesaage() {
		return errorMesaage;
	}

	public void setErrorMesaage(String errorMesaage) {
		this.errorMesaage = errorMesaage;
	}

	public String getErrorCode() {
		return errorCode;
	}

	public void setErrorCode(String errorCode) {
		this.errorCode = errorCode;
	}

}
