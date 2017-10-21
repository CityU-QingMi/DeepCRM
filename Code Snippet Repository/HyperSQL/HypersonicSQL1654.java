    public synchronized void setUnicodeStream(int parameterIndex,
            java.io.InputStream x, int length) throws SQLException {

        checkSetParameterIndex(parameterIndex);

        String    msg = null;
        final int ver = JDBCDatabaseMetaData.JDBC_MAJOR;

        if (x == null) {
            throw JDBCUtil.nullArgument("x");
        }

        // CHECKME:  Is JDBC4 clarification of UNICODE stream format retroactive?
        if ((ver < 4) && (length % 2 != 0)) {
            msg = "Odd length argument for UTF16 encoded stream: " + length;

            throw JDBCUtil.invalidArgument(msg);
        }

        String       encoding = (ver < 4) ? "UTF16"
                : "UTF8";
        StringWriter writer   = new StringWriter();

        try {
            CountdownInputStream cis    = new CountdownInputStream(x);
            InputStreamReader    reader = new InputStreamReader(cis, encoding);
            char[]               buff   = new char[1024];
            int                  charsRead;

            cis.setCount(length);

            while (-1 != (charsRead = reader.read(buff))) {
                writer.write(buff, 0, charsRead);
            }
        } catch (IOException ex) {
            throw JDBCUtil.sqlException(ErrorCode.SERVER_TRANSFER_CORRUPTED,
                                    ex.toString(), ex);
        }
        setParameter(parameterIndex, writer.toString());
    }
