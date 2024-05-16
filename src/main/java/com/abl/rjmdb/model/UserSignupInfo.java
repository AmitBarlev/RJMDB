package com.abl.rjmdb.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserSignupInfo {

    private String fName;
    private String lName;
    private String country;
    private String city;
    private String address;
}
