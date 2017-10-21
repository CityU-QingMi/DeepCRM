    void rollback() throws DataAccessPointException {

        if (srcStatement != null) {
            try {
                srcStatement.close();
            } catch (SQLException e) {}

            srcStatement = null;
        }

        try {
            conn.rollback();
        } catch (SQLException e) {
            throw new DataAccessPointException(e.toString());
        }
    }
