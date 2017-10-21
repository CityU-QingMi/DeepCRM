    private void checkColumnLists() throws SQLException {

        // just to ensure the column count handling is as expected, else below tests might be useless
        executeStatement("CREATE VIEW IMPOSSIBLE (\"A\") AS SELECT * FROM ABC",
                         ErrorCode.X_42593);

        // ................................................................
        // not that it should make any difference to S1, but who knows
        checkViewTranslationAndContent("L1", new String[] {
            "C1", "C2", "C3", "C4"
        }, "SELECT * FROM ABC", "SELECT PUBLIC.ABC.ID,PUBLIC.ABC.A,PUBLIC.ABC.B,PUBLIC.ABC.C FROM PUBLIC.ABC",
           "ABC");
    }
