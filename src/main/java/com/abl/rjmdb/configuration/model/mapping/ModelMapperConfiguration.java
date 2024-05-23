package com.abl.rjmdb.configuration.model.mapping;

import com.abl.rjmdb.model.RentalRequest;
import com.abl.rjmdb.model.RentalStatus;
import com.abl.rjmdb.model.User;
import com.abl.rjmdb.model.UserSignupInfo;
import com.abl.rjmdb.model.jooq.tables.records.RentalsRecord;
import com.abl.rjmdb.model.jooq.tables.records.UsersRecord;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.NameTokenizers;
import org.modelmapper.jooq.RecordValueReader;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ModelMapperConfiguration {

    @Bean
    public ModelMapper modelMapper() {
        ModelMapper mapper = new ModelMapper();

        mapper.getConfiguration().addValueReader(new RecordValueReader());
        mapper.getConfiguration().setSourceNameTokenizer(NameTokenizers.UNDERSCORE);

        userSignupInfoToUserRecord(mapper);
        userRecordToUser(mapper);
        rentalRequestInfoToRentalRecord(mapper);
        rentalsRecordToRentalStatus(mapper);

        return mapper;
    }

    private void userSignupInfoToUserRecord(ModelMapper mapper) {
        mapper.createTypeMap(UserSignupInfo.class, UsersRecord.class)
                .addMapping(UserSignupInfo::getFName, UsersRecord::setFirstName)
                .addMapping(UserSignupInfo::getLName, UsersRecord::setLastName)
                .addMappings(expression -> expression.using(new AddressConverter())
                        .map(x -> x, UsersRecord::setAddress));
    }

    private void userRecordToUser(ModelMapper mapper) {
        mapper.createTypeMap(UsersRecord.class, User.class)
                .addMapping(UsersRecord::getId, User::setId)
                .addMapping(UsersRecord::getFirstName, User::setFirstName)
                .addMapping(UsersRecord::getLastName, User::setLastName)
                .addMapping(UsersRecord::getAddress, User::setAddress);
    }

    private void rentalRequestInfoToRentalRecord(ModelMapper mapper) {
        mapper.createTypeMap(RentalRequest.class, RentalsRecord.class)
                .addMapping(RentalRequest::getBy, RentalsRecord::setId)
                .addMapping(RentalRequest::getTitle, RentalsRecord::setMovieName)
                .addMapping(RentalRequest::getTime, RentalsRecord::setStartTime);
    }

    private void rentalsRecordToRentalStatus(ModelMapper mapper) {
        mapper.createTypeMap(RentalsRecord.class, RentalStatus.class)
                .addMapping(RentalsRecord::getId, RentalStatus::setId)
                .addMapping(RentalsRecord::getMovieName, RentalStatus::setMovieName)
                .addMapping(RentalsRecord::getStartTime, RentalStatus::setTime);
    }
}
