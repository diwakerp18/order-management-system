package com.organization.ordermanagementsystem.exception.handler;

import com.organization.ordermanagementsystem.exception.OrderManagementServiceException;
import com.organization.record.helper.response.ApiResponse;
import io.swagger.codegen.v3.service.exception.BadRequestException;
import lombok.extern.slf4j.Slf4j;
import com.organization.ordermanagementsystem.exception.ApplicationException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import static org.eclipse.jetty.http.HttpStatus.Code.BAD_REQUEST;
import static org.eclipse.jetty.http.HttpStatus.Code.INTERNAL_SERVER_ERROR;

@ControllerAdvice(basePackages = {"com.organization"})
@Slf4j
public class OrderManagementServiceGlobalExceptionHandler {

    @ExceptionHandler(ApplicationException.class)
    @ResponseStatus(code = HttpStatus.BAD_REQUEST)
    public @ResponseBody
    ApiResponse handleException(ApplicationException ex) {
        ApiResponse apiOutput = getApiResponse(ex);
        log.error("Exception occurred", ex);
        return apiOutput;
    }

    @ExceptionHandler(BadRequestException.class)
    @ResponseStatus(code = HttpStatus.BAD_REQUEST)
    public @ResponseBody
    ApiResponse handleException(BadRequestException ex) {
        String errorMsg = ex.getLocalizedMessage() == null ? "Error" : ex.getLocalizedMessage();
        ApiResponse apiOutput = new ApiResponse();
        apiOutput.setSuccess(false);
        apiOutput.setErrorCode(BAD_REQUEST.getCode());
        apiOutput.setErrorMessage(errorMsg);
        log.error("Exception occurred", ex);
        return apiOutput;
    }

    @ExceptionHandler(Exception.class)
    @ResponseStatus(code = HttpStatus.INTERNAL_SERVER_ERROR)
    public @ResponseBody
    ApiResponse handleException(Exception ex) {
        String errorMsg = INTERNAL_SERVER_ERROR.getMessage() +
                ":" + (ex.getLocalizedMessage() == null ? "Error" : ex.getLocalizedMessage());
        ApiResponse apiOutput = new ApiResponse();
        apiOutput.setSuccess(false);
        apiOutput.setErrorCode(INTERNAL_SERVER_ERROR.getCode());
        apiOutput.setErrorMessage(errorMsg);
        log.error("Exception occurred", ex);
        return apiOutput;
    }

    private ApiResponse getApiResponse(ApplicationException ex) {
        ApiResponse apiOutput = new ApiResponse();
        apiOutput.setSuccess(false);
        Integer errorCode = ex.getErrorCode();
        if (errorCode != null) {
            apiOutput.setErrorCode(ex.getErrorCode());
        }
        apiOutput.setErrorMessage(ex.getErrorMessage());
        return apiOutput;
    }

    @ExceptionHandler(OrderManagementServiceException.class)
    @ResponseStatus(code = HttpStatus.BAD_REQUEST)
    public @ResponseBody
    ApiResponse handleException(OrderManagementServiceException ex) {
        String errorMsg = ex.getLocalizedMessage() == null ? "Error" : ex.getLocalizedMessage();
        ApiResponse apiOutput = new ApiResponse();
        apiOutput.setSuccess(false);
        apiOutput.setErrorCode(BAD_REQUEST.getCode());
        apiOutput.setErrorMessage(errorMsg);
        log.error("Exception occurred", ex);
        return apiOutput;
    }

}

