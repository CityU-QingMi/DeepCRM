    protected final void checkIsFile(final boolean checkExists) throws
            SQLException {

        boolean exists = false;
        boolean isFile = false;

        try {
            exists = m_file.exists();
        } catch (Exception ex) {
            throw JDBCUtil.sqlException(ex);
        }

        if (exists) {
            try {
                isFile = m_file.isFile();
            } catch (Exception ex) {
                throw JDBCUtil.sqlException(ex);
            }
        }

        if (exists) {
            if (!isFile) {
                throw JDBCUtil.invalidArgument("Is not a file: " + m_file);
            }
        } else if (checkExists) {
            throw JDBCUtil.invalidArgument("Does not exist: " + m_file);
        }
    }
