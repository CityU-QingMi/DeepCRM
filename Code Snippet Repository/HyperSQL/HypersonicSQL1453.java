    public JDBCBlobFile(final File file,
                        boolean deleteOnFree) throws SQLException {

        m_deleteOnFree = deleteOnFree;

        try {
            m_file = file.getCanonicalFile();
        } catch (IOException ex) {
            throw JDBCUtil.sqlException(ex);
        }

        checkIsFile(/*checkExists*/false);
    }
