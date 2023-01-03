package com.organization.ordermanagementsystem.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;
import org.springframework.stereotype.Component;

import java.util.Date;

@Builder
@Getter
@Setter
@Component
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class CustomerTypeDTO {

    private Long id;
    private String type;
    private Boolean deleted;
    private Date createdAt;
    private Date updatedAt;
}
