    public void testMerge1() {

        try {

            // merge statement with table aliases, using both match statements
            executeMerge(
                "MERGE INTO SA.T X " +
                "USING SA.S AS Y " +
                "ON X.I = Y.I " +
                "WHEN MATCHED THEN " +
                    "UPDATE SET X.A = Y.A, X.B = 'UPDATED' " +
                "WHEN NOT MATCHED THEN " +
                    "INSERT (I, A, B) VALUES (Y.I, Y.A, 'INSERTED');"
            );

            // table t should now have 5 rows, first and fifth with A/B updated
            // to values A/C from S, second should be the same, and third and
            // fourth should be the inserted rows that didn't exist before.
            printTable("SA.T", "*", 5);
        } catch (SQLException e) {
            fail(e.getMessage());
        }

        System.out.println("testMerge1 complete\n");
    }
