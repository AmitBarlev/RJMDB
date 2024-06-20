package com.abl.liquibase.statement;

import liquibase.statement.core.DeleteStatement;
import lombok.Getter;

import static com.abl.rjmdb.model.jooq.Tables.RENTAL;


@Getter
public class DeleteUserRentalStatement extends DeleteStatement {

    private final long userId;

    public DeleteUserRentalStatement(long userId, String catalogName, String schemaName, String tableName) {
        super(catalogName, schemaName, tableName);
        this.userId = userId;

        setWhere(String.format("%s = %s", RENTAL.USER_ID.getQualifiedName().unquotedName(), userId));
    }
}
