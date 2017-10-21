    public synchronized Writer setCharacterStream(final long pos)
    throws SQLException {

        checkClosed();

        if (pos < 1) {
            throw JDBCUtil.outOfRangeArgument("pos: " + pos);
        }

        if (!isWritable) {
            throw JDBCUtil.notUpdatableColumn();
        }

        startUpdate();

        return new Writer() {

            private long    m_clobPosition = pos - 1;
            private boolean m_closed;

            public void write(char[] cbuf, int off,
                              int len) throws IOException {

                checkClosed();
                clob.setChars(session, m_clobPosition, cbuf, off, len);

                m_clobPosition += len;
            }

            public void flush() throws IOException {

                // no-op
            }

            @Override
            public void close() throws IOException {
                m_closed = true;
            }

            private void checkClosed() throws IOException {

                if (m_closed || JDBCClobClient.this.isClosed()) {
                    throw new IOException("The stream is closed");
                }
            }
        };
    }
