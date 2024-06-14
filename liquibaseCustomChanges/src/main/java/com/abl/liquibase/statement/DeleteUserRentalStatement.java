package com.abl.liquibase.statement;

import liquibase.statement.core.DeleteStatement;
import lombok.Getter;

@Getter
public class DeleteUserRentalStatement extends DeleteStatement {

    private final long userId;

    public DeleteUserRentalStatement(long userId, String catalogName, String schemaName, String tableName) {
        super(catalogName, schemaName, tableName);
        this.userId = userId;

        setWhere(String.format("%s = %s", "userId", userId)); //replace with JOOQ
    }
}
