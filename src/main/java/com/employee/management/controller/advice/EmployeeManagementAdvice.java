package com.employee.management.controller.advice;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.employee.management.controller.DepartmentController;
import com.employee.management.exception.DepartmentException;
import com.employee.management.model.ErrorResponseModel;

@ControllerAdvice(basePackageClasses = DepartmentController.class)
public class EmployeeManagementAdvice {

	@ExceptionHandler(DepartmentException.class)
	public ResponseEntity<ErrorResponseModel> handleDepartmentException(final DepartmentException departmentException) {
		departmentException.getErrorCodes().getMessage();
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ErrorResponseModel(
				departmentException.getErrorCodes().getMessage(), departmentException.getErrorCodes().getCode()));
	}

}
