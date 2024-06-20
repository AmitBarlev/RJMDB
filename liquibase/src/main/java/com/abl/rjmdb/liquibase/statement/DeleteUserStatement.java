package com.abl.rjmdb.liquibase.statement;

import liquibase.statement.core.DeleteStatement;
import lombok.Getter;

import static com.abl.rjmdb.model.jooq.tables.Client.CLIENT;


@Getter
public class DeleteUserStatement extends DeleteStatement {

    private final long userId;

    public DeleteUserStatement(long userId, String catalogName, String schemaName, String tableName) {
        super(catalogName, schemaName, tableName);
        this.userId = userId;

        String whereStatement = String.format("%s = %s", CLIENT.ID.getQualifiedName().unquotedName(), userId);
        setWhere(whereStatement);
    }
}
