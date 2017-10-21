    public int getDatabaseMajorVersion() throws SQLException {

        ResultSet rs = execute("call database_version()");

        rs.next();

        String v = rs.getString(1);

        rs.close();

        return Integer.parseInt(v.substring(0, v.indexOf(".")));
    }
