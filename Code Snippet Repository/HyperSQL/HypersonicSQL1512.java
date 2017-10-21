    public Writer setCharacterStream(final long pos) throws SQLException {

        if (pos < 1) {
            throw JDBCUtil.invalidArgument("pos: " + pos);
        }

        checkClosed();
        createFile();

        long thisLength = this.length();

        if (pos > thisLength + 1) {
            this.fillSpace(thisLength + 1, pos);
        }

        Writer writer;
        WriterAdapter adapter;

        try {
            adapter = new WriterAdapter(m_file, pos - 1) {

                public void close() throws IOException {

                    try {
                        super.close();
                    } finally {
                        m_streams.remove(this);
                    }
                }
            };

            writer = new BufferedWriter(adapter);
        } catch (IOException ex) {
            throw JDBCUtil.sqlException(ex);
        } catch (RuntimeException ex) {
            throw JDBCUtil.sqlException(ex);
        }

        m_streams.add(adapter);

        return writer;
    }
