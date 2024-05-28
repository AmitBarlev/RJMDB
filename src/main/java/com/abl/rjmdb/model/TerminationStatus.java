package com.abl.rjmdb.model;

import lombok.*;

import java.time.LocalDateTime;

@Data
@Generated
@NoArgsConstructor
@AllArgsConstructor
public class TerminationStatus {

    private long id;
    private long by;
    private LocalDateTime start;
    private LocalDateTime end;
    private String movieName;
}
