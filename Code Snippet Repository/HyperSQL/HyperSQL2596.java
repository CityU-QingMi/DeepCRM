    public void testMerge10() {

        try {

            // merge statement with values as source table, with WHERE
            // condition that does not not match a row in T
            executeMerge("MERGE INTO SA.T "
                         + "USING (VALUES(10, 'testA', 'testB')) AS X (I, A, B) "
                         + "ON T.I = X.I " + "WHEN MATCHED THEN "
                         + "UPDATE SET T.A = X.A, T.B = 'UPDATED' "
                         + "WHEN NOT MATCHED THEN "
                         + "INSERT VALUES (X.I, X.A, 'INSERTED');");

            // 1 row should be inserted
            printTable("SA.T", "*", 4);
        } catch (SQLException e) {
            fail(e.getMessage());
        }

        System.out.println("testMerge10 complete\n");
    }
