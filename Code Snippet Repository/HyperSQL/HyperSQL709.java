    private void setCharStream(int parameterIndex, java.io.Reader reader,
                               long length) throws SQLException {

        checkSetParameterIndex(parameterIndex);

        if (parameterTypes[parameterIndex - 1].typeCode == Types.SQL_CLOB) {
            setClobParameter(parameterIndex, reader, length);

            return;
        }

        if (length > Integer.MAX_VALUE) {
            String msg = "Maximum Clob input length exceeded: " + length;

            throw JDBCUtil.sqlException(ErrorCode.JDBC_INPUTSTREAM_ERROR, msg);
        }

        try {
            CharArrayWriter writer;

            if (length < 0) {
                writer = new CharArrayWriter(reader);
            } else {
                writer = new CharArrayWriter(reader, (int) length);
            }
            setParameter(parameterIndex, writer.toString());
        } catch (Throwable e) {
            throw JDBCUtil.sqlException(ErrorCode.JDBC_INPUTSTREAM_ERROR,
                                    e.toString(), e);
        }
    }
