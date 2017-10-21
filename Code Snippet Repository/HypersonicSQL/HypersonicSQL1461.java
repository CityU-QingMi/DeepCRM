    public byte[] getBytes(final long pos,
                           final int length) throws SQLException {

        InputStream           is   = null;
        ByteArrayOutputStream baos = null;
        final int initialBufferSize =
            Math.min(InOutUtil.DEFAULT_COPY_BUFFER_SIZE, length);

        try {
            is   = getBinaryStream(pos, length);
            baos = new ByteArrayOutputStream(initialBufferSize);

            InOutUtil.copy(is, baos, length);
        } catch (IOException ex) {
            throw JDBCUtil.sqlException(ex);
        } finally {
            closeSafely(is);
        }

        return baos.toByteArray();
    }
