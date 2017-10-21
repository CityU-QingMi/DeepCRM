    public void truncate(long len) throws SQLException {

        if (len < 0) {
            throw JDBCUtil.invalidArgument("len: " + len);
        }

        checkClosed();

        RandomAccessFile randomAccessFile = null;

        try {
            randomAccessFile = new RandomAccessFile(m_file, "rw");

            randomAccessFile.setLength(len);
        } catch (IOException ex) {
            throw JDBCUtil.sqlException(ex);
        } catch (IllegalArgumentException ex) {
            throw JDBCUtil.sqlException(ex);
        } catch (SecurityException ex) {
            throw JDBCUtil.sqlException(ex);
        } finally {
            closeSafely(randomAccessFile);
        }
    }
