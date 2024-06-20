package com.abl.rjmdb.model;

import lombok.*;

import java.time.LocalDateTime;

@Data
@Generated
@NoArgsConstructor
@AllArgsConstructor
public class RentalStatus {

    private long id;
    private String movieName;
    private LocalDateTime time;
}
