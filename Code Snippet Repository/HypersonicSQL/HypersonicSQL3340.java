    public void testMerge2() {

        try {

            // merge statement with only update statement
            executeMerge(
                "MERGE INTO SA.T " +
                "USING SA.S " +
                "ON T.I = S.I " +
                "WHEN MATCHED THEN " +
                    "UPDATE SET T.A = S.A, T.B = 'UPDATED';"
            );

            // two rows should be updated, nothing inserted
            printTable("SA.T", "*", 3);
        } catch (SQLException e) {
            fail(e.getMessage());
        }

        System.out.println("testMerge2 complete\n");
    }
