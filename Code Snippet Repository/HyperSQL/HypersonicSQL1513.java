    public void truncate(long len) throws SQLException {

        if (len < 0) {
            throw JDBCUtil.invalidArgument("len: " + len);
        }

        checkClosed();

        ReaderAdapter adapter = null;
        RandomAccessFile randomAccessFile = null;
        long filePointer;

        try {
            adapter = new ReaderAdapter(m_file, len, Long.MAX_VALUE);
            filePointer = adapter.getFilePointer();

            adapter.close();

            randomAccessFile = new RandomAccessFile(m_file, "rw");

            randomAccessFile.setLength(filePointer);
        } catch (IOException ex) {
            throw JDBCUtil.sqlException(ex);
        } catch (RuntimeException ex) {
            throw JDBCUtil.sqlException(ex);
        } finally {
            closeSafely(adapter);
            closeSafely(randomAccessFile);
        }
    }
