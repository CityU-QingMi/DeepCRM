    public synchronized void setCatalog(String catalog) throws SQLException {

        checkClosed();

        try {
            sessionProxy.setAttribute(SessionInterface.INFO_CATALOG, catalog);
        } catch (HsqlException e) {
            throw JDBCUtil.sqlException(e);
        }
    }
