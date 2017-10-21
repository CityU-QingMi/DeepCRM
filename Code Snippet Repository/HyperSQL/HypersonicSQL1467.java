    public int setBytes(final long pos, final byte[] bytes, final int offset,
                        final int len) throws SQLException {

        if (bytes == null) {
            throw JDBCUtil.nullArgument("bytes");
        }

        final OutputStream os = setBinaryStream(pos);

        try {
            os.write(bytes, offset, len);
        } catch (IOException ex) {
            throw JDBCUtil.sqlException(ex);
        } finally {
            closeSafely(os);
        }

        return len;
    }
