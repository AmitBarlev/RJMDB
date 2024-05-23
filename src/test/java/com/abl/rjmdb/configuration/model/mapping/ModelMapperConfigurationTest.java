package com.abl.rjmdb.configuration.model.mapping;

import com.abl.rjmdb.model.RentalRequest;
import com.abl.rjmdb.model.RentalStatus;
import com.abl.rjmdb.model.User;
import com.abl.rjmdb.model.UserSignupInfo;
import com.abl.rjmdb.model.jooq.tables.records.RentalsRecord;
import com.abl.rjmdb.model.jooq.tables.records.UsersRecord;
import org.junit.jupiter.api.Test;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.NameTokenizers;
import org.modelmapper.jooq.RecordValueReader;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

public class ModelMapperConfigurationTest {

    private final ModelMapperConfiguration modelMapperConfiguration = new ModelMapperConfiguration();

    @Test
    public void modelMapper_sanity_expectedGeneralConfigurations() {
        ModelMapper mapper = modelMapperConfiguration.modelMapper();

        boolean hasValueReader = mapper.getConfiguration()
                .getValueReaders()
                .stream()
                .anyMatch(x -> x.getClass().equals(RecordValueReader.class));

        assertTrue(hasValueReader);
        assertEquals(NameTokenizers.UNDERSCORE, mapper.getConfiguration().getSourceNameTokenizer());
    }

    @Test
    public void modelMapper_userSignupInfoToUserRecordSanity_identicalValues() {
        ModelMapper mapper = modelMapperConfiguration.modelMapper();

        UserSignupInfo userSignupInfo = new UserSignupInfo("a", "b", "c", "d", "e");

        UsersRecord record = mapper.map(userSignupInfo, UsersRecord.class);

        assertEquals(userSignupInfo.getFName(), record.getFirstName());
        assertEquals(userSignupInfo.getLName(), record.getLastName());
        assertEquals("c, d, e", record.getAddress());
    }

    @Test
    public void modelMapper_userSignupInfoToUserRecordEveryFieldIsNull_identicalValues() {
        ModelMapper mapper = modelMapperConfiguration.modelMapper();

        UserSignupInfo userSignupInfo = new UserSignupInfo();

        UsersRecord record = mapper.map(userSignupInfo, UsersRecord.class);

        assertNull(record.getFirstName());
        assertNull(record.getLastName());
        assertEquals("", record.getAddress());
    }

    @Test
    public void modelMapper_userRecordToUserSanity_identicalValues() {
        ModelMapper mapper = modelMapperConfiguration.modelMapper();

        UsersRecord record = new UsersRecord(1L, "a", "b", "c");

        User user = mapper.map(record, User.class);

        assertEquals(record.getId(), user.getId());
        assertEquals(record.getFirstName(), user.getFirstName());
        assertEquals(record.getLastName(), user.getLastName());
        assertEquals(record.getAddress(), user.getAddress());
    }

    @Test
    public void modelMapper_rentalRequestToRentalRecordSanity_identicalValues() {
        ModelMapper mapper = modelMapperConfiguration.modelMapper();

        RentalRequest request = new RentalRequest(1L, "a", LocalDateTime.MIN);

        RentalsRecord record = mapper.map(request, RentalsRecord.class);

        assertEquals(request.getBy(), record.getId());
        assertEquals(request.getTitle(), record.getMovieName());
        assertEquals(request.getTime(), record.getStartTime());
    }

    @Test
    public void modelMapper_rentalRecordToRentalStatus_identicalValues() {
        ModelMapper mapper = modelMapperConfiguration.modelMapper();

        RentalsRecord record = new RentalsRecord(1L, null, "a", LocalDateTime.MIN, LocalDateTime.MAX);

        RentalStatus status = mapper.map(record, RentalStatus.class);

        assertEquals(status.getId(), record.getId());
        assertEquals(status.getMovieName(), record.getMovieName());
        assertEquals(status.getTime(), record.getStartTime());
    }
}
