    public OutputStream setBinaryStream(final long pos) throws SQLException {

        if (pos < 1) {
            throw JDBCUtil.invalidArgument("pos: " + pos);
        }

        checkClosed();
        createFile();

        OutputStream adapter;

        try {
            adapter = new OutputStreamAdapter(m_file, pos - 1) {
                private boolean closed;
                public synchronized void close() throws IOException {
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
        } catch (IOException ex) {
            throw JDBCUtil.sqlException(ex);
        } catch (IllegalArgumentException ex) {
            throw JDBCUtil.sqlException(ex);
        } catch (SecurityException ex) {
            throw JDBCUtil.sqlException(ex);
        }

        m_streams.add(adapter);

        final OutputStream result = new BufferedOutputStream(adapter);

        return result;
    }
