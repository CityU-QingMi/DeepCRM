    public void testPreparedWithManyParams() throws Exception {

        int    count    = 40;
        String tabledef = "CREATE TABLE T1 (";

        for (int i = 0; i < count; i++) {
            if (i != 0) {
                tabledef = tabledef + ',';
            }

            tabledef = tabledef + "COL_" + i + " INT NOT NULL";
        }

        tabledef += ");";

        String querydef = "INSERT INTO T1(";

        for (int i = 0; i < count; i++) {
            if (i != 0) {
                querydef = querydef + ',';
            }

            querydef = querydef + "COL_" + i;
        }

        querydef += ") VALUES (";

        for (int i = 0; i < count; i++) {
            if (i != 0) {
                querydef = querydef + ',';
            }

            querydef = querydef + "?";
        }

        querydef += ");";

        Statement st = connection.createStatement();

        st.execute("DROP TABLE T1 IF EXISTS;");
        st.execute(tabledef);

        PreparedStatement ps = connection.prepareStatement(querydef);

        for (int i = 0; i < count; i++) {
            ps.setInt(i + 1, i + 311);
        }

        ps.executeUpdate();
    }
