package com.abl.rjmdb.persistance.mock;

import com.abl.rjmdb.model.jooq.tables.Client;
import com.abl.rjmdb.model.jooq.tables.records.ClientRecord;
import lombok.Generated;
import org.jooq.DSLContext;
import org.jooq.Result;
import org.jooq.SQLDialect;
import org.jooq.impl.DSL;
import org.jooq.tools.jdbc.MockDataProvider;
import org.jooq.tools.jdbc.MockExecuteContext;
import org.jooq.tools.jdbc.MockResult;

@Generated
public class UserDatabaseRepositoryMockDataProvider implements MockDataProvider {

    @Override
    public MockResult[] execute(MockExecuteContext mockExecuteContext) {
        DSLContext dsl = DSL.using(SQLDialect.POSTGRES);

        Result<ClientRecord> result = dsl.newResult(Client.CLIENT);
        result.add(dsl.newRecord(Client.CLIENT));

        return new MockResult[] {
                new MockResult(1, result)
        };
    }
}
