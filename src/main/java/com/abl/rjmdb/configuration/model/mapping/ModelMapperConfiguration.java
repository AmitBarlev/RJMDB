package com.abl.rjmdb.configuration.model.mapping;

import com.abl.rjmdb.model.User;
import com.abl.rjmdb.model.UserSignupInfo;
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
        userToUserRecordMapping(mapper);

        return mapper;
    }

    private void userSignupInfoToUserRecord(ModelMapper mapper) {
        mapper.createTypeMap(UserSignupInfo.class, UsersRecord.class)
                .addMapping(UserSignupInfo::getFName, UsersRecord::setFirstName)
                .addMapping(UserSignupInfo::getLName, UsersRecord::setLastName)
                .addMapping(this::convertAddress, UsersRecord::setAddress);
    }

    private String convertAddress(UserSignupInfo info) {
        return String.format("%s, %s, %s", info.getCountry(), info.getCity(), info.getAddress());
    }

    private void userToUserRecordMapping(ModelMapper mapper) {
        mapper.createTypeMap(UsersRecord.class, User.class)
                .addMapping(UsersRecord::getId, User::setId)
                .addMapping(UsersRecord::getFirstName, User::setFirstName)
                .addMapping(UsersRecord::getLastName, User::setLastName)
                .addMapping(UsersRecord::getAddress, User::setAddress);
    }
}
