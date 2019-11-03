package com.sbcourse.agenda.dto;

import lombok.Data;
import javax.validation.constraints.NotEmpty;
import java.sql.Timestamp;

@Data
public class ReservationUpdateRequest {

    @NotEmpty
    private  long productServiceId;

    @NotEmpty(message = "No ha modificado su hora de inicio de la cita")
    private Timestamp startDate;

    @NotEmpty(message = "No ha puesto su email")
    private String email;

    @NotEmpty(message = "No ha puesto su nombre")
    private String fullName;

    @NotEmpty(message = "No ha puesto su teléfono")
    private String cellphone;

}
