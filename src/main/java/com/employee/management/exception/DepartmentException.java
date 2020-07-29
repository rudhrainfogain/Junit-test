package com.employee.management.exception;

import com.employee.management.error.DepartmentErrorCodes;

public class DepartmentException extends RuntimeException {

	private static final long serialVersionUID = 1L;
	private final DepartmentErrorCodes errorCodes;

	public DepartmentException(DepartmentErrorCodes errorCodes) {
		super(errorCodes.getMessage());
		this.errorCodes = errorCodes;
	}

	public DepartmentErrorCodes getErrorCodes() {
		return errorCodes;
	}

}
