package com.organization.ordermanagementsystem.exception;

import com.organization.ordermanagementsystem.exception.errorcode.ErrorCode;
import lombok.Data;

@Data
public class ApplicationException extends RuntimeException {

    private Integer errorCode;
    private String errorMessage;

    public ApplicationException(ErrorCode errorCode) {
        super(errorCode.getErrorMessage());
        this.errorCode = errorCode.getCode();
        this.errorMessage = errorCode.getErrorMessage();
    }

    public ApplicationException(String errorMessage) {
        super(errorMessage);
        this.errorCode = ErrorCode.BAD_REQUEST.getCode();
        this.errorMessage = errorMessage;
    }

    public ApplicationException(ErrorCode errorCode, String errorMessage) {
        super(errorMessage);
        this.errorCode = errorCode.getCode();
        this.errorMessage = errorMessage;
    }

}

