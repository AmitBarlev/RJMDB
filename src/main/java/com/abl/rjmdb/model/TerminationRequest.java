package com.abl.rjmdb.model;

import lombok.*;

import java.security.PrivateKey;
import java.time.LocalDateTime;

@Data
@Generated
@NoArgsConstructor
@AllArgsConstructor
public class TerminationRequest {

    private long id;
    private LocalDateTime time;
}
