    public void testMerge5() {

        try {

            // merge statement with select statement as source table, using all
            // columns from S
            executeMerge(
                "MERGE INTO SA.T " +
                "USING (SELECT * FROM SA.S) AS X " +
                "ON T.I = X.I " +
                "WHEN MATCHED THEN " +
                    "UPDATE SET T.A = X.A, T.B = 'UPDATED' " +
                "WHEN NOT MATCHED THEN " +
                    "INSERT VALUES (X.I, X.A, 'INSERTED');"
            );

            // two rows should be updated, two rows inserted
            printTable("SA.T", "*", 5);
        } catch (SQLException e) {
            fail(e.getMessage());
        }

        System.out.println("testMerge5 complete\n");
    }
