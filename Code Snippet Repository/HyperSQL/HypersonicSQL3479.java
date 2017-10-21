    protected boolean test(Statement aStatement) {

        try {
            try {

                //execute the SQL
                aStatement.execute(getSql());
            } catch (SQLException s) {
                throw new Exception("Expected a ResultSet, but got the error: "
                                    + s.getMessage());
            }

            //check that update count != -1
            if (aStatement.getUpdateCount() != -1) {
                throw new Exception(
                    "Expected a ResultSet, but got an update count of "
                    + aStatement.getUpdateCount());
            }

            //iterate over the ResultSet
            ResultSet    results  = aStatement.getResultSet();
            StringBuffer printVal = new StringBuffer();

            while (results.next()) {
                for (int j = 0; j < results.getMetaData().getColumnCount();
                        j++) {
                    if (j != 0) {
                        printVal.append(',');
                    }

                    printVal.append(results.getString(j + 1));
                }

                printVal.append(LS);
            }

            throw new Exception(printVal.toString());
        } catch (Exception x) {
            message = x.toString();

            return false;
        }
    }
