package com.abl.statement;

import com.abl.liquibase.statement.DeleteUserRentalStatement;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DeleteUserRentalStatementTest {

    private final DeleteUserRentalStatement statement =
            new DeleteUserRentalStatement(USER_ID, CATALOG_NAME, SCHEMA_NAME, TABLE_NAME);
    private static final long USER_ID = 37;
    private static final String CATALOG_NAME = "catalog";
    private static final String SCHEMA_NAME = "schema";
    private static final String TABLE_NAME = "table";

    @Test
    public void getUserId_sanity_expectedValue() {
        assertEquals(USER_ID, statement.getUserId());
    }

    @Test
    public void getWhere_sanity_expectedValue() {
        assertEquals("rental.user_id = 37", statement.getWhere());
    }
}
