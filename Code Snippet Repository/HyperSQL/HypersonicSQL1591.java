    public boolean isReadOnly() throws SQLException {

        ResultSet rs = execute("CALL IS_READONLY_DATABASE()");

        rs.next();

        boolean result = rs.getBoolean(1);

        rs.close();

        return result;
    }
