    private ResultSet execute(String sql) throws SQLException {

        // NOTE:
        // Need to create a JDBCStatement here so JDBCResultSet can return
        // its Statement object on call to getStatement().
        // The native JDBCConnection.execute() method does not
        // automatically assign a Statement object for the ResultSet, but
        // JDBCStatement does.  That is, without this, there is no way for the
        // JDBCResultSet to find its way back to its Connection (or Statement)
        // Also, cannot use single, shared JDBCStatement object, as each
        // fetchResult() closes any old JDBCResultSet before fetching the
        // next, causing the JDBCResultSet's Result object to be nullified
        final int scroll = ResultSet.TYPE_SCROLL_INSENSITIVE;
        final int concur = ResultSet.CONCUR_READ_ONLY;
        JDBCStatement st = (JDBCStatement) connection.createStatement(scroll,
            concur);

        st.maxRows = -1;

        ResultSet r = st.executeQuery(sql);

        ((JDBCResultSet) r).autoClose = true;

        return r;
    }
