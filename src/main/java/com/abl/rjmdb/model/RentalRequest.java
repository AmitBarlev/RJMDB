package com.abl.rjmdb.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class RentalRequest {

    private Long by;
    private String title;
    private LocalDateTime time;
}
