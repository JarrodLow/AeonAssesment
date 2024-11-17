package com.AeonAssesment.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class BorrowingReq {

    @NotNull
    private String borrowerName;
    @NotNull
    private String borrowerEmail;
    @NotNull
    private String ISBN;
    private Integer bookId;
}
