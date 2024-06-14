package com.abl.liquibase.statement;

import liquibase.statement.core.DeleteStatement;
import lombok.Getter;

@Getter
public class DeleteUserStatement extends DeleteStatement {

    private final long userId;

    public DeleteUserStatement(long userId, String catalogName, String schemaName, String tableName) {
        super(catalogName, schemaName, tableName);
        this.userId = userId;

        String whereStatement = String.format("id = %s", userId);
        setWhere(whereStatement);
    }
}
