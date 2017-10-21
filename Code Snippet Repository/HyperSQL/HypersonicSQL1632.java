    public synchronized ParameterMetaData getParameterMetaData() throws SQLException {

        checkClosed();

        if (pmd == null) {
            pmd = new JDBCParameterMetaData(connection, parameterMetaData);
        }

        return pmd;
    }
