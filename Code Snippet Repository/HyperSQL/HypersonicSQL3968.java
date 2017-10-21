    void setCatalog(String sCatalog) throws DataAccessPointException {

        if (sCatalog != null && sCatalog.length() > 0) {
            try {
                conn.setCatalog(sCatalog);
            } catch (SQLException e) {
                throw new DataAccessPointException(e.toString());
            }
        }
    }
