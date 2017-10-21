    public String getUserName() throws SQLException {

        ResultSet rs = execute("CALL USER()");

        rs.next();

        String result = rs.getString(1);

        rs.close();

        return result;
    }
