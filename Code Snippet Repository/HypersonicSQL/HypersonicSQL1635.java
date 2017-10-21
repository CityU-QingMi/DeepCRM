    private void setBinStream(int parameterIndex, java.io.InputStream x,
                              long length) throws SQLException {

        if (isClosed || connection.isClosed) {
            checkClosed();
        }

        if (parameterTypes[parameterIndex - 1].typeCode == Types.SQL_BLOB) {
            setBlobParameter(parameterIndex, x, length);

            return;
        }

        if (length > Integer.MAX_VALUE) {
            String msg = "Maximum Blob input length exceeded: " + length;

            throw JDBCUtil.sqlException(ErrorCode.JDBC_INPUTSTREAM_ERROR, msg);
        }

        try {
            HsqlByteArrayOutputStream output;

            if (length < 0) {
                output = new HsqlByteArrayOutputStream(x);
            } else {
                output = new HsqlByteArrayOutputStream(x, (int) length);
            }
            setParameter(parameterIndex, output.toByteArray());
        } catch (Throwable e) {
            throw JDBCUtil.sqlException(ErrorCode.JDBC_INPUTSTREAM_ERROR,
                                    e.toString(), e);
        }
    }
