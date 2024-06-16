package com.abl.liquibase.change;

import com.abl.liquibase.statement.DeleteUserRentalStatement;
import com.abl.liquibase.statement.DeleteUserStatement;
import liquibase.change.AbstractChange;
import liquibase.change.ChangeMetaData;
import liquibase.change.DatabaseChange;
import liquibase.change.DatabaseChangeProperty;
import liquibase.database.Database;
import liquibase.exception.ValidationErrors;
import liquibase.statement.SqlStatement;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
@DatabaseChange(name = "deleteUserChange", description = "", priority = ChangeMetaData.PRIORITY_DEFAULT)
public class DeleteUserHistoryChange extends AbstractChange {


    private long userId;

    @Override
    public SqlStatement[] generateStatements(Database database) {
        return new SqlStatement[]{
                new DeleteUserStatement(userId, database.getDefaultCatalogName(), database.getDefaultSchemaName(), "client"),
                new DeleteUserRentalStatement(userId, database.getDefaultCatalogName(), database.getDefaultSchemaName(), "rental")
        };
    }

    @Override
    public String getConfirmationMessage() {
        return "Suck Mah Balls";
    }

    @Override
    public ValidationErrors validate(Database database) {
        return null;
    }
}
