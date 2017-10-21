    public void testMerge11() {

        try {
            executeMerge("SET SCHEMA PUBLIC");

            PreparedStatement ps = connection.prepareStatement(
        "MERGE INTO SA.T "
                         + "USING (VALUES(CAST(? AS INT), 'testA', 'testB')) AS X (I, A, B) "
                         + "ON T.I = X.I " + "WHEN MATCHED THEN "
                         + "UPDATE SET T.A = X.A, T.B = 'UPDATED' "
                         + "WHEN NOT MATCHED THEN "
                         + "INSERT VALUES (X.I, X.A, 'INSERTED');");

            ps.setInt(1, 10);
            ps.executeUpdate();

            // 1 row should be inserted
            printTable("SA.T", "*", 4);
        } catch (SQLException e) {
            fail(e.getMessage());
        }

        System.out.println("testMerge10 complete\n");
    }
