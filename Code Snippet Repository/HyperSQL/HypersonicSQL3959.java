    void close() throws DataAccessPointException {

        if (srcStatement != null) {
            try {
                srcStatement.close();
            } catch (SQLException e) {}

            srcStatement = null;
        }

        if (conn != null) {
            try {
                conn.close();
            } catch (SQLException e) {}

            conn = null;
        }
    }
