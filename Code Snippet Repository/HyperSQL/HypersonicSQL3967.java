    Vector getCatalog() throws DataAccessPointException {

        Vector    ret    = new Vector();
        ResultSet result = null;

        if (databaseToConvert != null && databaseToConvert.length() > 0) {
            ret.addElement(databaseToConvert);

            return (ret);
        }

        try {
            result = meta.getCatalogs();
        } catch (SQLException e) {
            result = null;
        }

        try {
            if (result != null) {
                while (result.next()) {
                    ret.addElement(result.getString(1));
                }

                result.close();
            }
        } catch (SQLException e) {
            throw new DataAccessPointException(e.toString());
        }

        return (ret);
    }
