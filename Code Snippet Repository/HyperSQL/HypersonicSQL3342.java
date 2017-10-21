    public void testMerge4() {

        try {

            // merge statement with both update and insert, without
            // specifying insert columns
            executeMerge(
                "MERGE INTO SA.T " +
                "USING SA.S " +
                "ON T.I = S.I " +
                "WHEN MATCHED THEN " +
                    "UPDATE SET T.A = S.A, T.B = 'UPDATED' " +
                "WHEN NOT MATCHED THEN " +
                    "INSERT VALUES (S.I, S.A, 'INSERTED');"
            );

            // two rows should be updated, two rows inserted
            printTable("SA.T", "*", 5);
        } catch (SQLException e) {
            fail(e.getMessage());
        }

        System.out.println("testMerge4 complete\n");
    }
