    public synchronized void setAsciiStream(String parameterName,
            java.io.InputStream x, long length) throws SQLException {

        if (length > Integer.MAX_VALUE) {
            String msg = "Maximum ASCII input octet length exceeded: "
                         + length;    // NOI18N

            throw JDBCUtil.sqlException(ErrorCode.JDBC_INPUTSTREAM_ERROR, msg);
        }
        this.setAsciiStream(parameterName, x, (int) length);
    }
