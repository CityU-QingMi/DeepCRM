    void setAscStream(int parameterIndex, java.io.InputStream x,
                      long length) throws SQLException {

        if (length > Integer.MAX_VALUE) {
            throw JDBCUtil.sqlException(ErrorCode.X_22001);
        }

        if (x == null) {
            throw JDBCUtil.nullArgument("x");
        }

        try {
            String s = StringConverter.inputStreamToString(x, "US-ASCII");

            if (length >= 0 && s.length() > length) {
                s = s.substring(0, (int) length);
            }
            setParameter(parameterIndex, s);
        } catch (IOException e) {
            throw JDBCUtil.sqlException(ErrorCode.JDBC_INPUTSTREAM_ERROR, null, e);
        }
    }
