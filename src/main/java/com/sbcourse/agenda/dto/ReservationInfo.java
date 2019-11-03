package com.sbcourse.agenda.dto;

import com.sbcourse.agenda.persistence.model.ProductService;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class ReservationInfo {

    private Long id;
    private String uuid;
    private LocalDateTime createdAt;
    private LocalDateTime modifiedAt;

    private ProductService productService;

    private LocalDateTime startDate;
    private LocalDateTime endDate;

    private String email;
    private String fullName;
    private String cellphone;

}
