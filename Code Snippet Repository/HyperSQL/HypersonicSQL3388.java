    private int queryRowCount(String query) throws SQLException {

        int count = 0;

        if (!statement.execute(query)) {
            return count;
        }

        ResultSet rs = statement.getResultSet();

        try {
            while (rs.next()) {
                count++;
            }
        } finally {
            rs.close();
        }

        return count;
    }
