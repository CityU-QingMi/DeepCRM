    public OutputStream setAsciiStream(long pos) throws SQLException {

        if (pos < 1) {
            throw JDBCUtil.invalidArgument("pos: " + pos);
        }

        checkClosed();
        createFile();

        long thisLength = this.length();

        if (pos > thisLength + 1) {
            this.fillSpace(thisLength + 1, pos);
        }

        OutputStream stream;

        try {
            stream = new JDBCBlobFile.OutputStreamAdapter(m_file, pos - 1) {

                public void close() throws IOException {

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
        } catch (IllegalArgumentException ex) {
            throw JDBCUtil.sqlException(ex);
        } catch (NullPointerException ex) {
            throw JDBCUtil.sqlException(ex);
        } catch (SecurityException ex) {
            throw JDBCUtil.sqlException(ex);
        }

        m_streams.add(stream);

        return stream;
    }
