    Vector getSchemas() throws DataAccessPointException {

        Vector    ret    = new Vector();
        ResultSet result = null;

        try {
            result = meta.getSchemas();
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
