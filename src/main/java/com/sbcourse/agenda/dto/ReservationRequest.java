package com.sbcourse.agenda.dto;

import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.sql.Timestamp;

@Data
public class ReservationRequest {

    @NotNull
    private Long productServiceId;

    @NotNull
    private Timestamp startDate;

    @NotEmpty
    private String email;

    @NotEmpty
    private String fullName;

    @NotEmpty
    private String cellphone;

}
