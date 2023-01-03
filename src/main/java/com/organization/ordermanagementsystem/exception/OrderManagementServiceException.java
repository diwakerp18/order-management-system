package com.organization.ordermanagementsystem.exception;

import com.organization.ordermanagementsystem.exception.errorcode.ErrorCode;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class OrderManagementServiceException extends RuntimeException {

    private Integer errorCode;
    private String errorMessage;

    public OrderManagementServiceException(ErrorCode errorCode) {
        super(errorCode.getErrorMessage());
        this.errorCode = errorCode.getCode();
        this.errorMessage = errorCode.getErrorMessage();
    }


    public OrderManagementServiceException(ErrorCode errorCode, String errorMessage) {
        super(errorMessage);
        this.errorCode = errorCode.getCode();
        this.errorMessage = errorMessage;
    }

    public OrderManagementServiceException(String errorMessage) {
        super(errorMessage);
        this.errorCode = ErrorCode.BAD_REQUEST.getCode();
        this.errorMessage = errorMessage;
    }

    public OrderManagementServiceException(Integer customErrorCode, String errorMessage) {
        super(errorMessage);
        this.errorCode = customErrorCode;
        this.errorMessage = errorMessage;
    }
}


