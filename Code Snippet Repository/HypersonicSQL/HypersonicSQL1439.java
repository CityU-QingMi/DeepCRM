    public OutputStream setBinaryStream(final long pos) throws SQLException {

        checkReadonly();

        if (pos < MIN_POS || pos > MAX_POS) {
            throw JDBCUtil.outOfRangeArgument("pos: " + pos);
        }

        checkClosed();

        return new java.io.ByteArrayOutputStream() {
            private boolean closed;

            public synchronized void close() throws java.io.IOException {
                if (closed) {
                    return;
                }
                closed = true;
                byte[] bytes = super.buf;
                int length = super.count;
                super.buf = NO_BYTES;
                super.count = 0;
                try {
                    JDBCBlob.this.setBytes(pos, bytes, 0, length);
                } catch (SQLException se) {
                    throw JavaSystem.toIOException(se);
                } finally {
                    super.close();
                }
            }
        };
    }
