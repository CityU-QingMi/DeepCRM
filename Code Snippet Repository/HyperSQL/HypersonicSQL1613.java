    public Connection connect(String url,
                              Properties info) throws SQLException {

        if (url.regionMatches(true, 0, DatabaseURL.S_URL_INTERNAL, 0,
                              DatabaseURL.S_URL_INTERNAL.length())) {
            JDBCConnection conn = (JDBCConnection) threadConnection.get();

            if (conn == null) {
                return null;
            }

            return conn;
        }

        return getConnection(url, info);
    }
