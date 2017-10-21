    private void createFile() throws SQLException {

        try {
            if (!m_file.exists()) {
                FileUtil.getFileUtil().makeParentDirectories(m_file);
                m_file.createNewFile();
            }
        } catch (IOException ex) {
            throw JDBCUtil.sqlException(ex);
        }

        checkIsFile( /*checkExists*/true);
    }
