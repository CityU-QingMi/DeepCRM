    public java.io.OutputStream setAsciiStream(
            final long pos) throws SQLException {

        checkReadonly();
        checkClosed();

        if (pos < MIN_POS || pos > MAX_POS) {
            throw JDBCUtil.outOfRangeArgument("pos: " + pos);
        }

        return new java.io.ByteArrayOutputStream() {
            boolean closed = false;

            public synchronized void close() throws java.io.IOException {
                if (closed) {
                    return;
                }
                closed = true;
                final byte[] bytes = super.buf;
                final int length = super.count;
                super.buf = null;
                super.count = 0;
                try {
                    final String str = new String(bytes, 0, length, "US-ASCII");
                    JDBCClob.this.setString(pos, str);
                } catch (SQLException se) {
                    throw JavaSystem.toIOException(se);
                }
            }
        };
    }
