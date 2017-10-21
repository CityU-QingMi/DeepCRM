    public int getDatabaseMinorVersion() throws SQLException {

        ResultSet rs = execute("call database_version()");

        rs.next();

        String v = rs.getString(1);

        rs.close();

        int start = v.indexOf(".") + 1;

        return Integer.parseInt(v.substring(start, v.indexOf(".", start)));
    }
