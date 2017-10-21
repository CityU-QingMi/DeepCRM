    public synchronized void setBinaryStream(String parameterName,
            java.io.InputStream x, long length) throws SQLException {

        if (length > Integer.MAX_VALUE) {
            String msg = "Maximum Binary input octet length exceeded: "
                         + length;    // NOI18N

            throw JDBCUtil.sqlException(ErrorCode.JDBC_INPUTSTREAM_ERROR, msg);
        }
        setBinaryStream(parameterName, x, (int) length);
    }
