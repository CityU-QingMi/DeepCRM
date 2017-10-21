    public java.io.Writer setCharacterStream(
            final long pos) throws SQLException {

        checkReadonly();
        checkClosed();

        if (pos < MIN_POS || pos > MAX_POS) {
            throw JDBCUtil.outOfRangeArgument("pos: " + pos);
        }

        return new java.io.StringWriter() {
            private boolean closed = false;
            public synchronized void close() throws java.io.IOException {
                if (closed) {
                    return;
                }
                closed = true;
                final StringBuffer sb = super.getBuffer();
                try {
                    JDBCClob.this.setStringBuffer(pos, sb, 0, sb.length());
                } catch (SQLException se) {
                    throw JavaSystem.toIOException(se);
                } finally {
                    sb.setLength(0);
                    sb.trimToSize();
                }
            }
        };
    }
