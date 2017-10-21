    public JDBCClobFile(File file, String encoding) throws SQLException {

        if (file == null) {
            throw JDBCUtil.nullArgument("file");
        }

        try {
            setEncoding(encoding);

            m_file = file.getCanonicalFile();

            checkIsFile(/*checkExists*/false);

            m_deleteOnFree = false;
        } catch (IOException ex) {
            throw JDBCUtil.sqlException(ex);
        } catch (SQLException ex) {
            throw JDBCUtil.sqlException(ex);
        }
    }
