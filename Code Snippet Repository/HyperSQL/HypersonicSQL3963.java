    boolean execute(String statement) throws DataAccessPointException {

        boolean   result = false;
        Statement stmt   = null;

        try {
            stmt   = conn.createStatement();
            result = stmt.execute(statement);
        } catch (SQLException e) {
            throw new DataAccessPointException(e.toString());
        } finally {
            if (stmt != null) {
                try {
                    stmt.close();
                } catch (SQLException e) {}
            }
        }

        return result;
    }
