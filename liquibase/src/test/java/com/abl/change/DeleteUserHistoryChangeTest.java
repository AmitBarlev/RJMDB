package com.abl.change;

import com.abl.liquibase.change.DeleteUserHistoryChange;
import liquibase.database.Database;
import liquibase.statement.SqlStatement;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.Mockito.*;

public class DeleteUserHistoryChangeTest {

    private final DeleteUserHistoryChange change = new DeleteUserHistoryChange();
    private final int USER_ID = 37;


    @BeforeEach
    void setUp() {
        change.setUserId(USER_ID);
    }

    @Test
    public void getConfirmationMessage_sanity_equalStrings() {
        assertEquals("User id: 37 has been erased", change.getConfirmationMessage());
    }

    @Test
    public void validate_sanity_null() {
        assertNull(change.validate(null));
    }

    @Test
    public void generateStatements_sanity_twoStatements() {
        Database database = mock(Database.class);

        doReturn("schema").when(database).getDefaultSchemaName();
        doReturn("catalog").when(database).getDefaultCatalogName();

        SqlStatement[] statements = change.generateStatements(database);
        assertEquals(2, statements.length);

        verify(database).getDefaultCatalogName();
        verify(database).getDefaultSchemaName();
    }
}
