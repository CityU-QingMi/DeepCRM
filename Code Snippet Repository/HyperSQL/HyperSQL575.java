    public Reader getCharacterStream(long pos,
            long length) throws SQLException {

        if (pos < 1) {
            throw JDBCUtil.outOfRangeArgument("pos: " + pos);
        }

        if (length < 0) {
            throw JDBCUtil.outOfRangeArgument("length: " + length);
        }

        Reader reader;

        try {
            reader = new ReaderAdapter(m_file, pos - 1, length) {

                public void close() throws IOException {

                    try {
                        super.close();
                    } finally {
                        m_streams.remove(this);
                    }
                }
            };
        } catch (IOException ex) {
            throw JDBCUtil.sqlException(ex);
        } catch (RuntimeException ex) {
            throw JDBCUtil.sqlException(ex);
        }

        m_streams.add(reader);

        return reader;
    }
