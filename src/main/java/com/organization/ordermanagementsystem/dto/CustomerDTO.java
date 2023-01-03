package com.organization.ordermanagementsystem.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.util.Date;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Component;

@Builder
@Getter
@Setter
@Component
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class CustomerDTO {

  private Long id;
  private String name;
  private String email;
  private String contactNumber;
  private String customerType;
  private Boolean deleted;
  private Date createdAt;
  private Date updatedAt;

}
