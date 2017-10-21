    public InputStream getAsciiStream() throws SQLException {

        InputStream stream;

        try {
            stream = new JDBCBlobFile.InputStreamAdapter(m_file, 0,
                    Long.MAX_VALUE) {
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
        } catch (FileNotFoundException ex) {
            throw JDBCUtil.sqlException(ex);
        } catch (IOException ex) {
            throw JDBCUtil.sqlException(ex);
        } catch (SecurityException ex) {
            throw JDBCUtil.sqlException(ex);
        } catch (NullPointerException ex) {
            throw JDBCUtil.sqlException(ex);
        } catch (IllegalArgumentException ex) {
            throw JDBCUtil.sqlException(ex);
        }

        m_streams.add(stream);

        return stream;
    }
