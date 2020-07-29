package com.employee.management.error;

public enum DepartmentErrorCodes  {
    DEPARTMENT_NULL_EXCEPTION("DEPARTMENT.NULL.EXCEPTION","Department cann't be null"),
    DEPARTMENT_ID_EXCEPTION("DEPARTMENT.ID.EXCEPTION","Department id can't be negative"),
    DEPARTMENT_NAME_EXCEPTION("DEPARTMENT.NAME.EXCEPTION","Department name can't be empty");

    private String message;
    private String code;

    private DepartmentErrorCodes(String code, String message) {
        this.code = code;
        this.message = message;
    }

	public String getMessage() {
		return message;
	}

	public String getCode() {
		return code;
	}
    

   
}
