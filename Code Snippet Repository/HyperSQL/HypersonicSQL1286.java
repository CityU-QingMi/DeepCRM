    public static String getBanner(Connection c) {
        try {
            DatabaseMetaData md = c.getMetaData();
            return (md == null)
                    ? null
                    : SqltoolRB.jdbc_established.getString(
                            md.getDatabaseProductName(),
                            md.getDatabaseProductVersion(),
                            md.getUserName(),
                                    (c.isReadOnly() ? "R/O " : "R/W ")
                                    + RCData.tiToString(
                                    c.getTransactionIsolation()));
        } catch (SQLException se) {
            return null;
        }
    }
