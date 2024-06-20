package com.abl.rjmdb.persistance.mock;

import com.abl.rjmdb.model.jooq.tables.Rental;
import com.abl.rjmdb.model.jooq.tables.records.RentalRecord;
import lombok.Generated;
import org.jooq.DSLContext;
import org.jooq.Result;
import org.jooq.SQLDialect;
import org.jooq.impl.DSL;
import org.jooq.tools.jdbc.MockDataProvider;
import org.jooq.tools.jdbc.MockExecuteContext;
import org.jooq.tools.jdbc.MockResult;

@Generated
public class MovieDatabaseRepositoryMockDataProvider implements MockDataProvider {

    @Override
    public MockResult[] execute(MockExecuteContext mockExecuteContext) {
        DSLContext dsl = DSL.using(SQLDialect.POSTGRES);

        Result<RentalRecord> result = dsl.newResult(Rental.RENTAL);
        result.add(dsl.newRecord(Rental.RENTAL));

        return new MockResult[] {
                new MockResult(1, result)
        };
    }
}
