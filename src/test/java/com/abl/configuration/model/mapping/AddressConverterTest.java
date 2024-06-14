package com.abl.configuration.model.mapping;

import com.abl.model.UserSignupInfo;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;


public class AddressConverterTest {

    public static final String EXPECTED = "";
    private final AddressConverter converter = new AddressConverter();

    @Test
    public void convert_sanity_identicalStrings() {
        UserSignupInfo info = new UserSignupInfo(1, "a", "b", "c", "d", "e");

        assertEquals("c, d, e", converter.convert(info));
    }

    @Test
    public void convert_countryIsNull_identicalStrings() {
        UserSignupInfo info = new UserSignupInfo(1, "a", "b", null, "d", "e");

        assertEquals(EXPECTED, converter.convert(info));
    }

    @Test
    public void convert_cityIsNull_identicalStrings() {
        UserSignupInfo info = new UserSignupInfo(1, "a", "b", "c", null, "e");

        assertEquals(EXPECTED, converter.convert(info));
    }

    @Test
    public void convert_addressIsNull_identicalStrings() {
        UserSignupInfo info = new UserSignupInfo(1, "a", "b", "c", "d", null);

        assertEquals(EXPECTED, converter.convert(info));
    }
}
