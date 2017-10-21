    public void testMerge9() {

        try {

            // merge statement with select statement as source table, with WHERE
            // condition that does and does not not match a row in T
            executeMerge(
                "MERGE INTO SA.T " +
                "USING (SELECT * FROM SA.S WHERE I > 2) AS X " +
                "ON T.I = X.I " +
                "WHEN MATCHED THEN " +
                    "UPDATE SET T.A = X.A, T.B = 'UPDATED' " +
                "WHEN NOT MATCHED THEN " +
                    "INSERT VALUES (X.I, X.A, 'INSERTED');"
            );

            // 1 row should be inserted, 1 row updated
            printTable("SA.T", "*", 4);
        } catch (SQLException e) {
            fail(e.getMessage());
        }

        System.out.println("testMerge9 complete\n");
    }
