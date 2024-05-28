package com.abl.rjmdb.configuration.model.mapping;

import com.abl.rjmdb.model.RentalRequest;
import com.abl.rjmdb.model.RentalStatus;
import com.abl.rjmdb.model.User;
import com.abl.rjmdb.model.UserSignupInfo;
import com.abl.rjmdb.model.jooq.tables.records.RentalRecord;
import com.abl.rjmdb.model.jooq.tables.records.ClientRecord;
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
        RentalRecordToRentalStatus(mapper);

        return mapper;
    }

    private void userSignupInfoToUserRecord(ModelMapper mapper) {
        mapper.createTypeMap(UserSignupInfo.class, ClientRecord.class)
                .addMapping(UserSignupInfo::getFName, ClientRecord::setFirstName)
                .addMapping(UserSignupInfo::getLName, ClientRecord::setLastName)
                .addMappings(expression -> expression.using(new AddressConverter())
                        .map(x -> x, ClientRecord::setAddress));
    }

    private void userRecordToUser(ModelMapper mapper) {
        mapper.createTypeMap(ClientRecord.class, User.class)
                .addMapping(ClientRecord::getId, User::setId)
                .addMapping(ClientRecord::getFirstName, User::setFirstName)
                .addMapping(ClientRecord::getLastName, User::setLastName)
                .addMapping(ClientRecord::getAddress, User::setAddress);
    }

    private void rentalRequestInfoToRentalRecord(ModelMapper mapper) {
        mapper.createTypeMap(RentalRequest.class, RentalRecord.class)
                .addMapping(RentalRequest::getBy, RentalRecord::setUserId)
                .addMapping(RentalRequest::getTitle, RentalRecord::setMovieName);
    }

    private void RentalRecordToRentalStatus(ModelMapper mapper) {
        mapper.createTypeMap(RentalRecord.class, RentalStatus.class)
                .addMapping(RentalRecord::getId, RentalStatus::setId)
                .addMapping(RentalRecord::getMovieName, RentalStatus::setMovieName)
                .addMapping(RentalRecord::getStartTime, RentalStatus::setTime);
    }
}
