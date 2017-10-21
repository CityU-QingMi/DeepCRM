    TransferResultSet getData(String statement)
    throws DataAccessPointException {

        ResultSet rsData = null;

        try {
            if (srcStatement != null) {
                srcStatement.close();
            }

            srcStatement = conn.createStatement();
            rsData       = srcStatement.executeQuery(statement);
        } catch (SQLException e) {
            try {
                srcStatement.close();
            } catch (Exception e1) {}

            srcStatement = null;
            rsData       = null;

            throw new DataAccessPointException(e.toString());
        }

        return new TransferResultSet(rsData);
    }
