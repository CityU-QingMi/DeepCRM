    public InputStream getBinaryStream(final long pos,
                                       final long length) throws SQLException {

        if (pos < 1) {
            throw JDBCUtil.outOfRangeArgument("pos: " + pos);
        }

        checkClosed();

        InputStream result;

        try {
            result = new InputStreamAdapter(m_file, pos - 1, length) {
                private boolean closed;

                public void close() throws IOException {
                    if (closed) {
                        return;
                    }
                    closed = true;
                    try {
                        super.close();
                    } finally {
                        m_streams.remove(this);
                    }
                }
            };
        } catch (Exception ex) {
            throw JDBCUtil.sqlException(ex);
        }

        m_streams.add(result);

        return result;
    }
