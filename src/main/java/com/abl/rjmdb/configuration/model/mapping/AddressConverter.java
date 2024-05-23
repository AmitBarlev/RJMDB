package com.abl.rjmdb.configuration.model.mapping;

import com.abl.rjmdb.model.UserSignupInfo;
import org.modelmapper.AbstractConverter;

import java.util.Objects;

public class AddressConverter extends AbstractConverter<UserSignupInfo, String> {
    @Override
    protected String convert(UserSignupInfo userSignupInfo) {
        if (isValid(userSignupInfo))
            return "";

        return String.format("%s, %s, %s",
                userSignupInfo.getCountry(),
                userSignupInfo.getCity(),
                userSignupInfo.getAddress());
    }

    private boolean isValid(UserSignupInfo userSignupInfo) {
        return Objects.isNull(userSignupInfo.getCountry()) ||
                Objects.isNull(userSignupInfo.getCity()) ||
                Objects.isNull(userSignupInfo.getAddress());
    }
}
