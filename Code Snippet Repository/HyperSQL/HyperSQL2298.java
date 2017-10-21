    private static Connection getConnection() throws SQLException {

        try {
            Class.forName(drv).newInstance();

            return DriverManager.getConnection(url, usr, pwd);
        } catch (SQLException se) {
            throw se;
        } catch (Exception e) {
            throw new SQLException(e.toString());
        }
    }
