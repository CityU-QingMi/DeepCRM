    public void testMerge3() {

        try {

            // merge statement with only insert statement, without
            // specifying insert columns
            executeMerge(
                "MERGE INTO SA.T " +
                "USING SA.S " +
                "ON T.I = S.I " +
                "WHEN NOT MATCHED THEN " +
                    "INSERT VALUES (S.I, S.A, 'INSERTED');"
            );

            // two rows should be updated, nothing inserted
            printTable("SA.T", "*", 5);
        } catch (SQLException e) {
            fail(e.getMessage());
        }

        System.out.println("testMerge3 complete\n");
    }
