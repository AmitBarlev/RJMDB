package com.abl.liquibase.change;

import com.abl.liquibase.statement.DeleteUserRentalStatement;
import com.abl.liquibase.statement.DeleteUserStatement;
import liquibase.change.ChangeMetaData;
import liquibase.change.DatabaseChange;
import liquibase.change.custom.CustomSqlChange;
import liquibase.database.Database;
import liquibase.exception.ValidationErrors;
import liquibase.resource.ResourceAccessor;
import liquibase.statement.SqlStatement;
import lombok.Data;

@Data
@DatabaseChange(name = "protect", description = "", priority = ChangeMetaData.PRIORITY_DEFAULT)
public class DeleteUserHistoryChange implements CustomSqlChange {

    private long userId;
    private ResourceAccessor resourceAccessor;

    @Override
    public SqlStatement[] generateStatements(Database database) {
        return new SqlStatement[]{
                new DeleteUserStatement(userId, database.getDefaultCatalogName(), database.getDefaultSchemaName(), "client"),
                new DeleteUserRentalStatement(userId, database.getDefaultCatalogName(), database.getDefaultSchemaName(), "rental")
        };
    }

    @Override
    public String getConfirmationMessage() {
        return "User completely erased";
    }

    @Override
    public void setUp() {

    }

    @Override
    public void setFileOpener(ResourceAccessor resourceAccessor) {
        this.resourceAccessor = resourceAccessor;
    }

    @Override
    public ValidationErrors validate(Database database) {
        return null;
    }
}
