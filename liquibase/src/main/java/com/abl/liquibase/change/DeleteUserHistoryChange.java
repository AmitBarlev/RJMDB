package com.abl.liquibase.change;

import com.abl.liquibase.statement.DeleteUserRentalStatement;
import com.abl.liquibase.statement.DeleteUserStatement;
import liquibase.change.AbstractChange;
import liquibase.change.ChangeMetaData;
import liquibase.change.DatabaseChange;
import liquibase.database.Database;
import liquibase.exception.ValidationErrors;
import liquibase.statement.SqlStatement;
import lombok.Data;
import lombok.EqualsAndHashCode;

import static com.abl.rjmdb.model.jooq.tables.Client.CLIENT;
import static com.abl.rjmdb.model.jooq.tables.Rental.RENTAL;


@Data
@EqualsAndHashCode(callSuper = true)
@DatabaseChange(name = "deleteUserData", description = "", priority = ChangeMetaData.PRIORITY_DEFAULT)
public class DeleteUserHistoryChange extends AbstractChange {

    private long userId;

    @Override
    public SqlStatement[] generateStatements(Database database) {
        String catalogName = database.getDefaultCatalogName();
        String schemaName = database.getDefaultSchemaName();

        return new SqlStatement[]{
                new DeleteUserStatement(userId, catalogName, schemaName, CLIENT.getName()),
                new DeleteUserRentalStatement(userId, catalogName, schemaName, RENTAL.getName())
        };
    }

    @Override
    public String getConfirmationMessage() {
        return String.format("User id: %s has been erased", userId);
    }

    @Override
    public ValidationErrors validate(Database database) {
        return null;
    }
}
