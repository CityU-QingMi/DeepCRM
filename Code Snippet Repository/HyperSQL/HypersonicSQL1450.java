    public long length() throws SQLException {

        checkClosed();

        try {
            return m_file.length();
        } catch (SecurityException e) {
            throw JDBCUtil.sqlException(e);
        }
    }
