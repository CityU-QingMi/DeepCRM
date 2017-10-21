    boolean getAutoCommit() throws DataAccessPointException {

        boolean result = false;

        try {
            result = conn.getAutoCommit();
        } catch (SQLException e) {
            throw new DataAccessPointException(e.toString());
        }

        return result;
    }
