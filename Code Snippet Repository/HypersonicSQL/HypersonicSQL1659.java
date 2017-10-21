    private void setBlobForBinaryParameter(int parameterIndex,
            Blob x) throws SQLException {

        if (x instanceof JDBCBlob) {
            setParameter(parameterIndex, ((JDBCBlob) x).data());

            return;
        } else if (x == null) {
            setParameter(parameterIndex, null);

            return;
        }

        final long length = x.length();

        if (length > Integer.MAX_VALUE) {
            String msg = "Maximum Blob input octet length exceeded: " + length;    // NOI18N

            throw JDBCUtil.sqlException(ErrorCode.JDBC_INPUTSTREAM_ERROR, msg);
        }

        try {
            java.io.InputStream in = x.getBinaryStream();
            HsqlByteArrayOutputStream out = new HsqlByteArrayOutputStream(in,
                (int) length);

            setParameter(parameterIndex, out.toByteArray());
            out.close();
        } catch (Throwable e) {
            throw JDBCUtil.sqlException(ErrorCode.JDBC_INPUTSTREAM_ERROR,
                                    e.toString(), e);
        }
    }
