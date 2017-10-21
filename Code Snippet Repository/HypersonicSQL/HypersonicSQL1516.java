    public JDBCClobFile(String encoding) throws SQLException {

        try {
            setEncoding(encoding);

            m_file = File.createTempFile(TEMP_FILE_PREFIX, TEMP_FILE_SUFFIX);
            m_deleteOnFree = true;
        } catch (IOException ex) {
            throw JDBCUtil.sqlException(ex);
        } catch (SecurityException se) {
            throw JDBCUtil.sqlException(se);
        }
    }
