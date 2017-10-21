    public JDBCBlobFile(boolean deleteOnFree) throws SQLException {

        m_deleteOnFree = deleteOnFree;

        try {
            m_file = File.createTempFile(TEMP_FILE_PREFIX,
                                         TEMP_FILE_SUFFIX).getCanonicalFile();
        } catch (IOException ex) {
            throw JDBCUtil.sqlException(ex);
        }
    }
