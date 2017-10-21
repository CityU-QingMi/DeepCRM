    private int tableRowCount(String tableName) throws SQLException {

        String query = "SELECT count(*) FROM " + tableName;

        if (!statement.execute(query)) {
            return 0;
        }

        ResultSet rs = statement.getResultSet();

        try {
            if (!rs.next()) {
                throw new SQLException("0 rows returned by (" + query + ')');
            }

            int count = rs.getInt(1);

            if (rs.next()) {
                throw new SQLException("> 1 row returned by (" + query + ')');
            }

            return count;
        } finally {
            rs.close();
        }

        //throw new Exception("Failed to get rowcount for " + tableName);
    }
