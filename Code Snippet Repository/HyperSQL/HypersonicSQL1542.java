    public synchronized String getCatalog() throws SQLException {

        checkClosed();

        try {
            return (String) sessionProxy.getAttribute(
                SessionInterface.INFO_CATALOG);
        } catch (HsqlException e) {
            throw JDBCUtil.sqlException(e);
        }
    }
