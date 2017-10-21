    public synchronized void setCharacterStream(String parameterName,
            java.io.Reader reader, long length) throws SQLException {

        if (length > Integer.MAX_VALUE) {
            String msg = "Maximum character input length exceeded: " + length;    // NOI18N

            throw JDBCUtil.sqlException(ErrorCode.JDBC_INPUTSTREAM_ERROR, msg);
        }
        setCharacterStream(parameterName, reader, (int) length);
    }
