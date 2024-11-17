package com.AeonAssesment.model;

import com.AeonAssesment.data.Borrower;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.ser.OffsetDateTimeSerializer;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.OffsetDateTime;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class BorrowerRespDTO  {

    private String name;
    private String email;
    private String status;
    @JsonSerialize(using = OffsetDateTimeSerializer.class)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss.SSSZZZZZ")
    private OffsetDateTime createdTime;

    public static BorrowerRespDTO fromEntityToDTO(Borrower borrower)
    {
        BorrowerRespDTO borrowerRespDTO = new BorrowerRespDTO();
        borrowerRespDTO.setName(borrower.getName());
        borrowerRespDTO.setEmail(borrower.getEmail());
        borrowerRespDTO.setStatus(borrower.getStatus());
        borrowerRespDTO.setCreatedTime(borrower.getCreatedTime());
        return borrowerRespDTO;
    }

}
