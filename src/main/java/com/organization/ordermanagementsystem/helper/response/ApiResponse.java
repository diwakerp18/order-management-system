package com.organization.record.helper.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.organization.record.helper.consts.HeaderConstants;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.slf4j.MDC;
import org.springframework.http.HttpStatus;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@JsonIgnoreProperties(ignoreUnknown = true)
public class ApiResponse<T> {
    private boolean success = true;
    private Integer code;
    private Integer errorCode;
    private String errorMessage;
    private String requestId = MDC.get(HeaderConstants.ORGANIZATION_REQUEST_ID);
    private T data;

    public ApiResponse(HttpStatus ok, T data, String successfully_updated_snOrgProfile) {
        this.data = data;
    }

    @Override
    public Object clone() throws CloneNotSupportedException {
        return super.clone();
    }

    @Override
    public String toString() {
        return new com.google.gson.Gson().toJson(this);
    }
}

