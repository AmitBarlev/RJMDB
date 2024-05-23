package com.abl.rjmdb.model;

import lombok.Data;
import lombok.Generated;
import lombok.RequiredArgsConstructor;

import java.time.LocalDateTime;

@Data
@Generated
@RequiredArgsConstructor
public class TerminationStatus {

    private long id;
    private long by;
    private LocalDateTime start;
    private LocalDateTime end;
    private String movieName;
    private TerminationMessage message;
}
