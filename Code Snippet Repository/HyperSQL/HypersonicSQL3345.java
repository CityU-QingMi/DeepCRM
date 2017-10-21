    public void testMerge7() {

        try {

            // merge statement with select statement as source table, with WHERE
            // condition that matches a row in T
            executeMerge(
                "MERGE INTO SA.T " +
                "USING (SELECT * FROM SA.S WHERE I = 4) AS X " +
                "ON T.I = X.I " +
                "WHEN MATCHED THEN " +
                    "UPDATE SET T.A = X.A, T.B = 'UPDATED' " +
                "WHEN NOT MATCHED THEN " +
                    "INSERT VALUES (X.I, X.A, 'INSERTED');"
            );

            // 1 row should be updated
            printTable("SA.T", "*", 3);
        } catch (SQLException e) {
            fail(e.getMessage());
        }

        System.out.println("testMerge7 complete\n");
    }
