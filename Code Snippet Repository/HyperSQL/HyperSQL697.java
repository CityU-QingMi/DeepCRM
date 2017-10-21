    private void setClobForStringParameter(int parameterIndex,
            Clob x) throws SQLException {

        if (x instanceof JDBCClob) {
            setParameter(parameterIndex, ((JDBCClob) x).getData());

            return;
        } else if (x == null) {
            setParameter(parameterIndex, null);

            return;
        }

        final long length = x.length();

        if (length > Integer.MAX_VALUE) {
            String msg = "Max Clob input character length exceeded: " + length;    // NOI18N

            throw JDBCUtil.sqlException(ErrorCode.JDBC_INPUTSTREAM_ERROR, msg);
        }

        try {
            java.io.Reader  reader = x.getCharacterStream();
            CharArrayWriter writer = new CharArrayWriter(reader, (int) length);

            setParameter(parameterIndex, writer.toString());
        } catch (Throwable e) {
            throw JDBCUtil.sqlException(ErrorCode.SERVER_TRANSFER_CORRUPTED,
                                    e.toString(), e);
        }
    }
