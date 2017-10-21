    public long length() throws SQLException {

        checkClosed();

        if (m_fixedWidthCharset) {
            return m_file.length() / m_maxCharWidth;
        }

        ReaderAdapter adapter = null;

        try {
            adapter = new ReaderAdapter(m_file, 0, Long.MAX_VALUE);

            final long length = adapter.skip(Long.MAX_VALUE);

            return length;
        } catch (IOException ex) {
            throw JDBCUtil.sqlException(ex);
        } finally {
            closeSafely(adapter);
        }
    }
